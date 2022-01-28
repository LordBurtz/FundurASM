package systems.fundur.FundurASM.lib.base;

import systems.fundur.FundurASM.execs.Exec;

public class Add extends SafeLoadInstruction {
    @Override
    protected Exec safeGet(int param) {
        return new systems.fundur.FundurASM.execs.Add(param);
    }

    //@Override

}
