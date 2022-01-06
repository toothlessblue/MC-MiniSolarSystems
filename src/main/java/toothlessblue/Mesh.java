package toothlessblue;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3f;
import org.lwjgl.system.CallbackI;

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
    public void render(VertexConsumer consumer, MatrixStack matrices, int light, int overlay) {
        Matrix4f positionMatrix = matrices.peek().getPositionMatrix();

        for (Vertex vertex : this.vertices) {
            consumer.vertex(positionMatrix, vertex.position.x, vertex.position.y, vertex.position.z)
                    .color(vertex.colour.x, vertex.colour.y, vertex.colour.z, 255)
                    .texture(vertex.uv.x, vertex.uv.y)
                    //.overlay(overlay)
                    .light(light)
                    .normal(vertex.normal.x, vertex.normal.y, vertex.normal.z)
                    .next();
        }
    }

    public static Mesh generateBoxMesh(Sprite sprite) {
        Mesh mesh = new Mesh();

        for (int i = 0; i < 6; i++) {

            for (int j = 0; j < 4; j++) {
                Vector2 uv = switch (j) {
                    case 0 -> new Vector2(sprite.getMinU(), sprite.getMinV());
                    case 1 -> new Vector2(sprite.getMaxU(), sprite.getMinV());
                    case 2 -> new Vector2(sprite.getMaxU(), sprite.getMaxV());
                    case 3 -> new Vector2(sprite.getMinU(), sprite.getMaxV());
                    default -> new Vector2(0, 0);
                };

                Vector3 normal = switch (i) {
                    case 0 -> new Vector3(0, -1, 0);
                    case 1 -> new Vector3(0, 1, 0);
                    case 2 -> new Vector3(-1, 0, 0);
                    case 3 -> new Vector3(1, 0, 0);
                    case 4 -> new Vector3(0, 0, -1);
                    case 5 -> new Vector3(0, 0, 1);
                    default -> throw new IllegalStateException("Unexpected value: " + i);
                };


                Vector3 position = new Vector3();

                if (i < 2) {
                    position.y = i;

                    if (j == 0) {
                        position.x = position.y;
                        position.z = 0;
                    } else if (j == 1) {
                        position.x = 1 - position.y;
                        position.z = 0;
                    } else if (j == 2) {
                        position.x = 1 - position.y;
                        position.z = 1;
                    } else if (j == 3) {
                        position.x = position.y;
                        position.z = 1;
                    }
                } else if (i < 4) {
                    position.x = i - 2;

                    if (j == 0) {
                        position.y = 1 - position.x;
                        position.z = 0;
                    } else if (j == 1) {
                        position.y = position.x;
                        position.z = 0;
                    } else if (j == 2) {
                        position.y = position.x;
                        position.z = 1;
                    } else if (j == 3) {
                        position.y = 1 - position.x;
                        position.z = 1;
                    }
                } else {
                    position.z = i - 4;

                    if (j == 0) {
                        position.x = 1 - position.z;
                        position.y = 0;
                    } else if (j == 1) {
                        position.x = position.z;
                        position.y = 0;
                    } else if (j == 2) {
                        position.x = position.z;
                        position.y = 1;
                    } else if (j == 3) {
                        position.x = 1 - position.z;
                        position.y = 1;
                    }
                }

                Vertex vertex = new Vertex();
                vertex.position = position;
                vertex.normal = normal;
                vertex.uv = uv;

                mesh.addVertex(vertex);
            }
        }

        return mesh;
    }
}
