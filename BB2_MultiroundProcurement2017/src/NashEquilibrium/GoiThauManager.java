package NashEquilibrium;

import NashEquilibrium.NotPossibleException;

/**
 *
 * @author quyennv
 */
public class GoiThauManager {

    public int goiThau;
    public int[] nhaThau;
    public String[] rank;
    public int[] giaGoc;
    public int[] giaBan;

    /**
     *
     * @param goiThau
     * @param nhaThau
     * @param rank
     * @param giaGoc
     * @param giaBan
     * @throws NotPossibleException
     */
    public GoiThauManager(int goiThau, int[] nhaThau, String[] rank, int[] giaGoc, int[] giaBan) throws NotPossibleException {
        if (validate(goiThau, nhaThau, rank, giaGoc, giaBan)) {
            this.goiThau = goiThau;
            this.nhaThau = nhaThau;
            this.rank = rank;
            this.giaGoc = giaGoc;
            this.giaBan = giaBan;
        } else {
            throw new NotPossibleException("Goi thau khong hop le...");
        }
    }

    /**
     *
     * @return goiThau
     */
    public int getGoiThau() {
        return goiThau;
    }

    /**
     *
     * @return nhaThau
     */
    public int[] getNhaThau() {
        return nhaThau;
    }

    /**
     *
     * @return rank
     */
    public String[] getRankString() {
        return rank;
    }

    /**
     *
     * @return decimal rank 
     */
    public int[] getRankInt() {
        int[] Rank = new int[rank.length];
        for (int i = 0; i < rank.length; i++) {
            if ("R1".equals(rank[i])) {
                Rank[i] = 1;
            } else if ("R2".equals(rank[i])) {
                Rank[i] = 2;
            } else if ("R3".equals(rank[i])) {
                Rank[i] = 3;
            }
        }
        return Rank;
    }

    /**
     *
     * @return giaGoc
     */
    public int[] getGiaGoc() {
        return giaGoc;
    }

    /**
     *
     * @return giaBan
     */
    public int[] getGiaBan() {
        return giaBan;
    }

    public static boolean validateGoiThau(int goiThau) {
        return goiThau > 0;
    }

    public static boolean validateNhaThau(int[] nhaThau) {
        return nhaThau.length > 0;
    }

    public static boolean validateRank(String[] rank) {
        return rank.length > 0;
    }

    public static boolean validateGiaGoc(int[] giaGoc) {
        return giaGoc.length > 0;
    }

    public static boolean validateGiaBan(int[] giaBan) {
        return giaBan.length > 0;
    }

    public static boolean validate(int goiThau, int[] nhaThau, String[] rank, int[] giaGoc, int[] giaBan) {
        return validateGoiThau(goiThau) && validateNhaThau(nhaThau) && validateRank(rank) && validateGiaGoc(giaGoc) && validateGiaBan(giaBan);
    }

    @Override
    public String toString() {
        String goi = "";
        String space = "         ";
        goi += "Goi thau so: " + goiThau;
        for (int i = 0; i < nhaThau.length;) {
            
            goi += "\n" + space + "Nha thau " + nhaThau[i] + ", rank <" + rank[i] + ">"; 
            for(int j = 0; j < getGiaGoc().length;j++){
                goi+= "\n"+ space+""+space+"Gia goc: "+getGiaGoc()[j]+", gia ban: "+getGiaBan()[j];
            }
            i++;
        }
        goi += "\n";
        return goi;
    }

}
