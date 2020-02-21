package uk.ac.lancaster.scc210.game.ecs.component;

import org.jsfml.audio.Sound;
import uk.ac.lancaster.scc210.engine.content.SoundBufferManager;
import uk.ac.lancaster.scc210.engine.ecs.Component;

public class SpaceShipComponent implements Component {
    private final String[] items;

    private final String bulletName;

    private final Sound firingSound, hitSound;

    public SpaceShipComponent(String[] items, Sound firingSound, Sound hitSound,String bulletName) {
        this.items = items;
        this.firingSound = firingSound;
        this.hitSound = hitSound;
        this.bulletName = bulletName;
    }

    public String getBulletName() {
        return bulletName;
    }

    public String[] getItems() {
        return items;
    }

    public void playFiringSound() {
        SoundBufferManager.playSound(firingSound);
    }

    public void playHitSound() {
        SoundBufferManager.playSound(hitSound);
    }

}
