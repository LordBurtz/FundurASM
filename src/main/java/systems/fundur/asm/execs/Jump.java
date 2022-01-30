package systems.fundur.asm.execs;

import systems.fundur.asm.Runner;

public class Jump extends Exec{
    public Jump(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        runner.setProgramCounter(parameter -2);
    }
}
