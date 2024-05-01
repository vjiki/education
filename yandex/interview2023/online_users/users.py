# Даны два отсортированных списка с интервалами присутствия пользователей
# Начало интервала строго меньше конца
# Нужно вычислить интервалы когда оба пользователя были онлайн

# примеры
# user1 [(8,12), (17,22)]
# user2 [(5,11), (14,18), (20,23), (42,55)]
# => [(8,11), (17,18), (20,22)]
#
# user1 [(9,15), (18,21)]
# user2 [(10,14), (21,22)]
# => [(10,14)]



def is_intersects(i1, i2):
	if i1[1] <= i2[0]: # (1,2), (5,11)
		return False
	if i1[0] >= i2[1]: # (11,12), (5,11)
		return False
	return True

def main(list1, list2):
	idx1 = 0
	idx2 = 0
	result = []

	while True:
		if idx1 >= len(list1) or idx2 >= len(list2):
			return result

		item1 = list1[idx1]
		item2 = list2[idx2]
		if not is_intersects(item1, item2):
			if item1[1] <= item2[0]: # (1,2), (5,11)
				idx1 += 1
			else:
				idx2 += 1
			continue

		# intersection
		first = max(item1[0], item2[0])
		second = min(item1[1], item2[1])
		result.append((first, second))

		if second == item1[1]:
			idx1 += 1
		else if second == item2[1]:
			idx2 += 1