package toothlessblue;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;

import java.util.ArrayList;

/**
 * Stores vertex data for rendering by a vertex consumer
 */
public class Mesh {
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();

    public Mesh() {

    }

    /**
     * Add a vertex to the mesh
     * @param vertex The vertex to add, funnily enough
     */
    public void addVertex(Vertex vertex) {
        this.vertices.add(vertex);
    }

    /**
     * Render the meeesh
     * @param consumer The vertex consumer from a minecraft renderer
     * @param matrices The matrix stack from the same renderer
     */
    public void render(VertexConsumer consumer, MatrixStack matrices) {
        Matrix4f positionMatrix = matrices.peek().getPositionMatrix();

        for (Vertex vertex : this.vertices) {
            consumer.vertex(positionMatrix, vertex.position.x, vertex.position.y, vertex.position.z)
                    .light(vertex.light.x, vertex.light.y)
                    .color(vertex.colour.x, vertex.colour.y, vertex.colour.z, 1.0f)
                    .overlay(vertex.overlay.x, vertex.overlay.y)
                    .texture(vertex.uv.x, vertex.uv.y);
        }
    }
}
