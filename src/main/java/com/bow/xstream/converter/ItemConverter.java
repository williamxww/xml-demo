package com.bow.xstream.converter;

import java.util.ArrayList;
import java.util.List;

import com.bow.xstream.StringUtil;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author vv
 * @since 2018/1/18.
 */
public class ItemConverter implements Converter {

    private static final String ITEM = "item";

    private static final String NAME = "name";

    private static final String TYPE = "type";

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Item item = (Item) source;
        marshal(item, writer);
    }

    private void marshal(Item item, HierarchicalStreamWriter writer) {
        if (item.getName() != null) {
            writer.addAttribute(NAME, item.getName());
        }

        if (item.getTypes() != null) {
            writer.addAttribute(TYPE, StringUtil.join(item.getTypes(), ","));
        }

        // 递归写子节点
        if (item.getItems() != null) {
            for (Item child : item.getItems()) {
                writer.startNode(ITEM);
                marshal(child, writer);
                writer.endNode();
            }
        }
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        return unmarshal(reader);
    }

    private Item unmarshal(HierarchicalStreamReader reader) {
        Item item = new Item();
        item.setName(reader.getAttribute(NAME));

        String typeStr = reader.getAttribute(TYPE);
        if (StringUtil.isNotEmpty(typeStr)) {
            String[] typeAry = StringUtil.split(typeStr, ",");
            List<Integer> types = new ArrayList<>();
            for (String type : typeAry) {
                types.add(StringUtil.toInt(type));
            }
            item.setTypes(types);
        }

        List<Item> children = new ArrayList<>();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            Item child = unmarshal(reader);
            children.add(child);
            reader.moveUp();
        }
        if (children.size() > 0) {
            item.setItems(children);
        }
        return item;
    }

    @Override
    public boolean canConvert(Class type) {
        return Item.class == type;
    }
}
