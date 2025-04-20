# 22000 |   Strange dream (Evan intro)
sm.setSpeakerID(1013100)
sm.sendNext("Did you sleep well, Evan?")
sm.setPlayerAsSpeaker()
sm.sendNext("#bYes, what about you, mom?")
sm.setSpeakerID(1013100)
sm.sendNext("I did as well, but you seem so tired. Did you sleep well? Did the thunder and lightning last night keep you up?")
sm.setPlayerAsSpeaker()
sm.sendNext("#bOh, no. It's not that, Mom. I just had a strange dream last night.")
sm.setSpeakerID(1013100)
sm.sendNext("A strange dream? What kind of strange dream?")
sm.setPlayerAsSpeaker()
sm.sendNext("#bWell...")
sm.sendNext("#b(You explain that you met a Dragon in your dream.)")
sm.setSpeakerID(1013100)
if sm.sendAskYesNo("Hahaha, a Dragon? That's incredible. I'm glad he didn't swallow you whole! You should tell Utah about your dream. I'm sure he'll enjoy it"):
    sm.startQuest(22000)
    sm.sendNext("#bUtah#k wen to the #b Front Yard#k to feed the Bull Dog. You'll see him right outside.")
    sm.dispose()
else:
    sm.dispose()


