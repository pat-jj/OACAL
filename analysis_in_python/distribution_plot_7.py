import matplotlib
import matplotlib.pyplot as plt
import numpy as np

with open("7modules.txt", "r") as f:
    rdata = f.read()

# 8-modules distribution

data = []
for i in range(1715):
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

data_by_5 = []
for j in range(int(len(pro_data) / 5)):
    cnt = 0
    for i in range(j*5, j*5 + 5):
        if pro_data[i] == 1:
            cnt = cnt + 1

    data_by_5.append(cnt)

print(data_by_5)
print(len(data_by_5))

x = []
for i in range(len(data_by_5)):
    x.append(i)

z1 = np.polyfit(x, data_by_5, 1)
p1 = np.poly1d(z1)
w = np.arange(0, 350)
q = z1[0]*w + z1[1]
print(z1)

matplotlib.pyplot.axis([0, 350, -1, 8])

plt.title("Revision Distribution - 7 modules", fontsize=12)

for i in range(len(data_by_5)):
    plt.plot(i, data_by_5[i], 'bo')

plt.ylabel('Firstly appeared revisions', fontsize=11)
plt.xlabel('Every 5 revisions', fontsize=12)
plt.plot(w, q, 'r')
plt.plot(0, 24, "bo", label="revision numbers")
plt.plot(0, 100, "r-", label="y = -0.0069x + 2.431")
plt.legend(loc=2)
plt.show()
