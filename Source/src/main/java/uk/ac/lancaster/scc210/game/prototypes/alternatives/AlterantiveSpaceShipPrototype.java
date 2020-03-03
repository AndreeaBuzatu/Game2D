package uk.ac.lancaster.scc210.game.prototypes.alternatives;

import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.engine.service.ServiceProvider;
import uk.ac.lancaster.scc210.game.content.SpaceShipPrototypeManager;
import uk.ac.lancaster.scc210.game.prototypes.SpaceShipPrototype;
import uk.ac.lancaster.scc210.game.resources.SerialisedSpaceShip;

public class AlterantiveSpaceShipPrototype {
    private final SpaceShipPrototype alternative;

    public AlterantiveSpaceShipPrototype(ServiceProvider serviceProvider, SpaceShipPrototypeManager spaceShipPrototypeManager, Pool pool) {
        SerialisedSpaceShip serialisedSpaceShip = new SerialisedSpaceShip("unknown", "", new String[]{""}, "", "", 1, 0, 1, "", "", "");

        alternative = new SpaceShipPrototype(serviceProvider, spaceShipPrototypeManager, pool, serialisedSpaceShip);
    }

    public SpaceShipPrototype getAlternative() {
        return alternative;
    }
}
