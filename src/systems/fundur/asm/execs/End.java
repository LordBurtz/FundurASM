package systems.fundur.asm.execs;

import systems.fundur.asm.Runner;

public class End extends Exec{
    public End(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        runner.setRunning(false);
        runner.setErred(false);
        runner.setReturnCode(runner.get(parameter));
    }
}
