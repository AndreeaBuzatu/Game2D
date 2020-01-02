package uk.ac.lancaster.scc210.resources;

import uk.ac.lancaster.scc210.content.TextureManager;

public class ResourceProvider {
    private TextureManager textureManager;

    public ResourceProvider() throws ResourceNotFoundException {
        textureManager = new TextureManager();
    }

    public TextureManager getTextureManager() {
        return textureManager;
    }
}
