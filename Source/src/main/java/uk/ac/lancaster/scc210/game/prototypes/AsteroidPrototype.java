package uk.ac.lancaster.scc210.game.prototypes;

import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.prototypes.Prototype;
import uk.ac.lancaster.scc210.game.ecs.component.*;

public class AsteroidPrototype implements Prototype {
    private final int ASTEROID_SCORE = 10;

    private final TextureManager textureManager;

    public AsteroidPrototype(TextureManager textureManager) {
        this.textureManager = textureManager;
    }

    @Override
    public Entity create() {
        final AsteroidComponent asteroidComponent = new AsteroidComponent(textureManager.get("enemy-spritesheet.png:asteroid"));

        final SpeedComponent speedComponent = new SpeedComponent(5);

        final TransformableComponent transformableComponent = new TransformableComponent(asteroidComponent.getCircle());

        final OrientatedBoxComponent orientatedBoxComponent = new OrientatedBoxComponent(asteroidComponent.getCircle());

        final ScoreComponent scoreComponent = new ScoreComponent(ASTEROID_SCORE);

        return World.createEntity(asteroidComponent, speedComponent, transformableComponent, orientatedBoxComponent, scoreComponent);
    }
}
