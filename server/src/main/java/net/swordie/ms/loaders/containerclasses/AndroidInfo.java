package net.swordie.ms.loaders.containerclasses;

import java.util.ArrayList;
import java.util.List;

public class AndroidInfo {
    private List<Integer> hairs = new ArrayList<>();
    private List<Integer> faces = new ArrayList<>();
    private List<Integer> skins = new ArrayList<>();
    private int id;

    public AndroidInfo(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addHair(int hair) {
        getHairs().add(hair);
    }

    public void addFace(int face) {
        getFaces().add(face);
    }

    public void addSkin(int skin) {
        getSkins().add(skin);
    }

    public List<Integer> getHairs() {
        return hairs;
    }

    public List<Integer> getFaces() {
        return faces;
    }

    public List<Integer> getSkins() {
        return skins;
    }
}
