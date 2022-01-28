package systems.fundur.FundurASM.lib.base;

import systems.fundur.FundurASM.error.RegistryOutOfBoundsError;
import systems.fundur.FundurASM.execs.Exec;
import systems.fundur.FundurASM.util.Bool;

public abstract class SafeLoadInstruction extends Instruction{
    @Override
    protected Exec getInstance(int param, Bool failed, int stackSize, int lineN, int offSet) {
        if (param < stackSize && param >= 0) {
            return safeGet(param);
        } else {
            error(param, stackSize, lineN, failed);
            return null;
        }
    }

    protected abstract Exec safeGet(int param);

    private void error(int param, int stackSize, int lineN, Bool failed) {
        new RegistryOutOfBoundsError(this.getClass().getSimpleName(), param, stackSize, lineN, failed).error();
    }
}
