package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;

public class EnemyComponent implements Component {
    private boolean canFire;

    public EnemyComponent() {
        canFire = true;
    }

    public boolean canFire() {
        return canFire;
    }

    public void setFiring(boolean firing) {
        this.canFire = firing;
    }
}
