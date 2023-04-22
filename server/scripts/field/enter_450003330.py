# id 450003330 (Lachelein Night Market : Noisy Market), field 450003330
if sm.hasQuest(34316):
    sm.setSpeakerType(3)
    sm.setParam(5)
    sm.setInnerOverrideSpeakerTemplateID(3003238) # Huge Watermelon Mask
    sm.sendNext("What's going on?")
    sm.setInnerOverrideSpeakerTemplateID(3003236) # Beauty Mask
    sm.sendSay("What was I doing just now?")
    sm.setInnerOverrideSpeakerTemplateID(3003201) # Protective Mask
    sm.sendSay("My memories... Argh! My head... ")
    sm.setParam(3)
    sm.sendSay("Protective Mask? Are you okay? What is going on?")
    sm.setParam(5)
    sm.sendSay("Don't worry about me... Take them to safety. But where is safe...?")
    sm.setParam(3)
    sm.sendSay("Should I take them back to the hideout?")
    sm.setParam(5)
    sm.sendSay("No. You saw how the Dreamkeepers vanished when the music box was destroyed... This place may actually be safer than the hideout. ")
    sm.setParam(3)
    sm.sendSay("Then we should return to the hideout alone.")
    sm.setParam(5)
    sm.setInnerOverrideSpeakerTemplateID(3003221) # Watermelon Mask
    sm.sendSay("Hey! Let me come too!")
    sm.createQuestWithQRValue(34340, "enter=3")
    sm.warp(450003740)
