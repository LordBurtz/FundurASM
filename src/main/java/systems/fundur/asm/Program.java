package systems.fundur.asm;

import systems.fundur.asm.execs.Exec;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private final List<Exec> execs;
    private int stackSize;

    public Program() {
        this.execs = new ArrayList<>();
        this.stackSize = 0;
    }

    public int getStackSize() {
        return stackSize;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    public List<Exec> getExecs() {
        return execs;
    }

    public void append(Exec exec) {
        execs.add(exec);
    }
}
