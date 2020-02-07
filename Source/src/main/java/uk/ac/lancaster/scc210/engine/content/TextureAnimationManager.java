package uk.ac.lancaster.scc210.engine.content;

import uk.ac.lancaster.scc210.engine.animation.TextureAnimation;
import uk.ac.lancaster.scc210.engine.resources.deserialise.SerialisedAnimation;

import java.util.List;

/**
 * A ContentManager for storing TextureAnimations.
 */
public class TextureAnimationManager extends ContentManager<TextureAnimation> {
    /**
     * From a list of SerialisedAnimations, place them into the manager
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
