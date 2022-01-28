package systems.fundur.FundurASM.lib.base;

import systems.fundur.FundurASM.execs.Exec;
import systems.fundur.FundurASM.util.Bool;

public abstract class Instruction {
    public Exec getExec(int param, Bool failed, int stackSize, int lineN, int offSet) {
        return getInstance(param, failed, stackSize, lineN, offSet);
    }

    protected abstract Exec getInstance(int param, Bool failed, int stackSize, int lineN, int offSet);
}
