package net.swordie.ms.scripts;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.FieldInstanceType;
import net.swordie.ms.world.field.Instance;

/**
 * @author Sjonnie
 * Created on 1/22/2019.
 */
public class FieldTransferInfo {
    private int fieldId, portal, channel;
    private boolean changeChannel, init;
    private boolean isInstanceField, field;

    public FieldTransferInfo() {
        init = true;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        init = false;
        this.fieldId = fieldId;
    }

    public int getPortal() {
        return portal;
    }

    public void setPortal(int portal) {
        this.portal = portal;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public boolean isChangeChannel() {
        return changeChannel;
    }

    public void setChangeChannel(boolean changeChannel) {
        this.changeChannel = changeChannel;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public void warp(Char chr) {
        setInit(true);
        if (isInstanceField()) {
            chr.setInstance(new Instance(chr));
        }
        if (isChangeChannel()) {
            chr.changeChannelAndWarp((byte) getChannel(), getFieldId());
        } else {
            chr.warp(getFieldId(), getPortal());
        }
        chr.getField().setChangeToChannelOnLeave(isInstanceField());
    }

    public void warp(Field field) {
        setInit(true);
        for (Char chr : field.getChars()) {
            chr.warp(getFieldId(), getPortal());
        }
    }

    public void setIsInstanceField(boolean isInstanceField) {
        this.isInstanceField = isInstanceField;
    }

    public boolean isInstanceField() {
        return isInstanceField;
    }

    public boolean isField() {
        return field;
    }

    public void setField(boolean field) {
        this.field = field;
    }
}
