package systems.fundur.FundurASM;

import systems.fundur.FundurASM.execs.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Parser {
    public static Exec[] parse(String filePath) {
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
        /**
         * Till here all things are loaded now begins the real horror of parsing
         * for the sake of simplicity I won't tokenize anything
         * I'll parse the file as it is only catching the crudest errors
         */

        String asmFile = contents.toString();
        List<Exec> instructions = new ArrayList<>();
        int stackSize = 0;

        asmFile.lines().forEach((line) -> {
            line = line.toLowerCase(Locale.ROOT);
            line = line.replaceAll(" ", "");
            switch (line) {
                case "load" -> instructions.add(new Load(Integer.parseInt(line.substring(4))));
                case "dload" -> instructions.add(new DLoad(Integer.parseInt(line.substring(5))));
                case "store" -> instructions.add(new Store(Integer.parseInt(line.substring(4))));
                case "add" -> instructions.add(new Add(Integer.parseInt(line.substring(3))));
                case "sub" -> instructions.add(new Sub(Integer.parseInt(line.substring(3))));
                case "mult" -> instructions.add(new Mult(Integer.parseInt(line.substring(4))));

            }

        });

        return null;
    }

    public static void main(String[] args) {
        parse("/home/fridolin/dev/FundurASM/src/systems/fundur/FundurASM/Parser.java");

        String test = "Dload    187000";
        test = test.toLowerCase(Locale.ROOT);
        //test = test.replaceAll(" ", "");
        System.out.println(test.trim().split(" +")[1]);
    }
}
