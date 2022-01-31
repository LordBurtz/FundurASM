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
        Object[] parsed = parseFromFile(filePath);

        //if erred -> null returned, interrupt at this point
        if (parsed == null) return;

        //strip the first entry (the stackSize)
        Exec[] execs = new Exec[parsed.length -1];
        int k = 0;
        for (int i = 1; i < parsed.length; i++) {
            execs[k++] = (Exec) parsed[i];
        }

        //create our Runner with the instructions and the stack size
        Runner runner = new Runner((int) parsed[0], execs);

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
