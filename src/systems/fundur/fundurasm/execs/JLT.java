package systems.fundur.fundurasm.execs;

import systems.fundur.fundurasm.Runner;

public class JLT extends Exec{
    public JLT(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        if (runner.getAcc() < 0) runner.setProgramCounter(parameter -2);
    }
}
