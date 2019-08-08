package NashEquilibrium;

import NashEquilibrium.GoiThauManager;
import NashEquilibrium.ReadFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author quyennv
 */
public class GoiThau {

    public static GoiThauManager[] goiThauMan = new GoiThauManager[ReadFile.getSoGoi()];

    public static GoiThauManager[] getGoiThau() {
        File file = new File(ReadFile.getPathGoiThau());
        try {
            Scanner input = new Scanner(file);
            for (int i = 0; i < ReadFile.getSoGoi(); i++) {
                String content = input.nextLine();
                String[] goiThau = content.split("@");
                String[] div = goiThau[1].split("&");
                int[] nhaThau = new int[div.length];
                String[] rank = new String[div.length];
                ArrayList<Integer> giaGoc1 = new ArrayList<Integer>();
                ArrayList<Integer> giaBan1 = new ArrayList<Integer>();
                for (int j = 0; j < div.length; j++) {
                    String[] div2 = div[j].split("#");
                    nhaThau[j] = new Integer(div2[0]).intValue();
                    String[] div3 = div2[1].split("/");
                    rank[j] = div3[0];
                    String[] div4 = div3[1].split("-");
                    for (int k = 0; k < div4.length; k++) {
                        String[] div5 = div4[k].split(":");
                            giaGoc1.add(new Integer(div5[0]).intValue() * 1000);
                            giaBan1.add(new Integer(div5[1]).intValue() * 1000);
                    }
                    

                }
                int[] giaGoc = new int[giaGoc1.size()];
                    int[] giaBan = new int[giaBan1.size()];
                    for(int d = 0; d < giaGoc1.size();d++){
                        giaGoc[d] = giaGoc1.get(d);
                        giaBan[d] = giaBan1.get(d);
                    }
                goiThauMan[i] = new GoiThauManager(new Integer(goiThau[0]).intValue(), nhaThau, rank, giaGoc, giaBan);
            }
        } catch (IOException io) {
            System.err.println("\"goithau.txt\": Not Found!");
        }
        return goiThauMan;
    }
}
