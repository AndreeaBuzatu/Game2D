package uk.ac.lancaster.scc210.game.resources;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.engine.resources.deserialise.Deserialiser;

public class HighScoresSerialiser extends Deserialiser<HighScore> {
    /**
     * Instantiates a new Deserialiser.
     *
     * @param document the xml document
     * @throws ResourceNotFoundException if the resource cannot be created or found
     */
    public HighScoresSerialiser(Document document) throws ResourceNotFoundException {
        super(document, "high-score");
    }

    /**
     * Deserialise the given XML document into a Java Object.
     */
    @Override
    protected void deserialise() {
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (foundNode(node)) {
                Element elem = (Element) node;

                String name = elem.getAttribute("name");

                int score = Integer.parseInt(elem.getAttribute("score"));

                serialised.add(new HighScore(name, score));
            }
        }
    }
}
