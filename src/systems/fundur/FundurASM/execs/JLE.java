package systems.fundur.FundurASM.execs;

import systems.fundur.FundurASM.Runner;

public class JLE extends Exec{
    public JLE(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        if (runner.getAcc() <= 0) runner.setProgramCounter(parameter);
    }
}
