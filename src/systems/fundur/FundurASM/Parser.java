package systems.fundur.FundurASM;

import systems.fundur.FundurASM.execs.Exec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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


        return null;
    }

    public static void main(String[] args) {
        parse("/home/fridolin/dev/FundurASM/src/systems/fundur/FundurASM/Parser.java");
    }
}
