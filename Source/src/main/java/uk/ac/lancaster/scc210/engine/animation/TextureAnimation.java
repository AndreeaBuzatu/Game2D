package uk.ac.lancaster.scc210.engine.animation;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;
import uk.ac.lancaster.scc210.engine.content.TextureAtlas;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;

/**
 * TextureAnimation is the most basic form of animation. It consists of a series of frames (Textures).
 * Animations are played by looping through each frame one-by-one at a rate of 24FPS.
 * Once the final frame has been reached, the animation goes back to the first animation.
 *
 * Frames are typically sourced from a Texture Atlas where they are loaded left to right, line-by-line.
 */
public class TextureAnimation {
    // 24 FPS is the standard speed for animation
    private final float FPS = 24;

    private Clock animationTimer;

    private final Texture[] frames;

    private int currentFrame;

    private float frameTimeRemaining;

    /**
     * Instantiates a new Texture animation.
     *
     * @param textureAtlas   texture atlas to load from
     * @param startingRow    the row in the texture atlas to load from (0-indexed)
     * @param startingColumn the column in the texture atlas to load from (0-indexed)
     * @param numFrames      the number of frames to load from the texture atlas at the given position.
     * @throws ResourceNotFoundException occurs when the a frame cannot be found in the texture atlas
     */
    public TextureAnimation(final TextureAtlas textureAtlas, final int startingRow, final int startingColumn, final int numFrames) throws ResourceNotFoundException {
        frames = new Texture[numFrames];

        currentFrame = 0;

        frameTimeRemaining = FPS;

        int currentRow = startingRow;

        int currentColumn = startingColumn;

        for (int i = 0; i < frames.length; i++) {
            frames[i] = textureAtlas.get(currentRow, currentColumn);

            if (currentColumn < textureAtlas.getColumns()) {
                currentColumn++;

            } else {
                currentRow++;

                currentColumn = 0;
            }
        }
    }

    /**
     * Gets the texture at this point in time for the animation speed.
     * This should be used to update a sprite.
     *
     * @return the texture
     */
    public Texture getTexture() {
        if (animationTimer == null) {
            // The constructor starts the clock, so initialise it later
            animationTimer = new Clock();
        }

        frameTimeRemaining -= animationTimer.getElapsedTime().asSeconds();

        if (frameTimeRemaining <= 0) {
            currentFrame++;

            // Loop back around to the start
            if (currentFrame >= frames.length) {
                currentFrame = 0;
            }

            frameTimeRemaining = FPS;

            animationTimer.restart();
        }

        return frames[currentFrame];
    }
}
