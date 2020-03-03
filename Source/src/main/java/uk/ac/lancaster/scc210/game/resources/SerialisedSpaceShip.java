package uk.ac.lancaster.scc210.game.resources;

import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;

/**
 * Represents a Serialised Space Ship. Created from an XML document
 */
public class SerialisedSpaceShip implements Serialised {
    private final String[] items;

    private final String name, animation, bullet, pattern, firingSound, hitSound, texture;

    private final int speed, score, lives;

    /**
     * Instantiates a new Serialised space ship.
     *
     * @param name      the name of the spaceship
     * @param animation the animation of the spaceship
     * @param items     the items the spaceship can drop when destroyed
     * @param bullet    the bullet fired by the spaceship
     * @param pattern   the pattern used by bullets
     * @param speed     the speed of the spaceship
     * @param score     the score awarded to the player when killing this spaceship
     * @param lives     the number of the lives that spaceship has
     * @param firingSound the firing sound of the spaceship
     * @param hitSound  the sound played when colliding
     */
    public SerialisedSpaceShip(String name, String animation, String[] items, String bullet, String pattern, int speed, int score, int lives, String firingSound, String hitSound, String texture) {
        this.name = name;
        this.animation = animation;
        this.items = items;
        this.bullet = bullet;
        this.pattern = pattern;
        this.speed = speed;
        this.score = score;
        this.lives = lives;
        this.firingSound = firingSound;
        this.hitSound = hitSound;
        this.texture = texture;
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
     * Get the current firing pattern
     *
     * @return the current firing pattern
     */
    public String getPattern() {
        return pattern;
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

    /**
     * Get the number of lives
     *
     * @return the number of lives
     */
    public int getLives() {
        return lives;
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

    public String getTexture() {
        return texture;
    }
}
