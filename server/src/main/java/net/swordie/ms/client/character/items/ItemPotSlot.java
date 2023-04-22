package net.swordie.ms.client.character.items;

import net.swordie.ms.util.FileTime;

/**
 * Created on 12/14/2017.
 */
public class ItemPotSlot {
    private int lifeId;
    private byte level;
    private byte lastState;
    private int satiety;
    private int friendly;
    private int friendlyremainAbleFriendly;
    private int remainFriendlyTime;
    private byte maximumIncLevel;
    private byte maximumIncSatiety;
    private FileTime lastEatTime;
    private FileTime lastSleepStartTime;
    private FileTime lastDecSatietyTime;
    private FileTime consumedLastTime;
}
