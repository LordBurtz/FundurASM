package systems.fundur.FundurASM.error;

import systems.fundur.FundurASM.util.Bool;

public class InstructionNotFoundError implements Error{
    private final String message;
    public InstructionNotFoundError(String content, int line, Bool failed) {
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
