package uk.ac.lancaster.scc210.engine.resources;

import org.jsfml.graphics.Shader;
import org.jsfml.graphics.ShaderSourceException;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type Shader adapter.
 */
public class ShaderAdapter extends Resource<Shader> {
    private final Shader.Type shaderType;

    /**
     * Instantiates a new ShaderAdapter.
     *
     * @param shader     the shader
     * @param shaderType the shader type
     */
    public ShaderAdapter(Shader shader, Shader.Type shaderType) {
        super(null);

        this.shaderType = shaderType;

        resource = shader;
    }

    @Override
    public void loadFromFile(InputStream stream) throws IOException {
        try {
            resource.loadFromStream(stream, shaderType);

        } catch (ShaderSourceException e) {
            e.printStackTrace();

            throw new IOException("Unable to compile shader");
        }
    }
}
