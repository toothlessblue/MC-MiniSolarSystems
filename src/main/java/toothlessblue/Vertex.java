package toothlessblue;

public class Vertex {
    public Vector3 position;
    public Vector3 normal;

    public Vector3 colour;

    // For the following, X is U, and Y is V.
    public Vector2 uv;
    public Vector2Int light;
    public Vector2Int overlay;

    // Bunch'a' default values for your sweet smelling eyes
    public Vertex() {
        this.position = new Vector3();
        this.normal = new Vector3();
        this.colour = new Vector3(1,1,1);
        this.uv = new Vector2(0, 1);
        this.light = new Vector2Int(0, 255);
        this.overlay = new Vector2Int(0, 255);
    }

    public Vertex(Vector3 position, Vector3 normal, Vector2 uv, Vector2Int light, Vector2Int overlay) {
        this.position = position;
        this.normal = normal;
        this.uv = uv;
        this.light = light;
        this.overlay = overlay;
    }
}
