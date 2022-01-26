package systems.fundur.FundurASM.execs;

import systems.fundur.FundurASM.Runner;

public class Div extends Exec{
    public Div(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        runner.setAcc(runner.getAcc() / runner.get(parameter));
    }
}
