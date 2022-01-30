package systems.fundur.asm.lib.math;

import systems.fundur.asm.lib.Library;
import systems.fundur.asm.lib.math.instr.Mod;

import java.util.HashMap;

public class Math extends Library {
    public  Math() {
        this.instructions = new HashMap<>();
        identifier = "math";
        register(new Mod());
    }
}
