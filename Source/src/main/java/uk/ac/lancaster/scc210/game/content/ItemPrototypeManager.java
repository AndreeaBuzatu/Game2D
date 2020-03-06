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

/**
 * The type Item prototype manager.
 */
public class ItemPrototypeManager extends ContentManager<ItemPrototype> {
    /**
     * Instantiates a new Item prototype manager.
     *
     * @param textureManager the texture manager
     * @param bulletPool     the bullet pool
     */
    public ItemPrototypeManager(TextureManager textureManager, BulletPool bulletPool) {
        super(new ItemPrototype(textureManager.get(""), new MissingItemEffect()));

        put("speed-item", new ItemPrototype(textureManager.get("speed_spritesheet_final.png:last"), new SpeedIncreaseEffect()));

        put("starPattern-item", new ItemPrototype(textureManager.get("spritesheet.png:other"), new StarPatternEffect(bulletPool)));

        put("linePattern-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new LinePatternEffect(bulletPool)));

        put("morningStarPattern-item", new ItemPrototype(textureManager.get("spritesheet.png:item"), new MorningStarPatternEffect(bulletPool)));

        put("freezebulleteffect-item", new ItemPrototype(textureManager.get("spritesheet.png:other"), new FreezeBulletEffectItem()));

        put("damage1effect-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new Damage1EffectItem()));

        put("damage2effect-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new Damage2EffectItem()));

        put("degradinglivesbulleteffect-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new DegradingLivesBulletEffectItem()));

        put("health1effect-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new Health1Effect()));

        put("health2effect-item", new ItemPrototype(textureManager.get("spritesheet.png:example"), new Health2Effect()));
    }
}
