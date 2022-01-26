package systems.fundur.FundurASM.execs;

import systems.fundur.FundurASM.Runner;

public class Sub extends Exec{
    public Sub(int parameter) {
        this.parameter = parameter;
    }

    @Override
    public void exec(Runner runner) {
        runner.setAcc(runner.getAcc() - runner.get(parameter));
    }
}
