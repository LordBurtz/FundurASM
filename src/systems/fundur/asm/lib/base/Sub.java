package systems.fundur.asm.lib.base;

import systems.fundur.asm.execs.Exec;

public class Sub extends SafeLoadInstruction {
    @Override
    protected Exec safeGet(int param) {
        return new systems.fundur.asm.execs.Sub(param);
    }
}
