package toothlessblue;

public class StringUtils {
    public static String[] chunkString(String string, int chunkSize) {
        int length = string.length();
        int chunkCount = (int)Math.ceil((double)length / chunkSize);
        String[] chunks = new String[chunkCount];

        for (int i = 0; i < chunkCount; i++) {
            int chunkStart = chunkCount * chunkSize;
            int chunkEnd = Math.max(chunkStart + chunkSize, length);

            chunks[i] = string.substring(chunkStart, chunkEnd);
        }

        return chunks;
    }
}
