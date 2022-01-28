package systems.fundur.FundurASM.error;

import systems.fundur.FundurASM.util.Bool;

public class LibraryNotFoundError implements Error{
    private String message;
    public LibraryNotFoundError(String arg, int lineN, Bool failed) {
        message = "\n\n----------\n" +
                "Instruction unclear in line #%d".formatted(lineN) +
                "\n Library: --> %s <-- was not found!".formatted(arg) +
                "\n Pwease correct it to continue interpreting your file!" +
                "\n----------";
        failed.setVal(true);
    }

    @Override
    public void error() {
        System.out.println(message);
    }
}
