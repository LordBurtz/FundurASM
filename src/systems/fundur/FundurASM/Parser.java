package systems.fundur.FundurASM;

import systems.fundur.FundurASM.error.InstructionNotFoundError;
import systems.fundur.FundurASM.error.RegistryOutOfBoundsError;
import systems.fundur.FundurASM.execs.*;
import systems.fundur.FundurASM.util.Bool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Parser {
    public static Object[] parse(String filePath) {
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

        //System.out.println(contents);
        /*
          Till here all things are loaded now begins the real horror of parsing
          for the sake of simplicity I won't tokenize anything
          I'll parse the file as it is only catching the crudest errors
         */

        String asmFile = contents.toString();
        List<Object> instructions = new ArrayList<>();
        final int[] stackSize = {0};
        final int[] currentLine = {0};
        Bool failed = new Bool(false);

        asmFile.lines().forEach((line) -> {
            currentLine[0]++;
            if (line.equals("") || line.equals("\n")) return;

            line = line.toLowerCase(Locale.ROOT);
            String op = line.split(" +")[0];
            String arg = line.split(" +")[1];
            if (op.startsWith(";") || op.startsWith("#")) return;
            if (op.substring(0, 3).equals("all")) stackSize[0] = Integer.parseInt(arg);
            switch (op.substring(0, 3)) {
                case "all" ->  stackSize[0] = Integer.parseInt(arg); //allocate
                case "dlo" -> instructions.add(new DLoad(Integer.parseInt(arg)));       //dload
                case "jum" -> instructions.add(new Jump(Integer.parseInt(arg)));        //jump to
                case "jge" -> instructions.add(new JGE(Integer.parseInt(arg)));
                case "jgt" -> instructions.add(new JGT(Integer.parseInt(arg)));
                case "jle" -> instructions.add(new JLE(Integer.parseInt(arg)));
                case "jlt" -> instructions.add(new JLT(Integer.parseInt(arg)));
                case "jeq" -> instructions.add(new JEQ(Integer.parseInt(arg)));
                case "jne" -> instructions.add(new JNE(Integer.parseInt(arg)));
                case "loa" -> safeAdd(new Load(Integer.parseInt(arg)), failed, stackSize[0], instructions, currentLine[0]); //load
                case "sto" -> safeAdd(new Store(Integer.parseInt(arg)), failed, stackSize[0], instructions, currentLine[0]);//store
                case "add" -> safeAdd(new Add(Integer.parseInt(arg)), failed, stackSize[0], instructions, currentLine[0]);
                case "sub" -> safeAdd(new Sub(Integer.parseInt(arg)), failed, stackSize[0], instructions, currentLine[0]);
                case "mul" -> safeAdd(new Mult(Integer.parseInt(arg)), failed, stackSize[0], instructions, currentLine[0]);
                case "div" -> safeAdd(new Div(Integer.parseInt(arg)), failed, stackSize[0], instructions, currentLine[0]);
                case "end" -> safeAdd(new End(Integer.parseInt(arg)), failed, stackSize[0], instructions, currentLine[0]);
                default -> new InstructionNotFoundError(line, currentLine[0], failed).error();
            }

        });

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
