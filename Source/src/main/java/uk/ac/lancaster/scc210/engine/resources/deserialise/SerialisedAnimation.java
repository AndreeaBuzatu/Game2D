package uk.ac.lancaster.scc210.engine.resources.deserialise;

import uk.ac.lancaster.scc210.engine.animation.TextureAnimation;
import uk.ac.lancaster.scc210.engine.content.TextureAtlas;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;

public class SerialisedAnimation implements Serialised {
    private TextureAnimation animation;

    private String name;

    SerialisedAnimation(final TextureAtlas textureAtlas, final String name, final int row, final int column, final int numFrames) throws ResourceNotFoundException {
        this.name = name;

        animation = new TextureAnimation(textureAtlas, row, column, numFrames);
    }

    public TextureAnimation getAnimation() {
        return animation;
    }

    public String getName() {
        return name;
    }
}
