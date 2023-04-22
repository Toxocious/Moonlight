# Ludi VIP Hair
# Male: Afro, Bowl Cut, Fuzz, Hector, Julian, //Tentacle, Trip Scratch, URban
# Female: Apple Hair, Candy Heart, Eye-skimming Bang, Female Runway, Lovely Ladyhawk, Naomi, Pigtails, Ayu
options = []

al = chr.getAvatarData().getAvatarLook()
hairColour = al.getHair() % 10
if al.getGender() == 0: # Male
    options = [30250, 30190, 30660, 30870, 30840, 30160, 30640]
else: # Female
    options = [31810, 31550, 31830, 31840, 31680, 31290, 31270, 31870]
options = list(map(lambda x: x + hairColour, options))
answer = sm.sendAskAvatar("Choose your new hairstyle!", False, False, options)


if answer < len(options):
        sm.changeCharacterLook(options[answer])