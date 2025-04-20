# [Silent Crusade] An Emergency Summons

STARLING = 9120221
LORA = 9073004
DANGEROUS_CAVE = 230040410
TICKET = 2030027

sm.setPlayerAsSpeaker()
sm.sendNext("(The symbol on the letter you've obtained looks familiar.)\r\n"
            "A new mission, perhaps?")

sm.setSpeakerID(STARLING)
sm.sendSay("From #b#p"+ str(LORA) +"##k at the #r#m"+ str(DANGEROUS_CAVE) +"##k.")

sm.setPlayerAsSpeaker()
response = sm.sendAskAccept("Something super urgent has come up. What to do, what to do?")

if response:
    sm.sendNext("The ticket's in here. Use it to visit #p"+ str(LORA) +"#.")

    if not sm.canHold(TICKET):
        sm.sendSayOkay("Let me make some space in my inventory.")
        sm.dispose()

    sm.startQuest(parentID)
    sm.showFieldEffect("Map/Effect.img/crossHunter/chapter/start4")
    sm.giveItem(TICKET)