# horntail - Cave of Life - Cave of trial 1 & 2
from net.swordie.ms.constants import BossConstants

if sm.getFieldID() == 240060000:
        if sm.getChr().getField().getMobs().size() == 0:
            sm.warp(240060100, True)
            sm.setInstanceTime(BossConstants.HORNTAIL_TIME)
            sm.dispose()
        else:
            sm.chat("Please eliminate all monsters")

elif sm.getFieldID() == 240060100:
        if sm.getChr().getField().getMobs().size() == 0:
            sm.warpInstanceIn(240060300, True)
            sm.setInstanceTime(BossConstants.HORNTAIL_TIME)
            sm.dispose()
        else:
            sm.chat("Please eliminate all monsters")
elif sm.getFieldID() == 240060002:
        if sm.getChr().getField().getMobs().size() == 0:
            sm.warp(240060102, True)
            sm.setInstanceTime(BossConstants.HORNTAIL_TIME)
            sm.dispose()
        else:
            sm.chat("Please eliminate all monsters")
elif sm.getFieldID() == 240060102:
        if sm.getChr().getField().getMobs().size() == 0:
            sm.warpInstanceIn(240060200, True)
            sm.setInstanceTime(BossConstants.HORNTAIL_TIME)
            sm.dispose()
        else:
            sm.chat("Please eliminate all monsters")
elif sm.getFieldID() == 240060001:
        if sm.getChr().getField().getMobs().size() == 0:
            sm.warp(240060101, True)
            sm.setInstanceTime(BossConstants.HORNTAIL_TIME)
            sm.dispose()
        else:
            sm.chat("Please eliminate all monsters")

elif sm.getFieldID() == 240060101:
        if sm.getChr().getField().getMobs().size() == 0:
            sm.warpInstanceIn(240060201, True)
            sm.setInstanceTime(BossConstants.HORNTAIL_TIME)
            sm.dispose()
        else:
            sm.chat("Please eliminate all monsters")
