# 140090400
PUTZKI = 1202005

sm.setSpeakerID(PUTZKI)
answer = sm.sendSay("Ah, you're the hero. I've been dying to meet you.\r\n#b#L0#(Seems a bit shy...)#l")
if answer == 0:
    if sm.sendAskAccept("I have something I've been wanting to give you as a gift for a very long time... I know you're busy, especially since you're on your way to town, but will you accept my gift?"):
        sm.removeEscapeButton()
        sm.startQuest(parentID)
        sm.sendNext("The parts of the gift have been packed inside a box nearby. Sorry to trouble you, but could you break the box and bring me a #b#t4032309##k and some #b#t4032310##k? I'll assemble them for you right away.")
        sm.tutorAutomatedMsg(18)
    else:
        sm.sendNext("I'm sure it will come in handy during your journey. Please, don't decline my offer.")
        sm.dispose()