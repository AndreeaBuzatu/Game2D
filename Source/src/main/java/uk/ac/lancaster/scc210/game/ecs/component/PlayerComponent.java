package uk.ac.lancaster.scc210.game.ecs.component;

import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.game.bullets.effects.BulletEffect;
import uk.ac.lancaster.scc210.game.bullets.effects.Damage1Effect;
import uk.ac.lancaster.scc210.game.items.ItemEffect;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Player component.
 */
public class PlayerComponent implements Component {
    private final BulletEffect defaultBulletEffect;

    private final Set<ItemEffect> currentItemEffects;

    private BulletEffect bulletEffect;

    private Vector2f spawnPoint;

    private int currentScore;

    /**
     * Instantiates a new Player component.
     */
    public PlayerComponent() {
        defaultBulletEffect = new Damage1Effect();

        bulletEffect = defaultBulletEffect;

        currentItemEffects = new HashSet<>();

        currentScore = 0;
    }

    /**
     * Gets current item effects.
     *
     * @return the current item effects
     */
    public Set<ItemEffect> getCurrentItemEffects() {
        return currentItemEffects;
    }

    /**
     * Sets current item effects.
     *
     * @param currentItemEffects the current item effects
     */
    public void setCurrentItemEffects(Set<ItemEffect> currentItemEffects) {
        this.currentItemEffects.addAll(currentItemEffects);
    }

    /**
     * Change score.
     *
     * @param score the score
     */
    public void changeScore(int score) {
        this.currentScore += score;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return currentScore;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.currentScore = score;
    }

    /**
     * Sets spawn point.
     *
     * @param spawnPoint the spawn point
     */
    public void setSpawnPoint(Vector2f spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

    /**
     * Gets spawn point.
     *
     * @return the spawn point
     */
    public Vector2f getSpawnPoint() {
        return spawnPoint;
    }

    /**
     * Gets bullet effect.
     *
     * @return the bullet effect
     */
    public BulletEffect getBulletEffect() {
        return bulletEffect;
    }

    /**
     * Sets bullet effect.
     *
     * @param bulletEffect the bullet effect
     */
    public void setBulletEffect(BulletEffect bulletEffect) {
        this.bulletEffect = bulletEffect;
    }

    /**
     * Sets bullet effect to default.
     */
    public void setBulletEffectToDefault() {
        this.bulletEffect = defaultBulletEffect;

        defaultBulletEffect.setDead(false);
    }
}
