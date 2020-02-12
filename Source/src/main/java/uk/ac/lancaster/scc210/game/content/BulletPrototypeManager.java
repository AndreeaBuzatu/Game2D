package uk.ac.lancaster.scc210.game.content;

import uk.ac.lancaster.scc210.engine.content.ContentManager;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.game.prototypes.BulletPrototype;
import uk.ac.lancaster.scc210.game.resources.SerialisedBullet;

import java.util.List;

public class BulletPrototypeManager extends ContentManager<BulletPrototype> {
    /**
     * Instantiates a new Bullet Prototype Manager.
     *
     * @param textureManager    the TextureManager to source the textures from bullets.xml
     * @param serialisedBullets the bullets from bullets.xml
     */
    public BulletPrototypeManager(TextureManager textureManager, List<SerialisedBullet> serialisedBullets) {
        super(new BulletPrototype(textureManager, "", 5));

        for (SerialisedBullet serialisedBullet : serialisedBullets) {
            BulletPrototype bullet = new BulletPrototype(textureManager, serialisedBullet.getTexture(), serialisedBullet.getSpeed());

            put(serialisedBullet.getName(), bullet);
        }
    }
}
