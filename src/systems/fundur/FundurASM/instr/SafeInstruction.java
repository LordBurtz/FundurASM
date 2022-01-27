package systems.fundur.FundurASM.instr;

import systems.fundur.FundurASM.execs.Exec;
import systems.fundur.FundurASM.util.Bool;

public abstract class SafeInstruction extends Instruction{
    @Override
    protected Exec getInstance(int param, Bool failed, int stackSize, int lineN) {
        if (param < stackSize && param >= 0) {
            return safeGet(param);
        } else {
            error(param, stackSize, lineN, failed);
            return null;
        }
    }

    protected abstract Exec safeGet(int param);

    protected abstract void error(int param, int stackSize,int lineN,Bool failed);
}
