# Created by MechAviv
# Kinesis Introduction
# Map ID :: 101030000
# East Forest :: Giant Tree
KINESIS = 1531000
FANZY = 1040002
sm.setNpcOverrideBoxChat(FANZY)
sm.sendNext("Meow. Huh? Who are you?")

sm.setNpcOverrideBoxChat(KINESIS)
sm.sendSay("#face2#Man... I never thought talking cats would be the norm for me.")

sm.setNpcOverrideBoxChat(FANZY)
sm.sendSay("(Sniff sniff) This smell... You are not #ba human from this world#k!")

sm.setNpcOverrideBoxChat(KINESIS)
sm.sendSay("#face3#Hey, don't go smelling new people without asking. Now, where is Grendel the Really Old?")

sm.setNpcOverrideBoxChat(FANZY)
sm.sendSay("Grendel the Really Old... is no more.")

sm.setNpcOverrideBoxChat(KINESIS)
sm.sendSay("#face4#Wait, what? What happened? How...")

sm.setNpcOverrideBoxChat(FANZY)
sm.sendSay("Wait, wait, wait! HERE no more. He's not HERE no more. He just returned to the Magician Association!")

sm.setNpcOverrideBoxChat(KINESIS)
sm.sendSay("#face2#Oh, geez... Don't scare me like that, cat. I guess I just missed him.")

sm.setNpcOverrideBoxChat(FANZY)
sm.sendSay("Hold up a moment, chief! I need your help. There's a student named #bCootie#k in our association. He wandered off into the woods and never came back. I'm worried about the little squirt...")

sm.setNpcOverrideBoxChat(KINESIS)
sm.sendSay("#face4#Wait, wait, wait... I'm sorry about Creepy or whatever his name is, but I just need to get back to my world.")

sm.setNpcOverrideBoxChat(FANZY)
sm.sendSay("Sigh... You're right. I don't know why I thought an outsider would help.")
sm.sendSay("By the way, what is WITH your clothes? I feel embarrassed for you. Here, would you like to use this? Someone left it in a ditch, but it's still an improvement.\r\n\r\n#b#i1082020#  #t1082020#")

sm.giveItem(1082020)
sm.completeQuest(parentID)
sm.giveExp(4100)

sm.setIntroBoxChat(KINESIS)
sm.sendNext("#face2#Actually, this looks pretty useful. Thanks, I guess.")

sm.setNpcOverrideBoxChat(FANZY)
sm.sendSay("Hmph, don't mention it. Just hurry on back to wherever you're from.")

sm.warp(101020400, 0)