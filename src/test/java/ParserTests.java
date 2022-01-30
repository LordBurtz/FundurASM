import org.junit.jupiter.api.Test;

import systems.fundur.asm.Parser;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTests {
    @Test
    void testFromFile() {
        assertEquals(260, new Parser("test.fasm").execute());
    }
}
