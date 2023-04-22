# Tory (1012112) | Moon Bunny's Rice Cake Waiting Room

ENTRANCE_MAP = 933000000

pqItems = [
4001117, # Old Metal Key
4001120, # Rookie Pirate Mark
4001121, # Rising Pirate Mark
4001122, # Veteran Pirate Mark
]

def startMoonBunny():
    sm.warpInstanceIn(933001000, True)

if sm.getFieldID() == ENTRANCE_MAP:
 sm.setSpeakerID(parentID)
 selection = sm.sendNext("#e<Party Quest: Moon Bunny>#n \r\n Hi I'm Tory! \r\n #b#L0#Go to Primrose Hill.#l \r\n #L1#How many more times can I try Primrose Hill?#l")
if selection == 0:
    if sm.checkParty() and selection == 0:
        for item in pqItems:
            if sm.hasItem(item):
                sm.consumeItem(item, sm.getQuantityOfItem(item))
        startMoonBunny()
elif selection == 1:        
        sm.sendSayOkay("#Not yet available")



else:
    response = sm.sendAskYesNo("Do you want to help me fight Lord Pirate?")
    if response:
        sm.warp(ENTRANCE_MAP)