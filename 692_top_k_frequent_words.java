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


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// todo 1. implement and solve using the trie data structure
// todo 2. solve using priority queue
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String w : words) {
            Integer num = map.get(w);
            if (num == null)
                num = 0;
            map.put(w, num + 1);
        }

        List<String> result = map.entrySet().stream()
                .sorted((s1, s2) -> {
                    int numComp = s2.getValue().compareTo(s1.getValue());
                    if (numComp == 0)
                        return s1.getKey().compareTo(s2.getKey());
                    else
                        return numComp;
                })
                .map(Map.Entry::getKey)
                .limit(k)
                .collect(Collectors.toList());

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
            List<String> result = s.topKFrequent(t.words, t.k);
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
