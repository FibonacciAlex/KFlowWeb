package com.model.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class XmlUtil {


	public static Document openXml(File xml) {
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(xml);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (doc != null) {
			paramCKInclude(doc.getRootElement());
		}

		return doc;
	}

	public static Document openXml(InputStream srcIn) {
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(srcIn);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (doc != null) {
			paramCKInclude(doc.getRootElement());
		}
		return doc;
	}

	public static Document openXml(String xml) {
		return openXml(new File(xml));
	}

	private static void paramCKInclude(Element root) {
		ArrayList listE = new ArrayList<Element>(root.getChildren());
		for (Object temp : listE) {
			Element tempE = (Element) temp;
			if (!tempE.getName().equals("ck-include")) {
				paramCKInclude(tempE);
			} else {
				paramCKInclude2(root, tempE);
			}
		}
	}

	private static void paramCKInclude2(Element root, Element tempE) {
		int startIndex = root.indexOf(tempE);
		root.removeContent(tempE);

		Document tempdoc = XmlUtil.openXml(tempE.getTextTrim());
		Element temproot = tempdoc.getRootElement();

		ArrayList listE2 = new ArrayList<Element>(temproot.getChildren());
		for (Object temp2 : listE2) {
			Element tempE2 = (Element) temp2;
			temproot.removeContent(tempE2);
			root.addContent(startIndex++, tempE2);
		}
	}

	public static void updateXmlFile(Document ndoc, String xml) {
		XMLOutputter xmlOutputter = new XMLOutputter();
		xmlOutputter.setFormat(Format.getPrettyFormat().setEncoding("utf-8"));
		try {
			xmlOutputter.output(ndoc, new FileOutputStream(xml));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static String doc2String(Document doc) throws Exception {
		Format format = Format.getRawFormat();// .getPrettyFormat();
		format.setEncoding("UTF-8");
		XMLOutputter xmlout = new XMLOutputter(format);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		xmlout.output(doc, bo);
		return bo.toString("UTF-8");
	}
}


