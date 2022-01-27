package systems.fundur.FundurASM.instr;

import systems.fundur.FundurASM.error.RegistryOutOfBoundsError;
import systems.fundur.FundurASM.execs.Exec;
import systems.fundur.FundurASM.util.Bool;

public class Add extends SafeInstruction{
    @Override
    protected Exec safeGet(int param) {
        return new systems.fundur.FundurASM.execs.Add(param);
    }

    //@Override

}
