package systems.fundur.asm;

import systems.fundur.asm.execs.Exec;
import systems.fundur.asm.util.Logger;

import static systems.fundur.asm.Parser.parseFromFile;

public class Main {
    public static void main(String[] args) {
        Logger.setDebug(true);

        System.out.println("Starting FundurASM | Version Beta 1.4 | All rights reserved");

        //specify where the file is
        if (args.length <= 0) {
            System.out.println("Please specify a file");
            return;
        }

        String filePath = args[0];

        //get the instructions parsed back
        Program parsed = parseFromFile(filePath);

        //if erred -> null returned, interrupt at this point
        if (parsed == null) return;

        //create our Runner with the instructions and the stack size
        Runner runner = new Runner(parsed.getStackSize(), parsed.getExecs().toArray(new Exec[0]));

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
