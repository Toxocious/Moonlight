package net.swordie.ms.connection.db.converters;

import javax.persistence.AttributeConverter;
import java.util.HashMap;
import java.util.Map;

public class InlinedIntMapConverter implements AttributeConverter<Map<Integer, Integer>, String> {
    private static final String ENTRY_SPLIT = "=";
    private static final String ATTR_SPLIT = ";";
    private static final String NULL = "null";

    @Override
    public String convertToDatabaseColumn(Map<Integer, Integer> integerIntegerMap) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : integerIntegerMap.entrySet()) {
            sb.append(entry.getKey()).append(ENTRY_SPLIT).append(entry.getKey()).append(ATTR_SPLIT);
        }
        return sb.toString();
    }

    @Override
    public Map<Integer, Integer> convertToEntityAttribute(String s) {
        Map<Integer, Integer> resMap = new HashMap<>();
        String[] entries = s.split(ATTR_SPLIT);
        for (String entry : entries) {
            String[] e = entry.split(ENTRY_SPLIT);
            int key = Integer.parseInt(e[0]);
            if (NULL.equalsIgnoreCase(e[1])) {
                resMap.put(key, null);
            } else {
                resMap.put(key, Integer.parseInt(e[1]));
            }
        }
        return resMap;
    }
}
