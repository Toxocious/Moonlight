# Created by MechAviv
# Quest ID :: 25500
# Eclipse and Sunfire

sm.setSpeakerID(1032209)
sm.sendNext("It's time for you to harness the powers of light and dark, and make them your own.")


sm.setSpeakerID(1032209)
sm.setPlayerAsSpeaker()
sm.sendSay("#bHow does one go about doing such a thing?")


sm.setSpeakerID(1032209)
sm.sendSay("I wasn't done explaining. Look up, and to the right.\r\n#v3800300#\r\nCan you see something like this? A measure of your affinity for each side should be floating up there.")


sm.setSpeakerID(1032209)
sm.setPlayerAsSpeaker()
sm.sendSay("#bWhat are those wings?")


sm.setSpeakerID(1032209)
sm.sendSay("Those are the Light and Dark points you can accumulate. Each way will max out at five. When you first appear, you will have one Light or Dark Point. #rUsing the #v3800310#Eclipse#k skill once#k will consume one Dark Point and take you into #bEclipse status, where your heart is filled with darkness#k.\r\n#v3800301#")


sm.setSpeakerID(1032209)
sm.sendSay("Using Dark magic will give you more points, one by one.\r\n#v3800302#")


sm.setSpeakerID(1032209)
sm.sendSay("#rThese Dark Points can be used through the #v3800310#Eclipse skill#k. Going into Eclipse will allow you to#k #bcast magic with no MP deal 50% more Dark magic damage#k. Of course, if you use #b#v3800309#Sunfire buff#k, you will drive off the dark immediately.")


sm.setSpeakerID(1032209)
sm.setPlayerAsSpeaker()
sm.sendSay("#bI will never let darkness take my heart!")


sm.setSpeakerID(1032209)
sm.sendSay("Hahaha, I'm afraid you have very little choice, but do not worry. Dark is but the shadow cast by light. Neither is truly more good than the other. It is the person who controls that power that matters. And I'm sure #b#eyou'll#n#k handle it just fine, as opposed to...")


sm.setSpeakerID(1032209)
sm.setPlayerAsSpeaker()
sm.sendSay("#bThat's enough. How do I control this... curse?")


sm.setSpeakerID(1032209)
sm.sendSay("#bYou can wield both Light and Dark magic while using Eclipse. Just keep an eye on your points.\r\n#v3800303#")


sm.setSpeakerID(1032209)
sm.sendSay("Eclipse will remain active as long you still have points. #v3800310#, the Eclipse buff icon, will be on the top right. #bYour eye color and orb will change#k so that you can easily keep track.\r\n#v3800321#")


sm.setSpeakerID(1032209)
sm.sendSay("Dark magic is most powerful when your heart is full of darkness. #rUse Light Points in that state#k to fill your heart with Light#k.")


sm.setSpeakerID(1032209)
sm.sendSay("Conversely, #rwhen your heart is full of Light#k, the gauge will flash to let you know that you have activated Sunfire.\r\n#v3800304#")


sm.setSpeakerID(1032209)
sm.sendSay("The Sunfire buff icon #v3800309# will be displayed at the top right when #ryour heart is full of Light#k. #bYour eye color and orb will return to normal#k.\r\n#v3800320#")


sm.setSpeakerID(1032209)
sm.sendSay("The #bSunfire buff heals your HP by 1% with each cast, halves your MP cost, and lets you deal 50% more damage with Light magic. Very similar to Eclipse#k. You can still accumulate Light and Dark Points, just like Eclipse.\r\nYou'll return to Eclipse when you use Dark Points during Sunfire.")


sm.setSpeakerID(1032209)
sm.setPlayerAsSpeaker()
sm.sendSay("#bI will use whichever power fills my heart to crush the enemies of good! I will wrestle control of this violent beast that resides deep inside.")


sm.setSpeakerID(1032209)
sm.sendSay("See for yourself which skills are enhanced below the gauge if you don't know what to use.\r\n<Light Magic>              <Dark Magic>\r\n#v3800312#  #v3800315#\r\nDid you get all that?")


sm.setSpeakerID(1032209)
sm.setPlayerAsSpeaker()
sm.sendSay("#bI must test these powers.")


sm.setSpeakerID(1032209)
if sm.sendAskAccept("Yes, yes, very well. I hope you took notes, because I'm tired of giving lectures. Just go out and... I don't know... #bfill up both your light and dark completely#k. That'll be good practice."):
    sm.startQuest(25500)
    sm.setQuestEx(25502, "light", "0")
    sm.setQuestEx(25502, "dark", "0")
    sm.setQuestEx(25502, "mix", "0")
    sm.setSpeakerID(1032209)
    sm.removeEscapeButton()
    sm.sendNext("Talk to me again after you have filled up each side once.")
else:
    sm.setSpeakerID(1032209)
    sm.sendSayOkay("You'll never get a handle on your powers if you don't practice. When did you get so lazy?")


