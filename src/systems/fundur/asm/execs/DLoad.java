package systems.fundur.asm.execs;

import systems.fundur.asm.Runner;

public class DLoad extends Exec{
    public DLoad(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        runner.setAcc(parameter);
    }
}
