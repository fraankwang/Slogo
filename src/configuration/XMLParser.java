package configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import constants.Constants;

public class XMLParser {

	/**
	 * @param file
	 *            picked by user
	 * @return Parameters - a map of parameters and a 2D array of initial states
	 *         (integers) packaged together
	 */
	public ConfigurationInfo parse(File file) {
		ConfigurationInfo configInfo = new ConfigurationInfo();

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			NodeList root = doc.getFirstChild().getChildNodes();

			for (int i = 0; i < root.getLength(); i++) {
				Node elem = root.item(i);
				if (elem.getNodeType() == Node.ELEMENT_NODE) {

					if (!elem.getNodeName().equalsIgnoreCase("initialStates")) {
						params.put(elem.getNodeName(), elem.getTextContent());
					}

					else if (elem.getNodeName().equalsIgnoreCase("initialStates")) {
						NodeList states = elem.getChildNodes();
						ArrayList<String> stateValues = new ArrayList<String>();

						for (int k = 0; k < states.getLength(); k++) {
							if (states.item(k).getNodeType() == Node.ELEMENT_NODE) {
								String row = states.item(k).getTextContent().trim();

								stateValues.add(row);
							}

						}

						initialStates = createInitialStates(stateValues);
					}
				}
			}

		} catch (Exception e) {
			return null;
		}

		return configInfo;

	}

}
