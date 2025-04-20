# Lakelis (90761100) | First Time Together Waiting Room
sm.setSpeakerID(parentID)
selection = sm.sendNext("#e<Party Quest: First Time Together>#n \r\n \r\n #b#L0#I want to attempt this party quest.#l \r\n #L1#How many more times can I attempt this party quest today?#l")

if selection == 0:
    sm.warpPartyIn(933011000)
elif selection == 1:
    #TODO finish this
    sm.sendNext("Todo")