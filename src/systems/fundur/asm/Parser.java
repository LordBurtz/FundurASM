package systems.fundur.asm;

import systems.fundur.asm.error.IncorrectInstructionError;
import systems.fundur.asm.error.LibraryNotFoundError;
import systems.fundur.asm.execs.Exec;
import systems.fundur.asm.lib.Library;
import systems.fundur.asm.lib.base.*;
import systems.fundur.asm.lib.math.Math;
import systems.fundur.asm.util.BinFormat;
import systems.fundur.asm.util.Bool;
import systems.fundur.asm.util.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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

    public static Object[] parseFromFile(String filePath) {
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
        return parseFromString(asmFile);
    }

    public static Object[] parseFromString(String asmFile) {
        log ("Starting to parse");
        List<Object> instructions = new ArrayList<>();
        Map<String, Library> loadedLibs = new HashMap<>();
        AtomicInteger currentLine = new AtomicInteger(0);
        AtomicInteger stackSize = new AtomicInteger(0);
        AtomicInteger offSet = new AtomicInteger(0);
        Bool failed = new Bool(false);

        asmFile.lines().forEach((line) -> {
            currentLine.incrementAndGet();

            if (line.equals("\n") || line.isEmpty() || line.isBlank() || line.startsWith(";")) {
                offSet.incrementAndGet();
                return;
            }

            line = line.toLowerCase(Locale.ROOT);
            String op = line.split(" +")[0];
            String arg = line.split(" +")[1];

            if ( line.startsWith("#")) {
                op = op.substring(1);
                switch (op) {
                    case "alloc":
                        stackSize.set(Integer.parseInt(arg));
                        break;
                    case "include":
                        if (libs.containsKey(arg)) {
                            loadedLibs.put(arg, libs.get(arg));
                        } else {
                            new LibraryNotFoundError(arg, currentLine.get(), failed).error();
                        }
                        break;
                    default:
                        //handle it like a comment
                        break;
                }
                offSet.incrementAndGet();
                return;
            }

            if (funcs.containsKey(op)) {
                instructions.add(funcs.get(op).getExec(Integer.parseInt(arg), failed, stackSize.get(), currentLine.get(), offSet.get()));
                return;
            }

            try {
                String[] libInstructions = op.split("\\.");
                if (loadedLibs.containsKey(libInstructions[0])) {
                    instructions.add(loadedLibs.get(libInstructions[0]).getInstruction(libInstructions[1]).getExec(
                            Integer.parseInt(arg), failed, stackSize.get(), currentLine.get(), offSet.get()));
                    return;
                }
            } catch (IndexOutOfBoundsException ignored){}


            log("#" + currentLine.get(), op, arg);
            new IncorrectInstructionError(line, currentLine.get(), failed).error();
        });

        if (!failed.getVal()) log("Successfully parsed %d lines. Stacksize @%d entries\n\n".formatted(currentLine.get(), stackSize.get()));
        instructions.add(0, stackSize.get());
        return failed.getVal() ? null : instructions.toArray();
    }

    public static int parseArgument(String arg, Bool failed) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException ignored) {}
        switch (arg.charAt(0)) {
            case 'x':
                return HexFormat.fromHexDigits(arg.substring(1));
            case 'b':
                return (int) BinFormat.parseBinary(arg.substring(1));
            default:
                throw new IllegalStateException("Unexpected value: " + arg.charAt(0));
        }
    }

    public static void main(String[] args) {
        Logger.setDebug(false);
        Object[] parsed = parseFromFile("/home/fridolin/dev/FundurASM/src/systems/fundur/FundurASM/test.fasm");
        if (parsed == null) return;
        Exec[] execs = new Exec[parsed.length -1];
        int k = 0;
        for (int i = 1; i < parsed.length; i++) {
            execs[k++] = (Exec) parsed[i];
        }
        Runner runner = new Runner((int) parsed[0], execs);
        runner.start();
        System.out.println(runner.getReturnCode());
    }
}
