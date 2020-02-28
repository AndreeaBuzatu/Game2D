package uk.ac.lancaster.scc210.game.resources;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uk.ac.lancaster.scc210.engine.StateBasedGame;
import uk.ac.lancaster.scc210.engine.service.Service;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

public class PlayerWriter implements Service {
    private final Document document;
    private final NodeList nodes;

    private Element playerElement;

    public PlayerWriter(Document document) {
        this.document = document;

        nodes = document.getElementsByTagName("player");

        getPlayerElement();
    }

    public void writePlayerLevel(PlayerData playerData) {
        playerElement.setAttribute("unlocked-level", playerData.getUnlockedLevel());

        playerElement.setAttribute("score", Integer.toString(playerData.getScore()));

        playerElement.setAttribute("lives", Integer.toString(playerData.getLives()));

        System.out.println("Score: " + playerData.getScore() + ", Lives: " + playerData.getLives());

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            File playerFile = new File("player.xml").getAbsoluteFile();

            // Create if doesn't exist
            //noinspection ResultOfMethodCallIgnored
            playerFile.createNewFile();

            FileWriter writer = new FileWriter(playerFile);

            StreamResult result = new StreamResult(writer);

            transformer.transform(new DOMSource(document), result);

        } catch (TransformerException | IOException e) {
            StateBasedGame.LOGGER.log(Level.WARNING, "Unable to save unlocked level in player.xml");
        }
    }

    private void getPlayerElement() {
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (foundPlayerNode(node)) {
                playerElement = (Element) node;

                break;
            }
        }
    }

    private boolean foundPlayerNode(Node node) {
        return node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("player");
    }
}
