# I think this is called by many different NPCs

say = "I haven't received any instructions yet! Tell staff that I'm not working and maybe they'll thank you with a reward!"

if parentID == 2002000: # Rupi
	say = "Isn't it cold? I love it! Snow is my favorite form of water!"

sm.sendSayOkay(say)