import matplotlib
import matplotlib.pyplot as plt
import numpy as np

with open("6modules.txt", "r") as f:
    rdata = f.read()

# 8-modules distribution

data = []
for i in range(461):
    data.append(rdata[1 + i * 3])


def string_to_num(data):
    pro_data = []
    for i in range(len(data)):
        if data[i] == '1':
            pro_data.append(1)
        else:
            pro_data.append(0)
    return pro_data


pro_data = string_to_num(data)

print(pro_data)

data_by_2 = []
for j in range(int(len(pro_data) / 2)):
    cnt = 0
    for i in range(j*2, j*2 + 2):
        if pro_data[i] == 1:
            cnt = cnt + 1

    data_by_2.append(cnt)

print(data_by_2)
print(len(data_by_2))

x = []
for i in range(len(data_by_2)):
    x.append(i)

z1 = np.polyfit(x, data_by_2, 1)
p1 = np.poly1d(z1)
w = np.arange(0, 240)
q = z1[0]*w + z1[1]
print(z1)

matplotlib.pyplot.axis([0, 240, -1, 5])

plt.title("Revision Distribution - 6 modules", fontsize=12)

for i in range(len(data_by_2)):
    plt.plot(i, data_by_2[i], 'bo')

plt.ylabel('Firstly appeared revisions', fontsize=11)
plt.xlabel('Every 2 revisions', fontsize=12)
plt.plot(w, q, 'r')
plt.plot(0, 100, "bo", label="revision numbers")
plt.plot(0, 100, "r-", label="y = -0.00495x + 1.141")
plt.legend(loc=2)
plt.show()
