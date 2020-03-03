package uk.ac.lancaster.scc210.game.states;

import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.TextEvent;
import uk.ac.lancaster.scc210.engine.InputListener;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.content.MusicManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.states.State;
import uk.ac.lancaster.scc210.game.content.LevelManager;
import uk.ac.lancaster.scc210.game.content.SpaceShipPrototypeManager;
import uk.ac.lancaster.scc210.game.content.StateManager;
import uk.ac.lancaster.scc210.game.dialogue.DialogueBox;
import uk.ac.lancaster.scc210.game.ecs.component.*;
import uk.ac.lancaster.scc210.game.ecs.entity.Player;
import uk.ac.lancaster.scc210.game.ecs.system.*;
import uk.ac.lancaster.scc210.game.level.Level;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;
import uk.ac.lancaster.scc210.game.resources.PlayerData;
import uk.ac.lancaster.scc210.game.resources.PlayerWriter;

import java.util.List;
import java.util.Set;

/**
 * Represents the actual game-play state.
 */
public class Playing implements State, InputListener {
    private static final int TEXT_SIZE = 70;

    public static final int INFO_BOX_HEIGHT = TEXT_SIZE + 5;

    private final int ALPHA_CHANGE = 4;

    private final int MAX_OPACITY = 255;

    private List<Level> unlockedLevels, totalLevels;

    private StateBasedGame game;

    private World world;

    private LevelManager levelManager;

    private StateManager stateManager;

    private LevelSystem levelSystem;

    private PlayerData playerData;

    private PlayerWriter playerScoreWriter;

    private Level level;

    private Entity player;

    private Music music;

    private Font font;

    private Text scoreText, livesText;

    private ViewSize viewSize;

    private DialogueBox dialogueBox;

    private boolean paused, fadedIn, shouldFadeIn, shouldFadeOut;

    private int alpha, currentUnlocked;

    @Override
    public void setup(StateBasedGame game) {
        this.game = game;

        levelManager = (LevelManager) game.getServiceProvider().get(LevelManager.class);

        stateManager = (StateManager) game.getServiceProvider().get(StateManager.class);

        playerData = (PlayerData) game.getServiceProvider().get(PlayerData.class);

        viewSize = (ViewSize) game.getServiceProvider().get(ViewSize.class);

        String unlockedLevel = playerData.getUnlockedLevel();

        currentUnlocked = levelManager.indexOf(unlockedLevel);

        // If the level can't be found, default to the first level
        if (currentUnlocked < 0) {
            unlockedLevel = levelManager.getLevelList().get(0).getName();

            currentUnlocked = levelManager.indexOf(unlockedLevel);
        }

        totalLevels = levelManager.getLevelList();

        unlockedLevels = levelManager.getUnlocked(unlockedLevel);

        level = unlockedLevels.get(currentUnlocked);

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

        world.addSystem(new BulletCollisionSystem(world));

        world.addSystem(new ItemDropSystem(world));

        world.addSystem(new ItemCollisionSystem(world));

        world.addSystem(new PlayerEffectsUpdateSystem(world));

        world.addSystem(new EnemyCollisionSystem(world));

        world.addSystem(new EnemyFiringSystem(world));

        world.addSystem(new ScoreSystem(world));

        world.addSystem(levelSystem);

        world.addSystem(new LivesSystem(world));

        player = new Player(game.getServiceProvider()).getPlayer();

        playerScoreWriter = (PlayerWriter) game.getServiceProvider().get(PlayerWriter.class);

        world.addEntity(player);

        MusicManager musicManager = (MusicManager) world.getServiceProvider().get(MusicManager.class);

        music = musicManager.get("battle_music");

        music.setVolume(100);

        music.setLoop(true);

        FontManager fontManager = (FontManager) world.getServiceProvider().get(FontManager.class);

        font = fontManager.get("font");

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

        game.addKeyListener(dialogueBox);

        paused = false;

        fadedIn = false;

        shouldFadeOut = false;

        // We want to initially fade in the first level
        shouldFadeIn = true;

        alpha = 0;
    }

    @Override
    public void onEnter(StateBasedGame game) {
        game.addKeyListener(dialogueBox);

        game.addKeyListener(this);

        music.play();

        paused = false;
    }

