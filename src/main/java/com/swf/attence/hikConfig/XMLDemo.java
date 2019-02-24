package com.swf.attence.hikConfig;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author : white.hou
 * @description : xml测试
 * @date: 2019/2/16_15:49
 */
public class XMLDemo {
    public static void main(String[] args) throws DocumentException, IOException {
      /*  SAXReader saxReader = new SAXReader();
        Document read = saxReader.read(new File(USERDATAPATH));
        Element rootElement = read.getRootElement();
        Element element = (Element) rootElement.selectSingleNode("/FaceAppendData/name");
        element.setText("111");
        System.out.println(element.getText());*/
/*        Document document = DocumentHelper.createDocument();
        Element element = document.addElement("FaceAppendData");
        Element name = element.addElement("name");
        name.setText("white.hou");
        Element certificateNumber = element.addElement("certificateNumber");
        certificateNumber.setText("66666666");
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        outputFormat.setEncoding("utf-8");
        Writer  out ;
        out= new FileWriter("1111.xml");
        XMLWriter xmlWriter = new XMLWriter(out, outputFormat);
        xmlWriter.write(document);
        xmlWriter.close();
        System.out.println("生成");
    }*/
    }
}
