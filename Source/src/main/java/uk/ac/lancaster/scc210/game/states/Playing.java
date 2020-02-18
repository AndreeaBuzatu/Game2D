package uk.ac.lancaster.scc210.game.states;

import org.jsfml.audio.Music;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.content.MusicManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.LevelManager;
import uk.ac.lancaster.scc210.game.content.SpaceShipPrototypeManager;
import uk.ac.lancaster.scc210.game.dialogue.DialogueBox;
import uk.ac.lancaster.scc210.game.ecs.component.*;
import uk.ac.lancaster.scc210.game.ecs.system.*;
import uk.ac.lancaster.scc210.game.level.Level;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;

import java.util.Iterator;
import java.util.Set;

/**
 * Represents the actual game-play state.
 */
public class Playing implements State {
    private static final int TEXT_SIZE = 70;

    public static final int INFO_BOX_HEIGHT = TEXT_SIZE + 5;

    private final int MAX_OPACITY = 255;

    private Iterator<Level> levelIterator;

    private World world;

    private Level level;

    private LevelSystem levelSystem;

    private Entity player;

    private boolean completed;

    private Music example;

    private Font font;

    private Text scoreText, livesText;

    private ViewSize viewSize;

    private DialogueBox dialogueBox;

    private boolean fadedIn, fadedOut, shouldFadeOut;

    private int alpha;

    @Override
    public void setup(StateBasedGame game) {
        completed = false;

        LevelManager levelManager = (LevelManager) game.getServiceProvider().get(LevelManager.class);

        levelIterator = levelManager.getIterator();

        level = levelIterator.next();

        world = new World(game.getServiceProvider());

        levelSystem = new LevelSystem(world, level);

        world.addPool((BulletPool) game.getServiceProvider().get(BulletPool.class));

        world.addSystem(new AnimationUpdateSystem(world));

        world.addSystem(new PlayerMovementSystem(world));

        world.addSystem(new RenderSystem(world));

        world.addSystem(new PlayerFiringSystem(world));

        world.addSystem(new MovementSystem(world));

        world.addSystem(new ViewBoundsSystem(world));

        world.addSystem(new AsteroidSystem(world));

        world.addSystem(new AsteroidCollisionSystem(world));

        world.addSystem(new BulletCollisionSystem(world));

        world.addSystem(new ItemDropSystem(world));

        world.addSystem(new ItemCollisionSystem(world));

        world.addSystem(new SpaceShipCollisionSystem(world));

        world.addSystem(new EnemyFiringSystem(world));

        world.addSystem(new ScoreSystem(world));

        world.addSystem(levelSystem);

        world.addSystem(new LivesSystem(world));

        SpaceShipPrototypeManager spaceShipManager = (SpaceShipPrototypeManager) game.getServiceProvider().get(SpaceShipPrototypeManager.class);

        player = spaceShipManager.get("player").create();

        player.addComponent(new PlayerComponent());

        ((SpriteComponent) player.findComponent(SpriteComponent.class)).getSprite().setPosition(400, 200);

        world.addEntity(player);

        MusicManager musicManager = (MusicManager) world.getServiceProvider().get(MusicManager.class);

        example = musicManager.get("example");

        example.setVolume(100);

        example.setLoop(true);

        example.play();

        FontManager fontManager = (FontManager) world.getServiceProvider().get(FontManager.class);

        font = fontManager.get("font");

        viewSize = (ViewSize) world.getServiceProvider().get(ViewSize.class);

        scoreText = new Text();

        scoreText.setFont(font);

        scoreText.setColor(Color.WHITE);

        scoreText.setCharacterSize(TEXT_SIZE);

        livesText = new Text();

        livesText.setFont(font);

        livesText.setColor(Color.WHITE);

        livesText.setCharacterSize(TEXT_SIZE);

        dialogueBox = new DialogueBox(viewSize, fontManager);

        dialogueBox.setDialogue(level.getLines());

        fadedIn = false;

        fadedOut = false;

        shouldFadeOut = false;

        alpha = 0;
    }

