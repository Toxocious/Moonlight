# Only the Brave ; Xenon 3rd Job

PROMATHUS = 2300002

sm.setSpeakerID(PROMATHUS)
sm.sendNext("Ah, there you are, I must speak with you. I don't know if you know this, but Gelimer has not ended his search for you.")
sm.sendSay("The thing that I find very strange is that Beryl was the only one to actually pursue you. It seems your very existence was kept a secret, even among the Black Wings. Was... things tend to change rapidly in their organization.")
sm.sendSay("Only a handful of the Black Wings will know of you now, but it is only a matter of time until your infamy becomes a burden. The people of this laboratory are busy at work for a weapon to make you safe... but I am not so sure it is the best course of action. Do you know why?")
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Is it because I'm not human?")
sm.setSpeakerID(PROMATHUS)
sm.sendSay("I would not be swayed by so petty a reason.")
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Is it because Gelimer is my creator?")
sm.setSpeakerID(PROMATHUS)
sm.sendSay("Again, no. You were crafted as a weapon, and a weapon is only as dangerous as the person who wields it. Who's to say that the evil dwelling within Gelimer's soul has not infected mine as well? All men are suspectible to greed, especially those fueled by the desire of knowledge.")

if sm.sendAskYesNo("A scientist must take responsibility for his own curiosity. THAT is... why I hesitate to grant you this power.\r\nI need proof that you will handle it with care. Will you prove yourself to me?"):
    sm.sendSayOkay("Splendid, I would like you to show me the courage in your heart, and bring me a #bBlack Wings Hat#k. There is a gentleman on the #Road to the Mine1#k. He is known to be rather unscrupulous towards his own organization. But be wary, their base is rather nearby, it will require skill, bravery, and cunning. Good luck.")
    sm.startQuest(parentID)
