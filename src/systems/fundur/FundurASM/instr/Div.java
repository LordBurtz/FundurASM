package systems.fundur.FundurASM.instr;

import systems.fundur.FundurASM.execs.Exec;

public class Div extends SafeInstruction{
    @Override
    protected Exec safeGet(int param) {
        return new systems.fundur.FundurASM.execs.Div(param);
    }
}
