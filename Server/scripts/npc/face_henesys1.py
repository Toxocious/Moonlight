import os.path
path = "C:/Users/Downloads/v207-master/v207-master/wz/Character.wz/Face"
files = os.listdir(path)
face = []
for x in files:
    face.append(int(x[3:8]))
   
    
    
text = "I'll change your face\r\n#b"
for i in range(60):
    text += "#L" + str(i+1) + "#Selection " + str(i) + "#l\r\n"
selection = sm.sendSayOkay(text) - 1

if selection <= 60:
    min = 100*selection
    max = min + 100
    a = face[min:max]
    choice = sm.sendAskAvatar("Choose your face!", False, False, a)
    sm.changeCharacterLook(a[choice])