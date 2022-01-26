package systems.fundur.FundurASM.execs;

import systems.fundur.FundurASM.Runner;

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
