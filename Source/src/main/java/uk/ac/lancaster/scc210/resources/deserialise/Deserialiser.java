package uk.ac.lancaster.scc210.resources.deserialise;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uk.ac.lancaster.scc210.resources.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

public abstract class Deserialiser<T extends Serialised> {
    private final String tagName;

    final List<T> serialised;

    final Document document;

    NodeList nodes;

    Deserialiser(Document document, String tagName) throws ResourceNotFoundException {
        this.document = document;
        this.tagName = tagName;

        serialised = new ArrayList<>();

        if (document != null) {
            nodes = document.getDocumentElement().getChildNodes();

            deserialise();
        }
    }

    Deserialiser(Document document, NodeList nodes, String tagName) throws ResourceNotFoundException {
        this.document = document;
        this.nodes = nodes;
        this.tagName = tagName;

        serialised = new ArrayList<>();

        deserialise();
    }

    protected abstract void deserialise() throws ResourceNotFoundException;

    boolean foundNode(Node node) {
        return node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(tagName);
    }

    public List<T> getSerialised() {
        return serialised;
    }
}
