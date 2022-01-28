package systems.fundur.FundurASM.lib.base;

import systems.fundur.FundurASM.execs.Exec;
import systems.fundur.FundurASM.util.Bool;

public class JEQ extends Instruction{
    @Override
    protected Exec getInstance(int param, Bool failed, int stackSize, int lineN, int offSet) {
        return new systems.fundur.FundurASM.execs.JEQ(param - offSet);
    }
}
