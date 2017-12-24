package com.bow.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * @author vv
 * @since 2017/12/24.
 */
@XStreamAlias("config")
public class Config {


    @XStreamImplicit(itemFieldName = "block")
    private List<Block> blocks;

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
}
