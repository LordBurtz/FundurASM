package systems.fundur.fundurasm.execs;

import systems.fundur.fundurasm.Runner;

public class Sub extends Exec{
    public Sub(int parameter) {
        this.parameter = parameter;
    }

    @Override
    public void exec(Runner runner) {
        runner.setAcc(runner.getAcc() - runner.get(parameter));
    }
}
