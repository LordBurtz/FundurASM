package systems.fundur.FundurASM;

import systems.fundur.FundurASM.error.InstructionNotFoundError;
import systems.fundur.FundurASM.error.RegistryOutOfBoundsError;
import systems.fundur.FundurASM.execs.Exec;
import systems.fundur.FundurASM.instr.DLoad;
import systems.fundur.FundurASM.instr.End;
import systems.fundur.FundurASM.instr.Instruction;
import systems.fundur.FundurASM.instr.JEQ;
import systems.fundur.FundurASM.instr.JGE;
import systems.fundur.FundurASM.instr.JGT;
import systems.fundur.FundurASM.instr.JLE;
import systems.fundur.FundurASM.instr.JLT;
import systems.fundur.FundurASM.instr.JNE;
import systems.fundur.FundurASM.instr.Jump;
import systems.fundur.FundurASM.instr.Load;
import systems.fundur.FundurASM.util.Bool;
import systems.fundur.FundurASM.instr.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static systems.fundur.FundurASM.util.Logger.log;

public class Parser {

    private static Map<String, Instruction> lib;

    static {
        lib = new HashMap<>();

        registerInstruction("add", new systems.fundur.FundurASM.instr.Add());
        registerInstruction("sub", new systems.fundur.FundurASM.instr.Sub());
        registerInstruction("mult", new systems.fundur.FundurASM.instr.Mult());
        registerInstruction("div", new systems.fundur.FundurASM.instr.Div());
        registerInstruction("end", new systems.fundur.FundurASM.instr.End());
        registerInstruction("load", new systems.fundur.FundurASM.instr.Load());
        registerInstruction("store", new systems.fundur.FundurASM.instr.Store());
        registerInstruction("dload", new DLoad());
        registerInstruction("jump", new Jump());
        registerInstruction("jge", new JGE());
        registerInstruction("jgt", new JGT());
        registerInstruction("jle", new JLE());
        registerInstruction("jlt", new JLT());
        registerInstruction("jeq", new JEQ());
        registerInstruction("jne", new JNE());
    }

    public static void registerInstruction(String key, Instruction instruction) {
        lib.put(key, instruction);
    }

    public static Object[] parse(String filePath) {
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

        /*
          Till here all things are loaded now begins the real horror of parsing
          for the sake of simplicity I won't tokenize anything
          I'll parse the file as it is only catching the crudest errors
         */

        log ("Starting to parse");
        String asmFile = contents.toString();
        List<Object> instructions = new ArrayList<>();
        final int[] stackSize = {0};
        final int[] currentLine = {0};
        final int[] offSet = {0};
        Bool failed = new Bool(false);

        asmFile.lines().forEach((line) -> {
            currentLine[0]++;
            if (line.equals("") || line.equals("\n")) {
                offSet[0]++;
                return;
            };

            line = line.toLowerCase(Locale.ROOT);
            String op = line.split(" +")[0];
            String arg = line.split(" +")[1];
            if (line.startsWith(";") || line.startsWith("#")) {
                offSet[0]++;
                return;
            }
            if (lib.containsKey(op)) {
                instructions.add(lib.get(op).getExec(Integer.parseInt(arg), failed, stackSize[0], currentLine[0], offSet[0]));
                return;
            }
            log("#" + currentLine[0], op, arg);
            switch (op) {
                case "alloc" ->  stackSize[0] = Integer.parseInt(arg); //allocate
                //case "dload" -> instructions.add(new DLoad(Integer.parseInt(arg)));       //dload
                //case "jump" -> instructions.add(new Jump(Integer.parseInt(arg) - offSet[0]));        //jump to
                //case "jge" -> instructions.add(new JGE(Integer.parseInt(arg) - offSet[0]));
                //ase "jgt" -> instructions.add(new JGT(Integer.parseInt(arg) - offSet[0]));
                //case "jle" -> instructions.add(new JLE(Integer.parseInt(arg) - offSet[0]));
                //case "jlt" -> instructions.add(new JLT(Integer.parseInt(arg) - offSet[0]));
                //case "jeq" -> instructions.add(new JEQ(Integer.parseInt(arg) - offSet[0]));
                //case "jne" -> instructions.add(new JNE(Integer.parseInt(arg) - offSet[0]));
                //case "load" -> safeAdd(new Load(Integer.parseInt(arg)), failed, stackSize[0], instructions, currentLine[0]); //load
                //case "store" -> safeAdd(new Store(Integer.parseInt(arg)), failed, stackSize[0], instructions, currentLine[0]);//store
                //case "add" -> safeAdd(new Add(Integer.parseInt(arg)), failed, stackSize[0], instructions, currentLine[0]);
                //case "sub" -> safeAdd(new Sub(Integer.parseInt(arg)), failed, stackSize[0], instructions, currentLine[0]);
                //case "mult" -> safeAdd(new Mult(Integer.parseInt(arg)), failed, stackSize[0], instructions, currentLine[0]);
                //case "div" -> safeAdd(new Div(Integer.parseInt(arg)), failed, stackSize[0], instructions, currentLine[0]);
                //case "end" -> safeAdd(new End(Integer.parseInt(arg)), failed, stackSize[0], instructions, currentLine[0]);
                default -> new InstructionNotFoundError(line, currentLine[0], failed).error();
            }

        });

        if (!failed.getVal()) log("Successfully parsed %d lines. Stacksize @%d entries\n\n".formatted(currentLine[0], stackSize[0]));
        instructions.add(0, stackSize[0]);
        return failed.getVal() ? null : instructions.toArray();
    }

    private static final void safeAdd(Exec func, Bool failed, int stackSize, List<Object> instructions, int lineN) {
        if (func.getParameter() < stackSize && func.getParameter() >= 0) {
            instructions.add(func);
        } else {
            new RegistryOutOfBoundsError(func, stackSize, lineN, failed).error();
        }
    }

    public static void main(String[] args) {
        Object[] parsed = parse("/home/fridolin/dev/FundurASM/src/systems/fundur/FundurASM/test.fasm");
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
