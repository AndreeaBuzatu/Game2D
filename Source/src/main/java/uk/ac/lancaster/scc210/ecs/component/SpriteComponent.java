package uk.ac.lancaster.scc210.ecs.component;

import org.jsfml.graphics.Sprite;

public class SpriteComponent implements Component {
    private final Sprite sprite;

    public SpriteComponent(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
