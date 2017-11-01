package diplom.debugger;

import javafx.util.Pair;
import org.w3c.dom.Node;

public interface CodeCreator {
    Pair<StringBuilder, StringBuilder> generateCode(Node node, int tabulation);
}
