# Ariant VIP Hair
# Male: Afro, Cabana, Dreadlocks, Kravitz Locks, Line Scratch, Mane, Matinee, Natural, Tornade
# Female: Bridget, Celeb, Edgy, Lana, Penelope, Rae, Boyish, Desert Flower, Tighty Bun

options = []

al = chr.getAvatarData().getAvatarLook()
hairColour = al.getHair() % 10
if al.getGender() == 0: # Male
    options = [30320, 30330, 30150, 30900, 30170, 30180, 30820, 30410, 30460]
else: # Female
    options = [31090, 31190, 31040, 31420, 31330, 31340, 31620, 31660]
options = list(map(lambda x: x + hairColour, options))
answer = sm.sendAskAvatar("Choose your new hairstyle!", False, False, options)

if answer < len(options):
    sm.changeCharacterLook(options[answer])