package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.*;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.*;
import uk.ac.lancaster.scc210.game.states.Playing;

import java.util.Collection;
import java.util.Random;

/**
 * System used to draw non-animated Sprites to the Screen
 */
public class RenderSystem extends IterativeSystem {
    private final Color[] STAR_COLOURS = {
            new Color(153, 204, 255),
            new Color(255, 255, 255),
            new Color(255, 102, 0),
            new Color(255, 0, 0)
    };

    private final VertexArray stars;

    private final Random random;

    private final ViewSize viewSize;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world to draw entities from
     */
    public RenderSystem(World world) {
        super(world);

        stars = new VertexArray(PrimitiveType.POINTS);

        random = new Random();

        viewSize = (ViewSize) world.getServiceProvider().get(ViewSize.class);

        entities = world.getEntitiesWithAny(SpriteComponent.class, AsteroidComponent.class);

        for (int i = 0; i < 50; i++) {
            Vertex star = new Vertex(randPosition(), STAR_COLOURS[randInt(0, STAR_COLOURS.length - 1)]);

            stars.add(star);
        }
    }

    @Override
    public void entityAdded(Entity entity) {
        entities = world.getEntitiesWithAny(SpriteComponent.class, AsteroidComponent.class);
    }

    @Override
    public void entitiesAdded(Collection<? extends Entity> entities) {
        this.entities = world.getEntitiesWithAny(SpriteComponent.class, AsteroidComponent.class);
    }

    @Override
    public void entityRemoved(Entity entity) {
        this.entities = world.getEntitiesWithAny(SpriteComponent.class, AsteroidComponent.class);
    }

    @Override
    public void update(Time deltaTime) {
        for (Entity entity : entities) {
            if (entity.hasComponent(FlashComponent.class)) {
                FlashComponent flashComponent = (FlashComponent) entity.findComponent(FlashComponent.class);

                flashComponent.updateRenderState(deltaTime);
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {
        target.draw(stars);

        for (Entity entity : entities) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            Drawable drawable = null;

            RenderStates renderStates = RenderStates.DEFAULT;

            if (entity.hasComponent(SpriteComponent.class)) {
                drawable = spriteComponent.getSprite();
            }

            if (entity.hasComponent(AsteroidComponent.class)) {
                drawable = ((AsteroidComponent) entity.findComponent(AsteroidComponent.class)).getCircle();
            }

            if (entity.hasComponent(FlashComponent.class)) {
                FlashComponent flashComponent = (FlashComponent) entity.findComponent(FlashComponent.class);

                if (entity.hasComponent(PlayerComponent.class)) {
                    LivesComponent livesComponent = (LivesComponent) entity.findComponent(LivesComponent.class);

                    // Flash the player red if they only have 1 remaining
                    if (livesComponent.getLives() == 1) {
                        flashComponent.setShaderRed();
                    }

                } else {
                    // If the previous shader was white, reset back to white
                    flashComponent.setShaderWhite();
                }

                renderStates = flashComponent.getCurrentState();
            }

            target.draw(drawable, renderStates);
        }
    }

    private Vector2f randPosition() {
        FloatRect viewBounds = viewSize.getViewBounds();

        return new Vector2f(randInt(0, (int) viewBounds.width), randInt(Playing.INFO_BOX_HEIGHT, (int) viewBounds.height));
    }

    private int randInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
