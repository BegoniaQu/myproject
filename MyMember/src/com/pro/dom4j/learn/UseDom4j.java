package com.pro.dom4j.learn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class UseDom4j {
	// ���xml�ļ�
	public static int createXmlFile(String fileName) {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("customer");
		Element eleInfo = root.addElement("info");
		eleInfo.addAttribute("name", "andrew");
		Element eleNation = eleInfo.addElement("nationality");
		eleNation.addText("China");
		Element eleAge = eleInfo.addElement("marital");
		eleAge.addText("no");
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(
					new FileWriter(new File(fileName)), format);

			writer.write(doc);
			writer.flush();
			writer.close();
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			return 1;
		}
	}

	public static void main(String[] args) {
		System.out.println(createXmlFile("testdom4j.xml"));
	}
}
