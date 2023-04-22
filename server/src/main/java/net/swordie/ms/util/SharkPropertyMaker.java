package net.swordie.ms.util;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.handlers.header.OutHeader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author Sjonnie
 * Created on 1/11/2019.
 */
public class SharkPropertyMaker {

    private static void createOutPropertyFile(File output, OutHeader[] objects) {
        try (PrintWriter pw = new PrintWriter(output)) {
            for (OutHeader obj : objects) {
                pw.println(String.format("%s = %d", obj, obj.getValue()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void createInPropertyFile(File output, InHeader[] objects) {
        try (PrintWriter pw = new PrintWriter(output)) {
            for (InHeader obj : objects) {
                pw.println(String.format("%s = %d", obj, obj.getValue()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Util.makeDirIfAbsent("properties");
        createOutPropertyFile(new File(String.format("properties/LP_%d-%s.properties",
                ServerConstants.VERSION, ServerConstants.MINOR_VERSION)), OutHeader.values());
        createInPropertyFile(new File(String.format("properties/CP_%d-%s.properties",
                ServerConstants.VERSION, ServerConstants.MINOR_VERSION)), InHeader.values());
    }
}
