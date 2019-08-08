package NashEquilibrium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author quyennv
 */
public class Model {

    private static final int PHAN_TRAM_GIU_LAI = 30;
    private static final int PHAN_TRAM_LAI_GHEP = 60;
    private static final int PHAN_TRAM_DOT_BIEN = 10;
    private static final int HE_SO_CHU_DAU_TU = 1;
    private static final int HE_SO_PHUONG_SAI = 3;
    private static final int HE_SO_DOT_BIEN = 3;

    public static GenericAlgorithms[] sortedGA() {
        // tao ra 100 gen @gen
        int[][] gen = MaGen.createHundredGen();
        GenericAlgorithms[] GA = new GenericAlgorithms[gen.length];
        // truyen cac gen @gen vao constructor @GA
        for (int i = 0; i < gen.length; i++) {
            // tinh loi nhuan @loiNhuanChuDauTu tren doan gen @gen
            long loiNhuanChuDauTu = TinhLoiNhuanGen.tinhLoiNhuanChuDauTu(gen[i]);
            // tinh loi nhuan @tongTBPhuongSai tren doan gen @gen
            long tongTbPhuongSai = TinhLoiNhuanGen.tinhTBPhuongSai(gen[i]);
            // tinh ham fitNess @fitNess dua theo loi nhuan nha thau @loiNhuanNhaThau va loi nhuan chu dau tu @loiNhuanChuDauTu
            long fitNess = TinhLoiNhuanGen.tinhFitNess(loiNhuanChuDauTu, HE_SO_CHU_DAU_TU, tongTbPhuongSai, HE_SO_PHUONG_SAI);
            GA[i] = new GenericAlgorithms(gen[i], fitNess);
        }
        // them cac @GA vao arrayList
        ArrayList<GenericAlgorithms> arrGA = new ArrayList<>();
        for (int i = 0; i < GA.length; i++) {
            arrGA.add(GA[i]);
        }
        // sort cac @GA theo ham fitness @fitNess
        Collections.sort(arrGA, new Comparator<GenericAlgorithms>() {
            @Override
            public int compare(GenericAlgorithms o1, GenericAlgorithms o2) {
                return (int) (o1.getFitNess() - o2.getFitNess());
            }
        });
        GenericAlgorithms[] sortedGA = new GenericAlgorithms[arrGA.size()];
        // truyen cac phan tu trong arrayList vao array 
        for (int i = 0; i < arrGA.size(); i++) {
            sortedGA[i] = arrGA.get(i);
        }
        // tra ve mot chuoi cac @GA da duoc sap xep theo ham @fitNess
        return sortedGA;
    }

    public static GenericAlgorithms[] getSortedBenGen(GenericAlgorithms[] sortedGA) {
        ArrayList<GenericAlgorithms> giuLai = new ArrayList<>();
        ArrayList<GenericAlgorithms> laiGhep = new ArrayList<>();
        ArrayList<GenericAlgorithms> dotBien = new ArrayList<>();
        // lay ra cac gen duoc giu lai @giuLai
        for (int i = 0; i < PHAN_TRAM_GIU_LAI; i++) {
            giuLai.add(sortedGA[i]);
        }
        int lengthLaiGhep = PHAN_TRAM_GIU_LAI + PHAN_TRAM_DOT_BIEN;
        // lay ra mot tap cac gen lai ghep @laiGhep

        for (int i = PHAN_TRAM_GIU_LAI; i < lengthLaiGhep; i++) {
            laiGhep.add(sortedGA[i]);
        }
        // lay ra mot tap cac gen de tao dot bien @dotBien

        for (int i = lengthLaiGhep; i < sortedGA.length; i++) {
            dotBien.add(sortedGA[i]);
        }
        GenericAlgorithms[] laiGh = new GenericAlgorithms[laiGhep.size()];
        for (int i = 0; i < laiGhep.size(); i++) {
            laiGh[i] = laiGhep.get(i);
        }
        GenericAlgorithms[] dotBi = new GenericAlgorithms[dotBien.size()];
        for (int i = 0; i < dotBien.size(); i++) {
            dotBi[i] = dotBien.get(i);
        }
        // tao ra cac gen lai ghep @lai theo @PHAN_TRAM_LAI_GHEP
        GenericAlgorithms[] lai = sortedGenLaiGhep(laiGh);
        // tao ra cac gen dot bien @dot theo @PHAN_TRAM_DOT_BIEN
        GenericAlgorithms[] dot = sortedGenDotBien(dotBi);

        ArrayList<GenericAlgorithms> arrSorted = new ArrayList();
        for (int i = 0; i < giuLai.size(); i++) {
            arrSorted.add(giuLai.get(i));
        }

        for (int i = 0; i < lai.length; i++) {
            arrSorted.add(lai[i]);
        }
        for (int i = 0; i < dot.length; i++) {
            arrSorted.add(dot[i]);
        }
        Collections.sort(arrSorted, new Comparator<GenericAlgorithms>() {
            @Override
            public int compare(GenericAlgorithms o1, GenericAlgorithms o2) {
                return (int) (o1.getFitNess() - o2.getFitNess());
            }
        });
        GenericAlgorithms[] sortedGAl = new GenericAlgorithms[arrSorted.size()];
        // truyen cac phan tu trong arrayList vao array 
        for (int i = 0; i < arrSorted.size(); i++) {
            sortedGAl[i] = arrSorted.get(i);
        }
        return sortedGAl;
    }

