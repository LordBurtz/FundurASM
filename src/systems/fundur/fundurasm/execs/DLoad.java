package systems.fundur.fundurasm.execs;

import systems.fundur.fundurasm.Runner;

public class DLoad extends Exec{
    public DLoad(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        runner.setAcc(parameter);
    }
}
