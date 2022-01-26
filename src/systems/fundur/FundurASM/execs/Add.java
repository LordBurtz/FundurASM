package systems.fundur.FundurASM.execs;

import systems.fundur.FundurASM.Runner;

public class Add extends Exec{
    public Add(int parameter) {
        this.parameter = parameter;
    }

    @Override
    public void exec(Runner runner) {
        runner.setAcc(runner.getAcc() +  runner.get(parameter));
    }
}
