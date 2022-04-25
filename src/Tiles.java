package tiles;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.*;

/**
 * Tiles class extends Application for javafx,
 * contains main method. Produces the GUI
 * used for a Tiles game.
 * @author Sam Cox
 * @version date 1/31/20
 */
public class Tiles extends Application {

    private static Map<Rectangle, Tile> boardGui = new HashMap<>();
    private static Board board = new Board();

    /**
     * Main method starts program.
     * @param args command line args
     */
    public static void main(String[] args){


        launch(args);

    }

    /**
     * Starts the GUI and handles user input.
     * Creates a grid of rectangles with 4
     * circles inside each rectangle. Changes
     * the state of the game based on user input.
     * @param stage where everything is displayed
     */
    @Override
    public void start(Stage stage){

        int rectStroke = 2;
        int arcWeight = 20;
        int sceneWidth = 700;
        int sceneHeight = 510;
        int rectSize = 100;
        int circleSize = 50;
        int scoreHolderX = 450;
        int scoreHolderY = 200;
        int scoreHolderWidth = 200;
        int scoreHolderHeight = 100;
        int tileSize = 4;
        int textOffset = 10;

        List<Tile> posMatches = new LinkedList<>();
        List<Rectangle> prevRects = new LinkedList<>();
        FacePiece nullCir = new FacePiece("NULL", 0);
        Group group = new Group();
        Scene scene = new Scene(group, sceneWidth,sceneHeight,
                                            Color.DARKTURQUOISE);
        List<Rectangle> rects = new LinkedList<>();
        List<Circle> circles = new LinkedList<>();
        Rectangle scoreHolder = new Rectangle(scoreHolderX,scoreHolderY,
                                    scoreHolderWidth,scoreHolderHeight);
        scoreHolder.setFill(Color.TRANSPARENT);
        Text currentCombo = new Text(scoreHolderX + textOffset,scoreHolderY
                + textOffset, "Current Combo: " +
                board.getScore().getCurrentCombo());
        Text longestCombo = new Text(scoreHolderX + textOffset, scoreHolderY
                + textOffset * 3, "Longest Combo: " +
                board.getScore().getLongestCombo());

        currentCombo.setFont(new Font(arcWeight));
        longestCombo.setFont(new Font(arcWeight));
        group.getChildren().add(longestCombo);
        group.getChildren().add(currentCombo);
        group.getChildren().add(scoreHolder);

        for(int i = 0; i < board.getRows(); i++){
            for(int j = 0; j < board.getCols(); j++){
                Rectangle rect = new Rectangle(rectSize*j + (2*j),
                                      rectSize*i + (2*i), rectSize,rectSize);
                boardGui.put(rect, board.getTile(j,i));
                rect.setFill(Color.TRANSPARENT);
                rect.setStroke(Color.BLACK);
                rect.setStrokeWidth(rectStroke);
                rect.setArcWidth(arcWeight);
                rect.setArcHeight(arcWeight);
                rect.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
                    Tile tile = boardGui.get(rect);
                    Tile tileB = board.getTile(tile.getX(),tile.getY());
                    boolean removed = false;
                    boolean clickedClear = true;
                    boolean zeroed = false;

                    if(posMatches.size() == 0){
                        zeroed = true;
                    }

                    if(!tileB.getMarked() && !tileB.isClear()){
                        clickedClear = false;
                        posMatches.add(tileB);
                        tileB.toggleMarked();
                        prevRects.add(rect);
                    }
                    if(posMatches.size() == 2){
                        Tile tileOne = posMatches.get(0);
                        Tile tileTwo = posMatches.get(1);
                        if(tileOne.equals(tileTwo)){
                            posMatches.clear();
                            return;
                        }
                        for(int check = 0; check < tileSize; check++){
                            if(check >= tileOne.getTileFace().size() ||
                               check >= tileTwo.getTileFace().size()){
                                break;
                            }
                            FacePiece checkOne =
                                    tileOne.getTileFace().get(check);
                            FacePiece checkTwo =
                                    tileTwo.getTileFace().
                                            get(check);
                            if(checkOne.getPieceColor() == null ||
                               checkTwo.getPieceColor() == null){
                                continue;
                            }
                            if(checkOne.equals(checkTwo) && !checkOne.equals(nullCir)){
                                removed = true;
                                int idxCheckOne = tileOne.
                                        getTileFace().indexOf(checkOne);
                                int idxCheckTwo = tileTwo.
                                        getTileFace().indexOf(checkTwo);

                                int tileIdx1 = board.getBoard().
                                        indexOf(tileOne);
                                int tileIdx2 = board.getBoard().
                                        indexOf(tileTwo);
                                circles.get((tileIdx1 * tileSize) +
                                       idxCheckOne).setFill(Color.TRANSPARENT);
                                circles.get((tileIdx1 * tileSize) +
                                    idxCheckOne).setStroke(Color.TRANSPARENT);
                                circles.get((tileIdx2 * tileSize) +
                                       idxCheckTwo).setFill(Color.TRANSPARENT);
                                circles.get((tileIdx2 * tileSize) +
                                     idxCheckTwo).setStroke(Color.TRANSPARENT);
                                tileOne.removePic(checkOne);
                                tileTwo.removePic(checkTwo);
                            }
                        }

                        tileOne.toggleMarked();
                        if(tileTwo.isClear() || !removed){
                            tileTwo.toggleMarked();
                            prevRects.get(1).setStroke(Color.BLACK);
                            prevRects.remove(prevRects.get(1));
                            posMatches.remove(tileTwo);
                        }
                        prevRects.get(0).setStroke(Color.BLACK);
                        prevRects.remove(prevRects.get(0));
                        posMatches.remove(tileOne);
                    }
                    if(removed){
                        board.addToCurrentScore();
                    }else if(!clickedClear && !zeroed){
                        board.resetCurrentCombo();
                    }
                    if(tileB.getMarked()){
                        rect.setStroke(Color.YELLOW);
                    }

                    currentCombo.setText("Current Combo: " +
                            board.getScore().getCurrentCombo());
                    longestCombo.setText("Longest Combo: " +
                            board.getScore().getLongestCombo());
                    if(board.checkGameOver()){
                        Text gameOver = new Text(scoreHolderX,
                                scoreHolderY+100,"Game Over");
                        gameOver.setFont(new Font(arcWeight));
                        group.getChildren().add(gameOver);
                    }
                });
                for(FacePiece piece : board.getTile(j,i).getTileFace()){
                    String color = piece.getPieceColor();
                    Color circleColor;
                    switch (color){
                        case "O":
                            circleColor = Color.DARKORANGE;
                            break;
                        case"G":
                            circleColor = Color.FORESTGREEN;
                            break;
                        case"P":
                            circleColor = Color.BLUEVIOLET;
                            break;
                        case"B":
                            circleColor = Color.BEIGE;
                            break;
                        case"R":
                            circleColor = Color.DARKRED;
                            break;
                        default:
                            circleColor = Color.GRAY;
                            break;
                    }
                    Circle circle = new Circle(circleSize + (rectSize * j) +
                                              (2*j), circleSize +
                                              (rectSize * i) + (2*i),
                            piece.getCircleSize(), circleColor);
                    circle.setStroke(Color.BLACK);
                    circles.add(circle);

                }
                rects.add(rect);
            }
        }
        group.getChildren().addAll(circles);
        group.getChildren().addAll(rects);
        stage.setTitle("Tiles");
        stage.setScene(scene);
        stage.show();
    }
}
