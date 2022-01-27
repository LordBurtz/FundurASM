package systems.fundur.FundurASM.instr;

import systems.fundur.FundurASM.execs.Exec;
import systems.fundur.FundurASM.execs.Sub;

public class Store extends SafeInstruction{
    @Override
    protected Exec safeGet(int param) {
        return new systems.fundur.FundurASM.execs.Store(param);
    }
}
