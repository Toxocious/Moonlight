# id 1466 (A Greater Power), field 270000000
sm.setSpeakerType(3)
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(2140001) # Memory Keeper
sm.sendNext("So, how was it facing those monsters? \r\n\r\n #b#L0#They're stronger than expected. I don't think I can take them alone.#l")
sm.sendNext("Of course... The power of the body and the power of the sould are different. Without #bArcane Power#k, you stand no chance against the threats that lie ahead. And only one tapped into th Erda Flow can wield true #bArcane Power#k")
sm.sendNext("But there is a way. You can forge the Erda within you into the shape of an #bArcane Symbol#k. \r\n\r\n #b#L0#Arcane Symbol?#l")
sm.sendNext("I will give you the most basic symbol for now. It won't be complete at first. But after you gain enough experience there, #bthe symbol will grow more elaborate, and you will be able to enhance its power#k.  Don't rush the process... it will happen in time. \r\n\r\n#i1712000# #bArcane Symbol x1")
if sm.canHold(1712000):
    sm.giveItem(1712000)
    sm.completeQuest(parentID)
else:
    sm.sendNext("Please make room in your Equip inventory to receive the Arcane Symbol.")