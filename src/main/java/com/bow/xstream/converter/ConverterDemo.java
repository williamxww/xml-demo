package com.bow.xstream.converter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bow.jdk.DocumentDemo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

/**
 * 自定义converter demo
 * 
 * @author vv
 * @since 2018/1/18.
 */
public class ConverterDemo {

    private XStream xstream;

    public ConverterDemo() {
        xstream = new XStream();
        xstream.autodetectAnnotations(true);
        xstream.processAnnotations(Item.class);
        xstream.registerConverter(new ItemConverter());

        // clear out existing permissions and set own ones
        xstream.addPermission(NoTypePermission.NONE);
        // allow some basics
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);
        Class<?>[] classes = new Class[] { Item.class };
        xstream.allowTypes(classes);
    }

    public static void main(String[] args) {
        ConverterDemo demo = new ConverterDemo();
        // System.out.println(demo.marshal());
        demo.unmarshal();
    }

    public String marshal() {
        Item root = new Item();
        root.setName("root");
        Item lva = new Item();
        lva.setName("level a");
        List<Integer> types = new ArrayList<>();
        types.add(1);
        types.add(2);
        lva.setTypes(types);
        Item lvb = new Item();
        lvb.setName("level b");

        List<Item> children = new ArrayList<>();
        children.add(lva);
        children.add(lvb);
        root.setItems(children);
        return xstream.toXML(root);
    }

    private void unmarshal() {
        InputStream is = DocumentDemo.class.getClassLoader().getResourceAsStream("items.xml");
        Item item = (Item) xstream.fromXML(is);
        System.out.println(item.getItems().size());
    }
}
