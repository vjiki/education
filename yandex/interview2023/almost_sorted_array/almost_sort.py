# Дан массив, который почти отсортирован.
# Нужно найти подмассив минимального размера, отсортировав который,
# массив превращается в отсортированный, и вернуть его размер:
# 10, 20, 50, (*) 40, 15, 60, 70, (*) 9, 10, 11, 12
# 1 - перепады 50    9:    50, 40, 15, 60, 70, 9,
# 2 - внутри перепадного инт (50, 40, 15, 60): мин и макс;       9    70
# 3 - реальные границы

# 10, 20, 31, 34, 36, 50, 40, 30, 60, 70
# 10, 13
# 10, 8, 6, 4, 3
# 10, 10, 10, 10, 10

def sort_size(arr: List[int]) -> int:
    # your code
    if arr is None or len(arr) == 0:
        raise Exception(...)
    if len(arr) == 1:
        return 0

    n = len(arr)
    t1 = None
    for i in range(1, n):
        if arr[i-1] > arr[i]:
            t1 = i-1
            break
    if t1 is None:
        return 0

    minimum = arr[t1]
    for i in range(t1 + 1, n):
        if arr[i] < minimum:
            minimum = arr[i]

    t2 = 0                       #     st t1/ma     mi/t2/en
    for i in range(n-2, 0, -1):  # 10, 20, 50, 60, 40,    15,     60, 70
        if arr[i] > arr[i+1]:
            t2 = i+1
            break
    maximum = arr[t2]
    for i in range(0, t2):
        if arr[i] > maximum:
            maximum = arr[i]

    start = 0
    end = 0
    for i in range(0, n):
        if arr[i] > minimum:
            start = i
            break
    for i in range(n-1, 0, -1):
        if arr[i] < maximum:
            end = i
            break

    return end-start+1