package systems.fundur.fundurasm.lib.base;

import systems.fundur.fundurasm.execs.Exec;

public class Add extends SafeLoadInstruction {
    @Override
    protected Exec safeGet(int param) {
        return new systems.fundur.fundurasm.execs.Add(param);
    }

    //@Override

}
