package NashEquilibrium;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author quyennv
 */
public class NhaThau {

    public static NhaThauManager[] nhaThau = new NhaThauManager[ReadFile.getSoNhaThau()];

    public static NhaThauManager[] getNhaThauManager() {
        File file = new File(ReadFile.getPathNhaThau());
        try {
            Scanner input = new Scanner(file);
            for (int i = 0; i < ReadFile.getSoNhaThau(); i++) {
                String content = input.nextLine();
                String[] soNhaThau = content.split("@");
                String[] rank = soNhaThau[1].split("#");
                String[] goi = rank[1].split("&");
                int[] soGoi = new int[goi.length];
                String[] gia = new String[goi.length];
                for (int j = 0; j < goi.length; j++) {
                    String[] div = goi[j].split("/");
                    soGoi[j] = new Integer(div[0]).intValue();
                    gia[j] = div[1];
                }
                int[] giaGoc = new int[gia.length];
                int[] giaBan = new int[gia.length];
                for(int k = 0; k < gia.length;k++){
                    String[] div = gia[k].split("-");
                    for (String div1 : div) {
                        String[] div2 = div1.split(":");
                        giaGoc[k] = new Integer(div2[0]).intValue()*1000;
                        giaBan[k] = new Integer(div2[1]).intValue()*1000;
                    }
                }
                int nhaThau = new Integer(soNhaThau[0]).intValue();
                String rank2 = rank[0];
                int[] goiThau = soGoi;
                NhaThau.nhaThau[i] = new NhaThauManager(nhaThau, rank2, goiThau, giaGoc, giaBan);
            }
        } catch (IOException io) {
            System.err.println("\"nhathau.txt\": Not Found!");
        }
        return nhaThau;
    }
    
}
