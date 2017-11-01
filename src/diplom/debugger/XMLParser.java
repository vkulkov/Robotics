package diplom.debugger;

import javafx.util.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

public class XMLParser {
    private CodeCreator codeCreator;
    private Node currentNode;

    public CodeCreator getCodeCreator() {
        return codeCreator;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public XMLParser(CodeCreator codeCreator) {
        this.codeCreator = codeCreator;
    }

    public void processInput() {
        try (InputStream in = new FileInputStream("input.xml");
             OutputStream out = new FileOutputStream("output.c")) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(in);
            doc.getDocumentElement().normalize();
            Node listing = doc.getDocumentElement();
            if (!listing.getNodeName().equals("listing")) {
                throw new ImproperInputException();
            }
            NodeList nodeList = listing.getChildNodes();
            StringBuilder sb = new StringBuilder();
            String result = this.parseListing(nodeList, sb, 0).toString();
            out.write(result.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private StringBuilder parseListing(NodeList nodeList, StringBuilder code, int tabulation) {
        for (int index = 0; index < nodeList.getLength(); index++) {
            setCurrentNode(nodeList.item(index));
            Pair<StringBuilder, StringBuilder> function = getCodeCreator().generateCode(getCurrentNode(), tabulation);
            code.append(function.getKey());
            if (getCurrentNode().hasChildNodes()) {
                this.parseListing(getCurrentNode().getChildNodes(), code, ++tabulation);
                tabulation--;
            }
            code.append(function.getValue());
        }
        return code;
    }


    public static void main(String[] args) {
        new XMLParser(new ArduinoCodeCreator()).processInput();
    }
}
