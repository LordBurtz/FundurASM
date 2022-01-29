package systems.fundur.asm.lib.math.execs;

import systems.fundur.asm.Runner;
import systems.fundur.asm.execs.Exec;

public class Mod extends Exec {

    public Mod(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        runner.setAcc(runner.getAcc() % runner.get(parameter));
    }
}
