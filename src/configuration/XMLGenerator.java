package configuration;

import java.io.FileWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import constants.Constants;
import javafx.scene.paint.Color;
import model.Palette;
import model.UserCommands;
import model.Variables;

public class XMLGenerator {

	private static int fileNameCounter = 0;

	public void writeXML(ConfigurationInfo configInfo) {
		try {
			DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			Element root = doc.createElement("root");
			doc.appendChild(root);

			root.appendChild(translatePenColor(doc, configInfo.getMyPenColor().toString()));
			root.appendChild(translateBackgroundColor(doc, configInfo.getMyBackgroundColor().toString()));
			root.appendChild(translatePenWidth(doc, Double.toString(configInfo.getMyPenWidth())));
			root.appendChild(translateLanguage(doc, configInfo.getMyLanguage()));
			root.appendChild(translateVariables(doc, configInfo.getMyVariables()));
			root.appendChild(translateCommands(doc, configInfo.getMyCommands()));
			root.appendChild(translatePalette(doc, configInfo.getMyPalette()));

			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");

			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(doc);
			trans.transform(source, result);
			String xmlString = sw.toString();

			String filePath = Constants.getSpecification("saveXMLFilePath")
					+ Constants.getSpecification("ConfigurationsFileName") + Constants.getSpecification("Underscore")
					+ Integer.toString(fileNameCounter) + Constants.getSpecification("SavedFileExtension");

			FileWriter fw = new FileWriter(filePath);
			fileNameCounter++;
			fw.write(xmlString);
			;
			fw.close();

		} catch (Exception e) {
			e.getMessage();
		}

	}

	/**
	 * Helper method to translate pen color to XML Element
	 * 
	 * @param doc
	 * @param penColor
	 * @return
	 */
	private Element translatePenColor(Document doc, String penColor) {
		Element penColorElement = doc.createElement("pencolor");
		Node penColorNode = doc.createTextNode(penColor);
		penColorElement.appendChild(penColorNode);
		return penColorElement;
	}

	/**
	 * Helper method to translate background color to XML Element
	 * 
	 * @param doc
	 * @param penColor
	 * @return
	 */
	private Node translateBackgroundColor(Document doc, String backgroundColor) {
		Element backgroundColorElement = doc.createElement("backgroundcolor");
		Node backgroundColorNode = doc.createTextNode(backgroundColor);
		backgroundColorElement.appendChild(backgroundColorNode);
		return backgroundColorElement;
	}

	/**
	 * Helper method to translate pen width to XML Element
	 * 
	 * @param doc
	 * @param penColor
	 * @return
	 */
	private Element translatePenWidth(Document doc, String penWidth) {
		Element penWidthElement = doc.createElement("penwidth");
		Node penWidthNode = doc.createTextNode(penWidth);
		penWidthElement.appendChild(penWidthNode);
		return penWidthElement;
	}

	/**
	 * Helper method to translate language to XML Element
	 * 
	 * @param doc
	 * @param penColor
	 * @return
	 */
	private Node translateLanguage(Document doc, String language) {
		Element languageElement = doc.createElement("language");
		Node languageNode = doc.createTextNode(language);
		languageElement.appendChild(languageNode);
		return languageElement;
	}

	/**
	 * Helper method to convert Variables object to XML Element
	 * 
	 * @param doc
	 * @param variables
	 * @return
	 */
	private Element translateVariables(Document doc, Variables variables) {
		Element myVariables = doc.createElement("variables");

		Map<String, Double> map = variables.getVariableMap();
		for (String entry : map.keySet()) {
			Element var = doc.createElement("var");

			Element key = doc.createElement("key");
			Node keyValue = doc.createTextNode(entry);
			key.appendChild(keyValue);
			var.appendChild(key);

			Element value = doc.createElement("value");
			Node valueValue = doc.createTextNode(Double.toString(map.get(entry)));
			value.appendChild(valueValue);
			var.appendChild(value);

			myVariables.appendChild(var);
		}

		return myVariables;
	}

	/**
	 * Helper method to convert UserCommands object to XML Element
	 * 
	 * @param doc
	 * @param variables
	 * @return
	 */
	private Element translateCommands(Document doc, UserCommands commands) {
		Element myCommands = doc.createElement("usercommands");

		Map<String, List<String>> paramMap = commands.getUserCommandMap();

		for (String key : paramMap.keySet()) {
			Element item = doc.createElement("item");

			Element commandName = doc.createElement("commandname");
			Node commandNameValue = doc.createTextNode(key);
			commandName.appendChild(commandNameValue);
			item.appendChild(commandName);

			Element commandCode = doc.createElement("command");
			Node commandCodeValue = doc.createTextNode(commands.getCommand(key));
			commandCode.appendChild(commandCodeValue);
			item.appendChild(commandCode);

			Element params = doc.createElement("params");
			for (int i = 0; i < paramMap.get(key).size(); i++) {
				Element param = doc.createElement("param");
				Node paramValue = doc.createTextNode(paramMap.get(key).get(i));
				param.appendChild(paramValue);
				params.appendChild(param);
			}

			item.appendChild(params);
			myCommands.appendChild(item);
		}

		return myCommands;
	}

	/**
	 * Helper method to convert Palette object to XML Element
	 * 
	 * @param doc
	 * @param variables
	 * @return
	 */
	private Element translatePalette(Document doc, Palette myPalette) {
		Element paletteElement = doc.createElement("palette");
		Map<Integer, String> shapeMap = myPalette.getPaletteShapeMap();
		Map<Integer, Color> colorMap = myPalette.getPaletteColorMap();

		for (Integer i : colorMap.keySet()) {
			Element colorElem = doc.createElement("shap");

			Element colorIndex = doc.createElement("index");
			Node indexValue = doc.createTextNode(Integer.toString(i));
			colorIndex.appendChild(indexValue);

			Element colorValue = doc.createElement("colorvalue");
			Node colorValueValue = doc.createTextNode(colorMap.get(i).toString());
			colorValue.appendChild(colorValueValue);

			colorElem.appendChild(colorIndex);
			colorElem.appendChild(colorValue);
			paletteElement.appendChild(colorElem);

		}

		for (Integer i : shapeMap.keySet()) {
			Element shapeElem = doc.createElement("shape");

			Element shapeIndex = doc.createElement("index");
			Node indexValue = doc.createTextNode(Integer.toString(i));
			shapeIndex.appendChild(indexValue);

			Element shapeValue = doc.createElement("shape");
			Node shapeValueValue = doc.createTextNode(colorMap.get(i).toString());
			shapeValue.appendChild(shapeValueValue);

			shapeElem.appendChild(shapeIndex);
			shapeElem.appendChild(shapeValue);
			paletteElement.appendChild(shapeElem);

		}

		return paletteElement;
	}

}
