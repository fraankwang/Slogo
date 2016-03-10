package configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import constants.Constants;
import javafx.scene.paint.Color;
import model.UserCommands;
import model.Variables;

public class XMLParser {

	/**
	 * @param file
	 *            picked by user
	 * @return fully populated ConfigurationInfo
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
					if (elem.getNodeName().equalsIgnoreCase("pencolor")) {
						configInfo.setMyPenColor(Color.valueOf(elem.getTextContent()));
					}

					else if (elem.getNodeName().equalsIgnoreCase("backgroundcolor")) {
						configInfo.setMyBackgroundColor((Color.valueOf(elem.getTextContent())));
					}

					else if (elem.getNodeName().equalsIgnoreCase("language")) {
						configInfo.setMyLanguage(elem.getTextContent());
					}

					else if (elem.getNodeName().equalsIgnoreCase("penwidth")) {
						configInfo.setMyPenWidth(Double.parseDouble(elem.getTextContent()));
					}
	
					else if (elem.getNodeName().equalsIgnoreCase("variables")) {
						Variables myVariables = new Variables();

						NodeList variables = elem.getChildNodes();
						
						System.out.println("length: " + variables.getLength());

						for (int varIndex = 0; varIndex < variables.getLength(); varIndex++) {
							Node n = variables.item(varIndex);
							
							if (n.getNodeType() == Node.ELEMENT_NODE) {
								System.out.println(n.getNodeName());
								NodeList nl = n.getChildNodes();
								
								Node n1 = nl.item(0);
								Node n2 = nl.item(1);
								System.out.println(n1.getTextContent().trim());
								System.out.println(n2.getTextContent().trim());
							}
						
						}
						
						
						
						for (int varIndex = 0; varIndex < variables.getLength(); varIndex++) {
							
							System.out.println(varIndex);
							
							NodeList keysAndValues = (NodeList) variables.item(varIndex).getChildNodes();
							String key = keysAndValues.item(0).getTextContent();
							Double value = Double.parseDouble(keysAndValues.item(1).getTextContent());
							myVariables.addVariable(key, value);
							System.out.println(key + " " + value);
						}
						configInfo.setMyVariables(myVariables);
						System.out.println(configInfo.getMyVariables().getVariableMap());

					}

//					else if (elem.getNodeName().equalsIgnoreCase("usercommands")) {
//						System.out.println("commands");
//						UserCommands userCommands = new UserCommands();
//						System.out.println(((NodeList) elem).getLength());
//						for (int itemIndex = 0; itemIndex < ((NodeList) elem).getLength(); itemIndex++) {
//							Node item = ((NodeList) elem).item(itemIndex);
//							String commandName = ((NodeList) item).item(0).getTextContent();
//							String commandCode = ((NodeList) item).item(1).getTextContent();
//							Node params = ((NodeList) item).item(2);
//							List<String> parametersList = new ArrayList<String>();
//							for (int paramIndex = 0; paramIndex < ((NodeList) params).getLength(); paramIndex++) {
//								Node param = ((NodeList) params).item(paramIndex);
//								String paramValue = param.getTextContent();
//								parametersList.add(paramValue);
//							}
//							userCommands.addCommand(commandName, parametersList, commandCode);
//
//						}
//						configInfo.setMyCommands(userCommands);
//
//					}
				}

			}

		} catch (Exception e) {
			return null;
		}

		return configInfo;

	}
}
