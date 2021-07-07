import matplotlib
import matplotlib.pyplot as plt
import numpy as np

with open("4modules.txt", "r") as f:
    rdata = f.read()

# 8-modules distribution

data = []
for i in range(34):
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

# data_by_2 = []
# for j in range(int(len(pro_data) / 2)):
#     cnt = 0
#     for i in range(j*2, j*2 + 2):
#         if pro_data[i] == 1:
#             cnt = cnt + 1
#
#     data_by_2.append(cnt)
#
# print(data_by_2)
# print(len(data_by_2))

x = []
for i in range(len(pro_data)):
    x.append(i)

z1 = np.polyfit(x, pro_data, 1)
p1 = np.poly1d(z1)
w = np.arange(0, 35)
q = z1[0]*w + z1[1]
print(z1)

matplotlib.pyplot.axis([0, 35, -0.5, 2])

plt.title("Revision Distribution - 4 modules", fontsize=12)

for i in range(len(pro_data)):
    plt.plot(i, pro_data[i], 'bo')

plt.ylabel('Firstly appeared revisions', fontsize=11)
plt.xlabel('Every revision', fontsize=12)
plt.plot(w, q, 'r')
plt.plot(0, 100, "bo", label="revision numbers")
plt.plot(0, 100, "r-", label="y = -0.0226x + 0.785")
plt.legend(loc=2)
plt.show()
