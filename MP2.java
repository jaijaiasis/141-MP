/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MP2 {
    
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws IOException{
        FileWriter fw = new FileWriter("MP2.out");
        ArrayList<String> string=new ArrayList<>();  
        
        FileInputStream read_file = new FileInputStream("C:\\mp2.in");
	BufferedReader br = new BufferedReader(new InputStreamReader(read_file));
 
	String line;
        
        //adding to arraylist string
	while ((line = br.readLine()) != null) {
		string.add(line);
	}
        
        //states
        final int MLCR_ = 0, //initial
            MLC_R = 1, 
            MLR_C = 2, 
            MRC_L = 3, 
            ML_RC = 4,
            MC_RL = 5, 
            MR_CL = 6,
            LC_RM = 7,
            LR_CM = 8,
            RC_LM = 9,
            L_RCM = 10,
            C_RLM = 11,
            R_CLM = 12,
            _LCRM = 13, //final state
            LOSE = 14;  //final state
        
        //state table
        final int[][] table = new int[][]{
            {RC_LM, LC_RM, LR_CM, LOSE},
            {C_RLM, LOSE, L_RCM, LC_RM},
            {R_CLM, L_RCM, LOSE, LR_CM},
            {LOSE, C_RLM, R_CLM, RC_LM},
            {LOSE, LOSE, LOSE, LOSE},
            {LOSE, LOSE, LOSE, LOSE},
            {LOSE, _LCRM, LOSE, R_CLM},
            {LOSE, MLCR_, LOSE, MLC_R},
            {LOSE, LOSE, LOSE, LOSE},
            {LOSE, LOSE, LOSE, LOSE},
            {LOSE, MLR_C, MLC_R, ML_RC},
            {MLC_R, MRC_L, LOSE, MC_RL},
            {MLR_C, LOSE, MRC_L, MR_CL},
            {ML_RC, MR_CL, MC_RL, LOSE},
            {LOSE, LOSE, LOSE, LOSE},
        };
        
        for (String string1 : string) {
            int state=0, input=3;
            for (int j = 0; j < string1.length(); j++) {
                //System.out.println("String " + i + j + " " + string.get(i).charAt(j));
                if (string1.charAt(j) == 'L') {
                    input = 0;
                } else if (string1.charAt(j) == 'R') {
                    input = 1;
                } else if (string1.charAt(j) == 'C') {
                    input = 2;
                } else if (string1.charAt(j) == 'N') {
                    input = 3;
                } else
                    continue;
                state = table[state][input];
            }
            
            if(isAccepted(state)){
                fw.write("OK\n");
            }
            else{
                fw.write("NG\n");
            }
        }
        br.close();
        fw.close();
    }

    private static boolean isAccepted(int n) {
        return n==13;
    }
    
}
