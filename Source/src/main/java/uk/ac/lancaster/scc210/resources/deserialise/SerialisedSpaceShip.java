package uk.ac.lancaster.scc210.resources.deserialise;

public class SerialisedSpaceShip {
    private String name, texture;

    private int speed;

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
