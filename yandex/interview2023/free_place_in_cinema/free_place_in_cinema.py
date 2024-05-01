"""
Места в кинотеатре расположены в один ряд.
Только что пришедший зритель выбирает место,
чтобы сидеть максимально далеко от остальных зрителей в ряду. То есть расстояние от того места, куда сядет зритель до ближайшего к нему зрителя должно быть максимально.
Гарантируется, что в ряду всегда есть свободные места
и уже сидит хотя бы один зритель.
Напишите функцию, которая по заданному ряду мест
(массиву из нулей и единиц) вернёт расстояние от выбранного места до ближайшего зрителя.

find_best_seat_dist([1, 0, 0, 0, 1]) -> 2
find_best_seat_dist([1, 0, 1, 0, 0, 1, 0, 0, 0, 1]) -> 2
find_best_seat_dist([1, 0, 1, 0]) -> 1

find_best_seat_dist([0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1]) -> 2

"""
# [1, 0]
# [0, 1]
def find_best_seat_dist(row: list):
    n = len(row) # 7

    counter1 = None
    for i in range(n):
        if row[i] == 1:
            counter1 = i # 1
            break

    counter2 = None
    for i in range(n-1, -1, -1):
        if row[i] == 1:
            counter2 = n-1-i    # 1
            break

    max_len = 0
    current_len = 0
    for i in range(counter1, n-counter2):   # [1, 0, 1]  c1=0, c2=1   0..2
        if row[i] == 0:
            current_len += 1
        else:
            if current_len > max_len:
                max_len = current_len
            current_len = 0
    counter3 = max_len
    #  0  1  2  3  4  5  6
    # [0, 1, 0, 0, 0, 1, 0], expected=2
    # [0, 0, 1]  c1 = 2, расстояние=2
    # [1, 0, 0]  c2 = 2, расстояние=2
    # [1, 0, 0, 1]  расст=1
    # [1, 0, 0, 0, 1]  расст=2
    # [1, 0, 0]
    return max(counter1, counter2, (counter3 + 1) / 2)
