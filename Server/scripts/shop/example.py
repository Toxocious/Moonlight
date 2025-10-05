# Lista de Items de la tienda con formato Triple<ID, Precio, Moneda> <int int string>
items = [
    (2501000, 7000000, "MESO"),  # AP Reset Scroll
    (2500000, 7000000, "MESO"),  # SP Reset Scroll
    (5040008, 3000000, "MESO"),  # Teleport Rock 3 Day
    (5133000, 1000000, "MESO"),  # Buff Freezer
    (5130000, 5000000, "MESO"),  # Safety Charm
    (5510000, 2000000, "MESO")  # Respawn Token
]

sm.sendShop(items)
sm.dispose()
