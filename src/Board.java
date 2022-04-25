package tiles;
import java.util.*;

/**
 * Board class contains methods and
 * constructor for Board object.
 * Used to keep track of tiles game board.
 * @author Sam Cox
 * @version date 1/30/20
 */
public class Board {

    private List<Tile> board;
    private int rows;
    private int cols;
    private Score score;


    /**
     * Board constructor, makes a new board object
     * with spaces for 20 tiles.
     */
    public Board() {
        this.rows = 5;
        this.cols = 4;
        this.board = makeBoard();
        this.makeAllPics();
        this.shuffle();
        this.giveTilesIndex();
        this.score = new Score();
    }



    /**
     * Places tiles on the game board.
     *
     * @return the tile filled game board.
     */
    public List<Tile> makeBoard(){
        List<Tile> board = new LinkedList<>();

        for(int i = 0; i < this.rows * this.cols; i++){
            Tile tile = new Tile();
            board.add(tile);
        }
        return board;
    }

    /**
     * Returns a list of tiles the represent the board.
     * @return list of tiles
     */
    public List<Tile> getBoard(){
        return this.board;
    }


    /**
     * Shuffles the circles on each tile around so that
     * tiles are random each time.
     */
    public void shuffle(){
        int boardSize = 200;
        int tileIdx = 0;
        int[] circleSizes = {50, 40 ,30 ,20};
        for(int i = 0; i < boardSize; i++){
            int randIdxTile = (int)(Math.random() * this.board.size());
            int randIdxSizes = (int)(Math.random() * circleSizes.length);
            FacePiece tempRand = this.board.get(randIdxTile).
                    getCircle(circleSizes[randIdxSizes]);
            FacePiece tempStart = this.board.get(tileIdx).
                    getCircle(circleSizes[randIdxSizes]);
            this.board.get(randIdxTile).setCircle(tempStart,randIdxSizes);
            this.board.get(tileIdx).setCircle(tempRand,randIdxSizes);
            tileIdx++;
            if(tileIdx == this.board.size() - 1){
                tileIdx = 0;
            }
        }
    }


    /**
     * Indexes each tile with x and y coordinates
     * to be able to be accessed by the GUI.
     */
    public void giveTilesIndex(){
        int tileIdx = 0;
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.cols; j++){
                Tile tile = this.board.get(tileIdx);
                tile.setX(j);
                tile.setY(i);
                tileIdx++;
            }
        }

    }

    /**
     * Makes all the circles needed for each tile
     * and adds them to each tile.
     */
    public void makeAllPics(){
        int maxSize = 50;
        int offSet = 10;
        int tileIndex = 0;
        int[] posFaceSizes =  {maxSize, maxSize - offSet,
                              maxSize - (offSet*2), maxSize - (offSet*3)};
        String[] posFaceColors = {"O","B","G","P","R"};
        int fourPics = 4;

        for(int i = 0; i < this.cols; i++){
            for(int j = 0; j < this.rows; j++){
                FacePiece face = new FacePiece(posFaceColors[j],
                                               posFaceSizes[i]);
                for(int k = 0; k < fourPics; k++){
                    Tile tile = this.board.get(tileIndex);
                    tile.addToPieceFace(face);
                    tileIndex++;
                }

            }
            tileIndex = 0;
        }
    }

    /**
     * Returns the tile from the given x y coordinates.
     * @param x value of tile
     * @param y value of tile
     * @return tile at x y coordinate
     */
    public Tile getTile(int x, int y){
        Tile tile = null;
        for(Tile t : this.board){
            if(t.getX() == x && t.getY() == y){
                tile = t;
            }
        }
        return tile;
    }

    /**
     * Returns the score object for the game
     * that contains the current and longest combos.
     * @return score object.
     */
    public Score getScore(){
        return this.score;
    }

    /**
     * Returns the number of rows the board has.
     * @return number of rows
     */
    public int getRows(){
        return this.rows;
    }

    /**
     * Returns the number of columns the board has.
     * @return number of columns
     */
    public int getCols(){
        return this.cols;
    }

    /**
     * Checks if the game is over by checking
     * for any tiles with circles remaining.
     * @return true or false
     */
    public boolean checkGameOver(){
        for(Tile t : this.board){
            if(!t.isClear()){
                return false;
            }
        }
        return true;
    }

    /**
     * Resets the current combo.
     */
    public void resetCurrentCombo(){

        this.score.resetCombo();
    }

    /**
     * Adds to the current combo and
     * increases the longest combo if
     * the new current combo is larger than the
     * previous longest combo.
     */
    public void addToCurrentScore(){

        this.score.setCurrentCombo();
        int currentCombo = this.score.getCurrentCombo();

        if(this.score.getCurrentCombo() > this.score.getLongestCombo()){
            this.score.setLongestCombo(currentCombo);
        }

    }





}
