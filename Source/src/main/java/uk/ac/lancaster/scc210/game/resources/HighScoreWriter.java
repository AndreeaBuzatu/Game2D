package uk.ac.lancaster.scc210.game.resources;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.engine.service.Service;
import uk.ac.lancaster.scc210.game.content.HighScores;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * The type High score writer.
 */
public class HighScoreWriter implements Service {
    private final HighScores highScores;

    /**
     * Instantiates a new High score writer.
     *
     * @param highScores the high scores
     */
    public HighScoreWriter(HighScores highScores) {
        this.highScores = highScores;
    }

    /**
     * Write high scores.
     *
     * @throws ResourceNotFoundException the resource not found exception
     */
    public void writeHighScores() throws ResourceNotFoundException {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            File highScoreFile = new File(Objects.requireNonNull(HighScoreWriter.class.getClassLoader().getResource("highscores.xml")).getFile());

            FileWriter writer = new FileWriter(highScoreFile);

            StreamResult result = new StreamResult(writer);

            Document newDocument = createDocument();

            transformer.transform(new DOMSource(newDocument), result);

        } catch (TransformerException | IOException e) {
            throw new ResourceNotFoundException("Could not write to highscores.xml");
        }
    }

    private Document createDocument() throws ResourceNotFoundException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            Document doc = builder.newDocument();

            Element head = doc.createElement("high-scores");

            for (HighScore highScore : highScores.getHighScores()) {
                Element highScoreElem = doc.createElement("high-score");

                highScoreElem.setAttribute("name", highScore.getPlayerName());

                highScoreElem.setAttribute("score", Integer.toString(highScore.getScore()));

                head.appendChild(highScoreElem);
            }

            doc.appendChild(head);

            return doc;

        } catch (ParserConfigurationException e) {
            throw new ResourceNotFoundException("Could not create document from High Score");
        }
    }
}
