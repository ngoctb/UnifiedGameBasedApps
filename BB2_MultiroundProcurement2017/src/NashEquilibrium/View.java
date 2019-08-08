package NashEquilibrium;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author quyennv
 */
public class View {
    public static String finalReport() {
        long start = System.currentTimeMillis();
        String report = Controller.report()+"\n";
        long stop = System.currentTimeMillis() - start;
        int time = (int) (stop / 1000);
        int hour = time / 60;
        int cl = time - (hour * 60);
        int minute = cl / 60;
        int cl2 = cl - (minute * 60);
        int second = cl2 % 60;
        report += "Sucessfully after "+hour+" hours "+minute+" minutes "+second+" seconds";
        return report;
    }
    public static void writeFile() {
        String report = finalReport();
        File file = new File("src/figure/Final_Report_200.txt");
        try{
            PrintWriter pw = new PrintWriter(file);
            pw.println(report);
            pw.close();
        }catch(IOException io){
            JOptionPane.showMessageDialog(null, "\"Final_Report.txt\": Not Found!");
        }
    }
    public static void main(String[] args) {
        System.out.println(finalReport());
        long duKien = TinhLoiNhuanGen.duKienChuDauTu();
        System.out.println("Estimated price: "+duKien);
        
    }
}
