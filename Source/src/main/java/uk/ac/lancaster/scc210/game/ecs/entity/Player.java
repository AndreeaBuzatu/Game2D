package uk.ac.lancaster.scc210.game.ecs.entity;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.service.ServiceProvider;
import uk.ac.lancaster.scc210.game.content.SpaceShipPrototypeManager;
import uk.ac.lancaster.scc210.game.ecs.component.LivesComponent;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.resources.PlayerData;

/**
 * The type Player.
 */
public class Player {
    private final int DEFAULT_LIVES = 3;

    private final Entity player;

    /**
     * Instantiates a new Player.
     *
     * @param serviceProvider the service provider
     */
    public Player(ServiceProvider serviceProvider) {
        SpaceShipPrototypeManager spaceShipManager = (SpaceShipPrototypeManager) serviceProvider.get(SpaceShipPrototypeManager.class);

        ViewSize viewSize = (ViewSize) serviceProvider.get(ViewSize.class);

        FloatRect viewBounds = viewSize.getViewBounds();

        PlayerData playerData = (PlayerData) serviceProvider.get(PlayerData.class);

        player = spaceShipManager.get("player").create();

        LivesComponent livesComponent = (LivesComponent) player.findComponent(LivesComponent.class);

        // If the player data has an impossible number of lives, just set an assumed default
        if (livesComponent.getLives() <= 0) {
            livesComponent.setLives(DEFAULT_LIVES);

            livesComponent.setStartingLives(DEFAULT_LIVES);
        }

        livesComponent.setLives(playerData.getLives());

        PlayerComponent playerComponent = new PlayerComponent();

        playerComponent.setScore(playerData.getScore());

        player.addComponent(playerComponent);

        SpriteComponent spriteComponent = (SpriteComponent) player.findComponent(SpriteComponent.class);

        Sprite playerSprite = spriteComponent.getSprite();

        playerComponent.setSpawnPoint(new Vector2f((viewBounds.width / 2) - playerSprite.getGlobalBounds().width, (viewBounds.height / 1.5f) - playerSprite.getGlobalBounds().height));

        playerSprite.setPosition(playerComponent.getSpawnPoint());
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Entity getPlayer() {
        return player;
    }
}
