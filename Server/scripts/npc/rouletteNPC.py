# russian roulette npc.
# custom function to work with in game event
# unsure what real function is

if sm.getFieldID()== 910030000: # verify in event map
    response = sm.sendNext("Hey! This is MapleStory Russian Roulette!\r\n#L0#What is that?#l\r\n#L1#Redeem rewards!#l")
    # todo
else:
    sm.sendSayOkay("Wanna give me a twirl, handsome?")