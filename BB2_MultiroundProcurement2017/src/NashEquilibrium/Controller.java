package NashEquilibrium;

/**
 *
 * @author quyennv
 */
public class Controller {

    private static final int SO_VONG_DOI = 10;

    public static GenericAlgorithms[] sorting(GenericAlgorithms[] sortedGA) {
        GenericAlgorithms[] bestGen = Model.getSortedBenGen(sortedGA);
        GenericAlgorithms[] banDau = bestGen;
        GenericAlgorithms min1 = getMinFitnessGen(banDau);
        for (int i = 0; i < SO_VONG_DOI; i++) {
            bestGen = Model.getSortedBenGen(bestGen);
        }
        GenericAlgorithms[] sau = bestGen;
        return sau;
    }
    
    
    public static GenericAlgorithms getMinFitnessGen(GenericAlgorithms[] sortedGA){
        GenericAlgorithms min = sortedGA[0];
        for(int i = 1; i < sortedGA.length;i++){
            if(min.getFitNess() > sortedGA[i].getFitNess()){
                min = sortedGA[i];
            }
        }
        return min;
    }
    public static String report() {
        GenericAlgorithms[] sortedGA = Model.sortedGA();
        GenericAlgorithms[] sorting = sorting(sortedGA);
        String report = "";
        String space = "         ";
        report += "Nash Equiliriums found as following:" + "\n";
        report += "Number of chromosomes: " +MaGen.getSoLuongGen()+space+ "\n";
        report += "Number of generations: "+SO_VONG_DOI+space+ "\n";
        report += "Cross-over percent: "+Model.getPhanTramLaiGep()+space+ "\n";
        report += "Mutation percent: "+Model.getPhanTramDotBien()+"%\n";
        GenericAlgorithms bestGen = getMinFitnessGen(sorting);
        report += "The Nash solution: \n";
        report += bestGen.toString()+"\n";
        int[] best = bestGen.getMaGen();
        long loiNhuanNT = TinhLoiNhuanGen.tinhLoiNhuanNT(best);
        long loiNhuanCDT = TinhLoiNhuanGen.tinhLoiNhuanChuDauTu(best);
        report += space +"The benefit of bidders: "+loiNhuanNT+"\n";
        report += space +"The benefit of project owner: "+loiNhuanCDT+"\n";
        return report;
    }
    
}
