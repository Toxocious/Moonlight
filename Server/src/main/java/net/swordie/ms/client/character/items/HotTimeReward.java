package net.swordie.ms.client.character.items;

import net.swordie.ms.connection.db.converters.FileTimeConverter;
import net.swordie.ms.enums.HotTimeRewardType;
import net.swordie.ms.util.FileTime;

import javax.persistence.*;

@Entity
@Table(name = "hottimerewards")
public class HotTimeReward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    protected int charId;
    @Convert(converter = FileTimeConverter.class)
    private FileTime startTime = FileTime.currentTime();
    @Convert(converter = FileTimeConverter.class)
    private FileTime endTime = FileTime.currentTime();
    @Enumerated(EnumType.ORDINAL)
    protected HotTimeRewardType rewardType;
    protected int itemId;
    protected int quantity;
    protected int maplePointAmount;
    protected int mesoAmount;
    protected int expAmount;
    @Transient
    protected int resultType;

    protected String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCharId() {
        return charId;
    }

    public void setCharId(int charId) {
        this.charId = charId;
    }



    public FileTime getStartTime() {
        return startTime;
    }

    public void setStartTime(FileTime startTime) {
        this.startTime = startTime;
    }

    public FileTime getEndTime() {
        return endTime;
    }

    public void setEndTime(FileTime endTime) {
        this.endTime = endTime;
    }

    public HotTimeRewardType getRewardType() {
        return rewardType;
    }

    public void setRewardType(HotTimeRewardType rewardType) {
        this.rewardType = rewardType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMaplePointAmount() {
        return maplePointAmount;
    }

    public void setMaplePointAmount(int maplePointAmount) {
        this.maplePointAmount = maplePointAmount;
    }

    public int getMesoAmount() {
        return mesoAmount;
    }

    public void setMesoAmount(int mesoAmount) {
        this.mesoAmount = mesoAmount;
    }

    public int getExpAmount() {
        return expAmount;
    }

    public void setExpAmount(int expAmount) {
        this.expAmount = expAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getResultType() {
        return resultType;
    }

    public void setResultType(int resultType) {
        this.resultType = resultType;
    }
}
