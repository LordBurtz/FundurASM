import org.junit.jupiter.api.Test;
import systems.fundur.asm.Runner;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static systems.fundur.asm.Parser.parseFromFile;

public class ParserTests {
    @Test
    void testFromFile() throws InterruptedException {
        Runner r = new Runner(Objects.requireNonNull(parseFromFile("test.fasm")));
        r.start();
        r.join();
        assertEquals(260, r.getReturnCode());
    }
}
