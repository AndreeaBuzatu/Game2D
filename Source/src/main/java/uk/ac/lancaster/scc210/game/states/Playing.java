package uk.ac.lancaster.scc210.game.states;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.content.MusicManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.LevelManager;
import uk.ac.lancaster.scc210.game.content.SpaceShipPrototypeManager;
import uk.ac.lancaster.scc210.game.ecs.component.LivesComponent;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.ecs.system.*;
import uk.ac.lancaster.scc210.game.level.Level;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;

import java.util.Iterator;

/**
 * Represents the actual game-play state.
 */
public class Playing implements State {
    private static final int TEXT_SIZE = 70;

    public static final int INFO_BOX_HEIGHT = TEXT_SIZE + 5;

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

        world.addSystem(new ItemUpdateSystem(world));

        world.addSystem(new SpaceShipCollisionSystem(world));

        world.addSystem(new EnemyFiringSystem(world));

        world.addSystem(new ScoreSystem(world));

        world.addSystem(levelSystem);

        world.addSystem(new LivesSystem(world));

        SpaceShipPrototypeManager spaceShipManager = (SpaceShipPrototypeManager) game.getServiceProvider().get(SpaceShipPrototypeManager.class);

        viewSize = (ViewSize) world.getServiceProvider().get(ViewSize.class);

        FloatRect viewBounds = viewSize.getViewBounds();

        player = spaceShipManager.get("player").create();

        PlayerComponent playerComponent = new PlayerComponent();

        player.addComponent(playerComponent);

        SpriteComponent spriteComponent = (SpriteComponent) player.findComponent(SpriteComponent.class);

        Sprite playerSprite = spriteComponent.getSprite();

        playerComponent.setSpawnPoint(new Vector2f((viewBounds.width / 2) - playerSprite.getGlobalBounds().width, (viewBounds.height / 1.5f) - playerSprite.getGlobalBounds().height));

        playerSprite.setPosition(playerComponent.getSpawnPoint());

        world.addEntity(player);

        MusicManager musicManager = (MusicManager) world.getServiceProvider().get(MusicManager.class);

        example = musicManager.get("example");

        example.setVolume(100);

        example.setLoop(true);

        example.play();

        font = ((FontManager) world.getServiceProvider().get(FontManager.class)).get("font");

        scoreText = new Text();

        scoreText.setFont(font);

        scoreText.setColor(Color.WHITE);

        scoreText.setCharacterSize(TEXT_SIZE);

        livesText = new Text();

        livesText.setFont(font);

        livesText.setColor(Color.WHITE);

        livesText.setCharacterSize(TEXT_SIZE);
    }

    @Override
    public void draw(RenderTarget target) {
        world.draw(target);

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

    @Override
    public boolean complete() {
        return completed;
    }

    @Override
    public void update(Time deltaTime) {
        world.update(deltaTime);

        if (level.complete()) {
            System.out.println("Complete level");

            // Reset the player's current item effects back to the game default
            PlayerComponent playerComponent = (PlayerComponent) player.findComponent(PlayerComponent.class);

            playerComponent.getCurrentEffects().parallelStream().forEach(itemEffect -> itemEffect.reset(player));

            if (levelIterator.hasNext()) {
                level = levelIterator.next();

                levelSystem.setLevel(level);

            } else {
                completed = true;
            }
        }
    }
}
