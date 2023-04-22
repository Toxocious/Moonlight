# 410000001
if sm.hasQuest(38002):
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("What happened? A house and a new name... But what happened to my friends? Are they alive? If I am, then maybe we failed to seal the Black Mage...")
    sm.sendSay("No. They wouldn't give up that easily. They're probably hiding out somewhere, waiting to get back together. I need to look after myself for now, and get my strength back.")
    sm.sendSay("Level 10... It's better than nothing, but it's not the best feeling. I'll hang around and get stronger. That's the only thing I can do now.")
    sm.setQRValue(38002, "clear", False)
elif sm.hasQuest(38018):
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.sendNext("W-what is that thing? It looks so fuzzy. I don't think I should touch it...")
    sm.setQRValue(38018, "clear", False)