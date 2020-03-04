package uk.ac.lancaster.scc210.engine.content;

import uk.ac.lancaster.scc210.engine.animation.TextureAnimation;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
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
     * @throws ResourceNotFoundException the resource not found exception
     */
    public TextureAnimationManager(List<SerialisedAnimation> serialisedAnimations) throws ResourceNotFoundException {
        super(new TextureAnimation(new TextureAtlas(), 0, 0, 1));

        serialisedAnimations
                .parallelStream()
                .forEach(serialisedAnimation -> put(serialisedAnimation.getName(), serialisedAnimation.getAnimation()));
    }
}
