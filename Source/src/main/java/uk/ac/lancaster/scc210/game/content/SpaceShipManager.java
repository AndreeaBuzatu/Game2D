package uk.ac.lancaster.scc210.game.content;

import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.content.ContentManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.resources.SerialisedSpaceShip;

import java.util.List;

public class SpaceShipManager extends ContentManager<Entity> {
    public SpaceShipManager(TextureManager textureManager, List<SerialisedSpaceShip> serialisedSpaceShips) {
        super(new Entity());

        for (SerialisedSpaceShip spaceShip : serialisedSpaceShips) {
            SpriteComponent spriteComponent = new SpriteComponent(new Sprite(textureManager.get(spaceShip.getTexture())));

            SpeedComponent speedComponent = new SpeedComponent(spaceShip.getSpeed());

            put(spaceShip.getName(), World.createEntity(spriteComponent, speedComponent));
        }
    }
}
