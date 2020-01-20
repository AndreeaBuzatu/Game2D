package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.animation.TextureAnimation;
import uk.ac.lancaster.scc210.engine.ecs.Component;

public class AnimationComponent implements Component {
    private final TextureAnimation textureAnimation;

    public AnimationComponent(final TextureAnimation textureAnimation) {
        this.textureAnimation = textureAnimation;
    }

    public TextureAnimation getTextureAnimation() {
        return textureAnimation;
    }
}
