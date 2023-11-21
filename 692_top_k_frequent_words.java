/**
 * @formatter:off
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Top K Frequent Words
 * @id             692
 * @Difficulty     Medium
 * @Tags           hash table, string, trie, sorting, heap (priority queue), bucket sort, counting
 * @Link           https://leetcode.com/problems/top-k-frequent-words

Top K Frequent Words
    Given an array of strings words and an integer k, return the k most frequent strings.
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

Constraints:
    1 <= words.length <= 500
    1 <= words[i].length <= 10
    words[i] consists of lowercase English letters.
    k is in the range [1, The number of unique words[i]]

Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space?
 @formatter:on
 */

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// todo Implement and solve using the trie data structure
class Solution {
    final Comparator<Map.Entry<String, Integer>> comparator = (e1, e2) -> {
        int numComp = e2.getValue().compareTo(e1.getValue());
        if (numComp == 0)
            return e1.getKey().compareTo(e2.getKey());
        else
            return numComp;
    };

    public List<String> topKFrequent(String[] words, int k) {
//        return topKFrequent1(words, k); // hashmap + streams
        return topKFrequent2(words, k); // hashmap + priority queue
    }

    // hashmap + streams
    public List<String> topKFrequent1(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String w : words) {
            Integer num = map.get(w);
            if (num == null)
                num = 0;
            map.put(w, num + 1);
        }

        List<String> result = map.entrySet().stream()
                .sorted(comparator)
                .map(Map.Entry::getKey)
                .limit(k)
                .collect(Collectors.toList());

        return result;
    }

    public List<String> topKFrequent2(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String w : words) {
            Integer num = map.get(w);
            if (num == null)
                num = 0;
            map.put(w, num + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(words.length, comparator);
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            queue.add(e);
        }

        List<String> result = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            result.add(queue.poll().getKey());
        }
        return result;
    }

    private static class TestData {
        String[] words;
        int k;
        String[] expected;

        TestData(int k, String[] words, String[] expected) {
            this.k = k;
            this.words = words;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        TestData[] testData = {
                new TestData(2, new String[]{"i", "love", "leetcode", "i", "love", "coding"}, new String[]{"i", "love"}),
                new TestData(4, new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, new String[]{"the", "is", "sunny", "day"}),
        };

        Solution s = new Solution();
        for (TestData t : testData) {
            List<String> result = s.topKFrequent2(t.words, t.k);
            if (Arrays.equals(result.toArray(), t.expected)) {
                System.out.println("OK");
            } else {
                System.out.println("FAILED");
                System.out.printf("   res=");
                result.forEach(wrd -> System.out.printf("%s ", wrd));
                System.out.println();
                System.out.printf("   exp=");
                Stream.of(t.expected).forEach(wrd -> System.out.printf("%s ", wrd));
                System.out.println();
            }
        }
    }
}
