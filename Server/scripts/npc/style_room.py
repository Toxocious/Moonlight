# Dressing Room for Hair/Face/Eyes

# Hair
# Male: 30000, 33000, 35000, 36000, 40000, 43000, 45000, 46000, 60000
# Female: 31000, 34000, 37000, 38000, 41000, 44000, 47000, 48000, 61000
# Unisex/Special: 32000, 39000, 42000

# Face
# Male: 20000, 23000, 25000, 27000, 50000, 53000
# Female: 21000, 24000, 26000, 28000, 51000, 54000

from net.swordie.ms.loaders import StyleRoom
from net.swordie.ms.ServerConfig import SERVER_NAME

hair_male = StyleRoom.getMaleHair()
hair_female = StyleRoom.getFemaleHair()
hair_special = StyleRoom.getSpecialHair()

face_male = StyleRoom.getMaleFace()
face_female = StyleRoom.getFemaleFace()

# id, gender_string, list
hair_categories = [
    [0, "Male Hair",     hair_male],
    [1, "Female Hair",   hair_female],
    [2, "Special Hair",  hair_special]
]

# id, gender_string, list
face_categories = [
    [0, "Male Face",     face_male],
    [1, "Female Face",   face_female],
]

# vars
selected_list = []
al = chr.getAvatarData().getAvatarLook()


def prompt_category(category_name, category_list):
    text = (
        "What type of #b{}#k would you like to browse?\r\n#b"
    ).format(category_name)

    for c in category_list:
        text += "\n#b#L" + repr(c[0]) + "#" + c[1] + "#l\r\n"

    return text

# SCRIPT_START

text_menu = (
    "Hello! Welcome to {}'s Style NPC! \r\n\r\nWhat would you like to change?\r\n#b"
    "#L0#Hair#l\r\n"
    "#L1#Face#l\r\n\r\n"

    "#L2#Hair Color#l\r\n"
    "#L3#Eye Color#l\r\n"

).format(SERVER_NAME)

selection_menu = sm.sendNext(text_menu)
selected_category = []
options = []

if selection_menu == 0: # hair
    selected_category = hair_categories
    selection_category = sm.sendNext(prompt_category("Hair", selected_category))
elif selection_menu == 1: # face
    selected_category = face_categories
    selection_category = sm.sendNext(prompt_category("Face", selected_category))
elif selection_menu == 2: # hair color
    hair = al.getHair() - (al.getHair() % 10)
    for x in range (9):
        options.append(hair + x)
elif selection_menu == 3: # face color
    face = al.getFace() - ((al.getFace() % 1000 / 100) * 100) # it rounds the number
    for x in range (9):
        options.append(face + x * 100)

if selection_menu < 2:
    option = selected_category[selection_category]
    category_name = option[1]
    category_list = option[2]

    text_category = "Select a category you'd like to browse!"
    for idx, category in enumerate(category_list):
        text_category += "\r\n#b#L" + repr(idx) + "#" + category_name + " " + repr(idx + 1) + "#l"
    selection_number = sm.sendNext(text_category)
    for hair in category_list[selection_number]:
        options.append(hair.getId())

if len(options) > 0:
    answer = sm.sendAskAvatar("Choose your new look!", False, False, options)
    if answer < len(options):
        sm.changeCharacterLook(options[answer])
