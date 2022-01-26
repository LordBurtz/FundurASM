package systems.fundur.FundurASM.execs;

import systems.fundur.FundurASM.Runner;

public class Store extends Exec{
    public Store(int parameter) {
        this.parameter = parameter;
    }

    @Override
    public void exec(Runner runner) {
        runner.getRegister()[parameter] = runner.getAcc();
    }
}
