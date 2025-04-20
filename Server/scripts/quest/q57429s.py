
# Quest: To Each His Own (57429)
# Author: Tiger

NAOE_KANESTSUGU = 9130022
HAKU = 9130081

if sm.hasQuestCompleted(57428) and chr.getLevel() >= 25: # Scaling the El Nath Mountains
    sm.removeEscapeButton()

    sm.setSpeakerID(NAOE_KANESTSUGU)
    sm.setBoxChat()
    sm.sendNext("What a coincidence! You've been so busy with all your missions I thought I'd never see you again. And you've grown stronger too. I can sense it. What's the tally at?")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("Kanetsugu! What brings you to such a faraway place? I haven't been counting, but if I had to guess, I'd say... Five thousand dead?")


    sm.setSpeakerID(NAOE_KANESTSUGU)
    sm.setBoxChat()
    sm.sendSay("Five thousand? Your powers truly have returned. And you've far surpassed my pitiful 4,000. Haruaki ordered me to collect info from various places in Maple World. I was just on my way to Aqua Road. Apparently they can make roads out of water here.")
    sm.sendSay("But if I must walk on water to fulfill Kenshin's vision, then walk on water I will! Nobunaga will fall by my hands, and Kenshin will love me for it.")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("Does he really believe you killed 5,000 soldiers? Ha. How about 500.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("Shush, Haku. Yes, Kanetsugu! We must show the alliance what we're made of! If Kenshin saw you here, I know she'd be proud.")

    sm.setSpeakerID(NAOE_KANESTSUGU)
    sm.setBoxChat()
    if sm.sendAskAccept("Sniff... Thank you Kanna. Good luck on your mission! I trust your tally will have doubled by the time we meet again."):
        sm.sendNext("For Kenshin!")

        sm.setSpeakerID(HAKU)
        sm.setBoxChat()
        sm.sendSay("He runs around the battlefield screaming her name and still he defeats more enemies than you.")

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendSay("With my powers recovered, I'll catch up in no time. Besides, I've got your divine fox hair all over my clothes. There's no way I can lose with that...")

        sm.startQuest(57429)  #  To Each His Own
        # direction nav to -> El Nath : El Nath (211000000)
