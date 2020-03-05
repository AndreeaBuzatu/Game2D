package uk.ac.lancaster.scc210.game.prototypes;

import org.jsfml.graphics.CircleShape;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.ShaderManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.prototypes.Prototype;
import uk.ac.lancaster.scc210.game.ecs.component.*;
import uk.ac.lancaster.scc210.game.states.Playing;

/**
 * The type Asteroid prototype.
 */
public class AsteroidPrototype implements Prototype {
    private final int ASTEROID_SCORE = 10;

    private final TextureManager textureManager;

    private final ShaderManager shaderManager;

    /**
     * Instantiates a new Asteroid prototype.
     *
     * @param textureManager the texture manager
     * @param shaderManager  the shader manager
     */
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

    public static void positionAsteroid(ViewSize viewSize, AsteroidComponent asteroidComponent , Vector2f pos){

        CircleShape circleShape = asteroidComponent.getCircle();

        Vector2i size = circleShape.getTexture().getSize();

        float posX = pos.x;
        float posY = pos.y;

        if(pos.x < size.x){

            posX =  size.x;
            circleShape.setPosition(new Vector2f(posX , posY));

        }

        if(pos.x > (viewSize.getViewBounds().width - size.x) ){
            posX = viewSize.getViewBounds().width - size.x;
            circleShape.setPosition(new Vector2f(posX,posY));
        }

        if(pos.y > viewSize.getViewBounds().height - size.y){
            posY = viewSize.getViewBounds().height - size.y;
            circleShape.setPosition(new Vector2f(posX, posY));
        }


        if(pos.y < Playing.INFO_BOX_HEIGHT){
            posY = Playing.INFO_BOX_HEIGHT ;
            circleShape.setPosition(new Vector2f(posX, posY + size.y));

        }
        else {
            circleShape.setPosition(new Vector2f(posX, posY));
        }

    }
}
