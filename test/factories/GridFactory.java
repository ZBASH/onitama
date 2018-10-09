package factories;

import java.util.Arrays;

public final class GridFactory {
    public static char[][] grid(String source) {
        return grid(source, 5);
    }

    public static char[][] grid(String source, final int size) {
        char[]   chars  = source.replaceAll("\\s+", "").toCharArray();
        char[][] result = new char[size][size];

        for (int i = 0; i < size; i++) {
            int start = i * size;
            result[i] = Arrays.copyOfRange(chars, start, start + size);
        }

        return result;
    }
}
