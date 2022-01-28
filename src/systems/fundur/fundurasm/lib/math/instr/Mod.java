package systems.fundur.fundurasm.lib.math.instr;

import systems.fundur.fundurasm.execs.Exec;
import systems.fundur.fundurasm.lib.base.SafeLoadInstruction;

public class Mod extends SafeLoadInstruction {
    @Override
    protected Exec safeGet(int param) {
        return new systems.fundur.fundurasm.lib.math.execs.Mod(param);
    }
}
