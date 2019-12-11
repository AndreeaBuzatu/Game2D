package uk.ac.lancaster.game.entites;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.game.Game;

import java.io.IOException;
import java.nio.file.Paths;

public abstract class Entity {
    protected Sprite sprite;

    protected boolean alive;

    Entity(String fileName) {
        Texture texture = new Texture();

        try {
            texture.loadFromFile(Paths.get(  "res", fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.sprite = new Sprite(texture);

        alive = true;
    }

    public abstract void update();

    public void draw(RenderTarget target) {
        target.draw(sprite);
    }

    protected boolean intersects(Entity other) {
        return sprite.getGlobalBounds().intersection(other.sprite.getGlobalBounds()) != null;
    }

    protected boolean outOfBounds() {
        Vector2f pos = sprite.getPosition();

        /*
        float width = sprite.getGlobalBounds().width;

        float height = -sprite.getGlobalBounds().height;

        float maxRight = Main.WINDOW_WIDTH - width;

        float maxDown = Main.WINDOW_HEIGHT + height;
         */

        /*
        float width = sprite.getLocalBounds().width;

        float height = -sprite.getLocalBounds().height;

        float maxRight = Game.WINDOW_WIDTH - width;

        float maxDown = Game.WINDOW_HEIGHT + height;

        if (pos.x < 0) { // Left
            sprite.setPosition(0, pos.y);

            return true;

        } else if (pos.y < 0) { // Top
            sprite.setPosition(pos.x, 0);

            return true;

        } else if (pos.x > maxRight) { // Right
            sprite.setPosition(maxRight, pos.y);

            return true;

        } else if (pos.y > maxDown) { // Bottom
            sprite.setPosition(pos.x, maxDown);

            return true;
        }
         */

        FloatRect globalBounds = sprite.getGlobalBounds();

        float width = sprite.getTextureRect().width;

        float height = sprite.getTextureRect().height;

        float maxRight = Game.WINDOW_WIDTH - globalBounds.width;

        float maxDown = Game.WINDOW_HEIGHT - globalBounds.height;

        System.out.println("Global bounds: " + globalBounds);

        if (globalBounds.left <= 0) { // Left
            //sprite.setPosition(globalBounds.width - pos.x, pos.y);

            sprite.setPosition(globalBounds.width - pos.x - 1, pos.y);

            System.out.println("Left");

            return true;

        } else if (globalBounds.top < 0) { // Top
            sprite.setPosition(pos.x, pos.y - globalBounds.top - 1);

            System.out.println("Top");

            return true;

        } else if (globalBounds.left > maxRight) { // Right
            //sprite.setPosition(maxRight, pos.y);

            sprite.setPosition(pos.x - width - 1, pos.y);

            System.out.println("Right");

            return true;

        } else if (globalBounds.top > maxDown) { // Bottom
            //sprite.setPosition(pos.x, maxDown);

            sprite.setPosition(pos.x, pos.y - height - 1);

            System.out.println("Bottom");

            return true;
        }

        return false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        alive = !alive;
    }
}
