package NashEquilibrium;

import NashEquilibrium.ReadFile;
import NashEquilibrium.ReadFile;
import NashEquilibrium.ReadFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author quyennv
 */
public class DuAn {

    public static ArrayList<DuAnManager> duAn = new ArrayList<>();
    public static int soGoi=0;
    /**
     *
     * @param path
     * @return
     */
    public static DuAnManager[] getDuAn() {
        //tao duong dan den file
        File file = new File(ReadFile.getPathDuAn());
        try {
            //quet trong file
            Scanner nhap = new Scanner(file);
            //lay ra so giai doan o dong thu 1
            int soGiaiDoan = new Integer((String) nhap.nextLine()).intValue();
            // lay tong so goi trong cac giai doan
            for (int k = 0; k < soGiaiDoan; k++) {
                //lay du lieu tung dong
                String line = nhap.nextLine();
                //cat chuoi ki tu cua dong vua lay
                String[] kitu = line.split(" ");
                // lay tong so goi
                soGoi = new Integer(kitu[1]).intValue();
            }
            //lay object duAn theo cac thong so
            for (int i = 0; i < soGoi; i++) {
                //doc dong tiep theo
                String text = nhap.nextLine();
                //cat chuoi de lay ra so goi thau
                String[] div = text.split("\\.");
                // lay ra so thu tu goi thau
                int goiThau = new Integer(div[0]).intValue();
                // cat chuoi de lay ra cac vat lieu
                String[] vatLieu = div[1].split(" ");
                String[] tenThietBi = new String[vatLieu.length];
                int[] soLuong = new int[vatLieu.length];
                int[] giaDuKien = new int[vatLieu.length];
                // lay ra ten thiet bi, so luong va gia du kien
                for (int j = 0; j < vatLieu.length; j++) {
                    String[] div2 = vatLieu[j].split("\\:");
                    tenThietBi[j] = div2[0];
                    soLuong[j] = new Integer(div2[1]).intValue();
                    giaDuKien[j] = new Integer(div2[2]).intValue()*1000;

                }
                //khoi tao duAn thu i theo cac so lieu da lay
                DuAn.duAn.add(new DuAnManager(goiThau, tenThietBi, soLuong, giaDuKien));
            }

        } catch (IOException fn) {
            System.err.println("\"duan.txt\": Not found!"); // bat loi khong tim thay file
        }
        
        return duAn.toArray(new DuAnManager[duAn.size()]);
    }
}
