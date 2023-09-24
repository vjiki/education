import java.util.HashMap;

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


*/


public class MaxRow {
  public static int getMaxSubStringFromString(String row, int k) {
    char[] charArray = row.toCharArray();
    int firstIndex = 0;
    int secondIndex = 0;
    int maxLength = 0;
    HashMap<Character, Integer> charToFrequencyMap = new HashMap<>();

    for (firstIndex = 0; firstIndex < charArray.length; firstIndex++) {
      if(charToFrequencyMap.containsKey(charArray[firstIndex])) {
        int freq = charToFrequencyMap.get(charArray[firstIndex]);
        freq++;
        charToFrequencyMap.put(charArray[firstIndex], freq);
      } else {
        charToFrequencyMap.put(charArray[firstIndex], 1);
      }

      int maxSymbols = secondIndex - firstIndex;

      if (charToFrequencyMap.size() > k) {
        continue;
      } else {
        if (maxLength < maxSymbols) {
          maxLength = maxSymbols;
        }
      }
    }

    return maxLength;
  }


}