    @Override
    public void draw(RenderTarget target) {
        world.draw(target);

        drawInterface(target);

        if (dialogueBox.isOpen()) {
            if (!fadedIn) {
                fadeIn();
            }

            // TODO: Fix drawing old level after the level has been complete (look at the player-death branch)
            target.draw(dialogueBox);

            if (shouldFadeOut && !fadedOut) {
                fadeOut();

            } else {
                shouldFadeOut = false;
            }
        }
    }

    private void drawInterface(RenderTarget target) {
        if (!dialogueBox.isOpen()) {
            if (player.hasComponent(PlayerComponent.class)) {
                PlayerComponent playerComponent = (PlayerComponent) player.findComponent(PlayerComponent.class);

                scoreText.setString(String.format("Score: %d", playerComponent.getScore()));
            }

            if (player.hasComponent(LivesComponent.class)) {
                LivesComponent livesComponent = (LivesComponent) player.findComponent(LivesComponent.class);

                livesText.setString(String.format("Lives: %d\n", livesComponent.getLives()));

                // Position the text at the left-most edge of the view
                livesText.setPosition(viewSize.getViewBounds().width - livesText.getGlobalBounds().width, 0);
            }

            target.draw(scoreText);

            target.draw(livesText);
        }
    }

    public boolean complete() {
        return completed;
    }

    @Override
    public void update(Time deltaTime) {
        if (dialogueBox.isOpen()) {
            dialogueBox.update(deltaTime);

        } else {
            updateWorld(deltaTime);
        }
    }

    private void updateWorld(Time deltaTime) {
        if (level.complete()) {
            System.out.println("Complete level");

            // Reset the player's current item effects back to the game default
            PlayerComponent playerComponent = (PlayerComponent) player.findComponent(PlayerComponent.class);

            playerComponent.getCurrentEffects().parallelStream().forEach(itemEffect -> itemEffect.reset(player));

            if (levelIterator.hasNext()) {
                level = levelIterator.next();

                // Remove bullets from the world
                world.removeIf(entity -> entity.hasComponent(BulletComponent.class) || entity.hasComponent(EnemyComponent.class));

                levelSystem.setLevel(level);

                dialogueBox.setDialogue(level.getLines());

                shouldFadeOut = true;

            } else {
                completed = true;
            }
        }

        if (!level.complete()) {
            world.update(deltaTime);
        }
    }

    private void fadeIn() {
        Color currentColour = new Color(MAX_OPACITY, MAX_OPACITY, MAX_OPACITY, alpha);

        setSpritesFillColour(world.getEntitiesFor(SpriteComponent.class), currentColour);

        setAsteroidsFillColour(world.getEntitiesFor(AsteroidComponent.class), currentColour);

        if (alpha >= MAX_OPACITY) {
            fadedIn = true;

            fadedOut = false;
        }

        alpha++;
    }

    private void fadeOut() {
        Color currentColour = new Color(MAX_OPACITY, MAX_OPACITY, MAX_OPACITY, alpha);

        setSpritesFillColour(world.getEntitiesFor(SpriteComponent.class), currentColour);

        setAsteroidsFillColour(world.getEntitiesFor(AsteroidComponent.class), currentColour);

        if (alpha <= 0) {
            fadedIn = false;

            fadedOut = true;
        }

        alpha--;
    }

    private void setSpritesFillColour(Set<Entity> entities, Color colour) {
        for (Entity entity : entities) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            spriteComponent.getSprite().setColor(colour);
        }
    }

    private void setAsteroidsFillColour(Set<Entity> entities, Color colour) {
        for (Entity entity : entities) {
            AsteroidComponent asteroidComponent = (AsteroidComponent) entity.findComponent(AsteroidComponent.class);

            asteroidComponent.getCircle().setFillColor(colour);
        }
    }
}
