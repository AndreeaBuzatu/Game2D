package uk.ac.lancaster.scc210.game.content;

import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.content.ContentManager;
import uk.ac.lancaster.scc210.engine.content.TextureAnimationManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.game.prototypes.ItemPrototype;
import uk.ac.lancaster.scc210.game.prototypes.SpaceShipPrototype;
import uk.ac.lancaster.scc210.game.resources.SerialisedSpaceShip;

import java.util.List;

public class ItemPrototypeManager extends ContentManager<ItemPrototype> {
    public ItemPrototypeManager(TextureManager textureManager) {
        super(null);

        put("test-item", new ItemPrototype(textureManager.get("spritesheet.png:example")));
        put("test-item", new ItemPrototype(textureManager.get("spritesheet.png:other")));
    }
}
