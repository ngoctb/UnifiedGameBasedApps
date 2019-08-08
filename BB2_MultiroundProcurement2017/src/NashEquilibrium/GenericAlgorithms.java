package NashEquilibrium;

/**
 *
 * @author quyennv
 */
public class GenericAlgorithms {

    public int[] maGen;
    public long fitNess;
    public int num;

    /**
     *
     * @param num
     * @param maGen
     * @param fitNess
     * @throws NotPossibleException
     */
    public GenericAlgorithms(int[] maGen, long fitNess) throws NotPossibleException {
        if (validate(maGen, fitNess)) {
            this.maGen = maGen;
            this.fitNess = fitNess;
        } else {
            throw new NotPossibleException("Ma gen khong hop le...");
        }
    }

    /**
     *
     * @return maGen
     */
    public int[] getMaGen() {
        return maGen;
    }

    /**
     *
     * @return fitNess
     */
    public long getFitNess() {
        return fitNess;
    }

    

    /**
     *
     * @param maGen
     * @return
     */
    public boolean validateMaGen(int[] maGen) {
        return maGen.length > 0;
    }

    /**
     *
     * @param fitNess
     * @return
     */
    public boolean validateFitNess(long fitNess) {
        return fitNess > 0;
    }

    /**
     *
     * @param maGen
     * @param fitNess
     * @return
     */
    public boolean validate(int[] maGen, long fitNess) {
        return validateMaGen(maGen) && validateFitNess(fitNess);
    }

    

    @Override
    public String toString() {
        String ga = "";
        String space = "         ";
        int[] genn = getMaGen();
        ga += "Chromosome: "+ "\n";
        ga += space;
        ga += "[";
        for (int i = 0; i < genn.length; i++) {
            ga += genn[i] + " ";
        } 
        ga += "] " + "\n"+ "Fitness: " + getFitNess();
        ga += "\n";
        return ga;
    }
}
