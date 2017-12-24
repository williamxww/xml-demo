package com.bow.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * @author vv
 * @since 2017/12/24.
 */
@XStreamAlias("block")
public class Block {

    public Block(String path,String desc){
        this.path = path;
        this.desc = desc;
    }

    @XStreamAsAttribute
    private String path;

    @XStreamAsAttribute
    private String desc;

    @XStreamImplicit
    private List<Node> nodes;

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

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
