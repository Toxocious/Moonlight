# Quest [Familiar] Roro's Function Report

ROROS_FUNCTION_REPORT_QUEST = 64999

RORO = 9133403
MYSTERIOUS_AIDE = 9133401


def arrayToMapleList(array):
    text = ""
    for i, val in enumerate(array):
        text += "#L" + str(i) + "#" + val + "#l\r\n"
    return text


def talkMysteriousAide(message):
    sm.setSpeakerID(MYSTERIOUS_AIDE)
    return sm.sendNext(message)


def talkRoro(message):
    sm.setSpeakerID(RORO)
    sm.sendNext(message)


def talkChar(message):
    sm.setPlayerAsSpeaker()
    sm.sendNext(message)
    sm.resetParam()


talkRoro("Roro...")
talkRoro("Roro... Rorororo... Roro...")
talkChar("What is it now, you overgrown digital launchbox?")
talkMysteriousAide(
    "Ahem! Are you the 'Chosen One'? The master has asked me to bring you up to speed on Roro's functions and Familiars in general."
)
talkChar("Gah! Another one!")
talkMysteriousAide("I will teach you all about what you can do with Familiars.")
talkMysteriousAide(
    "It may seem daunting at first, but your proprietary Roro unit is there to help you every step of the way!"
)

optionMysteriousAide = [
    "I'm fine. Trust me (Skip)",
    "Fine, let's get this over with.",
    "No! No need! I hate lectures."
]

selection = talkMysteriousAide(
    "So, are you ready to learn about Familiars? \r\n" + arrayToMapleList(optionMysteriousAide)
)

if selection == 0:  # I'm fine. Trust me (Skip)
    talkMysteriousAide("There you go! You're all set then.")
    sm.completeQuestNoRewards(ROROS_FUNCTION_REPORT_QUEST)
    sm.dispose()

elif selection == 1:  # Fine, let's get this over with.
    text = "Wonderful! Let's go through Roro's interface window-by-window.\r\n"
    text += "This is the excliting part: (bla,bla,bla)\r\n"

    talkMysteriousAide(text)
    sm.completeQuestNoRewards(ROROS_FUNCTION_REPORT_QUEST)
    sm.dispose()

elif selection == 2:  # No! No need! I hate lectures.
    talkMysteriousAide(
        "Are you sure you're okay with... no explanation at all?\r\n #L0#Ahh, I guess I should listen... I'm just so busy...#l")
    talkMysteriousAide("I'll #balways be waiting here,#k so chat with me any time.")
    sm.dispose()

sm.dispose()
