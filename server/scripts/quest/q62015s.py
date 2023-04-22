# [Shaolin Temple] Nine-Tailed Fox's One True Love

NINE_TAILED_FOX = 9310579
MOUNTAIN_PATH_2 = 701210130

sm.removeEscapeButton()
sm.setSpeakerID(NINE_TAILED_FOX)
sm.setBoxChat()
sm.sendNext("Hmph. So you helped the little one return the shoes.\r\n"
            "My problem is a little more complex.")

sm.sendNext("My request is of a delicate, sensitive nature.\r\n"
            "Will you listen?")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Go on. Spill your guts.")

sm.setSpeakerID(NINE_TAILED_FOX)
sm.setBoxChat()
response = sm.sendAskYesNo("First, you must promise not to judge me...")

if response:

    sm.sendNext("My tale is one of woe and #rforbidden love#k. The rest of my kind would never understand but...")

    sm.sendNext("I've fallen in love with a human woodcutter. "
                "We met in the woods. We fell fast and hard. "
                "He said that, for me, #rhe would do absolutely anything#k.")

    sm.sendNext("I left him after our few, precious moments together, and he must be in misery. "
                "Please, deliver this letter to him.")

    sm.sendNext("The humans are being ridiculous with their torches and their pitchforks. "
                "I can't reach my one true love, so hurry and deliver my letter. My beloved awaits.")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("(Wow, so #b#p"+ str(NINE_TAILED_FOX) +"##k has a soft side. But what type of man would fall for a demon?)")

    sm.setSpeakerID(NINE_TAILED_FOX)
    sm.setBoxChat()
    sm.sendNext("Find my beloved woodcutter sweetie-kuns at #r#m"+ str(MOUNTAIN_PATH_2) +"##k.")

    sm.startQuest(parentID)

else:
    sm.sendSayOkay("If you are going to judge me, I won't tell my tale.")