public static int binarySearch(int[] arr, int x, int left, int right) {
if (right <= left) { // промежуток пуст
return -1;
}
// промежуток не пуст
int mid = (left + right) / 2;
if (arr[mid] == x) {  // центральный элемент — искомый
return mid;
} else if (x < arr[mid]) { // искомый элемент меньше центрального значит следует искать в левой половине
return binarySearch(arr, x, left, mid);
} else { // иначе следует искать в правой половине
return binarySearch(arr, x, mid + 1, right);
}
}

// изначально мы запускаем двоичный поиск на всей длине массива
int index = binarySearch(arr, x, 0, arr.length); 


public static int binarySearchDescending(int[] arr, int x, int left, int right) {
if (right <= left) {
return -1;
}
// промежуток не пуст
int mid = (left + right) / 2;
if (arr[mid] == x) {  // центральный элемент — искомый
return mid;
} else if (arr[mid] < x) { // искомый элемент больше центрального на этот раз все элементы больше центрального располагаются в левой половине
return binarySearchDescending(arr, x, left, mid);
} else { // иначе следует искать в правой половине
return binarySearchDescending(arr, x, mid + 1, right);
}
}

// изначально мы запускаем двоичный поиск на всей длине массива
int index = binarySearchDescending(arr, x, 0, arr.length); 