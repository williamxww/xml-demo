package com.bow.dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * @author vv
 * @since 2017/12/24.
 */
public class Demo {

    public static void main(String[] args) throws Exception {

        Demo demo = new Demo();
        demo.modifyAttr();

    }

    private void test()throws Exception{
        SAXReader xmlReader = new SAXReader();
        File configXml = new File("output/config.xml");
        Document config = xmlReader.read(configXml);
        List<Node> blocks = config.selectNodes("/config/block");
        for (Node block : blocks) {
            System.out.println(block.valueOf("@desc"));
            List<Node> nodes = block.selectNodes("node");
            for(Node node : nodes){
                List<Node> properties = node.selectNodes("property");
                for(Node property : properties){
                    System.out.println(property.valueOf("@key"));
                }
            }
        }
    }

    private void modifyAttr()throws Exception {
        SAXReader xmlReader = new SAXReader();
        File configXml = new File("output/config.xml");
        Document config = xmlReader.read(configXml);

        String blockId = "/config/block[@path='/user-manager']";
        String nodeId = "/node[@path='/cache.properties']";
        String propsId = "/property[@key='report.daily.num']";
        String attrId = "/@value";
        Node attr = config.selectSingleNode(blockId+nodeId+propsId+attrId);

        FileWriter fileWriter = new FileWriter(configXml);
        XMLWriter writer = new XMLWriter(fileWriter);

        while(true){
            System.out.println("Input change value ");
            String value = new BufferedReader(new InputStreamReader(System.in)).readLine();
            Attribute attribute = (Attribute) attr;
            attribute.setValue(value);
            attribute.write(fileWriter);
            System.out.println(attribute.getValue());
//            writer.write(config);
            fileWriter.flush();
        }


//        writer.close();
    }


    private void sync(Document document) throws Exception {
        File configXml = new File("output/config.xml");
        FileWriter fileWriter = new FileWriter(configXml);
        XMLWriter writer = new XMLWriter(fileWriter);
        writer.write(document);
        writer.flush();
        writer.close();
    }
}
