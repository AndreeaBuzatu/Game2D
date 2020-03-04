package uk.ac.lancaster.scc210.engine.resources.deserialise;

import uk.ac.lancaster.scc210.engine.animation.TextureAnimation;
import uk.ac.lancaster.scc210.engine.content.TextureAtlas;

/**
 * Represents an Animation when it has been Serialised from XML. This object is not used to draw animations.
 */
public class SerialisedAnimation implements Serialised {
    private final TextureAnimation animation;

    private final String name;

    /**
     * Instantiates a new Serialised animation.
     *
     * @param textureAtlas the texture atlas to draw the frames from
     * @param name         the name of the animation
     * @param row          the row from the texture atlas (0-indexed)
     * @param column       the column from the texture atlas (0-indexed)
     * @param numFrames    the number of frames which the animation has
     */
    SerialisedAnimation(final TextureAtlas textureAtlas, final String name, final int row, final int column, final int numFrames) {
        this.name = name;

        animation = new TextureAnimation(textureAtlas, row, column, numFrames);
    }

    /**
     * Gets animation.
     *
     * @return animation animation
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
