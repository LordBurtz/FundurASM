package systems.fundur.fundurasm.error;

import systems.fundur.fundurasm.util.Bool;

public class IncorrectInstructionError implements Error{
    private final String message;
    public IncorrectInstructionError(String content, int line, Bool failed) {
        message = "\n\n----------\n" +
                "Instruction unclear in line #%d".formatted(line) + "" +
                "\n Instruction: --> %s <-- was not found!".formatted(content) +
                "\n Pwease correct it to continue interpreting your file!" +
                "\n----------";
        failed.setVal(true);
    }

    @Override
    public void error() {
        System.out.println(message);
    }
}
