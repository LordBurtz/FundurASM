package systems.fundur.fundurasm.lib.math;

import systems.fundur.fundurasm.lib.Library;
import systems.fundur.fundurasm.lib.math.instr.Mod;

import java.util.HashMap;

public class Math extends Library {
    public  Math() {
        this.instructions = new HashMap<>();
        identifier = "math";
        register(new Mod());
    }
}
