package net.swordie.ms.connection.db.converters;


import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sjonnie
 * Created on 12/19/2018.
 */
public class InlinedIntArrayConverter implements AttributeConverter<List<Integer>, String> {
    @Override
    public String convertToDatabaseColumn(List<Integer> integers) {
        StringBuilder sb = new StringBuilder();
        if (integers == null) {
            return null;
        }
        for (int i : integers) {
            sb.append(i).append(",");
        }
        String res;
        if (integers.size() > 0) {
            res = sb.toString().substring(0, sb.length() - 1); // removes last comma
        } else {
            res = "";
        }
        return res;
    }

    @Override
    public List<Integer> convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        String[] split = s.split(",");
        List<Integer> ints = new ArrayList<>();
        if (split.length > 0 && split[0].length() > 0) {
            for (String str : split) {
                ints.add(Integer.valueOf(str));
            }
        }
        return ints;
    }
}
