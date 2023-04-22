package net.swordie.ms.client.character.quest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MechAviv on 2/27/2019.
 */
public class QuestEx {
    private int questID;
    private String qrValue = "";
    private Map<String, String> values;

    public QuestEx(int questID) {
        this.questID = questID;
        this.values = new HashMap<>();
    }

    public Map<String, String> getValues() {
        return values;
    }

    public boolean setValue(String key, String value) {
        if (key == null || key.isEmpty() || key.equals("")) {
            return false;
        }
        if (value != null && !value.isEmpty() && !value.equals("")) {
            values.put(key, value);
        } else {
            values.remove(key);
        }
        return true;
    }

    public String getValue(String key) {
        return values.getOrDefault(key, null);
    }

    public int getCount() {
        if (values == null) {
            return 0;
        }
        return values.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Map<String, String> vals = (Map<String, String>) obj;
        if (vals.size() != values.size()) {
            return false;
        }
        return values.entrySet().stream().filter(value -> vals.entrySet().stream().anyMatch(value1 -> (value1.getKey().equals(value.getKey()) && value1.getValue().equals(value.getValue())))).findAny().isPresent();
    }

    public int getQuestID() {
        return questID;
    }

    public void setQuestID(int questID) {
        this.questID = questID;
    }
}
