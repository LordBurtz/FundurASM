package systems.fundur.asm.lib.math.instr;

import systems.fundur.asm.execs.Exec;
import systems.fundur.asm.lib.base.SafeLoadInstruction;

public class Mod extends SafeLoadInstruction {
    @Override
    protected Exec safeGet(int param) {
        return new systems.fundur.asm.lib.math.execs.Mod(param);
    }
}
