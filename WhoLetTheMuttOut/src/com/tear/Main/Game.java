
package com.tear.Main;

import com.tear.Loader.Loader;
import com.tear.Map.MapLoader;
import java.util.HashMap;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 *
 * @author tear AKA Ashish Bhattarai
 * https://www.youtube.com/watch?v=jxhM52YDNcU
 */
public class Game extends Application {
    
    public static final int WIDTH = 800, HEIGHT = 600;
    public int levelWidth;
    
    Stage window;
    Scene scene;
    public Group root;
    private Group mainRoot;
    private final HashMap<KeyCode, Boolean> keys = new HashMap<>();
    public MainMenu mainMenu;
    
    public CastingDirector castDirector;
    public Loader spriteLoader;
    public GameLoop gameLoop;
    public int score = 0;
    public Label scoreLabel;
    MapLoader mapLoader;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    
        window = primaryStage;
        window.setTitle("Who Let The Mutt Out");
        root = new Group();
        mainRoot = new Group();
        ImageView backGround = new ImageView(new Image("/Resources/Background/b1.png"));
        ImageView coin = new ImageView(new Image("Resources/Texture/Coins/coin1.gif", 32, 32, true, false, true));
        scoreLabel = new Label(Integer.toString(score));
        mainRoot.getChildren().addAll(backGround, coin, scoreLabel, root);
        coin.setTranslateX(700);
        scoreLabel.setTranslateX(750);
        scoreLabel.setTranslateY(8);
        scene = new Scene(mainRoot, WIDTH, HEIGHT);
        window.getIcons().add(new Image("/Resources/Background/cat1.png"));
        window.setScene(scene);
        window.setResizable(false);
        window.show();
        
        setKeyMapper();
        loadMenu();
        loadMap();
        loadSprites();
        gameLoop();
    }
    //Maps scene keys with HashMap
    private void setKeyMapper() {
        
        scene.setOnKeyPressed(e -> keys.put(e.getCode(), true)); //ture if pressed
        scene.setOnKeyReleased(e -> keys.put(e.getCode(), false)); //false if relesed
    }
    
    private void loadMenu() {
        mainMenu = new MainMenu(this);
        mainMenu.main();
    }
    
    public void loadMap() {
        
        castDirector = new CastingDirector();
        
        mapLoader = new MapLoader(this);
        mapLoader.loadMap();
        levelWidth = mapLoader.levelWidth;
    }
    
    
    private void loadSprites() {

        spriteLoader = new Loader(this);
        spriteLoader.loadSprite();
    }
    
    private void gameLoop() {
        
        gameLoop = new GameLoop(spriteLoader.player, spriteLoader.enemy);
    }
    
    //returns true is the passed key(KeyCode.KEY) is true and false if its released, default value = false(returns false if nothing is pressed)
    public boolean isKeyPressed(KeyCode key) {
        
        return keys.getOrDefault(key, false);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
