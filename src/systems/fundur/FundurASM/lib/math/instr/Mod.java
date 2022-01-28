package systems.fundur.FundurASM.lib.math.instr;

import systems.fundur.FundurASM.execs.Exec;
import systems.fundur.FundurASM.lib.base.SafeLoadInstruction;

public class Mod extends SafeLoadInstruction {
    @Override
    protected Exec safeGet(int param) {
        return new systems.fundur.FundurASM.lib.math.execs.Mod(param);
    }
}
