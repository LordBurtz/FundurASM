package systems.fundur.FundurASM.execs;

import systems.fundur.FundurASM.Runner;

public class JGT extends Exec{
    public JGT(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        if (runner.getAcc() > 0) runner.setProgramCounter(parameter);
    }
}
