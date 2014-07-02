package com.threeti.umax.sample.workflow.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "VariableMap")
@XmlAccessorType(XmlAccessType.FIELD)
public class VariableMap {
	
    @XmlElement(nillable = false, name = "entry")
    List<KeyValueEntry> entries = new ArrayList<KeyValueEntry>();

    public List<KeyValueEntry> getEntries() {
        return entries;
    }
    
    public void addEntry(KeyValueEntry entry) {
    	entries.add(entry);
    }
}
