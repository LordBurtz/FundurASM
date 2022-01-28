package systems.fundur.fundurasm.lib.math.execs;

import systems.fundur.fundurasm.Runner;
import systems.fundur.fundurasm.execs.Exec;

public class Mod extends Exec {

    public Mod(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        runner.setAcc(runner.getAcc() % runner.get(parameter));
    }
}
