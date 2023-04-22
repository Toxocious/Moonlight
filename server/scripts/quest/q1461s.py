#   [Job Adv] (5th job)   5th Job : Blessings of the Goddess

sm.setSpeakerID(2140001)
sm.sendNext("According to legend, in ancient times, after the fall of the 365 gods, the goddesses used the Erdas to sculpt this world into being. Supposedly there have also been a select few... humans, elves, demons and dragons... who have learned the art of #bmanipulating The Erda Flow#k from the goddesses themselves.")
#todo show image
if sm.sendAskYesNo("The portal to the Goddess of Maple World can be found at the #bBowman Instructional School in Henesys#k. The Goddess of Grandis can be found at the #bGreat Temple Interior in Pantheon#k. And the Goddess of Tynerum can be found at the #bDeserted Camp at the Dark World Tree#k. If anyone can find their way to the goddess, it is you."):
    sm.startQuest(parentID)
    sm.sendSayOkay("Come back to me anytime you are lost. \r\n\r\n #b(Go find the goddess of Maple World.)")
sm.dispose()