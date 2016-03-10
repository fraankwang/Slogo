/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javafx.scene.paint.Color;
import model.Palette;
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
						configInfo.setMyVariables(parseVariables(elem));
					}

					else if (elem.getNodeName().equalsIgnoreCase("usercommands")) {
						configInfo.setMyCommands(parseCommands(elem));
					}
					
					else if (elem.getNodeName().equalsIgnoreCase("palette")) {
						configInfo.setMyPalette(parsePaletteColors(elem));
					}
				}
			}

		} catch (Exception e) {
			return null;
		}

		return configInfo;

	}


	/**
	 * Helper method to parse Variables (nested elements)
	 * 
	 * @param elem
	 * @return
	 */
	private Variables parseVariables(Node elem) {
		Variables myVariables = new Variables();
		NodeList variables = elem.getChildNodes();

		for (int varIndex = 0; varIndex < variables.getLength(); varIndex++) {
			Node item = variables.item(varIndex);

			if (item.getNodeType() == Node.ELEMENT_NODE) {
				NodeList nodeList = item.getChildNodes();
				String key = null;
				Double value = null;
				for (int j = 0; j < nodeList.getLength(); j++) {
					Node n = nodeList.item(j);
					if (n.getNodeType() == Node.ELEMENT_NODE) {
						if (n.getNodeName().equalsIgnoreCase("key")) {
							key = n.getTextContent().trim();
						}
						if (n.getNodeName().equalsIgnoreCase("value")) {
							value = Double.parseDouble(n.getTextContent().trim());
						}
					}
				}

				myVariables.addVariable(key, value);
			}
		}

		return myVariables;
	}

	/**
	 * Helper method to parse UserCommands (nested elements)
	 * 
	 * @param elem
	 * @return
	 */
	private UserCommands parseCommands(Node elem) {
		UserCommands userCommands = new UserCommands();
		NodeList commands = elem.getChildNodes();

		for (int itemIndex = 0; itemIndex < commands.getLength(); itemIndex++) {
			String commandName = "";
			String commandCode = "";
			List<String> parameters = new ArrayList<String>();
			Node item = commands.item(itemIndex);

			if (item.getNodeType() == Node.ELEMENT_NODE) {
				NodeList nodeList = item.getChildNodes();

				for (int i = 0; i < nodeList.getLength(); i++) {
					if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
						if (nodeList.item(i).getNodeName().equalsIgnoreCase("commandname")) {
							commandName = nodeList.item(i).getTextContent().trim();
							
						} else if (nodeList.item(i).getNodeName().equalsIgnoreCase("command")) {
							commandCode = nodeList.item(i).getTextContent().trim();
							
						} else if (nodeList.item(i).getNodeName().equalsIgnoreCase("params")) {
							NodeList paramList = nodeList.item(i).getChildNodes();
							
							for (int j = 0; j < paramList.getLength(); j++) {
								if (paramList.item(j).getNodeType() == Node.ELEMENT_NODE) {
									parameters.add(paramList.item(j).getTextContent().trim());
								}
							}
							
						}
					}
				}
			}

			if (commandName.length() != 0 && commandCode.length() != 0 && parameters.isEmpty()) {
				userCommands.addCommand(commandName, parameters, commandCode);
			}
		}

		return userCommands;
	}
	
	private Palette parsePaletteColors(Node elem) {
		Palette palette = new Palette();
		NodeList paletteElems = elem.getChildNodes();
		
		for (int i = 0; i < paletteElems.getLength(); i++) {
			Node item = paletteElems.item(i);
			if (item.getNodeType() == Node.ELEMENT_NODE) {
				
				if (item.getNodeName().equalsIgnoreCase("color")) {
					int index = 0;
					Color color = null;
					for (int j = 0; j < item.getChildNodes().getLength(); j++) {
						Node colorItem = item.getChildNodes().item(j);
								
						if (colorItem.getNodeType() == Node.ELEMENT_NODE) {
							if (colorItem.getNodeName().equalsIgnoreCase("index")) {
								index = Integer.parseInt(colorItem.getTextContent().trim());
							}
							if (colorItem.getNodeName().equalsIgnoreCase("colorvalue")) {
								color = Color.valueOf(colorItem.getTextContent().trim());
							}
						}
					}
					palette.addColor(index, color);
				}
				
				else if (item.getNodeName().equalsIgnoreCase("shape")) {
					int index = 0;
					String shape = "";
					for (int j = 0; j < item.getChildNodes().getLength(); j++) {
						Node shapeItem = item.getChildNodes().item(j);
								
						if (shapeItem.getNodeType() == Node.ELEMENT_NODE) {
							if (shapeItem.getNodeName().equalsIgnoreCase("index")) {
								index = Integer.parseInt(shapeItem.getTextContent().trim());
							}
							if (shapeItem.getNodeName().equalsIgnoreCase("shape")) {
								shape = shapeItem.getTextContent().trim();
							}
						}
					}
					palette.addShape(index, shape);
				}
			}
		}
		
		return palette;
	}


}
