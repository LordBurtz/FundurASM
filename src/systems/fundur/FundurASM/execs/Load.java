package systems.fundur.FundurASM.execs;

import systems.fundur.FundurASM.Runner;

public class Load extends Exec{
    public Load(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        runner.setAcc(runner.getRegister()[parameter]);
    }
}
