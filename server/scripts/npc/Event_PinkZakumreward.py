# Head Researcher Wynn
# Pink Zakum : Pink Zakum Exit

fid = sm.getFieldID()
returnmap = sm.getPreviousFieldID()

if fid == returnmap:
    returnmap = 100000000 # henesys until i figure out how to manage return maps more effectively

if sm.getFieldID() == 689010000: # waiting map
    if sm.isPinkZakumOpen():
        response = sm.sendNext("The event will start soon!\r\n#L0#I want to go back to where I was.#l\r\n#L1#Cool! I'll wait#l")
        if response == 0:
            sm.warpNoReturn(returnmap, 0)
    elif sm.sendAskYesNo("Have you heard of the terrifying Zakum? This one's pink! He's not so scary anymore.\r\nWould you like to go back to your previous map?"):
        sm.warpNoReturn(returnmap, 0)
elif sm.isPinkZakumWinner():
    sm.sendNext("Good job defeating the Pink Zakum!")
    # TODO give reward
    sm.warpNoReturn(returnmap, 0)
else:
    sm.sendSayOkay("I'll send you back to where you were, thanks for participating!")
    sm.warpNoReturn(returnmap, 0)