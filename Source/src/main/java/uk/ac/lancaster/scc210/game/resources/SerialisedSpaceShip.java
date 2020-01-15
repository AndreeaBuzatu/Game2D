package uk.ac.lancaster.scc210.game.resources;

import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;

public class SerialisedSpaceShip implements Serialised {
    private final String name;
    private final String texture;

    private final int speed;

    SerialisedSpaceShip(String name, String texture, int speed) {
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
