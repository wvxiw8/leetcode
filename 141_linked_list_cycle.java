import java.util.HashSet;

/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Linked List Cycle
 * @id             141
 * @Difficulty     Easy
 * @Tags           linked list
 * @Featured       Top interview collection
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/773/

Linked List Cycle

    Given head, the head of a linked list, determine if the linked list has a cycle in it.
    There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
    Return true if there is a cycle in the linked list. Otherwise, return false.

Example 1:

    Input: head = [3,2,0,-4], pos = 1
    Output: true
    Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

Example 2:

    Input: head = [1,2], pos = 0
    Output: true
    Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.

Example 3:

    Input: head = [1], pos = -1
    Output: false
    Explanation: There is no cycle in the linked list.

Constraints:

    The number of the nodes in the list is in the range [0, 10^4].
    -10^5 <= Node.val <= 10^5
    pos is -1 or a valid index in the linked-list.


Follow up: Can you solve it using O(1) (i.e. constant) memory?

*/


/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    
    ListNode(int[] a) { 
        ListNode node = this;
        ListNode after = null;
        for (int i = a.length - 1; i >= 0; --i) {
            node = new ListNode(a[i]);
            node.next = after;
            after = node;
        }
        this.val = node.val;
        this.next = node.next;
    }
    
    public ListNode next() { 
        return this.next; 
    }

    public ListNode getNth(int n) {
        int i = 0;
        ListNode node = this;
        while(i-- > 0 && node != null) {
            node = node.next();
        }
        return node;
    }

    public void add(ListNode newNode) {
        ListNode node = this;
        while( node.next() != null) {
            node = node.next();
        }
        node.next = newNode;
    }
    
    public int size() {
        int i = 0;
        ListNode node = this;
        do {
            ++i;
        } while ((node = node.next()) != null);
        return i;
    }

    public void dump() {
        ListNode node = this;
        while (node != null) {
            System.out.printf("%s = %d\n", node.toString(), node.val);
            node = node.next();
        } 
        System.out.println();
    }

    public void dump(int max) {
        ListNode node = this;
        int i = max;
        while (node != null) {
            if (i-- < 0) {
                System.out.printf("----------\nDump interrupted after %d\n", max);
                break;
            }
            System.out.printf("%s = %d\n", node.toString(), node.val);
            node = node.next();
        } 
        System.out.println();
    }
}


