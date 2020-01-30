package uk.ac.lancaster.scc210.game.resources;

import org.jsfml.system.Vector2f;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.engine.resources.deserialise.Deserialiser;
import uk.ac.lancaster.scc210.game.content.SpaceShipManager;
import uk.ac.lancaster.scc210.game.ecs.entity.SpaceShip;
import uk.ac.lancaster.scc210.game.level.Level;
import uk.ac.lancaster.scc210.game.level.LevelStage;
import uk.ac.lancaster.scc210.game.level.LevelWave;

import java.util.ArrayList;
import java.util.List;

public class LevelDeserialiser extends Deserialiser<Level> {
    private final String STAGE_TAG = "stage";

    private final String WAVE_TAG = "wave";

    private final SpaceShipManager spaceShipManager;

    /**
     * Instantiates a new Deserialiser.
     *
     * @param document the xml document
     * @throws ResourceNotFoundException if the resource cannot be created or found
     */
    public LevelDeserialiser(SpaceShipManager spaceShipManager, Document document) throws ResourceNotFoundException {
        super(document, "level");

        this.spaceShipManager = spaceShipManager;

        deserialise();
    }

    @Override
    protected void deserialise() {
        if (spaceShipManager == null) {
            return;
        }

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (foundNode(node)) {
                serialised.add(new Level(deserialiseStage(node.getChildNodes())));
            }
        }
    }

    private List<LevelStage> deserialiseStage(NodeList nodes) {
        List<LevelStage> stages = new ArrayList<>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (foundNode(node, STAGE_TAG)) {
                stages.add(new LevelStage(deserialiseWave(node.getChildNodes())));
            }
        }

        return stages;
    }

    private List<LevelWave> deserialiseWave(NodeList nodes) {
        List<LevelWave> waves = new ArrayList<>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (foundNode(node, WAVE_TAG)) {
                Element elem = (Element) node;

                String waveType = elem.getAttribute("type");

                float originX = Float.parseFloat(elem.getAttribute("start_x"));

                float originY = Float.parseFloat(elem.getAttribute("start_y"));

                float destinationX = Float.parseFloat(elem.getAttribute("end_x"));

                float destinationY = Float.parseFloat(elem.getAttribute("end_y"));

                int numShips = Integer.parseInt(elem.getAttribute("num_ships"));

                String shipName = elem.getAttribute("ship_name");

                Vector2f origin = new Vector2f(originX, originY);

                Vector2f destination = new Vector2f(destinationX, destinationY);

                SpaceShip spaceShip = spaceShipManager.get(shipName);

                waves.add(new LevelWave(waveType, origin, destination, numShips, spaceShip));
            }
        }

        return waves;
    }
}
