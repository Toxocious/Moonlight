package net.swordie.ms.world.auction.use;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.world.auction.AuctionEnum;

/**
 * @author Sjonnie
 * Created on 1/18/2019.
 */
public enum AuctionUseType implements AuctionEnum {

    All,
    Recovery,
    Alchemy,
    Scroll,
    ProfessionRecipe,
    SkillBook,
    Etc;

    public static AuctionUseType getByVal(int val) {
        return val >= 0 && val < values().length ? values()[val] : All;
    }

    @Override
    public AuctionEnum getSubByVal(int val) {
        switch (this) {
            case All:
                return All;
            case Recovery:
                return AuctionRecoveryType.getByVal(val);
            case Alchemy:
                return AuctionAlchemyType.getByVal(val);
            case Scroll:
                return AuctionScrollType.getByVal(val);
            case ProfessionRecipe:
                return AuctionRecipeType.getByVal(val);
            case SkillBook:
                return AuctionSkillBookType.getByVal(val);
            case Etc:
                return AuctionConsumeEtcType.getByVal(val);
        }
        return null;
    }

    @Override
    public boolean isMatching(Item item) {
        int itemID = item.getItemId();
        switch (this) {
            case All:
                return ItemConstants.isConsume(itemID);
            case Recovery:
                return ItemConstants.isRecoveryItem(itemID);
            case Alchemy:
                return ItemConstants.isRecoveryItem(itemID);
            case Scroll:
                return ItemConstants.isScroll(itemID);
            case ProfessionRecipe:
                return ItemConstants.isRecipeOpenItem(itemID);
            case SkillBook:
                return ItemConstants.isSkillBook(itemID);
            case Etc:
                return !(ItemConstants.isRecoveryItem(itemID) || ItemConstants.isRecoveryItem(itemID)
                        || ItemConstants.isScroll(itemID) || ItemConstants.isRecipeOpenItem(itemID)
                        || ItemConstants.isSkillBook(itemID));
        }
        return false;
    }
}
