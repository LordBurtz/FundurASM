package systems.fundur.FundurASM;

import systems.fundur.FundurASM.execs.Exec;

public class Runner extends Thread{
    private boolean running;
    private boolean erred;

    private int returnCode;
    private int acc;
    private final int[] register;
    private int programCounter;
    private final Exec[] execs;

    public Runner(int stackSize, Exec[] instructions) {
        running = true;
        erred = false;
        acc = 0;
        register = new int[stackSize];
        execs = instructions;
        programCounter = 0;
    }

    @Override
    public void run() {
        programCounter--;
        while (running && !erred) {
            programCounter++;
            execs[programCounter].exec(this);
        }
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public int[] getRegister() {
        return register;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public int get(int cell) {
        return register[cell];
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isErred() {
        return erred;
    }

    public void setErred(boolean erred) {
        this.erred = erred;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }
}
