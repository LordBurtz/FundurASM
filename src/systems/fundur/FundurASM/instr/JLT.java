package systems.fundur.FundurASM.instr;

import systems.fundur.FundurASM.execs.Exec;
import systems.fundur.FundurASM.util.Bool;

public class JLT extends Instruction{
    @Override
    protected Exec getInstance(int param, Bool failed, int stackSize, int lineN) {
        return new systems.fundur.FundurASM.execs.JLT(param);
    }
}
