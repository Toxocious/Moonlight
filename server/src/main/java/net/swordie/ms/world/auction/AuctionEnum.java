package net.swordie.ms.world.auction;

import net.swordie.ms.client.character.items.Item;

/**
 * @author Sjonnie
 * Created on 11/21/2018.
 */
public interface AuctionEnum {
    AuctionEnum getSubByVal(int val);
    boolean isMatching(Item item);
}
