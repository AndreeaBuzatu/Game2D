package uk.ac.lancaster.scc210.game.resources;

import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;

public class SerialisedBullet implements Serialised {
    private final String name, texture;

    private final int speed;

    SerialisedBullet(String name, String texture, int speed) {
        this.name = name;
        this.texture = texture;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public String getTexture() {
        return texture;
    }

    public int getSpeed() {
        return speed;
    }
}
