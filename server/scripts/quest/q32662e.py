# [Grand Athenaeum] Visit the Grand Athenaeum
LIBRARIAN = 2500002

sm.setSpeakerID(LIBRARIAN)
sm.sendNext("Come on over! I am #b#p2500002##k. Surprised? I'll have you know my simian parts are my best parts.")
sm.sendSay("What is this place, you ask? This is the #bGrand Athenaeum#k. All the knowledge and records of this world are enshrined here. Many people don't know it, but Maple World's stories write themselves into their own book when they happen. No pens or ink required!")
sm.sendSay("Ha ha, you think I'm lying? You can see for yourself! You can #benter the stories themselves#k, and experience them as a character.")
sm.sendSay("#b#p2500000##k gave me books that all touch on key events in Maple World's history.")
if sm.sendAskAccept("Let's see... Clearing all five would be too many, so if you clear #bthree, regardless of the order#k, I'll give you a #bpretty neat gift#k.\r\nAre you up for the challenge?\r\n\r\n"
                    "\n\n- Bonus Pendant Slot #e#b(21 Day)#k#n\r\n"
                    "\n\n- #i1122263#   #b#t1122263##k\r\n\r\n"
                    "\n\n- #i2431892#   #b#t2431892##k"):
    sm.completeQuest(parentID)
    sm.startQuest(32663)# [Grand Athenaeum] Read The White Mage
    sm.startQuest(32664)# [Grand Athenaeum] Read Empress In Training
    sm.startQuest(32665)# [Grand Athenaeum] Read The Black Witch
    #sm.startQuest(32698)# Book for higher version
    #sm.startQuest(32990)# Book for higher version
    sm.startQuest(32666)# [Grand Athenaeum] Food for the Soul
    sm.sendSayOkay("You can read the books containing stories in any order you'd like. Talk to #b#p2500000##k.")
else:
    sm.sendNext("Busy? You're always welcome here.")