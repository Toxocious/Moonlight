# Toh Relicseeker - FM Beauty NPC

from net.swordie.ms.loaders import StringData

FreeMarket = 910000000

if sm.getFieldID() == FreeMarket:
    al = chr.getAvatarData().getAvatarLook()
    selection = sm.sendNext("Hi my name is Toh I really love to make people look their best. Maybe I could help you look good too.\r\n#b"
                            "#L0#Change my Hairstyle.\r\n#b"
                            "#L1#Change my Face.\r\n#b"
                            "#L2#Change my Eye Color.\r\n#b"
                            "#L3#Change my Skin Color.\r\n#b"
                            "#L4#Change my Hair Color.\r\n#b"
                            "#L5#Change my Gender.\r\n#b")
    if selection == 0: # Hairstyle
        options = []
        hairColor = al.getHair() % 10
        if al.getGender() == 0: # Male
            for x in range(100):
                options.append(30000 + 10 * x)
        else: # Female
            for x in range(100):
                options.append(31000 + 10 * x)
        options = list(map(lambda x: x + hairColor, options))

    elif selection == 1: #face
        options = []
        eyeColor = al.getFace() % 10
        if al.getGender() == 0: # Male
            for x in range(60):
                options.append(20000 + x)
        else: # Female
            for x in range(37):
                options.append(21000 + x)
        options = list(map(lambda x: x + eyeColor, options))


    elif selection == 2: # eye color
        face = al.getFace() - ((al.getFace() % 1000 / 100) * 100) #it rounds the number
        options = []
        for x in range (9):
            options.append(face + x * 100)

    elif selection == 3: # skin
        options = []
        for x in range (5):
            options.append(x)

    elif selection == 4: #Hair color
        hair = al.getHair() - (al.getHair() % 10)
        options = []
        for x in range (9):
            options.append(hair + x)


if selection == 5:
    answer = sm.sendAskYesNo("Are you sure you want to change your gender?")
    if answer:
        chr.changeGender()
        sm.sendNext("Please change channel in order for the gender change to take in effect.")
else:
    for id in options:
        if StringData.getItemStringById(id) is None:
            options.remove(id)
    answer = sm.sendAskAvatar("Choose your new look!", False, False, options)
    if answer < len(options):
        sm.changeCharacterLook(options[answer])