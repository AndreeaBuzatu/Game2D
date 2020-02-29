package uk.ac.lancaster.scc210.game.resources;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.engine.resources.deserialise.Deserialiser;
import uk.ac.lancaster.scc210.game.content.LevelManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class PlayerDataDeserialiser extends Deserialiser<PlayerData> {
    private static final String PLAYER_TAG = "player";

    private final LevelManager levelManager;

    private PlayerData playerData;

    /**
     * Instantiates a new Deserialiser.
     *
     * @param document the xml document
     * @throws ResourceNotFoundException if the resource cannot be created or found
     */
    public PlayerDataDeserialiser(Document document, LevelManager levelManager) throws ResourceNotFoundException {
        super(document, PLAYER_TAG, PLAYER_TAG);

        this.levelManager = levelManager;
    }

    /**
     * Deserialise the given XML document into a Java Object.
     */
    @Override
    public void deserialise() {
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (foundNode(node, PLAYER_TAG)) {
                Element element = (Element) node;

                String unlocked = element.getAttribute("unlocked-level");

                int score = Integer.parseInt(element.getAttribute("score"));

                int lives = Integer.parseInt(element.getAttribute("lives"));

                playerData = new PlayerData(unlocked, score, lives);

                break;
            }
        }
    }

    @Override
    public void createStandinXML() throws ResourceNotFoundException {
        String playerXML = String.format("<player unlocked-level=\"%s\"/>", levelManager.getLevelList().get(0).getName());

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            InputSource inputSource = new InputSource();

            inputSource.setCharacterStream(new StringReader(playerXML));

            document = builder.parse(inputSource);

            nodes = document.getElementsByTagName(PLAYER_TAG);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ResourceNotFoundException("Could not create temporary player.xml");
        }
    }

    public PlayerData getPlayerData() {
        return playerData;
    }
}
