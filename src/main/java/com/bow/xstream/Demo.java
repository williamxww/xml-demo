package com.bow.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.util.Collection;
import java.util.Collections;

/**
 * @author vv
 * @since 2017/12/24.
 */
public class Demo {

    private XStream xstream;

    public Demo() {
        xstream = new XStream();
        xstream.autodetectAnnotations(true);
        xstream.processAnnotations(Config.class);

        // clear out existing permissions and set own ones
        xstream.addPermission(NoTypePermission.NONE);
        // allow some basics
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);
        Class<?>[] classes = new Class[] { Config.class, Block.class, Node.class, Property.class };
        xstream.allowTypes(classes);
        // allow any type from the same package
        // xstream.allowTypesByWildcard(new String[] { "com.your.package.**" });
    }

    private Config build() {
        Property property = new Property("cache.duration", "10", "缓存时长");

        Node node = new Node("cache", "缓存配置");
        node.setProperties(Collections.singletonList(property));

        Block block = new Block("user", "用户管理");
        block.setNodes(Collections.singletonList(node));

        Config config = new Config();
        config.setBlocks(Collections.singletonList(block));
        return config;
    }

    private String marshal(Config config) {

        String resultXml = xstream.toXML(config);
        return resultXml;
    }

    private Config unmarshal(String xmlStr) {
        Config config = (Config) xstream.fromXML(xmlStr);
        return config;
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        Config config = demo.build();
        String xml = demo.marshal(config);
        System.out.println(xml);
        Config r = demo.unmarshal(xml);
        System.out.println(r.getBlocks().get(0).getPath());
    }
}
