package uk.ac.lancaster.scc210.engine.content;

import uk.ac.lancaster.scc210.engine.animation.TextureAnimation;
import uk.ac.lancaster.scc210.engine.resources.deserialise.SerialisedAnimation;

import java.util.List;

public class TextureAnimationManager extends ContentManager<TextureAnimation> {
    public TextureAnimationManager(List<SerialisedAnimation> serialisedAnimations) {
        super(null);

        serialisedAnimations
                .parallelStream()
                .forEach(serialisedAnimation -> put(serialisedAnimation.getName(), serialisedAnimation.getAnimation()));
    }
}
