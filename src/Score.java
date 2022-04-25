package tiles;

/**
 * Score class contains methods and
 * constructor for Score object.
 * Used to keep score.
 * @author Sam Cox
 * @version 1/30/20
 */
public class Score {

    private int currentCombo;
    private int longestCombo;

    /**
     * Score constructor initializes the current
     * and longest combos to 0.
     */
    public Score(){
        this.currentCombo = 0;
        this.longestCombo = 0;
    }

    /**
     * Returns the value of the current combo.
     * @return value of current combo.
     */
    public int getCurrentCombo(){

        return this.currentCombo;
    }

    /**
     * Returns the value of the longest combo.
     * @return value of longest combo.
     */
    public int getLongestCombo(){

        return  this.longestCombo;
    }

    /**
     * Increases the value of the current combo.
     */
    public void setCurrentCombo(){

        this.currentCombo++;
    }

    /**
     * Sets the value of the longest combo.
     * @param longestCombo value of new longest combo.
     */
    public void setLongestCombo(int longestCombo){

        this.longestCombo = longestCombo;
    }

    /**
     * Sets the currents combo back to 0.
     */
    public void resetCombo(){
        this.currentCombo = 0;
    }

}
