import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class CheckOASISDistribution {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        list.add(3, 4);
        list.add(4, 5);
        list.add(5, 6);
        list.add(6, 7);
        list.add(7, 8);
//        list.add(8, 9);

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(list);

//        System.out.println(one_action(list));
        System.out.println("1-action: " + one_action(list).size());
//        System.out.println(two_actions(list));
        System.out.println("2-action: " + two_actions(list).size());
//        System.out.println(three_actions(list));
        System.out.println("3-action: " + three_actions(list).size());
//        System.out.println(four_actions(list));
        System.out.println("4-action: " + four_actions(list).size());

        ArrayList<Integer> distribute = OASIS_Coverage(nums);
        System.out.println(distribute);
        System.out.println(distribute.size());
        int count_one_1 = 0;
        int count_one_2 = 0;
        int count_one_3 = 0;
        int count_one_4 = 0;
        for (int i = 0; i < distribute.size(); i++) {
            if(distribute.get(i) == 1 && i < one_action(list).size()){
                count_one_1 ++;
            } else if(distribute.get(i) == 1 && i < one_action(list).size() + two_actions(list).size()){
                count_one_2 ++;
            } else if(distribute.get(i) == 1 && i < one_action(list).size()+ two_actions(list).size() + three_actions(list).size()){
                count_one_3 ++;
            } else if(distribute.get(i) == 1 && i < distribute.size()){
                count_one_4 ++;
            }
        }
        System.out.println("count_1:" + count_one_1);
        System.out.println("count_2:" + count_one_2);
        System.out.println("count_3:" + count_one_3);
        System.out.println("count_4:" + count_one_4);


        int count_one = 0;
        ArrayList<Integer> idx_of_one = new ArrayList<>();
        for (int i = 0; i < distribute.size(); i++) {
            if (distribute.get(i) == 1) {
                idx_of_one.add(i);
                count_one ++;
            }
        }
        System.out.println("last unrepeated revision appears at: " + (idx_of_one.get(idx_of_one.size()-1) + 1));
        System.out.println("middle unrepeated revision appears at: " + (idx_of_one.get(count_one / 2) + 1));

        ArrayList<Integer> distribute_plot = new ArrayList<>();
        for (int i = 0; i < distribute.size(); i++) {
            if (distribute.get(i) == 1){
                distribute_plot.add(i, distribute.get(i));
            }else{
                distribute_plot.add(i, 0);
            }
        }
        System.out.println("length of displot:  " + distribute_plot.size());
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(".\\analysis_in_python\\8modules.txt"), "utf-8");
        osw.write(String.valueOf(distribute_plot));
        osw.flush();
        osw.close();

        DistributionPlot.plot(distribute_plot);
    }
    public static ArrayList<Integer> OASIS_Coverage(int[] nums){
        ArrayList<Integer> original = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            original.add(nums[i]);
        }
        ArrayList<ArrayList<Integer>> comb = new ArrayList<>();

        if (original.size() == 4){
            for (ArrayList<Integer> integers : one_action(original)) {
                comb.add(integers);
            }
            for (int i = 0; i < 0.5 * two_actions(original).size(); i++) {
                comb.add(two_actions(original).get(i));
            }
        }

        if (original.size() == 5){
            for (ArrayList<Integer> integers : one_action(original)) {
                comb.add(integers);
            }
            for (ArrayList<Integer> integers : two_actions(original)) {
                comb.add(integers);
            }
        }

        if (original.size() == 6){
            for (ArrayList<Integer> integers : one_action(original)) {
                comb.add(integers);
            }
            for (ArrayList<Integer> integers : two_actions(original)) {
                comb.add(integers);
            }
            for (int i = 0; i < 0.5 * three_actions(original).size(); i++) {
                comb.add(three_actions(original).get(i));
            }
        }

        if (original.size() == 7){
            for (ArrayList<Integer> integers : one_action(original)) {
                comb.add(integers);
            }
            for (ArrayList<Integer> integers : two_actions(original)) {
                comb.add(integers);
            }
            for (ArrayList<Integer> integers : three_actions(original)) {
                comb.add(integers);
            }
        }

        if (original.size() == 8){
            for (ArrayList<Integer> integers : one_action(original)) {
                comb.add(integers);
            }
            for (ArrayList<Integer> integers : two_actions(original)) {
                comb.add(integers);
            }
            for (ArrayList<Integer> integers : three_actions(original)) {
                comb.add(integers);
            }
            for (int i = 0; i < 0.5 * four_actions(original).size(); i++) {
                comb.add(four_actions(original).get(i));
            }
        }

        if (original.size() == 9){
            for (ArrayList<Integer> integers : one_action(original)) {
                comb.add(integers);
            }
            for (ArrayList<Integer> integers : two_actions(original)) {
                comb.add(integers);
            }
            for (ArrayList<Integer> integers : three_actions(original)) {
                comb.add(integers);
            }
            for (ArrayList<Integer> integers : four_actions(original)) {
                comb.add(integers);
            }
        }

        ArrayList<Integer> repeated = new ArrayList<>(comb.size());
        for (int i = 0; i < comb.size(); i++) {
            int count = 1;
            for (int j = 0; j < i; j++){
                if (comb.get(i).equals(comb.get(j))){
                    count++;
                }
            }
            repeated.add(i, count);
        }

        return repeated;
    }

    public static ArrayList<ArrayList<Integer>> one_action(ArrayList<Integer> original){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < original.size(); i++) {
            ArrayList<Integer> mani = (ArrayList<Integer>) original.clone();
            Integer removed = mani.remove(i);
            for (int j = 0; j < original.size(); j++){
                ArrayList<Integer> mani1 = (ArrayList<Integer>) mani.clone();
                mani1.add(j, removed);
                result.add(mani1);
            }
        }
        return result;
    }
    public static ArrayList<ArrayList<Integer>> two_actions(ArrayList<Integer> original){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < original.size(); i++) {
            ArrayList<Integer> mani = (ArrayList<Integer>) original.clone();
            Integer removed1 = mani.remove(i);
            for (int j = i; j < mani.size(); j++) {
                ArrayList<Integer> mani1 = (ArrayList<Integer>) mani.clone();
                Integer removed2 = mani1.remove(j);
                    for (int p = 0; p < mani1.size()+1; p++) {
                        ArrayList<Integer> mani2 = (ArrayList<Integer>) mani1.clone();
                        mani2.add(p, removed1);
                        for (int q = p + 1; q < mani2.size()+1; q++) {
                            ArrayList<Integer> mani3 = (ArrayList<Integer>) mani2.clone();
                            mani3.add(q, removed2);
                            result.add(mani3);

                        }
                    }
                }

        }
        return result;
    }
    public static ArrayList<ArrayList<Integer>> three_actions(ArrayList<Integer> original){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < original.size(); i++) {
            ArrayList<Integer> mani = (ArrayList<Integer>) original.clone();
            Integer removed1 = mani.remove(i);
            for (int j = i; j < mani.size(); j++) {
                ArrayList<Integer> mani1 = (ArrayList<Integer>) mani.clone();
                Integer removed2 = mani1.remove(j);
                for (int m = j; m < mani1.size(); m++) {
                    ArrayList<Integer> mani2 = (ArrayList<Integer>) mani1.clone();
                    Integer removed3 = mani2.remove(m);
                    for (int p = 0; p < mani2.size()+1; p++) {
                        ArrayList<Integer> mani3 = (ArrayList<Integer>) mani2.clone();
                        mani3.add(p, removed1);
                        for (int q = p + 1; q < mani3.size()+1; q++) {
                            ArrayList<Integer> mani4 = (ArrayList<Integer>) mani3.clone();
                            mani4.add(q, removed2);
                            for (int f = q + 1; f < mani4.size()+1; f++) {
                                ArrayList<Integer> mani5 = (ArrayList<Integer>) mani4.clone();
                                mani5.add(f, removed3);
                                result.add(mani5);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<ArrayList<Integer>> four_actions(ArrayList<Integer> original){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < original.size(); i++) {
            ArrayList<Integer> mani = (ArrayList<Integer>) original.clone();
            Integer removed1 = mani.remove(i);
            for (int j = i; j < mani.size(); j++) {
                ArrayList<Integer> mani1 = (ArrayList<Integer>) mani.clone();
                Integer removed2 = mani1.remove(j);
                for (int m = j; m < mani1.size(); m++) {
                    ArrayList<Integer> mani2 = (ArrayList<Integer>) mani1.clone();
                    Integer removed3 = mani2.remove(m);
                    for (int n = m; n < mani2.size(); n++) {
                        ArrayList<Integer> mani3 = (ArrayList<Integer>) mani2.clone();
                        Integer removed4 = mani3.remove(n);
                        for (int p = 0; p < mani3.size() + 1; p++) {
                            ArrayList<Integer> mani4 = (ArrayList<Integer>) mani3.clone();
                            mani4.add(p, removed1);
                            for (int q = p + 1; q < mani4.size() + 1; q++) {
                                ArrayList<Integer> mani5 = (ArrayList<Integer>) mani4.clone();
                                mani5.add(q, removed2);
                                for (int f = q + 1; f < mani5.size() + 1; f++) {
                                    ArrayList<Integer> mani6 = (ArrayList<Integer>) mani5.clone();
                                    mani6.add(f, removed3);
                                    for (int l = f + 1; l < mani6.size() + 1; l++) {
                                        ArrayList<Integer> mani7 = (ArrayList<Integer>) mani6.clone();
                                        mani7.add(l, removed4);
                                        result.add(mani7);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
