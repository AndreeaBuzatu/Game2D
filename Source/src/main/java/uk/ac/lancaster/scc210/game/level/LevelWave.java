package uk.ac.lancaster.scc210.game.level;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.ecs.component.WaveComponent;
import uk.ac.lancaster.scc210.game.ecs.entity.SpaceShip;
import uk.ac.lancaster.scc210.game.waves.SineWave;
import uk.ac.lancaster.scc210.game.waves.StraightLineWave;
import uk.ac.lancaster.scc210.game.waves.Wave;

public class LevelWave {
    private final Entity[] entities;

    private Wave wave;

    private WaveComponent waveComponent;

    private final SpaceShip spaceShip;

    private final int numShips;

    public LevelWave(String wave, Vector2f origin, Vector2f destination, int numShips, SpaceShip spaceShip) {
        this.numShips = numShips;
        this.spaceShip = spaceShip;

        entities = new Entity[numShips];

        switch (wave) {
            case "straight":
                this.wave = new StraightLineWave(origin, destination);

                break;

            case "sine":
                this.wave = new SineWave(origin, destination);

                break;

            default:
                System.out.println("Fix this");

                break;
        }

        waveComponent = new WaveComponent(this.wave);

        System.out.println(this.wave.direction(origin, destination));

        Vector2f direction = this.wave.direction(origin, destination);

        for (int i = 0; i < entities.length; i++) {
            Entity entity = spaceShip.createEntity();

            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            Sprite sprite = spriteComponent.getSprite();

            Vector2f spritePos = sprite.getPosition();

            float width = sprite.getGlobalBounds().width;

            float height = sprite.getGlobalBounds().height;

            if (direction.x > 0) {
                sprite.setPosition((spritePos.x - width) * i, sprite.getPosition().y);

                System.out.println("X:" + (spritePos.x - width) * i);
            }

            if (direction.y > 0) {
                sprite.setPosition(sprite.getPosition().x, (spritePos.y - height) * i);
            }

            //sprite.setPosition((float) Math.random() * 100, (float) Math.random() * 100);

            System.out.println(spritePos);

            entities[i] = entity;

            entities[i].addComponent(waveComponent);
        }
    }

    boolean complete() {
        // All the spaceships are now dead
        return entities.length == 0;
    }


    public Wave getWave() {
        return wave;
    }

    public int getNumShips() {
        return numShips;
    }

    public Entity[] getEntities() {
        return entities;
    }
}
