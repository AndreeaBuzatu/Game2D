package uk.ac.lancaster.scc210.engine.resources.deserialise;

import uk.ac.lancaster.scc210.engine.animation.TextureAnimation;
import uk.ac.lancaster.scc210.engine.content.TextureAtlas;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;

/**
 * The type Serialised animation.
 */
public class SerialisedAnimation implements Serialised {
    private TextureAnimation animation;

    private String name;

    /**
     * Instantiates a new Serialised animation.
     *
     * @param textureAtlas the texture atlas
     * @param name         the name
     * @param row          the row
     * @param column       the column
     * @param numFrames    the num frames
     * @throws ResourceNotFoundException the resource not found exception
     */
    SerialisedAnimation(final TextureAtlas textureAtlas, final String name, final int row, final int column, final int numFrames) throws ResourceNotFoundException {
        this.name = name;

        animation = new TextureAnimation(textureAtlas, row, column, numFrames);
    }

    /**
     * Gets animation.
     *
     * @return the animation
     */
    public TextureAnimation getAnimation() {
        return animation;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
