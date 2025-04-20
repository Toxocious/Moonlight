pqItems = [
4000884, # Primrose Seed
# We dont remove Rice Cakes cause you use them to buy the Equip item
]

def endMoonBunny():
    sm.warpInstanceOut(933000000)


if sm.getFieldID() == 933001000:
 sm.setSpeakerID(parentID)
 if field.getProperty("stage") == 2:
  selection = sm.sendNext("Yum, yum! This is so tasty. Please bring me more #bMoon Bunny's Rice Cakes. \r\n #k#L1#I want to leave this place") 
 else:
  selection = sm.sendNext("Yum, yum! This is so tasty. Please bring me more #bMoon Bunny's Rice Cakes. \r\n #k#L0#I want to leave this place")
if selection == 0:
        for item in pqItems:
            if sm.hasItem(item):
                sm.consumeItem(item, sm.getQuantityOfItem(item))
        endMoonBunny()
elif selection == 1:        
        for item in pqItems:
            if sm.hasItem(item):
                sm.consumeItem(item, sm.getQuantityOfItem(item))
        endMoonBunny() 
        # Idk what to reward here so I'll put placeholders?
        sm.giveMesos(500 + (sm.getChr().getLevel() * 20))
        sm.giveExp(500 + (sm.getChr().getLevel() * 100))
        # PQPoints soonTM
        
       
        
          
