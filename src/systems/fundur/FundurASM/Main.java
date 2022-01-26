package systems.fundur.FundurASM;

import systems.fundur.FundurASM.execs.Exec;

import java.util.Arrays;

import static systems.fundur.FundurASM.Parser.parse;

public class Main {
    public static void main(String[] args) {
        //specify where the file is
        String filePath = "/home/fridolin/dev/FundurASM/src/systems/fundur/FundurASM/test.fasm";

        //get the instructions parsed back
        Object[] parsed = parse(filePath);

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
        runner.run();

        // get the return value via getReturnCode() and print it
        System.out.println(runner.getReturnCode());
    }
}
