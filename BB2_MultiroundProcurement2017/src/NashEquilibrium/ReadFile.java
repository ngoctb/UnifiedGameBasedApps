package NashEquilibrium;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author quyennv
 */
public class ReadFile {
    public static final String PATHDUAN = "src/figure/duan.txt";
    public static final String PATHNHATHAU= "src/figure/nhathau.txt";
    public static final String PATHGOITHAU = "src/figure/goithau.txt";

    public static String getPathDuAn() {
        return PATHDUAN;
    }

    public static String getPathGoiThau() {
        return PATHGOITHAU;
    }

    public static String getPathNhaThau() {
        return PATHNHATHAU;
    }
    public static int getSoNhaThau(){
        File file = new File(PATHNHATHAU);
        int soLuong = 0;
        try{
            Scanner input = new Scanner(file);
            while(input.hasNextLine()){
                String content = input.nextLine();
                soLuong++;
            }
        }catch(IOException io){
            System.err.println("\"nhathau.txt\": Not Found!");
        }
        return soLuong;
    }
    public static int getSoGiaiDoan(){
        File file = new File(PATHDUAN);
        int soGiaiDoan = 0;
        try{
            Scanner input = new Scanner(file);
            String content = input.nextLine();
            soGiaiDoan = new Integer(content).intValue();
        }catch(IOException io){
            System.err.println("\"duan.txt\": Not Found!");
        }
        return soGiaiDoan;
    }
    public static int getSoGoi(){
        File file = new File(PATHDUAN);
        int soGiaiDoan;
        int soGoi = 0;
        try{
            Scanner input = new Scanner(file);
            String content = input.nextLine();
            soGiaiDoan = new Integer(content).intValue();
            for(int i = 0; i < soGiaiDoan;i++){
                String text = input.nextLine();
                String[] div = text.split(" ");
                soGoi += new Integer(div[1]).intValue();
            }
        }catch(IOException io){
            System.err.println("\"duan.txt\": Not Found!");
        }
        return soGoi;
    }
}
