# What happened to Ayame (Sengoku Era) Questline

AYAME = 9130106

sm.setSpeakerID(AYAME)

response = sm.sendAskYesNo("... How did I get here? It's a long story and I'm pretty tired... Are you SURE you wanna hear it now?")

if response:
    sm.warp(811000009) # Master Room
