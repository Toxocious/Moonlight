package net.swordie.ms.util.dsl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 3/28/2018.
 */
public class SWEntity {
    private Map<String, String> propertyValues = new HashMap<>();
    private Map<String, SWEntity> entities = new HashMap<>();

    public Map<String, String> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(Map<String, String> propertyValues) {
        this.propertyValues = propertyValues;
    }

    public void putPropertyValue(String key, String value) {
        getPropertyValues().put(key, value);
    }

    public Map<String, SWEntity> getEntities() {
        return entities;
    }

    public void setEntities(Map<String, SWEntity> entities) {
        this.entities = entities;
    }

    public void putEntity(String key, SWEntity value) {
        getEntities().put(key, value);
    }

    public SWEntity deepCopy() {
        SWEntity entity = new SWEntity();
        getPropertyValues().forEach(entity::putPropertyValue);
        getEntities().forEach((key, value) -> entity.putEntity(key, value.deepCopy()));
        return entity;
    }
}
