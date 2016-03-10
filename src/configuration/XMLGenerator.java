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

			Element penColor = doc.createElement("pencolor");
			Node penColorNode = doc.createTextNode(configInfo.getMyPenColor().toString());
			penColor.appendChild(penColorNode);
			root.appendChild(penColor);

			Element backgroundColor = doc.createElement("backgroundcolor");
			Node backgroundColorNode = doc.createTextNode(configInfo.getMyBackgroundColor().toString());
			backgroundColor.appendChild(backgroundColorNode);
			root.appendChild(backgroundColor);

			Element penWidth = doc.createElement("penwidth");
			Node penWidthNode = doc.createTextNode(Double.toString(configInfo.getMyPenWidth()));
			penWidth.appendChild(penWidthNode);
			root.appendChild(penWidth);

			Element language = doc.createElement("language");
			Node languageNode = doc.createTextNode(configInfo.getMyLanguage());
			language.appendChild(languageNode);
			root.appendChild(language);
			root.appendChild(translateVariables(doc, configInfo.getMyVariables()));
			root.appendChild(translateCommands(doc, configInfo.getMyCommands()));
			

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
	 * Helper method to convert Variables object to XML
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
	 * Helper method to convert UserCommands object to XML
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
			
			Element commandCode= doc.createElement("command");
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


}
