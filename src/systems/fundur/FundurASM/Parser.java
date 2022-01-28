package systems.fundur.FundurASM;

import systems.fundur.FundurASM.error.IncorrectInstructionError;
import systems.fundur.FundurASM.execs.Exec;
import systems.fundur.FundurASM.lib.Library;
import systems.fundur.FundurASM.lib.base.*;
import systems.fundur.FundurASM.lib.math.Math;
import systems.fundur.FundurASM.util.Bool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static systems.fundur.FundurASM.util.Logger.log;

public class Parser {

    private static Map<String, Instruction> funcs;
    private static Map<String, Library> libs;

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
        final int[] stackSize = {0};
        final int[] currentLine = {0};
        final int[] offSet = {0};
        Bool failed = new Bool(false);

        asmFile.lines().forEach((line) -> {
            currentLine[0]++;
            if (line.equals("\n") || line.isEmpty() || line.isBlank() || line.startsWith(";")) {
                offSet[0]++;
                return;
            }

            line = line.toLowerCase(Locale.ROOT);
            String op = line.split(" +")[0];
            String arg = line.split(" +")[1];

            if ( line.startsWith("#")) {
                op = op.substring(1);
                switch (op) {
                    case "alloc":
                        stackSize[0] = Integer.parseInt(arg);
                        break;
                    case "include":
                        loadedLibs.put(arg, libs.get(arg));
                        break;
                    default:
                        //handle it like a comment
                        break;
                }
                offSet[0]++;
                return;
            }

            if (funcs.containsKey(op)) {
                instructions.add(funcs.get(op).getExec(Integer.parseInt(arg), failed, stackSize[0], currentLine[0], offSet[0]));
                return;
            }

            try {
                String[] libInstructions = op.split("\\.");
                if (loadedLibs.containsKey(libInstructions[0])) {
                    instructions.add(loadedLibs.get(libInstructions[0]).getInstruction(libInstructions[1]));
                }
            } catch (IndexOutOfBoundsException ignored){};


            log("#" + currentLine[0], op, arg);
            new IncorrectInstructionError(line, currentLine[0], failed).error();
        });

        if (!failed.getVal()) log("Successfully parsed %d lines. Stacksize @%d entries\n\n".formatted(currentLine[0], stackSize[0]));
        instructions.add(0, stackSize[0]);
        return failed.getVal() ? null : instructions.toArray();
    }

    public static void main(String[] args) {
        Object[] parsed = parseFromFile("/home/fridolin/dev/FundurASM/src/systems/fundur/FundurASM/test.fasm");
        if (parsed == null) return;
        Exec[] execs = new Exec[parsed.length -1];
        int k = 0;
        for (int i = 1; i < parsed.length; i++) {
            execs[k++] = (Exec) parsed[i];
        }
        Runner runner = new Runner((int) parsed[0], execs);
        runner.run();
        System.out.println(runner.getReturnCode());
    }
}
