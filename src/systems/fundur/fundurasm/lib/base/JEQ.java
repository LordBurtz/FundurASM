package systems.fundur.fundurasm.lib.base;

import systems.fundur.fundurasm.execs.Exec;
import systems.fundur.fundurasm.util.Bool;

public class JEQ extends Instruction{
    @Override
    protected Exec getInstance(int param, Bool failed, int stackSize, int lineN, int offSet) {
        return new systems.fundur.fundurasm.execs.JEQ(param - offSet);
    }
}
