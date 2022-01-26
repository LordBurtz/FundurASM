package systems.fundur.FundurASM.execs;

import systems.fundur.FundurASM.Runner;

public class End extends Exec{
    public End(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        runner.setRunning(false);
        runner.setErred(false);
    }
}
