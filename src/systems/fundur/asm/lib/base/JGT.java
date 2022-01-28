package systems.fundur.asm.lib.base;

import systems.fundur.asm.execs.Exec;
import systems.fundur.asm.util.Bool;

public class JGT extends Instruction{
    @Override
    protected Exec getInstance(int param, Bool failed, int stackSize, int lineN, int offSet) {
        return new systems.fundur.asm.execs.JGT(param - offSet);
    }
}
