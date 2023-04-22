# Shaolin Temple : Sutra Despository 5-6F (701220310)  |  Secret room used for a minigame in the Shaolin questline

from net.swordie.ms.client.character.skills.temp import CharacterTemporaryStat

time = 3 *60

sm.giveCTS(CharacterTemporaryStat.Morph, 222, 0, 180) # nOption 222 = Gold Giant
sm.lockInGameUI(True)
sm.sayMonologue("#fNpc/9310047.img/stand/0# #e#fs30# [Find the Secret Library!]\r\n\r\n\r\n"
                ""
                "#fs16#Investigate Sutra Depository Floor 5-6 and find the Secret Library within the time limit.\r\n"
                "A few tips:\r\n\r\n"
                "1. Search inside for the secret portal to the Secret Library\r\n"
                "2. Monsters won't attack you while you're in demon form.\r\n"
                "3. You will return to your original form if you bump into any obstacles.\r\n"
                "4. Monsters will attack you in your original form, so be careful!", True)

sm.setInstanceTime(time, 701220200)
sm.lockInGameUI(False)