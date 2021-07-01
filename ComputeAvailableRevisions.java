import java.util.*;

public class ComputeAvailableRevisions {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrayList<ArrayList<Integer>> permutation = permute(nums);
        System.out.println(permutation);
        System.out.println(permutation.size());

        int count = 0;
        for (int i = 0; i < permutation.size(); i++) {
            int[] nums1 = new int[nums.length];
            for (int j = 0; j < permutation.get(i).size(); j++) {
                nums1[j] = permutation.get(i).get(j);
            }
            if (compute1(nums1) == 1) {
                System.out.print("[");
                for (int k = 0; k < nums1.length; k++) {
                    System.out.print(nums1[k]);
                }
                System.out.println("]");
                count += compute1(nums1);
            }
        }
        System.out.println("result: OASIS has " + count + " possible revisions for " + nums.length + " actions");
    }
    /*
    大顶堆
     */
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


    public static ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        result.add(new ArrayList<Integer>());

        for (int i = 0; i < num.length; i++) {
            ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();

            for (ArrayList<Integer> l : result) {
                for (int j = 0; j < l.size()+1; j++) {
                    l.add(j, num[i]);
                    ArrayList<Integer> temp = new ArrayList<Integer>(l);
                    current.add(temp);
                    l.remove(j);
                }
            }
            result = new ArrayList<ArrayList<Integer>>(current);
        }

        return result;
    }


}
