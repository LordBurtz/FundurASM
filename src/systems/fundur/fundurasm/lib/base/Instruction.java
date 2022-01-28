package systems.fundur.fundurasm.lib.base;

import systems.fundur.fundurasm.execs.Exec;
import systems.fundur.fundurasm.util.Bool;

public abstract class Instruction {
    public Exec getExec(int param, Bool failed, int stackSize, int lineN, int offSet) {
        return getInstance(param, failed, stackSize, lineN, offSet);
    }

    protected abstract Exec getInstance(int param, Bool failed, int stackSize, int lineN, int offSet);
}
