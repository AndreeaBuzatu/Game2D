package uk.ac.lancaster.scc210.game.ecs.component;

import org.jsfml.audio.Sound;
import uk.ac.lancaster.scc210.engine.content.SoundBufferManager;
import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.game.bullets.effects.BulletEffect;
import uk.ac.lancaster.scc210.game.bullets.effects.Damage1Effect;

public class SpaceShipComponent implements Component {
    private final String[] items;

    private final String bulletName;

    private final String firingSound, hitSound;

    private final BulletEffect bulletEffect;

    public SpaceShipComponent(String[] items, String firingSound, String hitSound, String bulletName) {
        this.items = items;
        this.firingSound = firingSound;
        this.hitSound = hitSound;
        this.bulletName = bulletName;
        this.bulletEffect = new Damage1Effect();
    }

    public String getBulletName() {
        return bulletName;
    }

    public String[] getItems() {
        return items;
    }

    public String getFiringSound() {
        return firingSound;
    }

    public String getHitSound() {
        return hitSound;
    }

    public BulletEffect getBulletEffect() {
        return bulletEffect;
    }
}
