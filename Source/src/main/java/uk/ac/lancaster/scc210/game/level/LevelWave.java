package uk.ac.lancaster.scc210.game.level;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.ecs.component.WaveComponent;
import uk.ac.lancaster.scc210.game.ecs.entity.SpaceShip;
import uk.ac.lancaster.scc210.game.waves.Wave;

import java.util.HashSet;
import java.util.Set;

public class LevelWave {
    private final Set<Entity> entities;

    private final Wave wave;

    private final Vector2f origin;
    private final Vector2f destination;

    private final SpaceShip spaceShip;

    private final Clock spawnTimer;

    private float spawnGap;

    private final int numShips;

    public LevelWave(Wave wave, Vector2f origin, Vector2f destination, int numShips, SpaceShip spaceShip) {
        this.wave = wave;
        this.origin = origin;
        this.destination = destination;
        this.numShips = numShips;
        this.spaceShip = spaceShip;

        entities = new HashSet<>();

        spawnTimer = new Clock();
    }

    Entity spawnNew() {
        // Create the initial ship - ignore the timer for the first one.
        if (entities.isEmpty()) {
            Entity entity = createShip();

            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

            entities.add(entity);

            spawnGap = findSpawnGap(spriteComponent, speedComponent);

            return entity;
        }

        if (spawnTimer.getElapsedTime().asSeconds() > spawnGap && entities.size() < numShips) {
            Entity entity = createShip();

            entities.add(entity);

            spawnTimer.restart();

            return entity;
        }

        return null;
    }

    private Entity createShip() {
        Entity entity = spaceShip.createEntity();

        entity.addComponent(new WaveComponent());

        SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

        Sprite sprite = spriteComponent.getSprite();

        FloatRect localBounds = sprite.getLocalBounds();

        Vector2f centreMiddle = new Vector2f(localBounds.width / 2, localBounds.height / 2);

        sprite.setOrigin(centreMiddle);

        sprite.setPosition(Vector2f.sub(destination, origin));

        return entity;
    }

    private float findSpawnGap(SpriteComponent spriteComponent, SpeedComponent speedComponent) {
        FloatRect localBounds = spriteComponent.getSprite().getLocalBounds();

        float speed = speedComponent.getSpeed();

        // Find the amount of time taken for the sprite to move from (0, 0) to (width, 0) based upon it's speed and FPS
        float widthTime = localBounds.width / speed / StateBasedGame.FPS;

        float heightTime = localBounds.height / speed / StateBasedGame.FPS;

        return (widthTime + heightTime) / 2;
    }

    boolean complete() {
        // All the spaceships are now dead
        return entities.isEmpty();
    }

    boolean allSpawned() {
        return entities.size() == numShips;
    }

    Wave getWave() {
        return wave;
    }

    public Set<Entity> getEntities() {
        return entities;
    }
}
