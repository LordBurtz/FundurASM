package systems.fundur.FundurASM;

public class Runner {
    private boolean stopped;
    private boolean erred;

    private int acc;
    private int[] register;
    private int programCounter;

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

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public boolean isErred() {
        return erred;
    }

    public void setErred(boolean erred) {
        this.erred = erred;
    }
}
