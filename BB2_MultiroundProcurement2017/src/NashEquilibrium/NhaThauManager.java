package NashEquilibrium;

/**
 *
 * @author quyennv
 */
public class NhaThauManager {

    public int nhaThau;
    public String rank;
    public int[] goiThau;
    public int[] giaGoc;
    public int[] giaBan;
    public final int KHACH_HANG_THAN_QUEN = 1;
    public final int KHACH_HANG_MOI_QUEN = 2;
    public final int KHACH_HANG_CHUA_HOP_TAC = 3;

    public NhaThauManager(int nhaThau, String rank, int[] goiThau, int[] giaGoc, int[] giaBan) throws NotPossibleException {
        if (validate(nhaThau, rank, goiThau, giaGoc, giaBan)) {
            this.nhaThau = nhaThau;
            this.rank = rank;
            this.goiThau = goiThau;
            this.giaGoc = giaGoc;
            this.giaBan = giaBan;
        } else {
            throw new NotPossibleException("Nha thau khong hop le...");
        }
    }

    public int getNhaThau() {
        return nhaThau;
    }

    public int getRankInt() {
        int rank1;
        if ("R1".equals(rank)) {
            rank1 = KHACH_HANG_THAN_QUEN;
        } else if ("R2".equals(rank)) {
            rank1 = KHACH_HANG_MOI_QUEN;
        } else if ("R3".equals(rank)) {
            rank1 = KHACH_HANG_CHUA_HOP_TAC;
        } else {
            rank1 = KHACH_HANG_CHUA_HOP_TAC;
        }
        return rank1;
    }

    public String getRankString() {
        return rank;
    }

    public int[] getGoiThau() {
        return goiThau;
    }

    public int[] getGiaGoc() {
        return giaGoc;
    }

    public int[] getGiaBan() {
        return giaBan;
    }

    public boolean validateNhaThau(int nhaThau) {
        return nhaThau > 0;
    }

    public boolean validateRank(String Rank) {
        return "R1".equals(Rank) || "R2".equals(Rank) || "R3".equals(Rank);
    }

    public boolean validateGoiThau(int[] goiThau) {
        return goiThau.length > 0;
    }

    public boolean validateGiaGoc(int[] giaGoc) {
        return giaGoc.length > 0;
    }

    public boolean validateGiaBan(int[] giaBan) {
        return giaBan.length > 0;
    }

    public boolean validate(int nhaThau, String Rank, int[] goiThau, int[] giaGoc, int[] giaBan) {
        return validateNhaThau(nhaThau) && validateRank(Rank) && validateGoiThau(goiThau) && validateGiaGoc(giaGoc) && validateGiaBan(giaBan);
    }

    // ham tinh loi nhuan cua @nhathau khi duoc lua chon va trung thau goi thau @goi
    // ket qua tra lai gia thi loi nhuan
    public int tinhLoiNhuan(NhaThauManager nhaThau, int goi) {
        DuAnManager[] duAn = DuAn.getDuAn();
        //lay ra so luong vat lieu cua goi thu @goi
        int[] soLuong = duAn[goi].getSoLuong();
        int loiNhuan = 0;
        int[] Goc = nhaThau.getGiaGoc();
        int[] Ban = nhaThau.getGiaBan();
        int Rank = nhaThau.getRankInt();    //constant define trong so than thiet cua nha thau vs chu dau tu
        //tinh tong loi nhuan @loiNhuan tai goi thau thu @goi
        for(int i = 0; i < soLuong.length;i++){
            loiNhuan += soLuong[i] * (Ban[i] - Goc[i]);
        }
        return loiNhuan*Rank;
    }
    
    public int tinhGiaBan(NhaThauManager nhaThau, int goi) {
        DuAnManager[] duAn = DuAn.getDuAn();
        //lay ra so luong vat lieu cua goi thu @goi
        int[] soLuong = duAn[goi].getSoLuong();
        int giaBan = 0;
        int[] Ban = nhaThau.getGiaBan();
        //tinh tong gia ban @giaBan tai goi thau thu @goi
        for(int i = 0; i < soLuong.length;i++){
           giaBan += soLuong[i] * (Ban[i]);
        }
        return giaBan;
    }

}
