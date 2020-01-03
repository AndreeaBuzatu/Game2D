package uk.ac.lancaster.scc210.states;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.Game;
import uk.ac.lancaster.scc210.ecs.World;
import uk.ac.lancaster.scc210.ecs.system.MovementSystem;
import uk.ac.lancaster.scc210.ecs.system.RenderSystem;

public class Level implements State {
    private Sprite[] sprites;

    private World world;

    //private Entity player;

    @Override
    public void setup(Game game) {
        //sprites = new Sprite[7];

        //Properties playerProperties = game.getPropertyManager().get("thing");

        //player = new Entity();

        /*
        if (playerProperties.containsKey("texture")) {
            player.addComponent(new SpriteComponent(new Sprite(game.getTextureManager().get(playerProperties.getProperty("texture")))));
        }

        if (playerProperties.containsKey("speed")) {
            player.addComponent(new SpeedComponent(Integer.parseInt(playerProperties.getProperty("speed"))));
        }
         */

        //player.addComponent(new SpriteComponent(new Sprite(game.getTextureManager().get("example"))));

        world = new World();

        world.addSystem(new RenderSystem(world));

        world.addSystem(new MovementSystem(world));

        world.addEntity(game.getSpaceShipManager().get("player"));

        world.addEntity(game.getSpaceShipManager().get("other"));

        //world.addEntity(player);

        /*
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Sprite(game.getTextureManager().get("example"));

            sprites[i].setPosition(i * 66, sprites[i].getPosition().y);
        }
         */
    }

    @Override
    public void draw(RenderTarget target) {
        /*
        for (Sprite sprite : sprites) {
            target.draw(sprite);
        }
         */

        world.draw(target);
    }

    @Override
    public void update() {
        world.update();
    }
}
