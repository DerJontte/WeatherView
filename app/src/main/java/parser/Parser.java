package parser;

import android.util.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

public class Parser {

    public static Pair<String, String> parse() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        StringBuilder stringBuilder = new StringBuilder();

        String utcTime = UTCTime.getCompleteDate(-3);
        String query = "http://opendata.fmi.fi/wfs/fin?service=WFS&version=2.0.0&request=getFeature&storedquery_id=fmi::observations::weather::timevaluepair&fmisid=100949&parameters=t2m&starttime=" + utcTime;
        URL url = new URL(query);

        Document document = builder.parse(query);

        Element root = document.getDocumentElement();
        Element tempKeyVals = null;

        NodeList measurements = root.getElementsByTagName("wml2:MeasurementTimeseries");
        for(int i = 0; i < measurements.getLength(); i++) {
            Node currNode = measurements.item(i);
            if (currNode.getAttributes().getNamedItem("gml:id").getNodeValue().contains("t2m")) {
                tempKeyVals = (Element) measurements.item(i);
                break;
            }
        }

        NodeList keys = tempKeyVals.getElementsByTagName("wml2:time");
        NodeList values = tempKeyVals.getElementsByTagName("wml2:value");

        int i = keys.getLength() - 1;
        String lastKey = keys.item(i).getFirstChild().getNodeValue();
        String lastValue = values.item(i).getFirstChild().getNodeValue();
        return new Pair<String, String>(lastKey, lastValue);
    }
}
