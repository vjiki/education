public static void siftUp(List<Integer> heap, int index) {
    if (index == 1) {
    return;
    }

    int parentIndex = index / 2;
    if (heap.get(parentIndex - 1) < heap.get(index - 1)) {
    int temp = heap.get(parentIndex - 1);
    heap.set(parentIndex - 1, heap.get(index - 1));
    heap.set(index - 1, temp);
    siftUp(heap, parentIndex);
    }
    }

public static void heapAdd(List<Integer> heap, int key) {
    int index = heap.size() + 1;
    heap.add(key);
    siftUp(heap, index);
    }

public static void siftDown(List<Integer> heap, int index) {
    int left = 2 * index + 1;
    int right = 2 * index + 2;

    if (left >= heap.size()) {
    return;
    }

    int indexLargest = left;
    if (right < heap.size() && heap.get(left) < heap.get(right)) {
    indexLargest = right;
    }

    if (heap.get(index) < heap.get(indexLargest)) {
    int temp = heap.get(index);
    heap.set(index, heap.get(indexLargest));
    heap.set(indexLargest, temp);
    siftDown(heap, indexLargest);
    }
    }

public static int popMax(List<Integer> heap) {
    int result = heap.get(0);
    heap.set(0, heap.get(heap.size() - 1));
    heap.remove(heap.size() - 1);
    siftDown(heap, 0);
    return result;
    }