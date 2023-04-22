 # Scarf Snowman (2001004) | The Hill of Christmas (209000001 - 209000015)
 # Author: Tiger

response = sm.sendAskYesNo("Have you decorated your tree nicely? It's an interesting experience, to say the least, decorating a Christmas tree with other people. Don't cha think?  Oh yeah ... are you suuuuure you want to leave this place?")

if response:
	sm.warp(209000000) # Hidden Street - Happyville
else:
	sm.sendNext("You need more time decorating trees, huh? If you ever feel like leaving this place, feel free to come talk to me~")
