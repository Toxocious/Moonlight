option = list(range(8))
al = chr.getAvatarData().getAvatarLook()
hairNoColour = (al.getHair() / 10) * 10

option = list(map(lambda x: (x + hairNoColour), option))
answer = sm.sendAskAvatar("I can change your hair colour to anything you'd like!", False, False, option)

if (answer < len(option)):
    sm.changeCharacterLook(option[answer])