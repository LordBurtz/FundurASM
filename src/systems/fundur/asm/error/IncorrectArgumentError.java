package systems.fundur.asm.error;

import systems.fundur.asm.util.Bool;

public class IncorrectArgumentError implements Error{
    private String message;

    public IncorrectArgumentError(String arg, Bool failed) {
        message = "\n\n----------\n" +
                " Argument: --> %s <-- is incorrect!".formatted(arg) +
                "\n Pwease correct it to continue interpreting your file!" +
                "\n----------";
        failed.setVal(true);
    }

    @Override
    public void error() {
        System.out.println(message);
    }
}
