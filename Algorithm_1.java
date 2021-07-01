import ic.doc.ltsa.frontend.HPWindow;
import ic.doc.ltsa.lts.*;
import javafx.util.Pair;
import java.util.*;

public class Algorithm_1 {
    int ReqNum = 6;

    private long TimeSpent = 0;

    public long getTimeSpent() {
        return TimeSpent;
    }

    private LTS_to_KS LtK = new LTS_to_KS();

    public LTS_to_KS getLtK() {
        return LtK;
    }

    private ArrayList<Integer> Checking_Result = new ArrayList<>();

    public ArrayList<Integer> getChecking_Result() {
        return Checking_Result;
    }

    public Algorithm_1()
    {
        System.out.println("Algorithm construct()");
    }

    public void initAndRun(String BM) {
        System.out.println("Algorithm init()");

        String[] flag_v = new String[ReqNum];
        String[] vio_req = new String[ReqNum];
        String[][] TBL_1 = new String[2][ReqNum + 1];
//        HashMap<String, String> result = new HashMap<>();
        String REQ = "", CHECK = "";
        String Check = null;

        String Check1 = "NoVoteFlipping";
        String REQ1 = "\tNoVoteFlipping = Q0,\n" +
                "\tQ0\t= (mu.select -> Q0\n" +
                "\t\t  |u.select -> Q1),\n" +
                "\tQ1\t= (mu.select -> ERROR\n" +
                "\t\t  |u.select -> Q1).\n";
        String CHECK1 = "||Check = (Env||NoVoteFlipping).";

        String Check2 = "NoPersonalInfoLeak";
        String REQ2 = "    NoPersonalInfoLeak = Q0,\n" +
                "    Q0\t= (mu.charge -> Q0\n" +
                "          |u.charge -> Q1),\n" +
                "    Q1\t= (mu.charge -> ERROR\n" +
                "          |u.charge -> Q1).\n";
        String CHECK2 = "||Check = (Env||NoPersonalInfoLeak).";

        String Check3 = "NoVotesNumChange";
        String REQ3 = "\tNoVotesNumChange = Q0,\n" +
                "\tQ0\t= (mu.decide_votes -> Q0\n" +
                "\t\t  |u.decide_votes -> Q1),\n" +
                "\tQ1\t= (mu.decide_votes -> ERROR\n" +
                "\t\t  |u.decide_votes -> Q1).\n";
        String CHECK3 = "||Check = (Env||NoVotesNumChange).";

        String Check4 = "ChargeAfterLogin";
        String REQ4 = "\tChargeAfterLogin = Q0,\n" +
                "\tQ0\t= ({mu, u}.login -> Q0\n" +
                "\t\t  |{mu, u}.charge -> Q1),\n" +
                "\tQ1\t= ({mu, u}.login -> ERROR\n" +
                "\t\t  |{mu, u}.charge -> Q1).\n";
        String CHECK4 = "||Check = (Env||ChargeAfterLogin).";

        String Check5 = "NoConfirmAfterSelectUntilVote";
        String REQ5 = "\tNoConfirmAfterSelectUntilVote = Q0,\n" +
                "\tQ0\t= ({confirm, decide_votes} -> Q0\n" +
                "\t\t  |select -> Q1),\n" +
                "\tQ1\t= (confirm -> ERROR\n" +
                "\t\t  |decide_votes -> Q0\n" +
                "\t\t  |select -> Q1).\n";
        String CHECK5 = "||Check = (Env||{u, mu}::NoConfirmAfterSelectUntilVote).";

        String Check6 = "PayAfterCharge";
        String REQ6 = "\tPayAfterCharge = Q0,\n" +
                "\tQ0\t= ({mu, u}.charge -> Q0\n" +
                "\t\t  |{mu, u}.pay_pw -> Q1),\n" +
                "\tQ1\t= ({mu, u}.charge -> ERROR\n" +
                "\t\t  |{mu, u}.pay_pw -> Q1).\n";
        String CHECK6 = "||Check = (Env||PayAfterCharge).";

        LTSFunction LTSfunc1 = new LTSFunction();
        Check = "All";
        REQ = REQ1 + REQ2 + REQ3 + REQ4 + REQ5 + REQ6;
        CHECK = "||Check = (Env||NoVoteFlipping||NoPersonalInfoLeak||NoVotesNumChange||ChargeAfterLogin||{u, mu}::NoConfirmAfterSelectUntilVote||PayAfterCharge).";
        LTSfunc1.openFile("moti_example.lts", "<MOTI>", REQ, CHECK, BM);
        long start = System.currentTimeMillis();
        LTSfunc1.ModelChecking("Check");
        long end = System.currentTimeMillis();

        TimeSpent = (end - start);

            LtK.LTSfunc = LTSfunc1;
//            System.out.println("Machine Behavior Model:\n"+LtK.getMachineBM());
            LtK.RegexMatches(LtK.getMachineBM());
            LtK.lts_to_ks();
//        this.LTSfunc.ModelChecking("ORDER2");
//        this.LTSfunc.assertion.compile(this.LTSfunc.myoutput, "assert NoPersonalInfoLeak = [](u.input_info -> [](!mu.input_info))");
            CompositeState cs1 = LTSfunc1.getCurrent();
//        cs1.checkLTL(this.LTSfunc.myoutput, cs1);
//            System.out.println("Hello, CompactStates " + cs1);
            LTSfunc1.newMachine(cs1);
            for (int id = 0; id < LTSfunc1.sm.length; id++) {
                LTSfunc1.printTransitions(id);
            }
        System.out.println("violated properties!!!!" + LTSfunc1.violated_property);
//            if(LTSfunc1.violate_flag == 1) {
//                flag_v[i] = "         Y         ";
//                vio_req[i] = LTSfunc1.violated_property + "   ";
//                Checking_Result.add(1);
//            }else{
//                flag_v[i] = "         N         ";
//                vio_req[i] = Check + "   ";
//                Checking_Result.add(0);
//            }



        //System.out.println("Model Checking Time: " + TimeSpent + "ms");

//        System.out.println("---------------------------------------------\n" +
//                           "--------------------Result-------------------\n");
        TBL_1[1][0] = "Violated? (Y/N)"; TBL_1[0][0] = "Violated Req.  ";
        for(int i = 0; i < ReqNum; i++){
            TBL_1[1][i+1] = flag_v[i];
            TBL_1[0][i+1] = vio_req[i];
        }
//        for(int i = 0; i < 2; i++){
//            for(int j = 0; j < ReqNum + 1; j++){
//                System.out.print(TBL_1[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println("---------------------------------------------");
    }
}
