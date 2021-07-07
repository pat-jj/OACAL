import matplotlib
import matplotlib.pyplot as plt
import numpy as np

with open("9modules.txt", "r") as f:
    rdata = f.read()

# 9-modules distribution

data = []
for i in range(24309):
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

data_by_100 = []
for j in range(int(len(pro_data) / 100)):
    cnt = 0
    for i in range(j*100, j*100 + 100):
        if pro_data[i] == 1:
            cnt = cnt + 1

    data_by_100.append(cnt)

print(data_by_100)
print(len(data_by_100))

x = []
for i in range(len(data_by_100)):
    x.append(i)

z1 = np.polyfit(x, data_by_100, 1)
p1 = np.poly1d(z1)
w = np.arange(0, 250)
q = z1[0]*w + z1[1]
print(z1)

matplotlib.pyplot.axis([0, 250, -10, 100])

plt.title("Revision Distribution - 9 modules", fontsize=12)

for i in range(len(data_by_100)):
    plt.plot(i, data_by_100[i], 'bo')

plt.ylabel('Firstly appeared revisions', fontsize=11)
plt.xlabel('Every 100 revisions', fontsize=12)
plt.plot(w, q, 'r')
plt.plot(0, 75, "bo", label="revision numbers")
plt.plot(0, 100, "r-", label="y = -0.159x + 39.249")
plt.legend(loc=2)
plt.show()
