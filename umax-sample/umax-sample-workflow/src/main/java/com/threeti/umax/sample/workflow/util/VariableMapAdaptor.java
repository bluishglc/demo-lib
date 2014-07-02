package com.threeti.umax.sample.workflow.util;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class VariableMapAdaptor extends XmlAdapter<VariableMap, Map<String, Object>> {

	@Override
	public Map<String, Object> unmarshal(VariableMap v) throws Exception {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        for (KeyValueEntry entry : v.getEntries()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
	}

	@Override
	public VariableMap marshal(Map<String, Object> v) throws Exception {
		VariableMap variables = new VariableMap();
        for (Map.Entry<String, Object> e : v.entrySet()) { 
        	KeyValueEntry keyValueEntry = new KeyValueEntry();
        	keyValueEntry.setValue(e.getValue());
        	keyValueEntry.setKey(e.getKey());
        	variables.addEntry(keyValueEntry);
        }
        return variables; 
	}

}
