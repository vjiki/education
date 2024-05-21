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
