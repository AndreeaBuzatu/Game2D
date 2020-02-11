package uk.ac.lancaster.scc210.game.prototypes;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.prototypes.Prototype;
import uk.ac.lancaster.scc210.game.ecs.component.AsteroidComponent;
import uk.ac.lancaster.scc210.game.ecs.component.OrientatedBoxComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.TransformableComponent;

public class AsteroidPrototype implements Prototype {
    private final TextureManager textureManager;

    public AsteroidPrototype(TextureManager textureManager) {
        this.textureManager = textureManager;
    }

    @Override
    public Entity create() {
        final Texture texture = textureManager.get("enemy-spritesheet.png:asteroid");

        final AsteroidComponent asteroidComponent = new AsteroidComponent(texture);

        final SpeedComponent speedComponent = new SpeedComponent(5);

        final TransformableComponent transformableComponent = new TransformableComponent(asteroidComponent.getCircle());

        final OrientatedBoxComponent orientatedBoxComponent = new OrientatedBoxComponent(new Sprite(texture));

        return World.createEntity(asteroidComponent, speedComponent, transformableComponent, orientatedBoxComponent);
    }
}
