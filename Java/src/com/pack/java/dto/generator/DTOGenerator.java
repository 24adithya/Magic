package com.pack.java.dto.generator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DTOGenerator {

	public static void main(String[] args) {
		try {

			if (args == null || args.length < 3) {
				System.out
						.println("Please provide package name for ex: com.aligne as 1st argument, class name for ex. Aligne5DTO.java as second argument and view.xml as 3rd argument for ex: C:/AW/GasOps304_GIT/java/engserver/src/com/sungard/entegrate/etm/bc4j/trading/EtmMakeDealView.xml");
				return;
			}
			List<String> text = new ArrayList<>();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			Path filePath = Paths.get(args[1]);

			createJavaFile(text, filePath, args[0]);

			Document doc = dBuilder.parse(args[2]);
			doc.getDocumentElement().normalize();

			NodeList nList2 = doc.getElementsByTagName("ViewAttribute");

			String etmMethod = null;
			String translateCols = null;
			String formatCols = null;
			String unitCols = null;

			Map<String, String> columnMap = new HashMap<String, String>();

			Scanner scanner = new Scanner(System.in);
			for (int temp = 0; temp < nList2.getLength(); temp++) {
				Node nNode = nList2.item(temp);
				Node node = nNode.getAttributes().getNamedItem("Name");
				String textContent = node.getTextContent();

				System.out.print("Please enter camel case of " + textContent.toLowerCase().replace("_", "") + ": \n");
				String name = scanner.next();

				columnMap.put(textContent, name);

				Node node2 = nNode.getAttributes().getNamedItem("Type");
				String type = node2.getTextContent();

				switch (type) {
				case "java.lang.Integer":
					generateVariable(text, "Integer", name);
					break;
				case "java.lang.Float":
					generateVariable(text, "BigDecimal", name);
					break;
				case "java.lang.Boolean":
					generateVariable(text, "Boolean", name);
					break;
				case "java.lang.String":
					generateVariable(text, "String", name);
					break;
				case "oracle.jbo.domain.Number":
					generateVariable(text, "Integer", name);
					break;
				case "oracle.jbo.domain.Date":
					generateVariable(text, "Date", name);
					break;
				case "java.lang.Double":
					generateVariable(text, "BigDecimal", name);
					break;
				case "java.sql.Timestamp":
					generateVariable(text, "Timestamp", name);
					break;
				default:
					System.out.println(type);
					generateVariable(text, "String", name);
					break;
				}
			}

			for (int temp = 0; temp < nList2.getLength(); temp++) {
				Node nNode = nList2.item(temp);
				Node node = nNode.getAttributes().getNamedItem("Name");
				String textContent = node.getTextContent();

				String name = columnMap.get(textContent);

				Node node2 = nNode.getAttributes().getNamedItem("Type");
				String type = node2.getTextContent();

				switch (type) {
				case "java.lang.Integer":
					generateGetterMethod(text, "Integer", name);
					generateSetterMethod(text, "Integer", name);
					break;
				case "java.lang.Float":
					generateGetterMethod(text, "BigDecimal", name);
					generateSetterMethod(text, "BigDecimal", name);
					break;
				case "java.lang.Boolean":
					generateGetterMethod(text, "Boolean", name);
					generateSetterMethod(text, "Boolean", name);
					break;
				case "java.lang.String":
					generateGetterMethod(text, "String", name);
					generateSetterMethod(text, "String", name);
					break;
				case "oracle.jbo.domain.Number":
					generateGetterMethod(text, "Integer", name);
					generateSetterMethod(text, "Integer", name);
					break;
				case "oracle.jbo.domain.Date":
					generateGetterMethod(text, "Date", name);
					generateSetterMethod(text, "Date", name);
					break;
				case "java.lang.Double":
					generateGetterMethod(text, "BigDecimal", name);
					generateSetterMethod(text, "BigDecimal", name);
					break;
				case "java.sql.Timestamp":
					generateGetterMethod(text, "Timestamp", name);
					generateSetterMethod(text, "Timestamp", name);
					break;
				default:
					System.out.println(type);
					generateGetterMethod(text, "String", name);
					generateSetterMethod(text, "String", name);
					break;
				}
			}

			scanner.close();

			NodeList nList = doc.getElementsByTagName("Property");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Node node = nNode.getAttributes().getNamedItem("Name");
				Node node2 = nNode.getAttributes().getNamedItem("Value");
				if (node != null && node2 != null) {
					String textContent = node.getTextContent();
					String textContent2 = node2.getTextContent();
					switch (textContent) {
					case "etmMethod":
						etmMethod = convertFirstLetterUpperCase(textContent2);
						text.add("public static String getFindMethod() {	\n return \"" + etmMethod + "\";	}\n");
						break;
					case "translateCols":
						translateCols = convertFirstLetterUpperCase(textContent2);
						text.add("public static String getTransColumns() {	\n return \"" + translateCols + "\";	}\n");
						break;
					case "formatCols":
						formatCols = convertFirstLetterUpperCase(textContent2);
						text.add("public static String getFormatColumns() {	\n return \"" + formatCols + "\";	}\n");
						break;
					case "unitCols":
						unitCols = convertFirstLetterUpperCase(textContent2);
						text.add("public static String getUnitCols() {	\n return \"" + unitCols + "\";	}\n");
						break;
					default:
						break;
					}
				}
			}

			generateAttributeMap(text, columnMap);

			text.add("}");
			Files.write(filePath, text, Charset.forName("UTF-8"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void generateSetterMethod(	List<String> text,
												String type,
												String name) {
		text.add("public void set" + convertFirstLetterUpperCase(name) + "(" + type + " " + name + ") {\n this." + name + " = " + name + ";\n}");

	}

	private static void generateGetterMethod(	List<String> text,
												String type,
												String name) {
		text.add("public " + type + " get" + convertFirstLetterUpperCase(name) + "() {\n	return " + name + ";\n}");
	}

	private static void generateVariable(	List<String> text,
											String type,
											String name) {
		text.add("private " + type + " " + name + ";");
	}

	private static void generateAttributeMap(	List<String> text,
												Map<String, String> columnMap) {
		text.add("public static Map<String, String> loadAttributeMap() {\n		Map<String, String> columnFieldMap = new HashMap<String, String>();");
		for (Map.Entry<String, String> entry : columnMap.entrySet()) {
			text.add("columnFieldMap.put(\"" + entry.getKey() + "\",\"" + entry.getValue() + "\");");
		}
		text.add("return columnFieldMap;\n	}");
	}

	private static String convertFirstLetterUpperCase(String value) {
		return value.substring(0, 1).toUpperCase() + value.substring(1);
	}

	private static void createJavaFile(	List<String> text,
										Path filePath,
										String packageName) throws IOException {
		Files.deleteIfExists(filePath);

		text.add("package " + packageName + ";");

		text.add("import com.sungard.pipeline.model.dto.IDTO;");
		text.add("import java.math.BigDecimal;");
		text.add("import java.util.Date;");
		text.add("import java.util.HashMap;");
		text.add("import java.util.Map;");

		text.add("public class " + filePath.getFileName().toString().replace(".java", "") + " implements IDTO {");
		text.add("\n");
		text.add("private static final long serialVersionUID = 1L;");
		text.add("\n");

	}

}
