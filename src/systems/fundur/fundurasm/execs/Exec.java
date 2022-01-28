package systems.fundur.fundurasm.execs;

import systems.fundur.fundurasm.Runner;

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
