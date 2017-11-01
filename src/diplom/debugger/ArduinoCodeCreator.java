package diplom.debugger;

import javafx.util.Pair;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ArduinoCodeCreator implements CodeCreator {
    private int tabulation;

    @Override
    public Pair<StringBuilder, StringBuilder> generateCode(Node node, int tabulation) {
        this.tabulation = tabulation;
        switch (node.getNodeName()) {
            case "setup":
                return createSetup(node);
            case "loop":
                return createLoop(node);
            case "statement":
                return createStatement(node);
            case "if":
                return createIf(node);
            case "else":
                return createElse(node);
            case "while":
                return createWhile(node);

            case "pin_mode":
                return createPinMode(node);
            case "digital_write":
                return createDigitalWrite(node);

            case "delay":
                return createDelay(node);
            default: return new Pair<>(new StringBuilder(), new StringBuilder());
        }
    }

    private StringBuilder tabulator(StringBuilder stringBuilder) {
        for (int i = 0; i < this.tabulation; i++) {
            stringBuilder.append("\t");
        }
        return stringBuilder;
    }

    private Pair<StringBuilder, StringBuilder> createSetup(Node node) {
        StringBuilder key = new StringBuilder();
        tabulator(key).append("void setup()\n");
        tabulator(key).append("{\n");
        StringBuilder value = new StringBuilder();
        tabulator(value).append("}\n");
        return new Pair<>(key, value);
    }

    private Pair<StringBuilder, StringBuilder> createLoop(Node node) {
        StringBuilder key = new StringBuilder();
        tabulator(key).append("void loop()\n");
        tabulator(key).append("{\n");
        StringBuilder value = new StringBuilder();
        tabulator(value).append("}\n");
        return new Pair<>(key, value);
    }

    private Pair<StringBuilder, StringBuilder> createStatement(Node node) {
        StringBuilder key = new StringBuilder();
        tabulator(key).append(node.getAttributes().getNamedItem("content").getNodeValue()).append(";\n");
        StringBuilder value = new StringBuilder();
        return new Pair<>(key, value);
    }

    private Pair<StringBuilder, StringBuilder> createIf(Node node) {
        StringBuilder key = new StringBuilder();
        tabulator(key).append("if (");
        key.append(node.getAttributes().getNamedItem("condition").getNodeValue()).append(")\n");
        tabulator(key).append("{\n");
        StringBuilder value = new StringBuilder();
        tabulator(value).append("}\n");
        return new Pair<>(key, value);
    }

    private Pair<StringBuilder, StringBuilder> createElse(Node node) {
        StringBuilder key = new StringBuilder();
        tabulator(key).append("else\n");
        tabulator(key).append("{\n");
        StringBuilder value = new StringBuilder();
        tabulator(value).append("}\n");
        return new Pair<>(key, value);
    }

    private Pair<StringBuilder, StringBuilder> createWhile(Node node) {
        StringBuilder key = new StringBuilder();
        tabulator(key).append("while(");
        key.append(node.getAttributes().getNamedItem("condition").getNodeValue()).append(")\n");
        tabulator(key).append("{\n");
        StringBuilder value = new StringBuilder();
        tabulator(value).append("}\n");
        return new Pair<>(key, value);
    }

    private Pair<StringBuilder, StringBuilder> createPinMode(Node node) {
        StringBuilder key = new StringBuilder();
        tabulator(key).append("pinMode(");
        NamedNodeMap attr = node.getAttributes();
        key.append(attr.getNamedItem("arg1").getNodeValue()).append(", ");
        key.append(attr.getNamedItem("arg2").getNodeValue()).append(");\n");
        StringBuilder value = new StringBuilder();
        return new Pair<>(key, value);
    }

    private Pair<StringBuilder, StringBuilder> createDigitalWrite(Node node) {
        StringBuilder key = new StringBuilder();
        tabulator(key).append("digitalWrite(");
        NamedNodeMap attr = node.getAttributes();
        key.append(attr.getNamedItem("arg1").getNodeValue()).append(", ");
        key.append(attr.getNamedItem("arg2").getNodeValue()).append(");\n");
        StringBuilder value = new StringBuilder();
        return new Pair<>(key, value);
    }

    private Pair<StringBuilder, StringBuilder> createDelay(Node node) {
        StringBuilder key = new StringBuilder();
        tabulator(key).append("delay(");
        key.append(node.getAttributes().getNamedItem("arg").getNodeValue()).append(");\n");
        StringBuilder value = new StringBuilder();
        return new Pair<>(key, value);
    }
}
