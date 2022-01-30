package systems.fundur.asm.execs;

import systems.fundur.asm.Runner;

public class Add extends Exec{
    public Add(int parameter) {
        this.parameter = parameter;
    }

    @Override
    public void exec(Runner runner) {
        runner.setAcc(runner.getAcc() +  runner.get(parameter));
    }
}