    public static GenericAlgorithms[] sortedGenLaiGhep(GenericAlgorithms[] laiGhep) {
        GenericAlgorithms[] GA = new GenericAlgorithms[PHAN_TRAM_LAI_GHEP];
        Random rd = new Random();
        for (int i = 0; i < PHAN_TRAM_LAI_GHEP; i++) {
            int dad = rd.nextInt(laiGhep.length);
            int mom = rd.nextInt(laiGhep.length);
            int[] theHeCon = laiGhep(laiGhep[dad].getMaGen(), laiGhep[mom].getMaGen());
            long loiNhuanChuDauTu = TinhLoiNhuanGen.tinhLoiNhuanChuDauTu(theHeCon);
            // tinh loi nhuan @tongTBPhuongSai tren doan gen @gen
            long tongTbPhuongSai = TinhLoiNhuanGen.tinhTBPhuongSai(theHeCon);
            // tinh ham fitNess @fitNess dua theo loi nhuan nha thau @loiNhuanNhaThau va loi nhuan chu dau tu @loiNhuanChuDauTu
            long fitNess = TinhLoiNhuanGen.tinhFitNess(loiNhuanChuDauTu, HE_SO_CHU_DAU_TU, tongTbPhuongSai, HE_SO_PHUONG_SAI);
            GA[i] = new GenericAlgorithms(theHeCon, fitNess);
        }
        ArrayList<GenericAlgorithms> arrGA = new ArrayList<>();
        for (int i = 0; i < GA.length; i++) {
            arrGA.add(GA[i]);
        }
        GenericAlgorithms[] sortedGA = new GenericAlgorithms[arrGA.size()];
        // truyen cac phan tu trong arrayList vao array 
        for (int i = 0; i < arrGA.size(); i++) {
            sortedGA[i] = arrGA.get(i);
        }
        // tra ve mot chuoi cac @GA
        return sortedGA;
    }

    public static GenericAlgorithms[] sortedGenDotBien(GenericAlgorithms[] dotBien) {
        GenericAlgorithms[] GA = new GenericAlgorithms[PHAN_TRAM_DOT_BIEN];
        for (int i = 0; i < PHAN_TRAM_DOT_BIEN; i++) {
            int[] theHeCon = dotBien(dotBien[i].getMaGen(), HE_SO_DOT_BIEN);
            long loiNhuanChuDauTu = TinhLoiNhuanGen.tinhLoiNhuanChuDauTu(theHeCon);
            // tinh loi nhuan @tongTBPhuongSai tren doan gen @gen
            long tongTbPhuongSai = TinhLoiNhuanGen.tinhTBPhuongSai(theHeCon);
            // tinh ham fitNess @fitNess dua theo loi nhuan nha thau @loiNhuanNhaThau va loi nhuan chu dau tu @loiNhuanChuDauTu
            long fitNess = TinhLoiNhuanGen.tinhFitNess(loiNhuanChuDauTu, HE_SO_CHU_DAU_TU, tongTbPhuongSai, HE_SO_PHUONG_SAI);
            GA[i] = new GenericAlgorithms(theHeCon, fitNess);
        }
        ArrayList<GenericAlgorithms> arrGA = new ArrayList<>();
        for (int i = 0; i < GA.length; i++) {
            arrGA.add(GA[i]);
        }
        GenericAlgorithms[] sortedGA = new GenericAlgorithms[arrGA.size()];
        // truyen cac phan tu trong arrayList vao array 
        for (int i = 0; i < arrGA.size(); i++) {
            sortedGA[i] = arrGA.get(i);
        }
        // tra ve mot chuoi cac @GA da duoc sap xep theo ham @fitNess
        return sortedGA;
    }

    public static int[] laiGhep(int[] father, int[] mother) {
        Random rd = new Random();
        // lay ra gia tri lai ghep tai @cut
        int cut = rd.nextInt(father.length);
        int[] gen = new int[father.length];
        // truyen cac phan tu cua @father vao gen cho den diem @cut
        for (int i = 0; i < cut; i++) {
            gen[i] = father[i];
        }
        // truyen cac phan tu cua @mother vao gen tinh tu diem @cut
        for (int i = cut; i < mother.length; i++) {
            gen[i] = mother[i];
        }
        // tra ve doan ma gen @gen
        return gen;
    }

    public static int[] dotBien(int[] gen, int tiLe) {
        List<Integer> arr = new ArrayList<>();
        // truyen cac phan tu vao arrayList @arr
        for (int i = 0; i < gen.length; i++) {
            arr.add(gen[i]);
        }
        // tao random cac node
        Random rd = new Random();
        int[] ranNum = new int[tiLe];
        for (int i = 0; i < tiLe; i++) {
            ranNum[i] = rd.nextInt(gen.length);
        }

        GoiThauManager[] goiThau = GoiThau.getGoiThau();
        for (int i = 0; i < ranNum.length; i++) {
            int[] nhaThau = goiThau[ranNum[i]].getNhaThau();
            int ran = rd.nextInt(nhaThau.length);
            arr.remove(ranNum[i]);
            arr.add(ranNum[i], nhaThau[ran]);
        }
        int[] maGen = new int[arr.size()];
        Iterator<Integer> iter = arr.iterator();
        for (int i = 0; iter.hasNext(); i++) {
            maGen[i] = iter.next();
        }
        return maGen;
    }

    public static int getPhanTramLaiGep() {
        return PHAN_TRAM_LAI_GHEP;
    }

    public static int getPhanTramDotBien() {
        return PHAN_TRAM_DOT_BIEN;
    }
    
}
