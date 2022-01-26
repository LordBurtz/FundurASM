package systems.fundur.FundurASM;

import systems.fundur.FundurASM.error.InstructionNotFoundError;
import systems.fundur.FundurASM.execs.*;

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

        System.out.println(contents);
        /*
          Till here all things are loaded now begins the real horror of parsing
          for the sake of simplicity I won't tokenize anything
          I'll parse the file as it is only catching the crudest errors
         */

        String asmFile = contents.toString();
        List<Object> instructions = new ArrayList<>();
        final int[] stackSize = {0};
        final int[] currentLine = {0};
        Boolean failed = false;

        asmFile.lines().forEach((line) -> {
            currentLine[0]++;
            if (line.equals("") || line.equals("\n")) return;

            line = line.toLowerCase(Locale.ROOT);
            String op = line.split(" +")[0];
            String arg = line.split(" +")[1];
            if (op.startsWith(";") || op.startsWith("#")) return;
            if (op.substring(0, 3).equals("all")) stackSize[0] = Integer.parseInt(arg);
            switch (op.substring(0, 3)) {
                case "loa" -> instructions.add(new Load(Integer.parseInt(arg)));
                case "dlo" -> instructions.add(new DLoad(Integer.parseInt(arg)));
                case "sto" -> instructions.add(new Store(Integer.parseInt(arg)));
                case "add" -> instructions.add(new Add(Integer.parseInt(arg)));
                case "sub" -> instructions.add(new Sub(Integer.parseInt(arg)));
                case "mul" -> instructions.add(new Mult(Integer.parseInt(arg)));
                case "div" -> instructions.add(new Div(Integer.parseInt(arg)));
                case "jum" -> instructions.add(new Jump(Integer.parseInt(arg)));
                case "jge" -> instructions.add(new JGE(Integer.parseInt(arg)));
                case "jgt" -> instructions.add(new JGT(Integer.parseInt(arg)));
                case "jle" -> instructions.add(new JLE(Integer.parseInt(arg)));
                case "jlt" -> instructions.add(new JLT(Integer.parseInt(arg)));
                case "jeq" -> instructions.add(new JEQ(Integer.parseInt(arg)));
                case "jne" -> instructions.add(new JNE(Integer.parseInt(arg)));
                case "end" -> instructions.add(new End(Integer.parseInt(arg)));
                case "all" ->  stackSize[0] = Integer.parseInt(arg);
                default -> new InstructionNotFoundError(line, currentLine[0], failed).error();
            }

        });

        instructions.add(0, stackSize[0]);
        return instructions.toArray();
    }

    public static void main(String[] args) {
        Object[] parsed = parse("/home/fridolin/dev/FundurASM/src/systems/fundur/FundurASM/test.fasm");
        Exec[] execs = new Exec[parsed.length -1];
        int k = 0;
        for (int i = 1; i < parsed.length; i++) {
            execs[k++] = (Exec) parsed[i];
        }
        System.out.println("---");
        for (Exec exec : execs) {
            System.out.println(exec);
        }
        Runner runner = new Runner((int) parsed[0], execs);
        runner.run();
        System.out.println(runner.getReturnCode());
    }
}
