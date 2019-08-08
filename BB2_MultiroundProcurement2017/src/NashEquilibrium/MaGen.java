package NashEquilibrium;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author quyennv
 */
public class MaGen {
    public static final int DO_DAI_MA_GEN = ReadFile.getSoGoi();
    public static final int SO_LUONG_GEN = 100;
    public static int[] getMaGen(){
        ArrayList arr = new ArrayList();
        GoiThauManager[] goiThau = GoiThau.getGoiThau();
        int[] gen = new int[DO_DAI_MA_GEN];
        for(int i = 0;i < goiThau.length;i++ ){
            int[] nhaThau = goiThau[i].getNhaThau();
            for(int j = 0; j < nhaThau.length;j++){
                arr.add(nhaThau[j]);
            }
            Collections.shuffle(arr);
            gen[i] = (int) arr.get(i);
        }
        return gen;
    }
    public static int[][] createHundredGen(){
        int[][] hunGen = new int[SO_LUONG_GEN][DO_DAI_MA_GEN];
        //tao ngau nhien 100 ma gen
        for(int i = 0; i < SO_LUONG_GEN;i++){
            hunGen[i] = getMaGen();
        }
    return hunGen;
    }
    //in ra 100 ma gen da khoi tao
    public static void printOutGen(int[][] hunGen){
        int[] gen = new int[hunGen.length];
        for(int i = 0; i < hunGen.length;i++){
            gen = hunGen[i];
            System.out.print("Ma gen thu "+i+": [");
            for(int j = 0; j < gen.length;j++){
                System.out.print(gen[j] + " ");
            }
            System.out.print("]");
            System.out.println("");
        }
    }

    public static int getGenLength() {
        return DO_DAI_MA_GEN;
    }

    public static int getSoLuongGen() {
        return SO_LUONG_GEN;
    }
    

    
}
