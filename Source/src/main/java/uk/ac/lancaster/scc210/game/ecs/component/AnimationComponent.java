package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.animation.TextureAnimation;
import uk.ac.lancaster.scc210.engine.ecs.Component;

/**
 * The type Animation component.
 */
public class AnimationComponent implements Component {
    private final TextureAnimation textureAnimation;

    /**
     * Instantiates a new Animation component.
     *
     * @param textureAnimation the texture animation
     */
    public AnimationComponent(final TextureAnimation textureAnimation) {
        this.textureAnimation = textureAnimation;
    }

    /**
     * Gets texture animation.
     *
     * @return the texture animation
     */
    public TextureAnimation getTextureAnimation() {
        return textureAnimation;
    }
}
