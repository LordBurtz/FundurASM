package systems.fundur.asm.execs;

import systems.fundur.asm.Runner;

public class JEQ extends Exec{
    public JEQ(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        if (runner.getAcc() == 0) runner.setProgramCounter(parameter -2);
    }
}
