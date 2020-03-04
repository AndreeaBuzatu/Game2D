package uk.ac.lancaster.scc210.game.resources;

import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;

/**
 * The type Serialised bullet.
 */
public class SerialisedBullet implements Serialised {
    private final String name, texture;

    private final int speed;

    /**
     * Instantiates a new Serialised bullet.
     *
     * @param name    the name
     * @param texture the texture
     * @param speed   the speed
     */
    SerialisedBullet(String name, String texture, int speed) {
        this.name = name;
        this.texture = texture;
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
     * Gets texture.
     *
     * @return the texture
     */
    public String getTexture() {
        return texture;
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
