package com.bow.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * @author vv
 * @since 2017/12/24.
 */
@XStreamAlias("node")
public class Node {

    public Node(String path,String desc){
        this.path = path;
        this.desc = desc;
    }

    @XStreamAsAttribute
    private String path;

    @XStreamAsAttribute
    private String desc;

    @XStreamImplicit(itemFieldName = "property")
    private List<Property> properties;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
