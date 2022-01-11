package toothlessblue;

import net.minecraft.client.texture.NativeImage;

public class TextureHelper {
    public static int RGBAtoINT(int r, int g, int b, int a) {
        // bitwise OR equivalent to +, supposedly faster
        return (r) | (g << 8) | (b << 16) | (a << 24);
    }

    public static int RGBAtoINT(int r, int g, int b) {
        return RGBAtoINT(r, g, b, 255);
    }

    public static void blackenNativeImage(NativeImage image) {
        fillNativeImage(image, RGBAtoINT(0, 0, 0));
    }

    public static void fillNativeImage(NativeImage image, int colour) {
        setNativeImageArea(
                new Vector2Int(0, 0),
                new Vector2Int(image.getWidth(), image.getHeight()),
                colour,
                image);
    }

    /**
     * Fills an area in a native image with colour
     * @param from Coordinates to fill from, inclusive
     * @param to Coordinates to fill to, exclusive
     * @param colour Colour to fill with
     * @param image Image to fill
     */
    public static void setNativeImageArea(Vector2Int from, Vector2Int to, int colour, NativeImage image) {
        for (int x = from.x; x < to.x; x++) {
            for (int y = from.y; y < to.y; y++) {
                image.setColor(x, y, colour);
            }
        }
    }
}
