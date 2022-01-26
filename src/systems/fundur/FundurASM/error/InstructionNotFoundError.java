package systems.fundur.FundurASM.error;

public class InstructionNotFoundError implements Error{
    private String message;
    public InstructionNotFoundError(String content, int line, Boolean failed) {
        message = "\n\n----------\n" +
                "Instruction unclear in line #%d".formatted(line) + "" +
                "\n Instruction: --> %s <-- was not found!".formatted(content) +
                "\n Pwease correct it to continue interpreting your file!" +
                "\n----------";
        failed = true;
    }

    @Override
    public void error() {
        System.out.println(message);
    }
}
