# Natalie - Henesys Hair Salon
# Male: Aran cut, Catalyst, Evan hair (m), gaga hair, shaggy wax, the coco, the mo rawk
# Female: Dual blade, front braid, grace, hime, laguna beach, lively wave, long with bangs, wavy bob, wavy ponytail

options = []

al = chr.getAvatarData().getAvatarLook()
hairColour = al.getHair() % 10
if al.getGender() == 0: # Male
    options = [43140, 40250, 33040, 30060, 32350, 33170, 30210, 33100, 30610]
else: # Female
    options = [32360, 34400, 31820, 34270, 31860, 34210, 34250, 34490, 31360]
options = list(map(lambda x: x + hairColour, options))
answer = sm.sendAskAvatar("Choose your new hairstyle!", False, False, options)

if answer < len(options):
    sm.changeCharacterLook(options[answer])
