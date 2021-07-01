import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws IOException {
        String trial = "BM_1=(login->P1),\n" +
                "P1=(charge->P2|back->BM_1),\n" +
                "P2=(select->P3),\n" +
                "P3=(confirm->P4),\n" +
                "P4=(pay_pw->P5|back->P3),\n" +
                "P5=(decide_votes->P6|back->P4),\n" +
                "P6=(logout->END).\n";

/*        String trial = "BM_1=(login->P1),\n" +
                "P1=(select->P2),\n" +
                "P2=(charge->P3|back->P1),\n" +
                "P3=(decide_votes->P4|back->P2),\n" +
                "P4=(pay_pw->P5|back->P3),\n" +
                "P5=(confirm->P6),\n" +
                "P6=(logout->END).\n";*/

        Algorithm_ alg = new Algorithm_();
//        long start = System.currentTimeMillis();
        alg.initAndRun(trial);
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(alg.getChecking_Result());
//        long end = System.currentTimeMillis();
        System.out.println(list);
//        System.out.println("Time spent: " + (end - start));
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator.DESKTOP-ELM13H1\\Desktop\\oasis_project-master\\time1.txt");
        fos.write(Long.toString(alg.getTimeSpent()).getBytes());

    }

}


