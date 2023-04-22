# Researcher H
# Pink Zakum : Pink Zakum Raid

REVIVE_MAPS = [689011000, 689011001]

message = "Are you sure you want to leave? You won't be able to return.\r\n"

if sm.getFieldID() in REVIVE_MAPS:
	message += "Remember, you can rejoin the battle by entering the portal!\r\n#L0#Yes, warp me out.#l\r\n#L1#Nevermind, I'll head back to Pink Zakum.#l"
else:
	message += "#L0#Yes, I want to go back to where I was!#l\r\n#L1#Okay, I'll stay a little longer.#l"

if sm.sendNext(message) == 0:
	chr.warp(sm.getPreviousFieldID(), 0, False)