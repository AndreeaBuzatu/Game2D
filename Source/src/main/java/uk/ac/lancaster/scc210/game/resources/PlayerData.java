package uk.ac.lancaster.scc210.game.resources;

import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;
import uk.ac.lancaster.scc210.engine.service.Service;

public class PlayerData implements Serialised, Service {
    private final String unlockedLevel;

    PlayerData(String unlockedLevel) {
        this.unlockedLevel = unlockedLevel;
    }

    public String getUnlockedLevel() {
        return unlockedLevel;
    }
}
