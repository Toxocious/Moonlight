# [Commerci Republic] Delfinos? More Like dead Fishos

sm.setSpeakerID(9390203) # Gilberto Daniella
response = sm.sendAskYesNo("Are you curious about the Delfino?")

if response:
    sm.sendNext("We lived a good life alongside the Delfinos. "
                "Most of our merchants rarely interacted with them, but there was no hostility.")
else:
    sm.sendSayOkay("You have no interest in helping us? Hmmm..")
    sm.dispose()

sm.sendNext("San Commerci was nothing but a small fishing village in the early days of the Republic. "
            "We fished our areas of the seas, they fished theirs... "
            "But then the spice trade began and this port doubled in size overnight.")

sm.sendNext("I'm sure the Delfinos are only upset because our town has prospered without them. "
            "It's the only rational explanation to their transition into roving gangs of bandits.")

sm.sendNext("The Delfinos must be treated with a firm and swift hand. It's the only thing they'll respect.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("#b(This is my chance!)#k\r\n"
            "Excuse me, prime minister, but I'd like to help you against the Delfinos.")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("Why? This isn't your fight. This isn't even your country.")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("It's part of my responsibility as an envoy from Maple World to aid in the affairs of our #bAlly nations#k.")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("Thank you for the thought, but we can...")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Daddy! I mean, pops! #h0# is really strong! Plus, #h0# is my sidekick!")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("We're not so weak that we have to rely on outside nations for aid.")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("That's totally not what I meant, daddy.")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("Just get out there and eliminate those Delfinos...")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Y-yes, daddy")

sm.setSpeakerID(9390203) # Gilberto Daniella
sm.sendNext("#h0#? I appreciate the offer, but I don't want outsiders getting involved with our state affairs. I hope you understand.")
sm.startQuest(parentID)
sm.dispose()
