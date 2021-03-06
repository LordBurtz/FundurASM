package systems.fundur.asm.error;

import systems.fundur.asm.execs.Exec;
import systems.fundur.asm.util.Bool;

public class RegistryOutOfBoundsError implements Error{
    private final String message;

    @Deprecated
    public RegistryOutOfBoundsError(Exec func, int stackS, int lineN, Bool failed) {
        message = "\n\n----------\n" +
                " Registry out of Bounds for the operation %s in line #%d".formatted(func.getClass().getSimpleName(), lineN) +
                "\n Pointed to %d but stackSize was %d".formatted(func.getParameter(), stackS) +
                "\n Pwease correct it to continue interpreting your file!" +
                "\n----------";
        failed.setVal(true);
    }

    public RegistryOutOfBoundsError(String funcName, int param, int stackS, int lineN, Bool failed) {
        message = "\n\n----------\n" +
                " Registry out of Bounds for the operation %s in line #%d".formatted(funcName, lineN) +
                "\n Pointed to %d but stackSize was %d".formatted(param, stackS) +
                "\n Pwease correct it to continue interpreting your file!" +
                "\n----------";
        failed.setVal(true);
    }

    @Override
    public void error() {
        System.out.println(message);
    }
}
