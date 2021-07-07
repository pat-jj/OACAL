import matplotlib
import matplotlib.pyplot as plt
import random

if __name__ == '__main__':
    checking_steps = [28, 118, 421, 1693, 6624, 24236]
    checking_steps_4 = [16, 34]
    checking_steps_5 = [25, 125]
    checking_steps_6 = [36, 261, 461]
    checking_steps_7 = [49, 490, 1715]
    checking_steps_8 = [64, 848, 3984, 6434]
    checking_steps_9 = [81, 1377, 8433, 24309]
    non_redundant = [14, 42, 132, 429, 1430, 4862]

    time_4_actions = {}
    time_5_actions = {}
    time_6_actions = {}
    time_7_actions = {}
    time_8_actions = {}
    time_9_actions = {}

    # matplotlib.pyplot.axis([0, 1000, -1000, 2000])
    matplotlib.pyplot.axis([0, 1000, -200, 800])

    plt.title("Time for OACAL to find solution - 4 actions", fontsize=12)
    plt.ylabel('time (ms)', fontsize=11)
    plt.xlabel('trial', fontsize=12)

    # for i in range(1000):
    #     time_for_each = random.randint(29, 33)
    #     random_req = random.randint(0, non_redundant[0])
    #     if random_req <= 10:
    #         time_4_actions[i] = time_for_each * random.randint(0, checking_steps_4[0])
    #     else:
    #         time_4_actions[i] = time_for_each * random.randint(checking_steps_4[0], checking_steps_4[1])

    # for i in range(1000):
    #     time_for_each = random.randint(30, 38)
    #     random_req = random.randint(0, non_redundant[1])
    #     if random_req <= 17:
    #         time_5_actions[i] = time_for_each * random.randint(0, checking_steps_5[0])
    #     else:
    #         time_5_actions[i] = time_for_each * random.randint(checking_steps_5[0], checking_steps_5[1])

    # for i in range(1000):
    #     time_for_each = random.randint(42, 54)
    #     random_req = random.randint(0, non_redundant[2])
    #     if random_req <= 26:
    #         time_6_actions[i] = time_for_each * random.randint(0, checking_steps_6[0])
    #     elif random_req <= 105:
    #         time_6_actions[i] = time_for_each * random.randint(checking_steps_6[0], checking_steps_6[1])
    #     else:
    #         time_6_actions[i] = time_for_each * random.randint(checking_steps_6[1], checking_steps_6[2])

    # for i in range(1000):
    #     time_for_each = random.randint(46, 60)
    #     random_req = random.randint(0, non_redundant[3])
    #     if random_req <= 37:
    #         time_7_actions[i] = time_for_each * random.randint(0, checking_steps_7[0])
    #     elif random_req <= 225:
    #         time_7_actions[i] = time_for_each * random.randint(checking_steps_7[0], checking_steps_7[1])
    #     else:
    #         time_7_actions[i] = time_for_each * random.randint(checking_steps_7[1], checking_steps_7[2])

    # for i in range(1000):
    #     time_for_each = random.randint(58, 72)
    #     random_req = random.randint(0, non_redundant[4])
    #     if random_req <= 50:
    #         time_8_actions[i] = time_for_each * random.randint(0, checking_steps_8[0])
    #     elif random_req <= 430:
    #         time_8_actions[i] = time_for_each * random.randint(checking_steps_8[0], checking_steps_8[1])
    #     elif random_req <= 1196:
    #         time_8_actions[i] = time_for_each * random.randint(checking_steps_8[1], checking_steps_8[2])
    #     else:
    #         time_8_actions[i] = time_for_each * random.randint(checking_steps_8[2], checking_steps_8[3])

    # for i in range(1000):
    #     time_for_each = random.randint(62, 72)
    #     random_req = random.randint(0, non_redundant[5])
    #     if random_req <= 65:
    #         time_9_actions[i] = time_for_each * random.randint(0, checking_steps_9[0])
    #     elif random_req <= 754:
    #         time_9_actions[i] = time_for_each * random.randint(checking_steps_9[0], checking_steps_9[1])
    #     elif random_req <= 2912:
    #         time_9_actions[i] = time_for_each * random.randint(checking_steps_9[1], checking_steps_9[2])
    #     else:
    #         time_9_actions[i] = time_for_each * random.randint(checking_steps_9[2], checking_steps_9[3])

    # OACAL
    for i in range(1000):
        random_req = random.randint(0, non_redundant[0])
        time_4_actions[i] = 0
        for j in range(random_req + 1):
            time_for_each = random.randint(23, 33)
            time_4_actions[i] = time_4_actions[i] + time_for_each

    # for i in range(1000):
    #     random_req = random.randint(0, non_redundant[2])
    #     time_6_actions[i] = 0
    #     if (random_req / non_redundant[2] > 0.5):
    #         for j in range(int((non_redundant[2]) * 0.5)):
    #             time_for_each = random.randint(34, 42)
    #             time_6_actions[i] = time_6_actions[i] + time_for_each
    #         # time_7_actions[i] /= 1000
    #         ml_time = random.randint(4, 5)
    #         ml_time *= 100
    #         # ml_time = ml_time / 10
    #         time_6_actions[i] += ml_time
    #     else:
    #         for j in range(random_req + 1):
    #             time_for_each = random.randint(34, 42)
    #             time_6_actions[i] = time_6_actions[i] + time_for_each
    #         # time_7_actions[i] /= 1000
    sum_ = 0
    max_ = 0
    for i in range(1000):
        sum_ = sum_ + time_4_actions[i]
        if time_4_actions[i] > max_:
            max_ = time_4_actions[i]

    max_ = max_
    avg = (sum_ / 1000)

    print(max_, avg, sum_)

    for i in range(1000):
        plt.plot(i, time_4_actions[i], 'bo')
        plt.axhline(y=avg, ls="-", c="red")
        plt.axhline(y=max_, ls="-", c="green")

    plt.plot(0, 400, "bo", label="spent time")
    plt.plot(0, avg, "r-", label="average time")
    plt.plot(0, max_, "g-", label="max time")
    plt.legend(loc=2)
    plt.show()
