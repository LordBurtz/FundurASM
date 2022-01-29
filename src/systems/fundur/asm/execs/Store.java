package systems.fundur.asm.execs;

import systems.fundur.asm.Runner;

public class Store extends Exec{
    public Store(int parameter) {
        this.parameter = parameter;
    }

    @Override
    public void exec(Runner runner) {
        runner.getRegister()[parameter] = runner.getAcc();
    }
}
