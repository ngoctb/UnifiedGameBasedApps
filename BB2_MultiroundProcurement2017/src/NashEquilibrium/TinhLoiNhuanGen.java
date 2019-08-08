package NashEquilibrium;

/**
 *
 * @author quyennv
 */
public class TinhLoiNhuanGen {

    public static long duKienChuDauTu() {
        DuAnManager[] duAn = DuAn.getDuAn();
        long giaDuKien = 0;
        for (int i = 0; i < duAn.length; i++) {
            //lay so luong vat lieu tai goi thau thu i
            int[] soLuong = duAn[i].getSoLuong();
            // lay ra gia du kien cua goi thau thu i
            int[] giaDK = duAn[i].getGiaDuKien();
            //tinh tong gia du kien cua chu dau tu
            for (int j = 0; j < soLuong.length; j++) {
                giaDuKien += (long) soLuong[j] * (long) giaDK[j];
            }
        }
        return giaDuKien;
    }

    public static long tinhLoiNhuanNT(int[] gen) {
        long loiNhuan = 0;
        NhaThauManager[] nhaThau = NhaThau.getNhaThauManager();
        // cong them @loiNhuan cua nha thau thu @viTri
        for(int i = 0; i < gen.length;i++){
            int viTri = gen[i] - 1;
            loiNhuan += nhaThau[viTri].tinhLoiNhuan(nhaThau[viTri], i);
        }
        return loiNhuan;
    }

    public static long tinhFitNess(long loiNhuanChuDauTu,int heSoCDT, long tongTBPhuongSai, int heSoPhuongSai) {
        long fitness = (long)(loiNhuanChuDauTu * (long)heSoCDT) + (tongTBPhuongSai * (long)heSoPhuongSai);
        return fitness;
    }

    public static long tinhLoiNhuanChuDauTu(int[] gen) {
        long duKien = duKienChuDauTu();
        NhaThauManager[] nhaThau = NhaThau.getNhaThauManager();
        // lay ra vi tri @viTri cua nha thau
        int[] viTri = new int[gen.length];
        for(int i = 0; i < gen.length;i++){
            viTri[i] = gen[i] - 1;
            
        }
        long loiNhuan = 0;
        for(int i = 0; i< gen.length;i++){
            loiNhuan += nhaThau[viTri[i]].tinhGiaBan(nhaThau[viTri[i]], i);
        }
//        long dk = duKien - loiNhuan;
        return loiNhuan;
    }

    public static long[] tinhLoiNhuanMoiNT(int[] gen) {
        NhaThauManager[] nhaThauSo = NhaThau.getNhaThauManager();
        long[] loiNhuan = new long[ReadFile.getSoNhaThau()];
        // tinh loi nhuan cua nha thau tai vi tri @vitri
        for (int i = 0; i < gen.length; i++) {
            int genI = gen[i];
            int vitri = genI - 1;
            loiNhuan[vitri] += nhaThauSo[vitri].tinhLoiNhuan(nhaThauSo[vitri], i);
        }
        return loiNhuan;
    }

    public static long tinhTBLoiNhuanNT(int[] gen) {
        int soGoi = ReadFile.getSoGoi();
        // tinh loi nhuan cua can nha thau tren gen @gen
        long loiNhuanCacNhaThau = tinhLoiNhuanNT(gen);
        // tinh trung binh loi nhuan @giaTriTrungBinh 
        long giaTriTrungBinh = loiNhuanCacNhaThau / soGoi;
        return giaTriTrungBinh;
    }

    public static long[] tinhTBPhuongSaiMoiNT(int[] gen) {
        long[] loiNhuanMoiNhaThau = tinhLoiNhuanMoiNT(gen);
        long[] TBPhuongSaiMoiNhaThau = new long[loiNhuanMoiNhaThau.length];
        long tbLoiNhuanNT = tinhTBLoiNhuanNT(gen);
        // tinh trung binh phuong sai @TBPhuongSaiMoiNhaThau cua moi nha thau
        for(int i = 0; i < loiNhuanMoiNhaThau.length;i++){
            TBPhuongSaiMoiNhaThau[i] = Math.abs(loiNhuanMoiNhaThau[i] - tbLoiNhuanNT);
        }
        return TBPhuongSaiMoiNhaThau;
        
    }
    public static long tinhTBPhuongSai(int[] gen){
        long[] TBPhuongSaiMoiNT = tinhTBPhuongSaiMoiNT(gen);
        long TBPhuongSai = 0;
        // tinh tong trung binh phuong sai @TBPhuongSai
        for(int i = 0; i < TBPhuongSaiMoiNT.length;i++){
            TBPhuongSai += TBPhuongSaiMoiNT[i];
        }
        return TBPhuongSai;
    }
    
}
