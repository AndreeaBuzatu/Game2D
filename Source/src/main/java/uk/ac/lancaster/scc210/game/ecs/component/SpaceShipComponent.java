package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;

public class SpaceShipComponent implements Component {
    private final String[] items;

    public SpaceShipComponent(String[] items) {
        this.items = items;
    }

    public String[] getItems() {
        return items;
    }
}
