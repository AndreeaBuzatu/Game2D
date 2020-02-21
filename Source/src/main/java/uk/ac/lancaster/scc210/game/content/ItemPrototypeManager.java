package uk.ac.lancaster.scc210.game.content;

import uk.ac.lancaster.scc210.engine.content.ContentManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.game.items.LinePatternEffect;
import uk.ac.lancaster.scc210.game.items.LivesIncreaseEffect;
import uk.ac.lancaster.scc210.game.items.SpeedIncreaseEffect;
import uk.ac.lancaster.scc210.game.items.StarPatternEffect;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;
import uk.ac.lancaster.scc210.game.prototypes.ItemPrototype;

public class ItemPrototypeManager extends ContentManager<ItemPrototype> {
    public ItemPrototypeManager(TextureManager textureManager, BulletPool bulletPool) {
        super(null);

        put("test-item", new ItemPrototype(textureManager.get("spritesheet.png:item"), new SpeedIncreaseEffect()));

        put("other-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new LivesIncreaseEffect()));

        put("starPattern-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new StarPatternEffect(bulletPool)));

        put("linePattern-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new LinePatternEffect(bulletPool)));
    }
}
