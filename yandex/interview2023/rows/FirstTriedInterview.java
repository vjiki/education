/*
Дана строка и натуральное число k.
Требуется найти длину максимальной подстроки, содержащей не более k различных символов.
"acaba", k = 2 -> 3 ("aca")
"aaa", k = 1 -> 3 ("aaa")
"aaaaaaaaaaaaaacccbbbbbbdde"

input: "acaba", k = 2

a
ac
aca
acab 3
acaba

c
ca
cab
caba


*/
/*

int k;
String a;
char[] charArray = a.toCharArray();
char[]

for (int i = 0; i < charArray.length; i++) {

    int max = 0;
    int diffSymbolsNumber = 0;
    for (int j = i; j < charArray.length; j++) {
        if (charArray[i] == charArray[j]) {

        }
    }
}

- первый указатель = 0
- второй указатель = 0
- hash map

первый указатель
--> если нет символа добавляем в хеш  -> значение 1
--> если есть делаем get и добавляем 1

    количество частота
    длина -> max

    --> второй смотрим
    --> если добавляем
        -> hashmap.size () > k
        continue
        сохраняем максимальную длину
        сохраняем индексы подстроки

- если переходим к следующем
    hashmap -> get a
    == 0
     выкидываем из hashmap
     -1

a
ac
aca
acab 3
acaba


CRACKING the CODING INTERVIEW
 PROGRAMMING QUESTION S & SOLUTIONS
GAYLE LAAKMANN MCDOWELL 6TH

*/


import java.util.HashMap;

int getMaxSubStringFromString(String row, int k) {
    char[] charArray = row.toCharArray();
    int firstIndex = 0;
    int secondIndex = 0;
    int maxLength = 0;
    HashMap<Character, Integer> charToFrequencyMap = new HashMap();

    for (int firstIndex = 0; firstIndex < charArray.length; firstIndex++) {
        int maxSymbols = 0;
        if(!charToFrequencyMap.contains(charArray[i])) {
            int freq = charToFrequencyMap.get(charArray[i]);
            freq++;
            charToFrequencyMap.put(charArray[i], freq);
        } else {
            charToFrequencyMap.put(charArray[i], 1);
        }

        int

        if (charToFrequencyMap.size() > k) {
            continue;
        } else (
            if (maxLength < maxSymbols) {
                maxLength = maxSymbols;
            }
        )
    }
}


}




import java.util.HashMap;

public class MaxSubstring {

    public static int maxSubstringLength(String s, int k) {
        // Хэш-карта для отслеживания количества каждого символа в окне
        HashMap<Character, Integer> charCount = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        // Проходим по строке с правым указателем
        for (int right = 0; right < s.length(); right++) {
            // Добавляем символ в хэш-карту
            char currentChar = s.charAt(right);
            charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);

            // Если количество уникальных символов превышает k, сдвигаем левый указатель
            while (charCount.size() > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
            }

            // Обновляем максимальную длину подстроки
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String input = "acaba";
        int k = 2;
        int result = maxSubstringLength(input, k);
        System.out.println("Максимальная длина подстроки: " + result);  // Выводит: 3
    }
}
