# [Stone Colossus] Rolling with Kupo

from net.swordie.ms.client.character.skills.temp import CharacterTemporaryStat

KUPO = 2210002
KUPOS_RIDE = 1932165

sm.setSpeakerID(KUPO)
sm.sendNext("Who are you? Come to meet the famous driver, master of the wheel, Kupo?! I knew it. "
            "My fame is spreading across Maple World. "
            "It'll be a hundred million mesos for an autograph, but i'll let you take a picture with me for 75.")

sm.sendSay("Not even a chuckle?\r\n"
           "Let me introduce myself like a proper gent. The name's Kupo.\r\n"
           "Rhymes with 'eww, slow'.")

sm.sendSay("You're not much for small talk, are you? I get it. I respect your need for speed.\r\n"
           "Getting up to the Stone Colossus ain't exactly a walk in the park, but I've got the ride that will make it a trip to remember. "
           "of course, nobody rides for free...")

response = sm.sendAskYesNo("Your job's simple enough. I keep the motor running, you do everything else. "
                "Don't worry about messing up, just trust your instincts. "
                "You think you can handle that?")

if response:
    sm.sendNext("That's right! Let's do this!")
    sm.startQuest(parentID)
    sm.warpInstanceIn(240090800) # Kupo's Ride  The Road Up
    sm.rideVehicle(KUPOS_RIDE)
    sm.lockInGameUI(True, False)

    sm.sendDelay(10000)
    sm.sendNext("So...\r\n"
                "What's your favourite hobby?\r\n\r\n"
                "Mine is driving!")

    sm.sendDelay(10000)
    sm.sendNext("You don't like talking?..\r\nAlright..")

    sm.sendDelay(5000)
    sm.sendNext("#fs 18##eALRIGHT I GUESS IF YOU'RE NOT GONNA TALK TO ME I'LL JUST TURN THIS SHIT AROUND!!!")

    sm.sendDelay(2000)
    sm.warpInstanceOut(240090000)
    sm.removeCTS(CharacterTemporaryStat.RideVehicle)
    sm.lockInGameUI(False)
    sm.sendSayOkay("..Maybe I was a bit too harsh on you.. I'm sorry\r\n"
                   "I would give you a ride to the other side, but my car is out of fuel!")
else:
    sm.sendSayOkay("No?..")