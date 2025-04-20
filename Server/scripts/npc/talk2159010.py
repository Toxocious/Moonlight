
sm.flipSpeaker()
sm.sendNext("Looks like we lost him. Of course, I could've easily handled him, no problemo, but I wasn't sure I could protect you kiddos at the same time. *chuckle* What're you two doing here anyway? Didn't your parents warn you to steer clear of the mines?")

sm.setSpeakerID(2159007)
sm.flipDialogue()
sm.sendSay("It's my fault! #h0# was just trying to help! #h0# rescued me!")

sm.flipSpeaker()
sm.setSpeakerID(2159010)
sm.sendSay("Rescued you, eh? Hm, you are dressed kind of funny, little girl. Ooooh. Were you a prisoner of the Black Wings ?")

sm.setSpeakerID(2159007)
sm.flipDialogue()
sm.sendSay("#b(Vita quickly explains the situation.)#k")

sm.flipSpeaker()
sm.setSpeakerID(2159010)
sm.sendSay("Ah, yes, I knew the Black Wings were up to something dangerous. I knew it all along. I must tell the others so we can devise a plan.")

sm.setPlayerAsSpeaker()
sm.sendSay("But who are you? Where did you come from? And why did you rescue us?")

sm.flipSpeaker()
sm.setSpeakerID(2159010)
sm.sendSay("I suppose I can't hide it after everything you've seen today, including but not limited to my heroic rescue and brazen bravery. *cough* You know our grand city of Edelstein is currently under the control of the Black Wings, right?")
sm.sendSay("The stolen mines, the occupation of City Hall, the existence of the Watchmen... They are all signs that we no longer have our liberty. Despite all that, the Black Wings will never rule our hearts!")
sm.sendSay("I am a proud member of the Resistance, a group secretly fighting and undermining the Black Wings. I cannot tell you who I am, but I go by the codename of J. ")
sm.sendSay("Now, please return to town and stay away from the mines. As for you, Vita, come with me. If you're left unprotected, I fear the Black Wings will come look for you. No one can keep you safe like I can! Now, keep my words a secret. The fate of the Resistance depends on your discretion.")

sm.setPlayerAsSpeaker()
sm.sendSay("Wait, before you go, tell me one thing. How can I join the Resistance?")

sm.flipSpeaker()
sm.setSpeakerID(2159010)
sm.sendSay("Ah, little youngling, so you wish to fight the Black Wings, do you? Your heart is noble, but there is little you can do to aid our efforts until you reach Lv. 10. Do so, and I will have someone from the Resistance contact you. That's a promise, kiddo. Now, I must be off, but perhaps we will meet again someday!")

sm.startQuest(23007)
sm.completeQuest(23007)
sm.giveExp(90)
sm.warp(310000000, 8)