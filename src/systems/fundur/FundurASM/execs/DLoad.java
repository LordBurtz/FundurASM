package systems.fundur.FundurASM.execs;

import systems.fundur.FundurASM.Runner;

public class DLoad extends Exec{
    public DLoad(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        runner.setAcc(parameter);
    }
}
