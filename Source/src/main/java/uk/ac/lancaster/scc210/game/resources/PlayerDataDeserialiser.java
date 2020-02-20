package uk.ac.lancaster.scc210.game.resources;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.engine.resources.deserialise.Deserialiser;

public class PlayerDataDeserialiser extends Deserialiser<PlayerData> {
    private static final String PLAYER_TAG = "player";

    private PlayerData playerData;

    /**
     * Instantiates a new Deserialiser.
     *
     * @param document the xml document
     * @throws ResourceNotFoundException if the resource cannot be created or found
     */
    public PlayerDataDeserialiser(Document document) throws ResourceNotFoundException {
        super(document, "players");
    }

    /**
     * Deserialise the given XML document into a Java Object.
     */
    @Override
    protected void deserialise() {
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (foundNode(node, PLAYER_TAG)) {
                Element element = (Element) node;

                String unlocked = element.getAttribute("unlocked-level");

                playerData = new PlayerData(unlocked);

                break;
            }
        }
    }

    public PlayerData getPlayerData() {
        return playerData;
    }
}
