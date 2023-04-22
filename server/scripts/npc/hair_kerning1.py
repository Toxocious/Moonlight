# Kerning VIP Hair
# Male: Antagonist, Aran Cut, Cornrow, Dragon Tail, Rockstar, Short Top Tail, Male Runway Hair
# Female: Annie, Aran Hair, Curly Stream, Gardener, Pei Pei, Penelope, Ravishing Ravon, Shaggy Dog, Super Diva

options = []

al = chr.getAvatarData().getAvatarLook()
hairColour = al.getHair() % 10
if al.getGender() == 0: # Male
    options = [30130, 33040, 30850, 30780, 30040, 30920, 30860]
else: # Female
    options = [31060, 34090, 31520, 31880, 31140, 31330, 31440, 31760, 31750]
options = list(map(lambda x: x + hairColour, options))
answer = sm.sendAskAvatar("Choose your new hairstyle!", False, False, options)

if answer < len(options):
    sm.changeCharacterLook(options[answer])