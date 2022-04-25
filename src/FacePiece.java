package tiles;

/**
 * FacePiece class contains methods and
 * constructor for FacePiece object.
 * Used for each individual picture
 * on a tile.
 * @author Sam Cox
 * @version date 1/30/20
 */
public class FacePiece {
    private String pieceColor;
    private int circleSize;

    /**
     * Constructor for face piece.
     *
     * @param color of the circle as string
     * @param size radius of the circle
     */
    public FacePiece(String color, int size){
        this.pieceColor = color;
        this.circleSize = size;
    }

    /**
     * Returns the color of the circle.
     * @return color of circle as string.
     */
    public String getPieceColor(){
        return this.pieceColor;
    }

    /**
     * Returns the radius of the circle.
     * @return radius of circle.
     */
    public int getCircleSize(){
        return this.circleSize;
    }

    /**
     * Checks the equality of two circles.
     * Equal if the color and radius are the same.
     * @param piece circle being checked.
     * @return true or false
     */
    public boolean equals(FacePiece piece){
        return this.pieceColor.equals(piece.getPieceColor()) &&
                this.circleSize == piece.getCircleSize();
    }

}