class Solution {

    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> saved = new HashSet();
        while (head != null) {
            if (!saved.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    public boolean hasCycle2(ListNode head) {
        if (head == null) {
            return false;
        }
        final int MAX_NODES = 100000;
        Object saved [] = new Object[MAX_NODES];
        ListNode node = head;
        for (int i = 0; i < MAX_NODES; ++i) {
            for (int j = 0; j < i; ++j) {
                if (node == saved[j]) {
                    return true;
                }
            }
            saved[i] = node;
            node = node.next;
            if (node == null) {
                break;
            }
        }
        return false;
    }
}


class LinkedListCycle {
    public static void main(String[] args) {
        // int [] head = {3,2,0,-4}; int pos = 1;
        // int [] head = {1,2}; int pos = 0;
        // int [] head = {1}; int pos = -1;
        int [] head = {492,1194,724,972,-563,208,1270,1537,1086,915,-522,595,75,1298,520,-635,-524,1032,398,-698,502,343,1458,741,270,230,149,-493,-213,171,197,477,794,1568,283,1136,561,799,477,204,8,-617,1019,-178,1429,1090,1160,1569,1257,1616,281,436,-295,-197,-224,-202,-192,-289,969,-786,698,-750,640,-9,1547,100,1372,-290,804,1108,412,1129,919,884,164,966,1553,-280,722,426,1417,1095,1461,1596,77,-134,-727,1423,1203,1374,-390,343,1528,-792,1488,-32,-639,791,1103,844,-529,825,736,-338,946,1246,1285,-345,-236,1251,966,586,-355,1088,-700,1135,990,746,461,302,120,-6,1525,-500,1120,1129,34,697,202,427,-630,-277,414,-722,918,1060,888,1167,1605,902,-782,-649,639,1165,927,739,-327,1591,-447,-502,1067,789,-267,-122,715,583,-336,-82,100,1607,334,849,680,-804,557,454,985,1008,505,205,1166,751,962,979,-782,1545,1096,998,86,299,1231,377,-112,92,597,203,74,-642,1093,-169,83,728,-444,-272,-694,282,738,63,696,156,1185,-622,-385,493,714,756,574,-343,1078,55,1461,1560,-401,81,-327,239,91,-135,604,-231,-43,485,589,607,-689,-57,-16,374,1409,1385,-468,1564,1492,1178,-199,1205,515,1617,1462,261,-235,-719,1056,1285,65,-587,414,299,289,-189,1265,-137,1496,279,-551,1539,1277,232,-355,642,-364,-137,31,-273,408,538,1036,-248,1019,-576,-267,-772,385,978,71,803,-347,-765,-804,1160,1249,-267,100,-24,53,228,361,646,717,-725,1149,47,1613,-219,-742,378,1035,986,555,800,632,778,889,44,-720,-720,-733,1352,-795,268,902,480,526,-676,54,1400,709,1260,31,-208,1052,219,-259,530,843,1241,-406,646,203,413,85,297,1028,1166,1205,198,-569,656,997,-693,-609,1131,146,935,1250,278,178,-455,-572,367,-140,1449,-692,-456,1279,1566,1044,1401,104,1303,-312,307,-181,-171,-440,1281,1575,-690,-780,1319,-708,733,1454,1515,-455,259,-565,1248,463,589,-87,1587,16,1363,-338,-98,1594,1233,200,-242,246,-227,-298,835,807,1028,865,1533,-683,94,817,-357,-744,1038,835,-381,498,-357,757,1486,836,1385,-439,684,-129,1530,377,-126,-770,1159,-51,-328,439,-14,419,-489,239,-800,1333,-806,-377,176,781,274,1392,-290,974,-774,1432,1435,404,-274,546,-676,495,1281,1333,-336,-589,717,-301,0,763,-221,456,601,-403,1101,763,458,1018,389,236,547,591,-82,1344,178,542,1348,471,142,561,1142,-578,-95,1417,1582,693,-703,-277,1413,528,1402,1087,777,-146,39,-279,-634,1439,-403,600,1267,1482,-232,1602,827,341,1344,1298,-173,493,920,635,842,-110,325,1006,-516,909,-361,810,667,762,678,633,712,355,-371,-539,415,-464,-74,-123,-246,-577,-384,1440,259,739,-301,75,1523,648,950,1181,1550,1403,-497,-100,1602,-347,676,931,614,10,893,1222,248,154,1129,-534,1188,-717,691,-453,958,1565,-186,-637,-465,1526,-570,91,1502,418,-186,-282,-747,-457,-294,990,980,-514,43,462,907,844,152,489,-293,931,420,670,-376,-53,449,1561,1271,-174,-266,1301,146,825,-348,-352,-200,1001,1093,410,504,909,256,976,-645,484,-112,296,-263,1291,236,-593,-131,1344,601,1123,-599,-783,1538,634,1038,856,-573,-89,-594,1530,541,1085,-508,207,1080,-647,1139,-768,-371,244,-113,729,1162,1583,1035,173,-739,759,-176,987,1286,394,658,429,1129,1104,257,1541,941,-299,-197,1235,1486,351,197,880,1224,1505,-469,376,988,744,649,-491,1336,275,2,847,728,710,-764,98,602,748,872,-428,1095,941,-481,1365,1253,74,-161,-535,287,558,851,-589,-35,425,547,1199,-619,471,687,884,778,188,-330,894,1058,705,806,-45,1358,491,1584,325,-453,1289,991,79,623,1321,804,-59,442,1280,-498,869,92,-620,345,368,1329,1306,1597,1095,1442,-809,523,-765,954,27,-82,-410,1004,56,1512,499,-614,457,1343,-163,-88,1300,1204,1534,697,1588,-36,-405,1167,-86,551,976,1117,588,412,1037,806,372,-507,103,1012,-520,227,160,140,-537,1145,506,-678,-655,839,1450,1233,540,751,-809,-395,-201,1180,1303,311,1087,1500,-451,733,937,-774,1557,-492,1265,1606,1051,1598,1163,1611,-760,-695,1514}; int pos = 13;
        
        Solution solution = new Solution();
        
        ListNode list = new ListNode(head);
        list.dump();
        if (pos >= 0) {
            ListNode cycleNode = list.getNth(pos);
            list.add(cycleNode);
            list.dump(10000);
        }

        boolean hasCycle = solution.hasCycle(list);
        if (hasCycle == true && pos != -1) {
            System.out.println("Passed. Cycle is present and was detected");
        } else if (hasCycle == false && pos == -1) {
            System.out.println("Passed. Cycle is absent");
        } else if (hasCycle == false && pos != -1) {
            System.out.printf("Error. Cycle present, but not detected. pos=%d\n", pos);
        } else {
            System.out.println("Error. Bad test");
        }
    }
}