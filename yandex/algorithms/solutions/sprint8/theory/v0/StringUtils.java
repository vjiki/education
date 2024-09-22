// промокод FRIENDALGO

public class StringUtil {
    // Вставить строку substring в строку string перед позицией index.
    public static String insert(String string, int index, String substring) {
        int length = string.length();
        int shift = substring.length();
        if (index > length) {
            // index == length - край строки
            throw new IllegalArgumentException("Нет такой позиции");
        }
        string = String.format("%-" + (length + shift) + "s", string);
        if (length > 0) {
            // Если length == 0, делать сдвиг нет смысла.
            // Кроме того, не следует в вычислениях писать (length - 1),
            // не проверив, что индекс не ноль.
            // В некоторых языках длина представляется беззнаковым целым числом,
            // в таком случае (length - 1) будет равен не -1, а числу MAX_INT,
            // и цикл станет некорректным. Мы этого избегаем.
            for (int i = length - 1; i >= index; i--) {
                string = string.substring(0, i + shift) + string.charAt(i) + string.substring(i + shift + 1);
            }
        }
        for (int i = 0; i < shift; i++) {
            string = string.substring(0, index + i) + substring.charAt(i) + string.substring(index + i + 1);
        }
        return string;
    }
}



boolean compare(String first, String second) {
    if (first.length() != second.length()) {
        return false;
    }

    int length = first.length();
    for (int i = 0; i < length; ++i) {
        if (first.charAt(i) != second.charAt(i)) {
            return false;
        }
    }

    return true;
}




// Найти первое вхождение подстроки pattern в строке text,
//   находящееся на позиции не раньше start.
public static int find(String text, String pattern, int start) {
    if (text.length() < pattern.length()) {
        return -1;  // Длинный шаблон не может содержаться в короткой строке.
    }
    for (int pos = start; pos <= text.length() - pattern.length(); pos++) {
        // Проверяем, не совпадёт ли шаблон, сдвинутый на позицию pos,
        //   с соответствующим участком строки.
        boolean match = true;
        for (int offset = 0; offset < pattern.length(); offset++) {
            if (text.charAt(pos + offset) != pattern.charAt(offset)) {
                // Одного несовпадения достаточно, чтобы не проверять
                //   дальше текущее расположение шаблона.
                match = false;
                break;
            }
        }
        // Как только нашлось совпадение шаблона, возвращаем его.
        // Это первое вхождение шаблона в строку.
        if (match == true) {
            return pos;
        }
        // Если совпадение не нашлось, цикл перейдёт к проверке следующей позиции.
    }
    // Числом -1 часто маркируют, что подстрока не была найдена,
    //   поскольку в строке нет позиции -1.
    // В качестве альтернативы можно возвращать null.
    return -1;
}


public static List<Integer> findAll(String text, String pattern) {
    List<Integer> occurrences = new ArrayList<>();
    int start = 0; // Начнём поиск с начала строки.
    // Найдём первое вхождение, если оно есть.
    while (true) {
        int pos = find(text, pattern, start);
        if (pos == -1) {
            break;
        }
        occurrences.add(pos); // Сохраним вхождение в список.
        start = pos + 1;       // И продолжим поиск, начиная с позиции,
        // следующей за только что найденной.
    }
    return occurrences;
}


// prefix function
int N = s.length();
int[] π = new int[N];

for (int i = 1; i < N; i++) {
    String substring = s.substring(0, i);
    for (int k = i - 1; k >= 0; k--) {
        String prefix = substring.substring(0, k);
        String suffix = substring.substring(i - k, i);
        if (prefix.equals(suffix)) {
            π[i-1] = k;
            break;
        }
    }
}

// effective prefix
public static int[] prefixFunction(String s) {
    int N = s.length();
    int[] π = new int[N];
    for (int i = 1; i < N; i++) {
        int k = π[i-1];
        while (k > 0 && s.charAt(k) != s.charAt(i)) {
            k = π[k-1];
        }
        if (s.charAt(k) == s.charAt(i)) {
            k++;
        }
        π[i] = k;
    }
    return π;
}


// найти вхождения подстроки в строке и вывести индексы

public static List<Integer> search(String p, String text) {
    // Функция возвращает все позиции вхождения шаблона в тексте.
    List<Integer> result = new ArrayList<>();
    String s = p + "#" + text;
    int[] π = new int[p.length()];  // Массив длины |p|.
    Arrays.fill(π, 0);
    int π_prev = 0;
    for (int i = 1; i < s.length(); i++) {
        int k = π_prev;
        while (k > 0 && s.charAt(k) != s.charAt(i)) {
            k = π[k - 1];
        }
        if (s.charAt(k) == s.charAt(i)) {
            k++;
        }
        // Запоминаем только первые |p| значений π-функции.
        if (i < p.length()) {
            π[i] = k;
        }
        // Запоминаем последнее значение π-функции.
        π_prev = k;
        // Если значение π-функции равно длине шаблона, то вхождение найдено.
        if (k == p.length()) {
            // i - это позиция конца вхождения шаблона.
            // Дважды отнимаем от него длину шаблона, чтобы получить позицию начала:
            //  - чтобы «переместиться» на начало найденного шаблона,
            //  - чтобы не учитывать добавленное "pattern#".
            result.add(i - 2 * p.length());
        }
    }
    return result;
}