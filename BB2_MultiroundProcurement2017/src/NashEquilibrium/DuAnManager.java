package NashEquilibrium;

import NashEquilibrium.NotPossibleException;
import NashEquilibrium.NotPossibleException;

/**
 *
 * @author quyennv
 */
public class DuAnManager {

    public int goiThau;
    public String[] tenThietBi;
    public int[] soLuong;
    public int[] giaDuKien;

    /**
     *
     * @param goiThau
     * @param tenThietBi
     * @param soLuong
     * @param giaDuKien
     * @throws NotPossibleException
     */
    public DuAnManager(int goiThau, String[] tenThietBi, int[] soLuong, int[] giaDuKien) throws NotPossibleException {
        if (validate(goiThau, tenThietBi, soLuong, giaDuKien)) {
            this.goiThau = goiThau;
            this.tenThietBi = tenThietBi;
            this.soLuong = soLuong;
            this.giaDuKien = giaDuKien;
        } else {
            throw new NotPossibleException("Du lieu ve cac thiet bi khong hop le...");
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
     * @return tenThietBi
     */
    public String[] getTenThietBi() {
        return tenThietBi;
    }

    /**
     *
     * @return soLuong
     */
    public int[] getSoLuong() {
        return soLuong;
    }

    /**
     *
     * @return giaDuKien
     */
    public int[] getGiaDuKien() {
        return giaDuKien;
    }

    /**
     *
     * @param goiThau
     * @return 
     */
    public boolean validateGoiThau(int goiThau) {
        return goiThau > 0;
    }

    /**
     *
     * @param tenThietBi
     * @return
     */
    public boolean validateTenThietBi(String[] tenThietBi) {
        return tenThietBi.length > 0;
    }

    /**
     *
     * @param soLuong
     * @return
     */
    public boolean validateSoLuong(int[] soLuong) {
        return soLuong.length > 0;
    }
    // 

    /**
     *
     * @param giaDuKien
     * @return
     */
    public boolean validateGiaDuKien(int[] giaDuKien) {
        return giaDuKien.length > 0;
    }

    /**
     *
     * @param goiThau
     * @param tenThietBi
     * @param soLuong
     * @param giaDuKien
     * @return
     */
    public boolean validate(int goiThau, String[] tenThietBi, int[] soLuong, int[] giaDuKien) {
        return validateSoLuong(soLuong) && validateGiaDuKien(giaDuKien) && validateGoiThau(goiThau) && validateTenThietBi(tenThietBi);
    }

    @Override
    public String toString() {
        String duAn = "Goi thau thu " + goiThau + ": ";
        String space = "         ";
            for (int k = 0; k < tenThietBi.length; k++) {
                duAn += "\n" + space + "Ten thiet bi: " + tenThietBi[k] + ", so luong: " + soLuong[k] + ", gia du kien: " + giaDuKien[k];
            }
            duAn += "\n";
        return duAn;
    }
    

}
