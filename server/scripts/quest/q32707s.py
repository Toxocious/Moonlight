# Created by MechAviv
# Quest ID :: 32707
# [FriendStory] Student From Another World

sm.setIntroBoxChat(1530000)
sm.setSpeakerType(3)
sm.sendNext("Hello? Hello?\r\n\r\nOkay, the magician guy said he teleported the phone to someone who can help. So, um, hi? Can you help me, maybe?")


sm.setIntroBoxChat(1530000)
sm.setSpeakerType(3)
sm.sendSay("...What?\r\n\r\nYes, I'm talking to-\r\n\r\nDude, relax! I'll just ask-")


sm.setIntroBoxChat(1530000)
sm.setSpeakerType(3)
if sm.sendAskYesNo("Argh, this guy is being a total spaz.\r\nHey, he wants to know if he can teleport you here. That cool?\r\n#b(You will be moved to the Closet House in Henesys.)#k"):
    sm.setIntroBoxChat(1530000)
    sm.setSpeakerType(3)
    sm.sendNext("Great. ...Hey, magic dude! Do your magicky-thing!\r\n\r\n#e#b(You can also use the Dimensional Mirror to get here.)#n#k")

    sm.warp(330002040, 0)
else:
    sm.setIntroBoxChat(1530000)
    sm.setSpeakerType(3)
    sm.sendNext("...I understand. They wouldn't be interested in me, even with things turned around like they are.")


