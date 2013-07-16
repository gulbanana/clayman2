# calculates the action-cost of raising Narrow qualities to certain success chances, with a failure penalty of 1 action

def calc_limit(base_chance, iterations):
    ev = 0
    chance = base_chance
    total_chance = base_chance
    cost = 1

    for i in range(iterations):
        ev = ev + (chance * cost)
        chance = (1 - total_chance) * base_chance
        total_chance = total_chance + chance
        cost = cost + 2

    print("{0} terms at {1}%: {2} actions".format(iterations, base_chance*100, ev))


calc_limit(0.7, 1000)
calc_limit(0.8, 1000)