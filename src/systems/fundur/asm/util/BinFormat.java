package systems.fundur.asm.util;

public class BinFormat {
    public static Object parseBinary(String bin) {
        StringBuilder s = new StringBuilder();
        for (char c : bin.toCharArray()) {
            s.insert(0, c);
        }

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.toString().toCharArray()[i];
            if (c == '0') continue;
            if (c == '1') {
                result += Math.pow(2, i);
            } else {
                return null;
            }
        }

        return result;
    }
}
