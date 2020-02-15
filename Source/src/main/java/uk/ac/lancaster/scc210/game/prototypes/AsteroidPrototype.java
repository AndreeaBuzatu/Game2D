package uk.ac.lancaster.scc210.game.prototypes;

import uk.ac.lancaster.scc210.engine.content.ShaderManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.prototypes.Prototype;
import uk.ac.lancaster.scc210.game.ecs.component.*;

public class AsteroidPrototype implements Prototype {
    private final int ASTEROID_SCORE = 10;

    private final TextureManager textureManager;

    private final ShaderManager shaderManager;

    public AsteroidPrototype(TextureManager textureManager, ShaderManager shaderManager) {
        this.textureManager = textureManager;
        this.shaderManager = shaderManager;
    }

    @Override
    public Entity create() {
        final AsteroidComponent asteroidComponent = new AsteroidComponent(textureManager.get("enemy-spritesheet.png:asteroid"));

        final SpeedComponent speedComponent = new SpeedComponent(5);

        final TransformableComponent transformableComponent = new TransformableComponent(asteroidComponent.getCircle());

        final OrientatedBoxComponent orientatedBoxComponent = new OrientatedBoxComponent(asteroidComponent.getCircle());

        final ScoreComponent scoreComponent = new ScoreComponent(ASTEROID_SCORE);

        final LivesComponent livesComponent = new LivesComponent(3);

        final FlashComponent flashComponent = new FlashComponent(asteroidComponent.getCircle(), shaderManager.get("flash"));

        return World.createEntity(asteroidComponent, speedComponent, transformableComponent, orientatedBoxComponent, scoreComponent, livesComponent, flashComponent);
    }
}
