package net.swordie.ms.world.shop.result;

import net.swordie.ms.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 3/29/2018.
 */
public enum ShopResultType {
    Buy(0),
    NotEnoughInStockMsg(1), // int
    NotEnoughMesosMsg(2),
    NotEnoughCoinsMsg(3),
    RequireFloorEnterMsg(4), // int
    DoNotMeetQuestReqMsg(5),
    DoNotMeetReqsMsg(6), // int
    CannotBePurchasedRnMsg(7),
    // 8 int(0~3) 0: 1475 1~3: 15160
    MustBeRankToUse(9), // int
    FullInvMsg(10),
    Update(11),
    VendorNotEnoughInStockMsg(12),
    GeneralErrorMsg(13),
    CannotHoldMoreMesosMsg(14),
    MesoCapPerTransaction2mMsg(15),
    TooManyMesosMsg(16),
    Success(17),
    NotEnoughMesosMsg2(18),
    NeedMoreItemsMsg(21),
    NotEnoughStarCoinsMsg(22),
    MustBeUnderLevelMsgInt(23),
    MustBeOverLevelMsgInt(24),
    ItemPurchaseDateExpiredMsg(25),
    ItemHasBeenOutstockedMsgInt(26),
    CanOnlyBeBoughtOneByOneMsg(27),
    CannotBeMovedMsg(28),
    ShopRestockedMsgInt(29),
    CanOnlyPurchaseXMoreMsgInt(30),
    InactiveIPThereforeNoTradeMsg1Int(32),
    DifferentIPSoNoTradeAfterXMinMsgInt(33),
    CannotBeUsedMsg(34),
    CannotBeDoneOnThisWorldMsgByte(35),
    ItemDetailsChangedMsg(36),
    CannotBePurchaseAgain(37),
    CannotBeSoldMsg1(38),
    CannotSellAnyMoreMsg(39),
    CannotBeSoldMsg2(40),
    Below15LimitMsg(41),
    ErrorMsg(42), // if customized(bool) -> str
    TransactionCannotBeMadeMsg(43),
    No(44)
    ;

    private int val;

    ShopResultType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }




    public static void main(String[] args) {
        File file = new File("D:\\dev213MS\\dev213UTD\\src\\main\\java\\net\\dev213\\ms\\handlers\\header\\OutHeader.java");
        int change = 1;
        boolean check = false;
        ShopResultType checkOp = null;
        try (Scanner s = new Scanner(file)) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.contains(",") && line.contains("(")) {
                    String[] split = line.split("[()]");
                    String name = split[0];
                    if (!Util.isNumber(split[1])) {
                        System.out.println(line);
                        continue;
                    }
                    int val = Integer.parseInt(split[1]);
                    ShopResultType oh = Arrays.stream(ShopResultType.values()).filter(o -> o.toString().equals(name.trim())).findFirst().orElse(null);
                    if (oh != null) {
                        ShopResultType start = VendorNotEnoughInStockMsg;
                        if (oh.ordinal() >= start.ordinal() && oh.ordinal() < No.ordinal()) {
                            if (line.contains("*")) {
                                check = true;
                                checkOp = oh;
                            }
                            val += change;
                            System.out.println(String.format("%s(%d), %s", name, val, start == oh ? "// *" : ""));
                        } else {
                            System.out.println(line);
                        }
                    }
                } else {
                    System.out.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (check) {
            System.err.println(String.format("Current op (%s) contains a * (= updated). Be sure to check for overlap.", checkOp));
        }

    }
}