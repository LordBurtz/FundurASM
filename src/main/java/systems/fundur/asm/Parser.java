package systems.fundur.asm;

import systems.fundur.asm.error.IncorrectArgumentError;
import systems.fundur.asm.error.IncorrectInstructionError;
import systems.fundur.asm.error.LibraryNotFoundError;
import systems.fundur.asm.lib.Library;
import systems.fundur.asm.lib.base.*;
import systems.fundur.asm.lib.math.Math;
import systems.fundur.asm.util.BinFormat;
import systems.fundur.asm.util.Bool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static systems.fundur.asm.util.Logger.log;

public class Parser {
    private static final Map<String, Instruction> funcs;
    private static final Map<String, Library> libs;

    static {
        funcs = new HashMap<>();
        libs = new HashMap<>();

        registerInstruction("add", new Add());
        registerInstruction("sub", new Sub());
        registerInstruction("div", new Div());
        registerInstruction("end", new End());
        registerInstruction("jge", new JGE());
        registerInstruction("jgt", new JGT());
        registerInstruction("jle", new JLE());
        registerInstruction("jlt", new JLT());
        registerInstruction("jeq", new JEQ());
        registerInstruction("jne", new JNE());
        registerInstruction("mult", new Mult());
        registerInstruction("jump", new Jump());
        registerInstruction("load", new Load());
        registerInstruction("dload", new DLoad());
        registerInstruction("store", new Store());

        registerLibrary("math", new Math());
    }

    public static void registerInstruction(String key, Instruction instruction) {
        funcs.put(key, instruction);
    }

    public static void registerLibrary(String key, Library lib) {
        libs.put(key, lib);
    }

    public static Program parseFromFile(String filePath) {
        log("Loading file... ");
        File file = new File(filePath);
        FileInputStream stream;
        try {
            stream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.printf("\nFor the given filepath: %s no suitable file is found%n", filePath);
            return null;
        }


        StringBuilder contents = new StringBuilder();
        int readChar;

        try {
            while ((readChar = stream.read()) != -1) {
                contents.append((char) readChar);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        log ("File successfully loaded");
        String asmFile = contents.toString();
        return parseFromLines(asmFile.split("\n"));
    }

    public static Program parseFromLines(String[] lines) {
        log ("Starting to parse");
        Map<String, Library> loadedLibs = new HashMap<>();

        Program program = new Program();
        ParserState pState = new ParserState();

        for(String line : lines) {
            pState.incrCurrentLine();


            if (line.equals("\n") || line.isEmpty() || line.isBlank() || line.startsWith(";")) {
                pState.incrOffSet();
                continue;
            }

            line = line.toLowerCase(Locale.ROOT);
            String op, arg;
            try {
                op = line.split(" +")[0];
                arg = line.split(" +")[1];
            } catch (IndexOutOfBoundsException __) {
                pState.setFailed(true);
                new IncorrectInstructionError(line, pState.currentLine, pState.failed).error();
                continue;
            }

            //interpreter flags
            if ( line.startsWith("#")) {
                op = op.substring(1);
                switch (op) {
                    case "alloc":
                        program.setStackSize(parseArgument(arg, pState.failed));
                        break;
                    case "import":
                        if (libs.containsKey(arg)) {
                            loadedLibs.put(arg, libs.get(arg));
                        } else {
                            new LibraryNotFoundError(arg, pState.currentLine, pState.failed).error();
                        }
                        break;
                    default:
                        //handle it like a comment
                        break;
                }
                pState.incrOffSet();
                continue;
            }

            //parsing the instruction if in base
            if (funcs.containsKey(op)) {
                program.append(funcs.get(op).getExec(parseArgument(arg, pState.failed), pState.failed, program.getStackSize(), pState.currentLine, pState.offSet));
                continue;
            }

            //parsing the instruction if in loaded lib
            try {
                String[] libInstructions = op.split("\\.");
                if (loadedLibs.containsKey(libInstructions[0])) {
                    program.append(loadedLibs.get(libInstructions[0]).getInstruction(libInstructions[1]).getExec(
                            parseArgument(arg, pState.failed), pState.failed, program.getStackSize(), pState.currentLine, pState.offSet));
                    continue;
                }
                //continuing even if no instruction found as we will catch that at the end of the line
            } catch (IndexOutOfBoundsException ignored){/*Empty catch bc we catch that 2 lines later*/}

            //seems like previously nothing was found -> throw an error
            log("#" + pState.currentLine, op, arg);
            new IncorrectInstructionError(line, pState.currentLine, pState.failed).error();
        }

        if (!pState.failed.getVal()) log("Successfully parsed %d lines. Stacksize @%d entries\n\n".formatted(pState.currentLine, program.getStackSize()));
        return pState.failed.getVal() ? null : program;
    }

    public static int parseArgument(String arg, Bool failed) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException ignored) {}
        switch (arg.charAt(0)) {
            case 'x':
                try {
                    return HexFormat.fromHexDigits(arg.substring(1));
                } catch (NumberFormatException ignored) {}
            case 'b':
                Object res = BinFormat.parseBinary(arg.substring(1));
                if (res != null) {
                    return (int) res;
                }
        }
        new IncorrectArgumentError(arg, failed).error();
        return -1;
    }

    private static class ParserState {
        Bool failed = new Bool(false);
        int currentLine = 0;
        int offSet = 0;

        public Bool getFailed() {
            return failed;
        }

        public void setFailed(Bool failed) {
            this.failed = failed;
        }

        public int getOffSet() {
            return offSet;
        }

        public void setOffSet(int offSet) {
            this.offSet = offSet;
        }

        public void incrOffSet() {
            this.offSet++;
        }

        public void incrCurrentLine() {
            this.currentLine++;
        }

        public void setFailed(boolean val) {
            failed.setVal(val);
        }
    }

    public static void main(String[] args) {
        Program parsed = parseFromFile("test.fasm");

        //if erred -> null returned, interrupt at this point
        if (parsed == null) return;

        //create our Runner with the instructions and the stack size
        Runner runner = new Runner(parsed);

        //start it
        runner.start();
        try {
            runner.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // get the return value via getReturnCode() and print it
        System.out.println("Return code: " + runner.getReturnCode());
    }
}
