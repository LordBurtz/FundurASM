package systems.fundur.FundurASM.execs;

import systems.fundur.FundurASM.Runner;

public class Mult extends Exec{
    public Mult(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        runner.setAcc(runner.getAcc() * runner.get(parameter));
    }
}
