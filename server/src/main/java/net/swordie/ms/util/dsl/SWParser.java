package net.swordie.ms.util.dsl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Basically YAML but more compact.
 * Created on 3/28/2018.
 */
public class SWParser {
    private static final String OPEN_SCOPE = "{";
    private static final String CLOSE_SCOPE = "}";
    private static final String END_VAR = ";";

    public SWEntity parse(File file) {
        // Reinventing the wheel, but it works. Antlr is overrated Kappa
        if(!file.exists()) {
            return null;
        }
        String curName = null;
        Stack<String> nameStack = new Stack<>();
        Stack<SWEntity> entityStack = new Stack<>();
        SWEntity entity = new SWEntity();
        try (Scanner s = new Scanner(file)) {
            while(s.hasNext()) {
                String word = s.next();
                if(word.startsWith("//")) {
                    s.nextLine();
                } else if(word.contains(OPEN_SCOPE)){
                    nameStack.push(curName);
                    entityStack.push(entity);
                    curName = word.replace(OPEN_SCOPE, "");
                    entity = new SWEntity();
                } else if(word.equalsIgnoreCase(CLOSE_SCOPE)) {
                    String name = nameStack.pop();
                    SWEntity ent = entityStack.pop();
                    ent.putEntity(curName, entity.deepCopy());
                    curName = name;
                    entity = ent;
                } else {
                    String property = null;
                    StringBuilder value = new StringBuilder();
                    boolean stop = false;
                    while(!word.equalsIgnoreCase(END_VAR) && !stop) {
                        if(property == null) {
                            property = word;
                        } else {
                            if(word.contains(END_VAR)) {
                                stop = true;
                                word = word.replace(END_VAR, "");
                            } else {
                                value.append(" ");
                            }
                            value.append(word);
                        }
                        if(!stop) {
                            word = s.next();
                        }
                    }
                    entity.putPropertyValue(property, value.toString());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
