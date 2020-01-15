package uk.ac.lancaster.scc210.game.ecs.component;

import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.ecs.Component;

public class SpriteComponent implements Component {
    private final Sprite sprite;

    public SpriteComponent(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
