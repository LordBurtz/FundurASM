package systems.fundur.FundurASM.instr;

import systems.fundur.FundurASM.error.RegistryOutOfBoundsError;
import systems.fundur.FundurASM.execs.Exec;
import systems.fundur.FundurASM.util.Bool;

public class Add extends SafeInstruction{
    @Override
    protected Exec safeGet(int param) {
        return new systems.fundur.FundurASM.execs.Add(param);
    }

    @Override
    protected void error(int param, int stackSize, int lineN, Bool failed) {
        new RegistryOutOfBoundsError(this.getClass().getSimpleName(), param, stackSize, lineN, failed).error();
    }
}
