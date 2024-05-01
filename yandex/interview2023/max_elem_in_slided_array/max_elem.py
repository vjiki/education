# Есть массив чисел без повторов, который был отсортирован (по возрастанию),
# а затем элементы были циклически сдвинуты.
# Найдите максимальный элемент

# 40 50 10 15 20 30 35;   40 15 35;  0 3 6 -> 0 1 3
# 40 50 10 15         ;   0 1 3 -> 1 2 3
#    50 10 15         ;   50 < 10; 1 2
#             20 30 35

# 40 50 -> 50 15
# 50 10

# 20 30 35 40 50 10 15
# 20 40

# 10 20 30 40
# 40 10 20 30

# 20 30 35 40 50 10 15

# 10 20
# 20 10

def find_max_in_biased_array(arr: List[int]) -> int:
    if arr is None or len(arr) == 0:
        raise ...
    if len(arr) == 1:
        return arr[0]
    if len(arr) == 2:
        return max(arr[0], arr[1])

    start = 0
    end = len(arr)-1
    middle = (end+start)//2

    while True:
        if arr[start] < arr[middle]:
            start = middle
        else:
            end = middle
        if start + 1 == end:
            break
        middle = (end+start)//2

    maximum = max(arr[start], arr[end])
    return maximum
