package com.bow.jdk;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author vv
 * @since 2018/1/18.
 */
public class DocumentDemo {


    public static void main(String[] args) throws Exception {
        InputStream is = DocumentDemo.class.getClassLoader().getResourceAsStream("./config.xml");
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        Element root = doc.getDocumentElement();
        NodeList blocks = root.getChildNodes();
        for(int i=0; i<blocks.getLength(); i++){
            Node block = blocks.item(i);
            if(block.getNodeType() != Element.ELEMENT_NODE){
                //说明不是元素节点不处理
                continue;
            }
            System.out.println(block.getNodeName());
            NamedNodeMap attrs = block.getAttributes();
            if(attrs != null){
                Node desc = attrs.getNamedItem("desc");
                System.out.println("attr --"+desc.getNodeValue());
            }
        }
    }
}
