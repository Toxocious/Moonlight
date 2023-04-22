# Riena Strait : Frostwitch Barbara (141040003)  |  Riena Strait Questline ending cutscene

BARBARA = 1510008
PUTAN = 1510000
DACHI = 1510005
ALVESH = 1510003
ALVIOLA = 1510002
NORA = 1510007
HELMSMAN_TANYA = 1510006


sm.lockInGameUI(True)
sm.removeEscapeButton()
sm.moveCamera(150, 0, 100)
if "1" in sm.getQRValue(32192):

    sm.setSpeakerID(BARBARA)
    sm.sendNext("What? What are you doing here in a crowd?")

    sm.setSpeakerID(PUTAN)
    sm.sendSay("Hmm Hmm,. So.. Hmm... Where should I begin..")

    sm.setSpeakerID(DACHI)
    sm.sendSay("So what I meant was.. Hmmm..")

    sm.setSpeakerID(BARBARA)
    sm.sendSay("If you have something to say, say it quick! I am busy cooking dinner!")

    sm.setSpeakerID(ALVIOLA)
    sm.sendSay("I've come to apologise. I am sorry that I misunderstood you all these years.")

    sm.setSpeakerID(ALVESH)
    sm.sendSay("All of this was a big misunderstanding. You are not a witch... "
               "You did not destroy the glaciers nor did you raise the sea level. "
               "You instead have taken care of our children who had nowhere to go.")

    sm.setSpeakerID(BARBARA)
    sm.sendSay("You suck. You noticed that just now?")

    sm.setSpeakerID(PUTAN)
    sm.sendSay("Hmm hmm, I am sorry. But why didn't you say so? And we wouldn't misunderstand you!")

    sm.setSpeakerID(BARBARA)
    sm.sendSay("Such nonsense! Why would I harass the ones that I raised and took care of with my own hands?")

    sm.setSpeakerID(PUTAN)
    sm.sendSay("Huh?..")

    sm.setSpeakerID(BARBARA)
    sm.sendSay("I'm not originally from here. I lost my husband and children.. "
               "I didn't want to live any longer, but I decided to die after enjoying the snow that I like. "
               "That's why I came here.")

    sm.setSpeakerID(BARBARA)
    sm.sendSay("But then, I found you guys who were floating on the sea.")

    sm.setSpeakerID(BARBARA)
    sm.sendSay("Those little things held on to my garment... I just couldn't let them go. That's why I brought them all here.")

    sm.warp(141040002, 0)
    sm.setQRValue(32192, "2")

elif "2" in sm.getQRValue(32192):
    sm.hideUser(False)

    sm.setSpeakerID(PUTAN)
    sm.sendNext("Oh no... I don't remember it well.. But you are the one who saved us when we were little!")

    sm.setSpeakerID(ALVESH)
    sm.sendSay("I remember it vaguely. Someone was taking care of me with warm hands... I never knew that was a human's. Meanwhile, we were calling her a witch...")

    sm.setSpeakerID(DACHI)
    sm.sendSay("Then that means, you've been here doing the same work for centuries. Oh my... you are like a mother to our people!")

    sm.setSpeakerID(NORA)
    sm.sendSay("That's really touching, Granny.")

    sm.setSpeakerID(BARBARA)
    sm.sendSay("Stop talking nonsense and eat.")

    sm.sendSay("And you brats.. This granny's feelings are hurt..")

    sm.setSpeakerID(PUTAN)
    sm.sendSay("...?")

    sm.setSpeakerID(BARBARA)
    sm.sendNext("#r#eStop quarrelling, you brats!")

    sm.setSpeakerID(PUTAN)
    sm.sendSay("Ye.. Yes!")

    sm.moveCamera(150, -500, 50)

    sm.setSpeakerID(NORA)
    sm.sendNext("That's good. So there won't be any conflict among the three ethnic groups going forward, right?")

    sm.setSpeakerID(HELMSMAN_TANYA)
    sm.sendSay("Haha, all thanks to you, navigator.")

    sm.warpInstanceOut(141000000, 0)
    sm.lockInGameUI(False)