import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
* Given an array of strings words and an integer k, return the k most frequent strings.

Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.



Example 1:

Input: words = ["i","love","leetcode","i","love","coding"], k = 2
Output: ["i","love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:

Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
Output: ["the","is","sunny","day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
* */

class Solution {

    public static void main(String[] args) {

    }

    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> wordsFreq = new HashMap<>();

        for (String word : words) {
            Integer freq = wordsFreq.get(word);
            if (freq == null) {
                wordsFreq.put(word, 1);
            } else {
                int newFreq = freq + 1;
                wordsFreq.put(word, newFreq);
            }
        }

        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>( (a,b) -> {
                if(!a.getValue().equals(b.getValue())) {
                    return a.getValue().compareTo(b.getValue());
                }
                return -a.getKey().compareTo(b.getKey());
        });

        for (Map.Entry<String, Integer> entry: wordsFreq.entrySet()) {
            heap.offer(entry);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        // pop out the answer
        List<String> ans = new ArrayList();
        while(heap.size() > 0)
            ans.add(heap.poll().getKey());

        // check the order
        Collections.reverse(ans);
        return ans;

    }
}