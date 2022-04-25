package tiles;
import java.util.*;
import java.util.List;

/**
 * Tile class contains methods and constructor
 * for Tile object. Used to hold circle pictures.
 * @author Sam Cox
 * @version date 1/30/20
 */
public class Tile {

    private boolean marked;
    private List<FacePiece> tileFace;
    private int xPosition;
    private int yPosition;

    /**
     * Tile constructor gives each tile a
     * list of face pieces and sets variables to
     * default values.
     */
    public Tile(){
        this.tileFace = new LinkedList<>();
        this.yPosition = 0;
        this.xPosition = 0;
        this.marked = false;
    }


    /**
     * Toggles the marked state of the tile.
     * Used when the tile is clicked on.
     */
    public void toggleMarked(){
        this.marked = !this.marked;
    }

    /**
     * Returns whether the tile is marked or not.
     * @return true or false
     */
    public boolean getMarked(){
        return this.marked;
    }

    /**
     * Returns the x value of the tile.
     * @return x value of tile.
     */
    public int getX(){

        return this.xPosition;
    }

    /**
     * Returns the y value of the tile.
     * @return y value of tile.
     */
    public int getY(){

        return this.yPosition;
    }

    /**
     * Adds a circle to the tile.
     * @param facePiece circle to be added.
     */
    public void addToPieceFace(FacePiece facePiece){
        this.tileFace.add(facePiece);
    }

    /**
     * Returns the circle of the given size.
     * @param size radius of circle
     * @return circle with given radius
     */
    public FacePiece getCircle(int size){
        for(FacePiece f : this.getTileFace()){
            if(f.getCircleSize() == size){
                return f;
            }
        }
        return null;
    }

    /**
     * Places a circle on the tile at the specified index.
     * @param face circle to be placed
     * @param index to be placed
     */
    public void setCircle(FacePiece face, int index){
        this.getTileFace().set(index,face);
    }

    /**
     * Checks if a tile has any remaining non "Null" circles.
     * @return true or false
     */
    public boolean isClear(){
        FacePiece nullPic = new FacePiece("NULL", 0);
        for(FacePiece f : this.tileFace){
            if(!f.equals(nullPic)){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a list of all of the circles on the tile.
     * @return list of circles
     */
    public List<FacePiece> getTileFace(){
        return this.tileFace;
    }

    /**
     * Sets the x value of the tile.
     * @param x value
     */
    public void setX(int x){
        this.xPosition = x;
    }

    /**
     * Sets the y value of the tile.
     * @param y value
     */
    public void setY(int y){
        this.yPosition = y;
    }

    /**
     * Replaces a non null circle with a "Null" circle.
     * @param charToRemove circle to remove
     */
    public void removePic(FacePiece charToRemove){
        FacePiece nullPic = new FacePiece("NULL",0);
        int idx = this.tileFace.indexOf(charToRemove);
        this.tileFace.set(idx,nullPic);
    }


}
