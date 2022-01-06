package toothlessblue;

public class Vertex {
    public Vector3 position;
    public Vector3 normal;

    public Vector3 colour;

    // For the following, X is U, and Y is V.
    public Vector2 uv;

    // Bunch'a' default values for your sweet smelling eyes
    public Vertex() {
        this.position = new Vector3();
        this.normal = new Vector3();
        this.colour = new Vector3(1.0f, 1.0f, 1.0f);
        this.uv = new Vector2(0, 1);
    }
}
