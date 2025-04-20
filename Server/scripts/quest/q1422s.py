# 1422 - [Job Adv] (Lv.30) Path of the Assassin

darkMarble = 4031013
job = "Assassin"
monster = "Swamp Monster"

sm.setSpeakerID(1052001) # Dark Lord
sm.sendNext("You wish to become an #b"+ job +"#k?\r\n\r\n"
            "An #b"+ job +"#k is specialised in long range attacks and use #bthrowing stars#k to defeat their enemies. "
            "There are many useful skills you can acquire.")

# start of custom
response = sm.sendAskYesNo("Are you sure you wish to become an #b" + job + "#k?")

if response:
    sm.completeQuestNoRewards(parentID)
    sm.jobAdvance(410) # Assassin
    sm.sendSayOkay("Alright! You have now become a #b" + job + "!")
else:
    sm.sendSayOkay("Speak to me again and I can explain the two paths to you once more.")

sm.dispose()
# end of custom

# sm.sendNext("Before I teach you the ways of the "+ job +", you will have to accomplish a very difficult test. "
#             "I will warp you into a special map, in which I require you to defeat #b"+ monster +"#k "
#             "and return 30 #i"+ str(darkMarble) +"##z"+ str(darkMarble) +"#s to me.")
#
# response = sm.sendAskYesNo("Once you enter the map, you #rcannot#k return without the #b#t"+ str(darkMarble) +"#s#k, if you die you will lose your experience.\r\n"
#                 "Are you ready?")
#
# if response:
#     sm.warp(910370000, 0) # Thief Test Site
#     sm.startQuestNoCheck(parentID)
# else:
#     sm.sendSayOkay("You cannot be a Rogue forever. You #bwill#k have to face up to the test.\r\n"
#                    "Talk to me when you are ready.")
# sm.dispose()
