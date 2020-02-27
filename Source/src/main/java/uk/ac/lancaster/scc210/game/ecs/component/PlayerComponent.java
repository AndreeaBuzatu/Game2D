package uk.ac.lancaster.scc210.game.ecs.component;

import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.game.bullets.effects.BulletEffect;
import uk.ac.lancaster.scc210.game.bullets.effects.Damage1Effect;
import uk.ac.lancaster.scc210.game.items.ItemEffect;

import java.util.HashSet;
import java.util.Set;

public class PlayerComponent implements Component {
    private final BulletEffect defaultBulletEffect;

    private Set<ItemEffect> currentItemEffects;

    private BulletEffect bulletEffect;

    private Vector2f spawnPoint;

    private int currentScore;

    public PlayerComponent() {
        defaultBulletEffect = new Damage1Effect();

        bulletEffect = defaultBulletEffect;

        currentItemEffects = new HashSet<>();

        currentScore = 0;
    }

    public Set<ItemEffect> getCurrentItemEffects() {
        return currentItemEffects;
    }

    public void setCurrentItemEffects(Set<ItemEffect> currentItemEffects) {
        this.currentItemEffects.addAll(currentItemEffects);
    }

    public void changeScore(int score) {
        this.currentScore += score;
    }

    public int getScore() {
        return currentScore;
    }

    public void setScore(int score) {
        this.currentScore = score;
    }

    public void setSpawnPoint(Vector2f spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

    public Vector2f getSpawnPoint() {
        return spawnPoint;
    }

    public BulletEffect getBulletEffect() {
        return bulletEffect;
    }

    public void setBulletEffect(BulletEffect bulletEffect) {
        this.bulletEffect = bulletEffect;
    }

    public void setBulletEffectToDefault() {
        this.bulletEffect = defaultBulletEffect;

        defaultBulletEffect.setDead(false);

        if (bulletEffect == defaultBulletEffect) {
            System.out.println("Set to default");
        }
    }
}
