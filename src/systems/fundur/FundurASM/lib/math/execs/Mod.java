package systems.fundur.FundurASM.lib.math.execs;

import systems.fundur.FundurASM.Runner;
import systems.fundur.FundurASM.execs.Exec;

public class Mod extends Exec {

    public Mod(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        runner.setAcc(runner.getAcc() % runner.get(parameter));
    }
}
