package uk.ac.lancaster.scc210.game.resources;

import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;

public class SerialisedSpaceShip implements Serialised {
    private final String name;
    private final String animation;

    private final int speed;

    SerialisedSpaceShip(String name, String animation, int speed) {
        this.name = name;
        this.animation = animation;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public String getAnimation() {
        return animation;
    }

    public int getSpeed() {
        return speed;
    }

}
