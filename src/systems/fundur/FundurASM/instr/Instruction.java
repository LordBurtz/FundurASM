package systems.fundur.FundurASM.instr;

import systems.fundur.FundurASM.execs.Exec;
import systems.fundur.FundurASM.util.Bool;

public abstract class Instruction {
    public Exec getExec(int param, Bool failed, int stackSize, int lineN) {
        return getInstance(param, failed, stackSize, lineN);
    }

    protected abstract Exec getInstance(int param, Bool failed, int stackSize, int lineN);
}
