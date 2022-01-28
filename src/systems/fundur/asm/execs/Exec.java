package systems.fundur.asm.execs;

import systems.fundur.asm.Runner;

public abstract class Exec {
    protected int parameter;

    public abstract void exec(Runner runner);

    public int getParameter() {
        return parameter;
    }

    public void setParameter(int parameter) {
        this.parameter = parameter;
    }
}
