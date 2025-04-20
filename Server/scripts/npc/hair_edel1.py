# Amoria Hair Salon
# Male: Astro, //Babby, //Battle Mage, Bowling, Cabana, Grand Lionman, Rockstar, Roving Rockstar, //SHaggy Dragon, Slick Dean, Tornade
# Female: Carla, Cecelia Twist, Daisy Do, Jolie, Lori, Minnie, Rose, Roxy, Sunflower Power, Wild Hunter, Zessica
options = []

al = chr.getAvatarData().getAvatarLook()
hairColour = al.getHair() % 10
if al.getGender() == 0: # Male
    options = [30350, 30760, 30330, 30560, 30040, 30730, 30470, 30460]
else: # Female
    options = [31310, 31490, 37810, 31130, 31160, 31500, 31230, 31320, 31560, 34190, 31530]
options = list(map(lambda x: x + hairColour, options))
answer = sm.sendAskAvatar("Choose your new hairstyle!", False, False, options)

if answer < len(options):
    sm.changeCharacterLook(options[answer])
