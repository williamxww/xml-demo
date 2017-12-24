package com.bow.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author vv
 * @since 2017/12/24.
 */
@XStreamAlias("property")
public class Property {

    public Property(){

    }

    public Property(String key,String value,String desc){
        this.key = key;
        this.value = value;
        this.desc = desc;
    }

    @XStreamAsAttribute
    private String key;

    @XStreamAsAttribute
    private String value;

    @XStreamAsAttribute
    private String desc;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
