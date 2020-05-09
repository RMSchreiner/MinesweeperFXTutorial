package MinesweeperFX;


import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class MinesweeperFX extends Application { //this extends the Application functions from import

    private static final int TILE_SIZE = 40;   //have all been set to be used across the whole app as final int
    private static final int W = 400;     // W H TILES * 40 creates the correct size for the game board 
    private static final int H = 400;              

    private static final int X_TILES = 10;   //W / TILE_SIZE;
    private static final int Y_TILES = 10;        //H / TILE_SIZE;

    private Tile[][] grid = new Tile[X_TILES][Y_TILES];   //this is the grid of tiles
    private Scene scene;

    private Parent createContent() {                        //create content command connects with line 135 and is the board creator
        Pane root = new Pane();                             // pane is the background pane
        root.setStyle("-fx-background-color: saddlebrown;");
        root.setPrefSize(W, H);                             //This would be the user input for size of the background pane

        for (int y = 0; y < Y_TILES; y++) {                 // rows and columns
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = new Tile(x, y, Math.random() < 0.2);

                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }

        for (int y = 0; y < Y_TILES; y++) {                          //
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = grid[x][y];

                if (tile.hasBomb)
                    continue;

                long bombs = getNeighbors(tile).stream().filter(t -> t.hasBomb).count();

                if (bombs > 0)
                    tile.text.setText(String.valueOf(bombs));
            }
        }

        return root;  //returns the root game board
    }

    private List<Tile> getNeighbors(Tile tile) {
        List<Tile> neighbors = new ArrayList<>();

        // ttt
        // tXt
        // ttt

        int[] points = new int[] {
              -1, -1,
              -1, 0,
              -1, 1,
              0, -1,
              0, 1,
              1, -1,
              1, 0,
              1, 1
        };

        for (int i = 0; i < points.length; i++) {
            int dx = points[i];
            int dy = points[++i];

            int newX = tile.x + dx;
            int newY = tile.y + dy;

            if (newX >= 0 && newX < X_TILES
                    && newY >= 0 && newY < Y_TILES) {
                neighbors.add(grid[newX][newY]);
            }
        }

        return neighbors;
    }
    
//    Rectangle rec1 = new Rectangle(5, 5, 50, 40);
//    rec1.setFill(Color.RED);
//    rec1.setStroke(Color.GREEN);
//    rec1.setStrokeWidth(3);
    
    

    private class Tile extends StackPane {  //extends the StackPane functionality from import
        private int x, y;
        private boolean hasBomb;
        private boolean isOpen = false;
        private boolean hasFlag; //to add a flag right click on/off this could be its own method similar to has bomb below 

        private Rectangle border = new Rectangle(TILE_SIZE -3, TILE_SIZE - 3);
        private Text text = new Text();

        public Tile(int x, int y, boolean hasBomb) {
            this.x = x;
            this.y = y;
            this.hasBomb = hasBomb;

          //  Image truffle = new Image(getClass().getResourceAsStream("truffle.png"));
        	//button.setGraphic(new ImageView(okImage);     
           
            border.setStroke(Color.LIGHTGREEN);
            border.setFill(Color.GREEN);
            
            text.setFill(Color.TAN);
            text.setFont(Font.font(25));
            text.setText(hasBomb ? "@" : ""); 
            
            text.setVisible(true);

            getChildren().addAll(border, text);

            setTranslateX(x * TILE_SIZE);
            setTranslateY(y * TILE_SIZE);

            setOnMouseClicked(e -> open());  //mouse click open to open fields
                                          
        }

        public void open() {   // we would set up in here a right click option for flag
            if (isOpen)        // this open method if the person clicked on a bomb or an open space
                return;

            if (hasBomb) {
               System.out.println("Game Over");
               scene.setRoot(createContent());
               return;
            }

            isOpen = true;
            text.setVisible(true);
            border.setFill(null);

            if (text.getText().isEmpty()) {
                getNeighbors(this).forEach(Tile::open);
            }
        }
    }                                                         //This bottom part is the running of the methods above

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(createContent());   //board maker

        stage.setScene(scene);      //setScene is the implementation of the panes open the Stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);     //this launches the application
    }
}