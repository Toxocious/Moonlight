# Created by MechAviv
# Kinesis Introduction
# Map ID :: 101020400
# East Forest :: Magician Association
KINESIS = 1531000
NERO = 1531003
THREE_MOON = 1531004

sm.setIntroBoxChat(THREE_MOON)
sm.sendNext("#Indeed, it is as you say. Persons from your world have been scattered throughout. ")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("Well, don't hold back. Where should I start looking?")

sm.setIntroBoxChat(THREE_MOON)
sm.sendSay("#bEllinel Fairy Academy#k. ")

sm.setIntroBoxChat(NERO)
sm.sendSay("#face0##bEllinel#k? I heard something weird was going on there. #bFanzy#k asked me to help, but with Kinesis on the case, I don't have to worry! ")

sm.setIntroBoxChat(THREE_MOON)
sm.sendSay("#bEllinel#k has always been off-limits to outsiders. You should exercise caution. ")

if sm.sendAskYesNo("Accept this #bReturn Scroll#k. Use it at a time wherein you wish to return. With that done, I may now send you to where you need to be.\r\n#b(You will be moved to Fanzy's location if you accept.)#k"):
    sm.giveItem(2030055, 5)
    sm.createQuestWithQRValue(32147, "1")
    sm.startQuest(parentID)
    sm.warp(101030000, 1)