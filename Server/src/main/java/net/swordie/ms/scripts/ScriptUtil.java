package net.swordie.ms.scripts;

public class ScriptUtil {
    public String getItemImg(int id) {
        return "#i" + id + "#";
    }

    public String getItemName(int id) {
        return "#t" + id + "#";
    }

    public String getNpcIcon(int id) {
        return "#p" + id + "#";
    }

    public String getMapIcon(int id) {
        return "#m" + id + "#";
    }

    public String getMapName(int id) {
        return "#z" + id + "#";
    }

    //icono de la cara
    public String getFaceIcon(int id) {
        return "#f" + id + "#";
    }

    public String getMobName(int id) {
        return "#o" + id + "#";
    }

    public String getSkillName(int id) {
        return "#s" + id + "#";
    }

    public String getQuestName(int id) {
        String idStr = String.valueOf(id);
        //remove first and last character
        idStr = idStr.substring(1).substring(0, idStr.length() - 1);


        return "#q" + idStr + "#";
    }

    public String getItemFull(int id) {
        return getItemImg(id) + " " + getItemName(id);
    }
}
