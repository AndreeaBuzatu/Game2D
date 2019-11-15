package uk.ac.lancaster.game.screens;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;
import uk.ac.lancaster.game.Assets;
import uk.ac.lancaster.game.entites.Enemy;
import uk.ac.lancaster.game.entites.Entity;
import uk.ac.lancaster.game.entites.Player;
import uk.ac.lancaster.game.entites.SpaceShip;
import uk.ac.lancaster.game.screens.mainmenu.MainMenu;

import java.util.ArrayList;

public class Level extends Screen {
    private ArrayList<Entity> entities;

    private MainMenu mainMenu;

    private Player player;

    private Text livesText;

    private boolean gameOver;

    public Level(MainMenu mainMenu) {
        super();

        this.mainMenu = mainMenu;

        entities = new ArrayList<>();

        player = new Player();

        entities.add(player);

        entities.add(new Enemy(new Vector2f(300, 300)));

        livesText = new Text(String.format("Lives: %d", player.getLives()), Assets.loadFont("courier.ttf"));

        gameOver = false;
    }

    @Override
    public void update() {
        for (Entity entity : entities) {
            entity.update();

            if (entity instanceof SpaceShip) {
                ((SpaceShip) entity).handleCollision(entities);
            }
        }

        entities.removeIf(entity -> !entity.isAlive());

        updateLivesText();

        if (!player.isAlive()) {
            gameOver = true;

            backToMainMenu();
        }
    }

    @Override
    public void handleEvents(Event event) {
        if (event.type == Event.Type.KEY_PRESSED) {
            if (event.asKeyEvent().key == Keyboard.Key.ESCAPE) {
                backToMainMenu();
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {
        target.draw(livesText);

        entities.forEach(entity -> entity.draw(target));
    }

    @Override
    public Screen nextScreen() {
        return mainMenu;
    }

    private void updateLivesText() {
        livesText.setString(String.format("Lives: %d", player.getLives()));
    }

    private void backToMainMenu() {
        active = false;

        mainMenu.active = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
