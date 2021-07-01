import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class module_consistency_OASIS {
    public static void main(String[] args) {
        int[][] All_zeros = {{ 6 , 4 , 2 , 1 , 3 , 7 , 5 },
                { 4 , 6 , 2 , 1 , 3 , 7 , 5 },
                { 2 , 7 , 4 , 1 , 3 , 6 , 5 },
                { 2 , 1 , 4 , 6 , 3 , 7 , 5 },
                { 2 , 1 , 4 , 7 , 3 , 6 , 5 },
                { 2 , 1 , 3 , 7 , 4 , 6 , 5 },
                { 6 , 2 , 1 , 3 , 7 , 5 , 4 },
                { 2 , 1 , 3 , 7 , 5 , 4 , 6 },
                { 6 , 4 , 1 , 3 , 7 , 5 , 2 },
                { 4 , 6 , 1 , 3 , 7 , 5 , 2 },
                { 4 , 1 , 6 , 3 , 7 , 5 , 2 },
                { 7 , 4 , 1 , 3 , 6 , 5 , 2 },
                { 4 , 7 , 1 , 3 , 6 , 5 , 2 },
                { 4 , 1 , 7 , 3 , 6 , 5 , 2 },
                { 4 , 1 , 3 , 7 , 6 , 5 , 2 },
                { 4 , 1 , 3 , 6 , 7 , 5 , 2 },
                { 4 , 1 , 3 , 6 , 5 , 7 , 2 },
                { 4 , 1 , 3 , 6 , 5 , 2 , 7 },
                { 4 , 1 , 3 , 7 , 5 , 6 , 2 },
                { 1 , 4 , 6 , 3 , 7 , 5 , 2 },
                { 1 , 4 , 7 , 3 , 6 , 5 , 2 },
                { 1 , 3 , 6 , 4 , 7 , 5 , 2 },
                { 1 , 3 , 7 , 4 , 6 , 5 , 2 },
                { 6 , 1 , 3 , 7 , 5 , 4 , 2 },
                { 1 , 6 , 3 , 7 , 5 , 4 , 2 },
                { 7 , 1 , 3 , 6 , 5 , 4 , 2 },
                { 1 , 7 , 3 , 6 , 5 , 4 , 2 },
                { 1 , 3 , 7 , 6 , 5 , 4 , 2 },
                { 1 , 3 , 6 , 7 , 5 , 4 , 2 },
                { 1 , 3 , 6 , 5 , 7 , 4 , 2 },
                { 1 , 3 , 6 , 5 , 4 , 7 , 2 },
                { 1 , 3 , 6 , 5 , 4 , 2 , 7 },
                { 1 , 3 , 7 , 5 , 6 , 4 , 2 },
                { 1 , 3 , 7 , 5 , 4 , 6 , 2 },
                { 1 , 3 , 6 , 5 , 2 , 7 , 4 },
                { 6 , 4 , 1 , 3 , 2 , 7 , 5 },
                { 4 , 6 , 1 , 3 , 2 , 7 , 5 },
                { 4 , 1 , 6 , 3 , 2 , 7 , 5 },
                { 4 , 1 , 3 , 6 , 2 , 7 , 5 },
                { 1 , 4 , 6 , 3 , 2 , 7 , 5 },
                { 1 , 3 , 6 , 4 , 2 , 7 , 5 },
                { 1 , 3 , 2 , 7 , 4 , 6 , 5 },
                { 6 , 1 , 3 , 2 , 7 , 5 , 4 },
                { 1 , 6 , 3 , 2 , 7 , 5 , 4 },
                { 1 , 3 , 6 , 2 , 7 , 5 , 4 },
                { 1 , 3 , 2 , 7 , 5 , 4 , 6 }};

        int cnt = 0;
        for (int i = 0; i < All_zeros.length; i++) {
            if (compute1(All_zeros[i]) == 1){
                cnt++;
                System.out.println("index: " + i);
            }
        }

        System.out.println(cnt);
        /*
            { 2 , 1 , 4 , 6 , 3 , 7 , 5 }
         */
    }
    public static int compute1 (int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(i, nums[i]);
        }

        PriorityQueue<Integer> pq1 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        pq1.add(0); pq2.add(0);

        for (int i = 0; i < nums.length; i++) {
            if (map.get(i) > pq1.peek()) {
                pq1.add(map.get(i));
                map.remove(i);
            }else if (map.get(i) > pq2.peek()){
                pq2.add(map.get(i));
                map.remove(i);
            }
        }

        if (map.isEmpty()){
            return 1;
        } else{
            return 0;
        }
    }
}