    @Override
    public void onExit(StateBasedGame game) {
        game.removeKeyListener(dialogueBox);

        game.removeKeyListener(this);

        if (!paused) {
            level.reset();

            music.pause();
        }
    }

    @Override
    public void keyPressed(KeyEvent keyevent) {
        if (keyevent.key == Keyboard.Key.ESCAPE) {
            paused = true;
        }
    }

    @Override
    public void keyTyped(TextEvent textevent) {
    }

    @Override
    public void draw(RenderTarget target) {
        world.draw(target);

        handleLevelTransition();

        drawInterface(target);

        if (dialogueBox.isOpen()) {
            target.draw(dialogueBox);

        } else {
            shouldFadeIn = true;

            shouldFadeOut = false;

            game.removeKeyListener(dialogueBox);
        }
    }

    private void handleLevelTransition() {
        if (shouldFadeIn) {
            fadeIn();
        }

        if (shouldFadeOut) {
            fadeOut();
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

    @Override
    public void update(Time deltaTime) {
        if (paused) {
            game.pushState(stateManager.get("pause"));

            return;
        }

        if (dialogueBox.isOpen()) {
            dialogueBox.update(deltaTime);

        } else if (!dialogueBox.isOpen() && !fadedIn) {
            PlayerComponent playerComponent = (PlayerComponent) player.findComponent(PlayerComponent.class);

            SpriteComponent playerSpriteComponent = (SpriteComponent) player.findComponent(SpriteComponent.class);

            Sprite playerSprite = playerSpriteComponent.getSprite();

            // Reset the players position and rotation when the player goes to a new level (and has faded in)
            playerSprite.setPosition(playerComponent.getSpawnPoint());

            playerSprite.setRotation(0);

            // Remove bullets from the world
            world.removeIf(entity -> entity.hasComponent(BulletComponent.class) || entity.hasComponent(FiredComponent.class) ||
                    entity.hasComponent(EnemyComponent.class) || entity.hasComponent(ItemEffectsComponent.class));

            // Set and respawn the level once it has been cleared
            levelSystem.setLevel(level);

        } else if (fadedIn) {
            updateWorld(deltaTime);
        }
    }

    private void updateWorld(Time deltaTime) {
        if (level.complete()) {
            System.out.println("Complete level");

            // Reset the player's current item effects back to the game default
            PlayerComponent playerComponent = (PlayerComponent) player.findComponent(PlayerComponent.class);

            LivesComponent livesComponent = (LivesComponent) player.findComponent(LivesComponent.class);

            playerComponent.getCurrentItemEffects().parallelStream().forEach(itemEffect -> itemEffect.reset(player));

            if (currentUnlocked < totalLevels.size() - 1) {
                currentUnlocked++;

                unlockedLevels = levelManager.getUnlocked(totalLevels.get(currentUnlocked).getName());

                level = unlockedLevels.get(currentUnlocked);

                // Write the player data once they have completed a level.
                playerData.setUnlockedLevel(level.getName());

                playerData.setScore(playerComponent.getScore());

                playerData.setLives(livesComponent.getLives());

                playerScoreWriter.writePlayerLevel(playerData);

                dialogueBox.setDialogue(level.getLines());

                shouldFadeIn = false;

                shouldFadeOut = true;

            } else {
                Completion completionState = (Completion) stateManager.get("completion");

                completionState.setPlayerScore(playerComponent.getScore());

                //playerComponent.setScore(0);

                //livesComponent.setLives(livesComponent.getStartingLives());

                game.pushState(completionState);
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

            alpha = MAX_OPACITY;
        }

        alpha += ALPHA_CHANGE;
    }

    private void fadeOut() {
        Color currentColour = new Color(MAX_OPACITY, MAX_OPACITY, MAX_OPACITY, alpha);

        setSpritesFillColour(world.getEntitiesFor(SpriteComponent.class), currentColour);

        setAsteroidsFillColour(world.getEntitiesFor(AsteroidComponent.class), currentColour);

        if (alpha <= 0) {
            fadedIn = false;

            alpha = 0;
        }

        alpha -= ALPHA_CHANGE;
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

    public void setLevel(Level level) {
        this.level = level;

        levelSystem.setLevel(level);

        currentUnlocked = levelManager.indexOf(level.getName());

        dialogueBox.setDialogue(level.getLines());
    }
}
