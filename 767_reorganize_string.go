/*
Author         wvxiw
Title          Leetcode task
Task           Reorganize String
id             767
Difficulty     Medium
Tags           hash table, string, greedy, sorting, heap (priority queue), counting
Link           https://leetcode.com/problems/reorganize-string/

Reorganize String
    Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
    Return any possible rearrangement of s or return "" if not possible.

Example 1:
    Input: s = "aab"
    Output: "aba"

Example 2:
    Input: s = "aaab"
    Output: ""

Constraints:
    1 <= s.length <= 500
    s consists of lowercase English letters.

Hint 1:
    Alternate placing the most common letters.

Similar questions:
    358. Rearrange String k Distance Apart
    621. Task Scheduler
    1405. Longest Happy String

*/

package main

import (
	"container/heap"
	"fmt"
)

// For implementing the interface use the example of Priority Queue given by the package itself: https://pkg.go.dev/container/heap#example-package-PriorityQueue
// only change "value string" to "value byte" in the Item and > to < in the Less()

// Item represents an item in the priority queue.
type Item struct {
	value    byte // A char from the string
	priority int  // Number of repetitions of a letter
	index    int  // The index of the item in the heap
}

type PriorityQueue []*Item

func (pq PriorityQueue) Len() int { return len(pq) }
func (pq PriorityQueue) Less(i, j int) bool {
	return pq[i].priority > pq[j].priority
}
func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
	pq[i].index = i
	pq[j].index = j
}
func (pq *PriorityQueue) Push(x interface{}) {
	n := len(*pq)
	item := x.(*Item)
	item.index = n
	*pq = append(*pq, item)
}
func (pq *PriorityQueue) Pop() interface{} {
	old := *pq
	n := len(old)
	item := old[n-1]
	item.index = -1 // for safety
	*pq = old[0 : n-1]
	return item
}

type kv struct {
	Key   byte
	Value int
}

func newPriorityQueue(s string) PriorityQueue {
	// Number of repetitions of a letter (acts as priority)
	reps := make(map[byte]int)
	for _, v := range s {
		reps[byte(v)]++
	}

	// Use sorted data (repsSortedSlice) instead of unsorted (reps) to make if a repeatable result needed
	//var repsSortedSlice []kv
	//for k, v := range reps {
	//    repsSortedSlice = append(repsSortedSlice, kv{k, v})
	//}
	//sort.Slice(repsSortedSlice, func(i, j int) bool {
	//    if repsSortedSlice[i].Value == repsSortedSlice[j].Value {
	//        return repsSortedSlice[i].Key > repsSortedSlice[j].Key // Make the output predictable
	//    }
	//    return repsSortedSlice[i].Value > repsSortedSlice[j].Value
	//})
	// ... and edit the loop too

	pq := make(PriorityQueue, len(reps))

	i := 0
	for letter, repetition := range reps {
		pq[i] = &Item{byte(letter), repetition, i}
		i++
	}
	heap.Init(&pq)
	return pq
}

type testData struct {
	input    string
	expected string
}

func maintainNth(pq *PriorityQueue, n int) (i *Item) {
	if pq.Len() > 0 {
		i = (*pq)[n]
		i.priority--
		if i.priority > 0 {
			heap.Fix(pq, i.index)
		} else {
			heap.Remove(pq, i.index)
		}
	}
	return i
}

// For debug
func lettersTotal[T map[byte]int | []kv](data T) int {
	num := 0
	if d, ok := any(data).(map[byte]int); ok {
		for _, v := range d {
			num += v
		}
	} else if d, ok := any(data).([]kv); ok {
		for _, v := range d {
			num += v.Value
		}
	}
	return num
}

func reorganizeString(s string) string {
	var reorganized = make([]byte, len(s))

	pq := newPriorityQueue(s)
	j := 0 // Index when composing the new string
	var last byte = 0
	for {
		var i [2]*Item

		if pq.Len() <= 0 {
			break
		} else if pq.Len() > 1 {
			i[1] = pq[1]
		}
		i[0] = pq[0]

		// Swap if last letter from previous iteration if matches the first extracted now
		if last == i[0].value {
			if i[1] == nil {
				return ""
			}
			i[0], i[1] = i[1], i[0]
		}

		reorganized[j] = i[0].value
		maintainNth(&pq, i[0].index)
		if i[1] != nil {
			reorganized[j+1] = i[1].value
			maintainNth(&pq, i[1].index)
			last = i[1].value
			j += 2
		} else {
			last = i[0].value
			j++
		}
	}

	if j != len(reorganized) {
		return ""
	}

	return string(reorganized)
}

func check(s string, exp string) bool {
	if s == "" {
		if exp != "IMPOSSIBLE" {
			return false
		}
	} else {
		if exp == "" {

			var prev byte = 0
			for _, c := range s {
				if byte(c) == prev {
					return false
				}
				prev = byte(c)
			}
		} else if len(s) != len(exp) && s != exp {
			return false
		}
	}

	return true
}

func main() {
	const impossible = "IMPOSSIBLE"
	data := []testData{
		// If testData.expected is
		//    ""            - that means that the test string is doable
		//    "aba"         - an exact answer
		//    "IMPOSSIBLE"  - means the expected answer from reorganizeString() is ""

		{"aab", "aba"},
		{"aaab", impossible},
		{"zrhmhyevkojpsegvwolkpystdnkyhcjrdvqtyhucxdcwm", ""},
		{"dhrxvmvopqahsnilpzl", ""},
		{"ogccckcwmbmxtsbmozli", ""},
		{"gpneqthatplqrofqgwwfmhzxjddhyupnluzkkysofgqawjyrwhfgdpkhiqgkpupgdeonipvptkfqluytogoljiaexrnxckeofqojltdjuujcnjdjohqbrzzzznymyrbbcjjmacdqyhpwtcmmlpjbqictcvjgswqyqcjcribfmyajsodsqicwallszoqkxjsoskxxstdeavavnqnrjelsxxlermaxmlgqaaeuvneovumneazaegtlztlxhihpqbajjwjujyorhldxxbdocklrklgvnoubegjrfrscigsemporrjkiyncugkksedfpuiqzbmwdaagqlxivxawccavcrtelscbewrqaxvhknxpyzdzjuhvoizxkcxuxllbkyyygtqdngpffvdvtivnbnlsurzroxyxcevsojbhjhujqxenhlvlgzcsibcxwomfpyevumljanfpjpyhsqxxnaewknpnuhpeffdvtyjqvvyzjeoctivqwann", ""},
	}

	for i, v := range data {
		ret := reorganizeString(v.input)
		if !check(ret, v.expected) {
			fmt.Printf("ERROR i=%d \n\t in=%v \n\tres=%v \n\texp=%v\n", i, v.input, ret, v.expected)
			break
		} else {
			fmt.Printf("OK    i=%d input=%v ret=%v exp=%v\n", i, v.input, ret, v.expected)
		}
	}
}
