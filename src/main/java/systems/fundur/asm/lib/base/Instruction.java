package systems.fundur.asm.lib.base;

import systems.fundur.asm.execs.Exec;
import systems.fundur.asm.util.Bool;

public abstract class Instruction {
    public Exec getExec(int param, Bool failed, int stackSize, int lineN, int offSet) {
        return getInstance(param, failed, stackSize, lineN, offSet);
    }

    protected abstract Exec getInstance(int param, Bool failed, int stackSize, int lineN, int offSet);
}
