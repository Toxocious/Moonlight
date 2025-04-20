# End of Lesson 1 - Ereve History
from net.swordie.ms.life.npc.NpcScriptInfo import Param
scriptInfo = sm.getNpcScriptInfo()

KINU = 1102006
HAWKEYE = 1101007

sm.removeEscapeButton()

sm.setSpeakerID(HAWKEYE)
sm.sendNext("Tis a pleasure to welcome ye to the knights.")

sm.setPlayerAsSpeaker()
scriptInfo.addParam(Param.OverrideSpeakerID)
sm.sendSay("How kind! Thank you!")

sm.setSpeakerID(HAWKEYE)
scriptInfo.removeParam(Param.OverrideSpeakerID)
sm.sendSay("Anything ye need, ye come straight to me, ol' Hawkeye, captain of the Thunder Breakers. "
"I beg ye pardon, but what was ye name again? I plumb forgot already.")

sm.setSpeakerID(KINU)
scriptInfo.addParam(Param.OverrideSpeakerID)
sm.sendSay("Hawkeye, the new recruit's name is #h #! Now, shoo!")

sm.setSpeakerID(HAWKEYE)
scriptInfo.removeParam(Param.OverrideSpeakerID)
sm.sendSay("I promise, I won't forget yer name the next time we meet.")

sm.setSpeakerID(KINU)
scriptInfo.addParam(Param.OverrideSpeakerID)
sm.sendSay("Wonderful. Now, off you go, Hawkeye!")
sm.sendSay("Finally! Where was I? Oh yes, once your initial training is over, you will choose your knightly path and be promoted to Knight-in-Training! "
"The paths you can pick from are Light, Fire, Wind, Lightning and Darkness.")
sm.sendSay("As a Cygnus Knight, your duty will be to protect the Empress, defeat the Black Mage, spy on his henchmen, and keep peace in Maple World. Simple, yes?")
sm.sendSay("I've given you enough to ponder. Go speak with Kimu for your next lesson.")

sm.completeQuest(parentID)