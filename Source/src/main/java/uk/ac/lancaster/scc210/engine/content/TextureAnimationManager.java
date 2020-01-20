package uk.ac.lancaster.scc210.engine.content;

import uk.ac.lancaster.scc210.engine.animation.TextureAnimation;
import uk.ac.lancaster.scc210.engine.resources.deserialise.SerialisedAnimation;

import java.util.List;

/**
 * The type Texture animation manager.
 */
public class TextureAnimationManager extends ContentManager<TextureAnimation> {
    /**
     * Instantiates a new Texture animation manager.
     *
     * @param serialisedAnimations the serialised animations
     */
    public TextureAnimationManager(List<SerialisedAnimation> serialisedAnimations) {
        super(null);

        serialisedAnimations
                .parallelStream()
                .forEach(serialisedAnimation -> put(serialisedAnimation.getName(), serialisedAnimation.getAnimation()));
    }
}
