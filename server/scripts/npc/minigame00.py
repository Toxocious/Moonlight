# Casey (1012008) | Henesys Park

items = [
4080000,   # Slime & Mushroom Omok Set
4080001,   # Slime & Octopus Omok Set
4080002,   # Slime & Pig Omok Set
4080003,   # Octopus & Mushroom Omok Set
4080004,   # Pig & Octopus Omok Set
4080005,   # Pig & Mushroom Omok Set
4080006,   # Bloctopus & Pink Teddy Omok Set
4080007,   # Bloctopus & Trixter Omok Set
4080008,   # Pink Teddy & Trixter Omok Set
4080009,   # Panda Teddy & Blocktopus Omok Set
4080010,   # Panda Teddy & Pink Teddy Omok Set
4080011,   # Panda Teddy & Trixter Omok Set
4080100,   # A set of Match Cards
]
mesocost = 5000

selStr = "Hello! I am the MiniGame Master!\r\nIf you're here to purchase minigames, you're at the right place!\r\nIt will cost you "+ str(mesocost) +" meso per item\r\n\r\n#b"
i = 0
while i < len(items):
    selStr += "#L"+ str(i) +"##z"+ str(items[i]) +"##l\r\n"
    i += 1
answer = sm.sendNext(selStr)

if not sm.canHold(items[answer]) or sm.getMesos() < mesocost:
    sm.sendSayOkay("I'm sorry, it seems that either you don't have enough money, or you don't have enough space")
else:
    sm.sendSayOkay("Great Choice! Here you go")
    sm.deductMesos(mesocost)
    sm.giveItem(items[answer])
