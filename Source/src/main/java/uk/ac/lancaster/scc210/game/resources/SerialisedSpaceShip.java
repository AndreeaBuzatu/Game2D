package uk.ac.lancaster.scc210.game.resources;

import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;

/**
 * Represents a Serialised Space Ship. Created from an XML document
 */
public class SerialisedSpaceShip implements Serialised {
    private final String name;
    private final String animation;

    private final int speed;

    /**
     * Instantiates a new Serialised space ship.
     *
     * @param name      the name of the spaceship
     * @param animation the animation of the spaceship
     * @param speed     the speed of the spaceship
     */
    SerialisedSpaceShip(String name, String animation, int speed) {
        this.name = name;
        this.animation = animation;
        this.speed = speed;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets animation.
     *
     * @return the animation
     */
    public String getAnimation() {
        return animation;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

}
