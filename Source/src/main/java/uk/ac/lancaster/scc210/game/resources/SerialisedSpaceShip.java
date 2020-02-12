package uk.ac.lancaster.scc210.game.resources;

import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;

/**
 * Represents a Serialised Space Ship. Created from an XML document
 */
public class SerialisedSpaceShip implements Serialised {
    private final String[] items;

    private final String name;
    private final String animation;

    private final int speed, score;

    /**
     * Instantiates a new Serialised space ship.
     *
     * @param name      the name of the spaceship
     * @param animation the animation of the spaceship
     * @param items     the items the spaceship can drop when destroyed
     * @param speed     the speed of the spaceship
     * @param score     the score awarded to the plaer when killing this spaceship
     */
    SerialisedSpaceShip(String name, String animation, String[] items, int speed, int score) {
        this.name = name;
        this.animation = animation;
        this.items = items;
        this.speed = speed;
        this.score = score;
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

    /**
     * Get items
     *
     * @return the items
     */
    public String[] getItems() {
        return items;
    }

    /**
     * Get the score awarded the player when killing this shop
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }
}
