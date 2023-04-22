# 240060000 ~ 240060002
BASE_MAP = 240060100

NORMAL = 0
HARD = 1
EASY = 2

diff = field.getId() - BASE_MAP
sm.chat(str(field.getId()))
ht = 8810001 + diff * 100
sm.spawnMob(ht, -420, 230)