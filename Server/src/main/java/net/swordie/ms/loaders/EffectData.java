package net.swordie.ms.loaders;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.client.character.scene.EffectInfo;
import net.swordie.ms.client.character.scene.Scene;
import net.swordie.ms.enums.SceneType;
import net.swordie.ms.util.XMLApi;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.io.File;
import java.util.Map;

/**
 * Created by Asura on 28-8-2018.
 */
public class EffectData {

    public static void getEffectsFromWzPath(Scene scene) {
        String xmlPath = scene.getXmlPath();
        String sceneName = scene.getSceneName();
        String sceneNumber = scene.getSceneNumber();

        Node sceneNameNode = XMLApi.getNodeByPath(xmlPath, sceneName); // Scene Name  Node

        Node sceneNumberNode = XMLApi.getFirstChildByNameBF(sceneNameNode, sceneNumber); // Scene
        for (Node individualEffect : XMLApi.getAllChildren(sceneNumberNode)) {
            EffectInfo ei = new EffectInfo();
            for(Node eiInfo : XMLApi.getAllChildren(individualEffect)) {
                Map<String, String> attributes = XMLApi.getAttributes(eiInfo);
                String name = attributes.get("name");
                String value = attributes.get("value");
                switch (name) {
                    case "type":
                        ei.setType(SceneType.getByVal(Integer.parseInt(value)));
                        break;
                    case "visual":
                        ei.setVisual(value);
                        break;
                    case "start":
                        ei.setStart(Integer.parseInt(value));
                        break;
                    case "field":
                        if (value == null) {
                            ei.setField(-1);
                        } else {
                            ei.setField(Integer.parseInt(value));
                        }
                        break;
                    case "x":
                        ei.setX(Integer.parseInt(value));
                        break;
                    case "y":
                        ei.setY(Integer.parseInt(value));
                        break;
                    case "z":
                        ei.setZ(Integer.parseInt(value));
                        break;
                }
            }

            if(ei.getVisual() != null) {
                String effectPath = ei.getVisual().replace("Effect/", "") + "/0";
                String[] splitEffectPath = effectPath.split("/");
                String wzDir = ServerConstants.WZ_DIR + "/Effect.wz/" + splitEffectPath[0] + ".xml";
                File dir = new File(wzDir);

                Document doc = XMLApi.getRoot(dir);
                Node node = XMLApi.getAllChildren(doc).get(0);
                for (String indiEffPath : splitEffectPath) {
                    if (indiEffPath.contains(".img")) {
                        continue;
                    }
                    node = XMLApi.getFirstChildByNameBF(node, indiEffPath);
                }
                int duration = 0;
                for (Node n : XMLApi.getAllChildren(node)) {
                    for (Node infoNode : XMLApi.getAllChildren(n)) {
                        Map<String, String> attr = XMLApi.getAttributes(infoNode);
                        String name = attr.get("name");
                        String value = attr.get("value");
                        switch (name) {
                            case "delay":
                                duration += Integer.parseInt(value);
                                break;
                        }
                    }
                }
                ei.setDuration(duration);
            }
            scene.addEffectInfo(ei);
        }
    }
}
