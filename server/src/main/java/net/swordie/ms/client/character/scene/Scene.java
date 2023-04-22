package net.swordie.ms.client.character.scene;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.packet.FieldPacket;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.loaders.EffectData;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.fieldeffect.FieldEffect;
import org.apache.log4j.LogManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * Created by Asura on 28-8-2018.
 */
public class Scene {

    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    private Char chr;
    private String xmlPath;
    private String sceneName;
    private String sceneNumber;
    private List<EffectInfo> effectInfos;

    public Scene(Char chr, String xmlPath, String sceneName, String sceneNumber) {
        this.chr = chr;
        this.xmlPath = xmlPath;
        this.sceneName = sceneName;
        this.sceneNumber = sceneNumber;
        this.effectInfos = new ArrayList<>();
        loadScene();
    }

    public void loadScene() {
        EffectData.getEffectsFromWzPath(this);

        effectInfos = effectInfos.stream().sorted(Comparator.comparingInt(EffectInfo::getZ)).collect(Collectors.toList());
    }

    public void createScene() {
        for (EffectInfo ei : effectInfos) {

            if(ei.getType() == null) {
                log.error(String.format("Unhandled scene Type"));
                continue;
            }

            int delay = ei.getStart();
            String path = ei.getVisual() + "/0";
            switch (ei.getType()) {
                case FieldEffect:
//                    if(ei.getX() == 0 && ei.getY() == 0) {
                        EventManager.addEvent(() ->
                                chr.write(FieldPacket.fieldEffect(FieldEffect.getFieldEffectFromWz(
                                        path,
                                        0
                                ))), delay, TimeUnit.MILLISECONDS);
/*                    } else { // TODO  Still needs some work

                        Position position = new Position(ei.getX(), ei.getY());
                        EventManager.addEvent(() ->
                                chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.effectPlay(
                                        path,
                                        ei.getDuration(),
                                        position,
                                        ei.getZ(),
                                        0,
                                        true,
                                        0
                                ))), delay, TimeUnit.MILLISECONDS); // Delay for getFieldEffFromWz doesn't work
                    }
*/
                    break;

                case Warp:
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(ei.getField());
                    if(delay > 0) {
                        EventManager.addEvent(() -> chr.warp(toField), delay, TimeUnit.MILLISECONDS);
                    } else {
                        chr.warp(toField);
                    }
                    break;

                case playBGM:
                    // TODO
                    break;

                case unknown:
                    // TODO
                    break;
            }
        }
    }


    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getSceneNumber() {
        return sceneNumber;
    }

    public void setSceneNumber(String sceneNumber) {
        this.sceneNumber = sceneNumber;
    }

    public List<EffectInfo> getEffectInfos() {
        return effectInfos;
    }

    public void setEffectInfos(List<EffectInfo> effectInfos) {
        this.effectInfos = effectInfos;
    }

    public void addEffectInfo(EffectInfo effectInfo) {
        getEffectInfos().add(effectInfo);
    }

    public Char getChr() {
        return chr;
    }

    public void setChr(Char chr) {
        this.chr = chr;
    }

    public int getTransferField() {
        for (EffectInfo effect : getEffectInfos()) {
            if (effect.getField() != 0) {
                return effect.getField();
            }
        }
        return 0;
    }
}
