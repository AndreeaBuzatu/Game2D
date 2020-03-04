package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.game.bullets.effects.BulletEffect;
import uk.ac.lancaster.scc210.game.bullets.effects.Damage1Effect;

/**
 * The type Space ship component.
 */
public class SpaceShipComponent implements Component {
    private final String[] items;

    private final String bulletName;

    private final String firingSound, hitSound;

    private final BulletEffect bulletEffect;

    /**
     * Instantiates a new Space ship component.
     *
     * @param items       the items
     * @param firingSound the firing sound
     * @param hitSound    the hit sound
     * @param bulletName  the bullet name
     */
    public SpaceShipComponent(String[] items, String firingSound, String hitSound, String bulletName) {
        this.items = items;
        this.firingSound = firingSound;
        this.hitSound = hitSound;
        this.bulletName = bulletName;
        this.bulletEffect = new Damage1Effect();
    }

    /**
     * Gets bullet name.
     *
     * @return the bullet name
     */
    public String getBulletName() {
        return bulletName;
    }

    /**
     * Get items string [ ].
     *
     * @return the string [ ]
     */
    public String[] getItems() {
        return items;
    }

    /**
     * Gets firing sound.
     *
     * @return the firing sound
     */
    public String getFiringSound() {
        return firingSound;
    }

    /**
     * Gets hit sound.
     *
     * @return the hit sound
     */
    public String getHitSound() {
        return hitSound;
    }

    /**
     * Gets bullet effect.
     *
     * @return the bullet effect
     */
    public BulletEffect getBulletEffect() {
        return bulletEffect;
    }
}
