package systems.fundur.fundurasm.execs;

import systems.fundur.fundurasm.Runner;

@SuppressWarnings("SpellCheckingInspection")
public class Mult extends Exec{
    public Mult(int param) {
        this.parameter = param;
    }

    @Override
    public void exec(Runner runner) {
        runner.setAcc(runner.getAcc() * runner.get(parameter));
    }
}
