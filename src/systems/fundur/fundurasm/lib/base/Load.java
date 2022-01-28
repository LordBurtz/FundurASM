package systems.fundur.fundurasm.lib.base;

import systems.fundur.fundurasm.execs.Exec;

public class Load extends SafeLoadInstruction {
    @Override
    protected Exec safeGet(int param) {
        return new systems.fundur.fundurasm.execs.Load(param);
    }
}
