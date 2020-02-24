package uk.ac.lancaster.scc210.game.content;

import uk.ac.lancaster.scc210.engine.content.ContentManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.game.items.FreezeBulletEffectItem;
import uk.ac.lancaster.scc210.game.items.LivesIncreaseEffect;
import uk.ac.lancaster.scc210.game.items.MissingItemEffect;
import uk.ac.lancaster.scc210.game.items.SpeedIncreaseEffect;
import uk.ac.lancaster.scc210.game.items.patterns.LinePatternEffect;
import uk.ac.lancaster.scc210.game.items.patterns.MorningStarPatternEffect;
import uk.ac.lancaster.scc210.game.items.patterns.StarPatternEffect;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;
import uk.ac.lancaster.scc210.game.prototypes.ItemPrototype;

public class ItemPrototypeManager extends ContentManager<ItemPrototype> {
    public ItemPrototypeManager(TextureManager textureManager, BulletPool bulletPool) {
        super(new ItemPrototype(textureManager.get(""), new MissingItemEffect()));

        put("test-item", new ItemPrototype(textureManager.get("spritesheet.png:item"), new SpeedIncreaseEffect()));

        put("other-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new LivesIncreaseEffect()));

        put("starPattern-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new StarPatternEffect(bulletPool)));

        put("linePattern-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new LinePatternEffect(bulletPool)));

        put("morningStarPattern-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new MorningStarPatternEffect(bulletPool)));

        put("freezebulleteffect-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new FreezeBulletEffectItem()));
    }
}
