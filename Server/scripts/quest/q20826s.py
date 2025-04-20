# Start of Lesson 1 - Ereve History
from net.swordie.ms.life.npc.NpcScriptInfo import Param
scriptInfo = sm.getNpcScriptInfo()

KINU = 1102006
HAWKEYE = 1101007

sm.setSpeakerID(KINU)

sm.removeEscapeButton()

sm.sendNext("Your first lesson will be about Ereve. "
"Ereve is a floating island, held aloft by the Empress's powers. "
"It has remained stationary for a number of years but once floated about Maple World like a ship.")

sm.sendNext("Right now, we're focused on gathering up information on the Black Mage and preparing our forces to face him. "
"It's some serious business, lemme tell you.")

sm.sendNext("The knights themselves are gathered into 5 groups, based around the Spirits of Light, Fire, Wind, Lightning, and Darkness. "
"Each group is led by a Chief Knight and... Oh, here is one now. Hello Hawkeye.")

sm.setSpeakerID(HAWKEYE)
scriptInfo.addParam(Param.OverrideSpeakerID)
sm.sendNext("Ahoy! I wanted to welcome the new knight in person. "
"I must give ye my apologies, for I left the muffins I baked ye on my ship.")

sm.setSpeakerID(KINU)
scriptInfo.removeParam(Param.OverrideSpeakerID)
sm.sendNext("Hawkeye, this is highly unorthodox!")

sm.setSpeakerID(HAWKEYE)
scriptInfo.addParam(Param.OverrideSpeakerID)
sm.sendSay("Yar, have a heart, Kinu. Don't ye remember yer first day with the knights? "
"Wasn't it a mite overwhelming without a friend?")

sm.setSpeakerID(KINU)
scriptInfo.removeParam(Param.OverrideSpeakerID)
sm.sendSay("I suppose I could bend the rules this once. #h #, meet Hawkeye, the Chief Knight of Lightning.")

sm.startQuest(parentID)