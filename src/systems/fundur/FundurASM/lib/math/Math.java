package systems.fundur.FundurASM.lib.math;

import systems.fundur.FundurASM.lib.Library;
import systems.fundur.FundurASM.lib.math.instr.Mod;

import java.util.HashMap;

public class Math extends Library {
    public  Math() {
        this.instructions = new HashMap<>();
        identifier = "math";
        register("mod", new Mod());
    }
}
