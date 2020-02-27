package uk.ac.lancaster.scc210.game.content;

import uk.ac.lancaster.scc210.engine.content.ContentManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.game.items.DegradingLivesBulletEffectItem;
import uk.ac.lancaster.scc210.game.items.FreezeBulletEffectItem;
import uk.ac.lancaster.scc210.game.items.MissingItemEffect;
import uk.ac.lancaster.scc210.game.items.SpeedIncreaseEffect;
import uk.ac.lancaster.scc210.game.items.damage.Damage1EffectItem;
import uk.ac.lancaster.scc210.game.items.damage.Damage2EffectItem;
import uk.ac.lancaster.scc210.game.items.health.Health1Effect;
import uk.ac.lancaster.scc210.game.items.health.Health2Effect;
import uk.ac.lancaster.scc210.game.items.patterns.LinePatternEffect;
import uk.ac.lancaster.scc210.game.items.patterns.MorningStarPatternEffect;
import uk.ac.lancaster.scc210.game.items.patterns.StarPatternEffect;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;
import uk.ac.lancaster.scc210.game.prototypes.ItemPrototype;

public class ItemPrototypeManager extends ContentManager<ItemPrototype> {
    public ItemPrototypeManager(TextureManager textureManager, BulletPool bulletPool) {
        super(new ItemPrototype(textureManager.get(""), new MissingItemEffect()));

        put("test-item", new ItemPrototype(textureManager.get("spritesheet.png:item"), new SpeedIncreaseEffect()));

        put("starPattern-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new StarPatternEffect(bulletPool)));

        put("linePattern-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new LinePatternEffect(bulletPool)));

        put("morningStarPattern-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new MorningStarPatternEffect(bulletPool)));

        put("freezebulleteffect-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new FreezeBulletEffectItem()));

        put("damage1effect-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new Damage1EffectItem()));

        put("damage2effect-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new Damage2EffectItem()));

        put("degradinglivesbulleteffect-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new DegradingLivesBulletEffectItem()));

        put("health1effect-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new Health1Effect()));

        put("health2effect-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new Health2Effect()));
    }
}
