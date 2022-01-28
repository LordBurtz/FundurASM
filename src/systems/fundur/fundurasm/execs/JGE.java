package systems.fundur.fundurasm.execs;

import systems.fundur.fundurasm.Runner;

public class JGE extends Exec{
    public JGE(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        if (runner.getAcc() >= 0) runner.setProgramCounter(parameter -2);
    }
}
