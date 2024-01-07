import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;

// class Solution {
//     private static boolean cmp(int a, int b) {  
//         return a > b;
//     }
    
//     public int solution(String[] friends, String[] gifts) {
        
//         int idx = 0;
        
        
//         Map<String, Integer> friendsIdx = new HashMap<>();   
//         Map<String, Integer> friendsName = new HashMap<>();  
//         int[][] arr = new int[50][50];
        
//         for (String f : friends) {
//             friendsIdx.put(f, idx++);
//         }
        
//         List<Integer> v = new ArrayList<>(Collections.nCopies(friends.length, 0));

//         for (String gift : gifts) {
//             Scanner sc          = new Scanner(gift);
//             String sendName     = sc.next();
//             String receiveName  = sc.next();
            
//             friendsName.put(sendName, friendsName.getOrDefault(sendName, 0) +1);
//             friendsName.put(receiveName, friendsName.getOrDefault(receiveName, 0) -1);
//             arr[friendsIdx.get(sendName)][friendsIdx.get(receiveName)]++;
//         }
        
        
//         for (int i=0; i < friends.length; i++) {
//             for (int j=i+1; j<friends.length; j++) {
//                 String name     = friends[i];
//                 String name2    = friends[j];
//                 int idx1        = friendsIdx.get(name);
//                 int idx2        = friendsIdx.get(name2);
                
//                 if(arr[idx1][idx2] > arr[idx2][idx1]) {
//                     v.set(idx1, v.get(idx1) +1);
//                 } else if (arr[idx1][idx2] < arr[idx2][idx1]) {
//                     v.set(idx2, v.get(idx2) + 1);
//                 } else {
//                      if (friendsName.get(name) > friendsName.get(name2)) {
//                          v.set(idx1, v.get(idx1) + 1);
//                      } else if (friendsName.get(name) < friendsName.get(name2)) {
//                          v.set(idx2, v.get(idx2) + 1);
//                      }
                 
//                 }
//             }
//         }
//         v.sort((a, b) -> Integer.compare(b, a));

//         return v.get(0);
        
//     }
// }
class Solution {
    public int solution(String[] friends, String[] gifts) {
        int max = 50;
        int[][] arr = new int[max][max];
        Map<String, Integer> friendsIdx = new HashMap<>();
        int[] friendsName = new int[max];

        int idx = 0;
        for (String f : friends) {
            friendsIdx.put(f, idx++);
        }

        for (String gift : gifts) {
            Scanner sc = new Scanner(gift);
            String sendName = sc.next();
            String receiveName = sc.next();

            int sendIdx = friendsIdx.get(sendName);
            int receiveIdx = friendsIdx.get(receiveName);

            friendsName[sendIdx]++;
            friendsName[receiveIdx]--;
            arr[sendIdx][receiveIdx]++;
        }

        int len = friends.length;
        int[] v = new int[len];

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int idx1 = friendsIdx.get(friends[i]);
                int idx2 = friendsIdx.get(friends[j]);

                if (arr[idx1][idx2] > arr[idx2][idx1]) {
                    v[idx1]++;
                } else if (arr[idx1][idx2] < arr[idx2][idx1]) {
                    v[idx2]++;
                } else {
                    int count1 = friendsName[idx1];
                    int count2 = friendsName[idx2];

                    v[idx1] += Integer.compare(count1, count2);
                    v[idx2] += Integer.compare(count2, count1);
                }
            }
        }

        Arrays.sort(v);
        return v[len - 1];
    }
}