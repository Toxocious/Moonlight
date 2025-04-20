# [Shaolin Temple] Cleaning House

JANITOR_MONK = 9310048
WISE_CHIEF_PRIEST = 9310053
MINI_BRONZE_MARTIAL_ARTIST = 9480025
BRONZE_STAFFMAN = 9480028
SUTRA_1_2F = 701220100
SUTRA_3_4F = 701220200
SUTRA_5_6F = 701220300


sm.removeEscapeButton()
sm.setSpeakerID(JANITOR_MONK)
sm.setBoxChat()
sm.sendNext("Didn't you say you were looking for a #bbook#k?")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Long ago, #bMonk Laomo#k wrote a book detailing all of his demon research. I'm searching for that book.")

sm.setSpeakerID(JANITOR_MONK)
sm.setBoxChat()
sm.sendNext("#bMonk Laomo#k? I've heard of him. No one knew more about demons that he did. Back when he was the #rChief Priest#k, a demon wouldn't dare set foot on this mountain.")

sm.sendNext("He left to train one day and never returned. Our current #b#p"+ str(WISE_CHIEF_PRIEST) +"##k took over.")

sm.sendNext("#bAnd then the demons came back. You know, in a way, the demons have really boosted the temple's reputation. Tons of people come here to make offerings and pray.")

response = sm.sendAskYesNo("They say #bMonk Laomo#k had a secret library...\r\n"
                           "You know, I do recall seeing something strange while cleaning #r#m"+ str(SUTRA_5_6F) +"# Maybe you should investigate")

if response:
    sm.sendNext("Getting to the 6th floor might be tough. "
                "Why don't you clear out "
                "100 #o"+ str(MINI_BRONZE_MARTIAL_ARTIST) +"# monsters and "
                "100 #o"+ str(BRONZE_STAFFMAN) +"# monsters at #m"+ str(SUTRA_1_2F) +"# to thin them out? "
                "Then, #bwhen they're weakened, you can head up to #m"+ str(SUTRA_3_4F) +"#.")
    sm.startQuest(62009)
else:
    sm.sendSayOkay("Didn't you say you wanted that book?..")