package uk.ac.lancaster.scc210.game.content;

import uk.ac.lancaster.scc210.engine.content.ContentManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.game.prototypes.ItemPrototype;

public class ItemPrototypeManager extends ContentManager<ItemPrototype> {
    public ItemPrototypeManager(TextureManager textureManager) {
        super(null);

        put("test-item", new ItemPrototype(textureManager.get("spritesheet.png:example")));
        put("test-item", new ItemPrototype(textureManager.get("spritesheet.png:other")));
    }
}
