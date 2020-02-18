package uk.ac.lancaster.scc210.game.resources;

import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;

/**
 * Represents a Serialised Space Ship. Created from an XML document
 */
public class SerialisedSpaceShip implements Serialised {
    private final String[] items;

    private final String name, animation, bullet, firingSound, hitSound;

    private final int speed;

    /**
     * Instantiates a new Serialised space ship.
     *
     * @param name      the name of the spaceship
     * @param animation the animation of the spaceship
     * @param items     the items the spaceship can drop when destroyed
     * @param bullet    the bullet fired by the spaceship
     * @param speed     the speed of the spaceship
     */
    SerialisedSpaceShip(String name, String animation, String[] items, String bullet, int speed, String firingSound, String hitSound) {
        this.name = name;
        this.animation = animation;
        this.speed = speed;
        this.items = items;
        this.bullet = bullet;
        this.firingSound = firingSound;
        this.hitSound = hitSound;
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
     * Gets the SpaceShips bullet
     *
     * @return the bullet fired by the spaceship
     */
    public String getBullet() {
        return bullet;
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
     * Get the spaceships's firing sound
     *
     * @return the spaceship's firing sound
     */
    public String getFiringSound() {
        return firingSound;
    }

    /**
     * Get the sound which plays when the player hits something
     *
     * @return the player's hit sound
     */
    public String getHitSound() {
        return hitSound;
    }
}
