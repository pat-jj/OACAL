import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args){
        System.out.println("---- Start ----");
        //alg.initAndRun();
        //---------------------------------------------------------------------------
        //--------start
        ArrayList<ArrayList<Integer>> data = Retrieve_Data.retrieve_data();
        //---------------------------------------------------------------------------
        String csvFilePath = "C:\\Users\\Administrator.DESKTOP-ELM13H1\\Desktop\\oasis_project-master\\data\\";
        String[] csvHeaders = {"P0", "P1", "P2", "P3",
                "P4", "P5", "P6", "NoVoteFlipping",
                "NoPersonalInfoLeak", "NoVotesNumChange", "ChargeAfterLogin",
                "NoConfirmAfterSelectUntilVote", "PayAfterCharge"};

        CsvWriter.writeCSV(data, csvHeaders ,csvFilePath);


        System.out.println("---- End ----");

    }
}
