# Train trip to/from NLC doesn't display a script#
map = 600010001
string = "New Leaf City"
if sm.getFieldID() == 600010001:
    map = 103000100
    string = "Kerning City"

response = sm.sendAskYesNo("Would you like to take the train to " + string + "?")

if response:
    sm.warp(map, 0)