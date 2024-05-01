// https://contest.yandex.ru/contest/26133/run-report/103795532/

/*
-- ПРИНЦИП РАБОТЫ --
Создадим префиксное дерево (cпринт 8 урок 8: Префиксное дерево)
Создадим массив с булевыми промежуточные значения -
можно ли создать строку с данным индексом или же нет.
Для каждого индекса будем проходить по префиксному дереву.
Когда мы встречаем терминальный узел и при этом,
ответ положительный и для строки без текущего рассматриваемого слова, тогда записываем в массив True.
В противном случае записывается False.


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Пройдясь по каждому символу из шпаргалки ответ будет лежать
в последнем элементе массива с булевыми значениями.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Построение префиксного дерева - O(L), где L — суммарная длина всех слов во множестве.
Прохождение по префиксному дереву - O(n^2), где n - количество символов в строке.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Префиксное дерево - O(L), где L — суммарная длина всех слов во множестве.
Массив - O(n), где n - количество символов в шпаргалке.
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class CheatWords {

    private static final String SUCCESS = "YES";
    private static final String FAILURE = "NO";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String cheat = reader.readLine();
            int cheatLength = readInt(reader);

            Trie trie = new Trie();
            for (int i = 0; i < cheatLength; i++) {
                String word = reader.readLine();
                trie.addWord(word);
            }

            System.out.println(isPossibleToSplitCheatToWords(trie, cheat) ? SUCCESS : FAILURE);
        }
    }

    public static boolean isPossibleToSplitCheatToWords(Trie trie, String cheat) {
        TrieNode root = trie.getRoot();
        boolean[] dp = new boolean[cheat.length() + 1];
        dp[0] = true;
        for (int i = 0; i < cheat.length(); i++) {
            TrieNode node = root;
            if (dp[i]) {
                for (int j = i; j <= cheat.length(); j++) {
                    if (node.isEndOfWord()) {
                        dp[j] = true;
                    }
                    if (j == cheat.length() || !node.getChildren().containsKey(cheat.charAt(j))) {
                        break;
                    }
                    node = node.getChildren().get(cheat.charAt(j));
                }
            }
        }
        return dp[cheat.length()];
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}

class Trie {
    private final TrieNode root;

    public TrieNode getRoot() {
        return root;
    }

    Trie() {
        root = new TrieNode();
    }

    void addWord(String word) {
        TrieNode current = root;

        for (char l : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(l, c -> new TrieNode());
        }
        current.setEndOfWord(true);
    }
}

class TrieNode {
    private final Map<Character, TrieNode> children = new TreeMap<>();
    private boolean endOfWord;

    public TrieNode() {
        this.endOfWord = false;
    }

    boolean isEndOfWord() {
        return endOfWord;
    }

    void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    Map<Character, TrieNode> getChildren() {
        return children;
    }
}