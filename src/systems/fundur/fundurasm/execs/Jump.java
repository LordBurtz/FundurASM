package systems.fundur.fundurasm.execs;

import systems.fundur.fundurasm.Runner;

public class Jump extends Exec{
    public Jump(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        runner.setProgramCounter(parameter -2);
    }
}
