package net.swordie.ms.world.auction.weapon;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.world.auction.AuctionEnum;

/**
 * @author Sjonnie
 * Created on 11/21/2018.
 */
public enum AuctionSecondaryType implements AuctionEnum {
    All,
    Medal,
    Rosary,
    IronChain,
    Spellbook,
    ArrowFletcing,
    BowThimble,
    DaggerScabbard,
    Charm,
    WristBand,
    FarSight,
    PowderKeg,
    Jewel,
    Ballast,
    Document,
    Orb,
    MagicArrow,
    Card,
    FoxMarble,
    MagicMarble,
    Arrowhead,
    Controller,
    Magnum,
    NovaEssence,
    SoulRing,
    ChessPiece,
    Charge,
    WarpForge,
    LucentWings,
    Wakizashi,
    Whisper,
    Fist,
    Pass,
    ;

    public static AuctionEnum getByVal(int val) {
        return values()[val];
    }

    @Override
    public AuctionEnum getSubByVal(int val) {
        return null;
    }

    @Override
    public boolean isMatching(Item item) {
        int itemID = item.getItemId();
        int prefix = itemID / 10000;
        switch (this) {
            case All:
                // TODO add everything from below
                return true;
            case Medal:
                return ItemConstants.isHeroMedal(itemID);
            case Rosary:
                return ItemConstants.isPaladinRosario(itemID);
            case IronChain:
                return ItemConstants.isDarkKnightChain(itemID);
            case Spellbook:
                return ItemConstants.isIlBook(itemID) || ItemConstants.isFpBook(itemID) || ItemConstants.isClericBook(itemID);
            case ArrowFletcing:
                return ItemConstants.isBowmasterFeather(itemID);
            case BowThimble:
                return ItemConstants.isCrossbowMasterThimble(itemID);
            case DaggerScabbard:
                return ItemConstants.isShadowerSheath(itemID);
            case Charm:
                return ItemConstants.isNightlordPouch(itemID);
            case WristBand:
                return ItemConstants.isViperWristband(itemID);
            case FarSight:
                return ItemConstants.isCaptainSight(itemID);
            case PowderKeg:
                return ItemConstants.isCannonGunpowder(itemID);
            // TODO
            case Jewel:
            case Ballast:
            case Document:
            case Orb:
            case MagicArrow:
            case Card:
            case FoxMarble:
            case MagicMarble:
            case Arrowhead:
            case Controller:
            case Magnum:
            case NovaEssence:
            case SoulRing:
            case ChessPiece:
            case Charge:
            case WarpForge:
            case LucentWings:
            case Wakizashi:
            case Whisper:
            case Fist:
            case Pass:
                return false;
        }
        return false;
    }
}
