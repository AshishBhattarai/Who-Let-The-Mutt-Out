/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tear.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author tear
 */
public class MainMenu {

    private int currentChoice = 0;
    private final Label l1 = new Label();
    private final Label l2 = new Label();
    private final Label l3 = new Label();
    private final Label l4 = new Label();
    private final VBox menuBox = new VBox();
    private final Group menuRoot = new Group();
    private final Scene menuScene = new Scene(menuRoot, Game.WIDTH, Game.HEIGHT, Color.BLACK);;
    private final ImageView menuBG = new ImageView(new Image("/Resources/Background/menu1.png", 800, 600, true, false, true));
    private final ImageView holdImg = new ImageView(new Image("/Resources/Background/hold.gif", 800, 600, true, false, true));
    private final ImageView instructionImg = new ImageView(new Image("/Resources/Background/ins.gif", 800, 600, true, false, true));
    private final ImageView creditsImg = new ImageView(new Image("Resources/Background/credits.gif", 800, 600, true, false, true));
    
    private final ImageView levelComplete = new ImageView(new Image("Resources/Background/complete.gif", 800, 600, true, false, true));
    private final Game game;

    //Constructor
    public MainMenu(Game game) {

        this.game = game;

    }

    //Menu-1
    public void main() {

        l1.setText("Start Demo");
        setStyle(l1);
        l2.setText("Instruction");
        setStyle(l2);
        l3.setText("Credits");
        setStyle(l3);
        l4.setText("Exit");
        setStyle(l4);
        menuBox.getChildren().addAll(l1, l2, l3, l4);
        menuBox.setBackground(Background.EMPTY);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setTranslateX(330);
        menuBox.setTranslateY(250);
        menuRoot.getChildren().addAll(menuBG, menuBox);
        game.window.setScene(menuScene);
        setSceneEventHandling();
    }

    private void setStyle(Label label) {

        label.setStyle("-fx-text-fill: White; -fx-font-family: Ubuntu; -fx-font-size: 24px;");
        label.setPadding(new Insets(10, 10, 10, 10));
    }

    private void setSceneEventHandling() {

        menuScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    if (currentChoice > 1) {
                        currentChoice -= 1;
                    }
                    break;
                case DOWN:
                    if (currentChoice < 4) {
                        currentChoice += 1;
                    }
                    break;
                case ENTER:
                    if (currentChoice == 1) { startSelected();
                    }
                    if (currentChoice == 2) { instructionSelected();
                    }
                    if (currentChoice == 3) { creditSelected();
                    }
                    if (currentChoice == 4) {
                        System.exit(0);
                    }
            }
            if (currentChoice == 1) {
                l1.setStyle("-fx-text-fill: Aqua; -fx-font-family: Ubuntu; -fx-font-size: 24px");
                l2.setStyle("-fx-text-fill: White; -fx-font-family: Ubuntu; -fx-font-size: 24px");
                l3.setStyle("-fx-text-fill: White; -fx-font-family: Ubuntu; -fx-font-size: 24px");
                l4.setStyle("-fx-text-fill: White; -fx-font-family: Ubuntu; -fx-font-size: 24px");
            } else if (currentChoice == 2) {
                l2.setStyle("-fx-text-fill: Aqua; -fx-font-family: Ubuntu; -fx-font-size: 24px");
                l1.setStyle("-fx-text-fill: White; -fx-font-family: Ubuntu; -fx-font-size: 24px");
                l3.setStyle("-fx-text-fill: White; -fx-font-family: Ubuntu; -fx-font-size: 24px");
                l4.setStyle("-fx-text-fill: White; -fx-font-family: Ubuntu; -fx-font-size: 24px");
            } else if (currentChoice == 3) {
                l3.setStyle("-fx-text-fill: Aqua; -fx-font-family: Ubuntu; -fx-font-size: 24px");
                l1.setStyle("-fx-text-fill: White; -fx-font-family: Ubuntu; -fx-font-size: 24px");
                l2.setStyle("-fx-text-fill: White; -fx-font-family: Ubuntu; -fx-font-size: 24px");
                l4.setStyle("-fx-text-fill: White; -fx-font-family: Ubuntu; -fx-font-size: 24px");
            } else if (currentChoice == 4) {
                l4.setStyle("-fx-text-fill: Aqua; -fx-font-family: Ubuntu; -fx-font-size: 24px");
                l1.setStyle("-fx-text-fill: White; -fx-font-family: Ubuntu; -fx-font-size: 24px");
                l2.setStyle("-fx-text-fill: White; -fx-font-family: Ubuntu; -fx-font-size: 24px");
                l3.setStyle("-fx-text-fill: White; -fx-font-family: Ubuntu; -fx-font-size: 24px");
            }
        }); //Up/Down pressed
    } //createKeyHandeler

    private void startSelected() {
        menuRoot.getChildren().add(holdImg);
        game.window.setScene(menuScene);

        menuScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT) {
                game.window.setScene(game.scene);
                game.gameLoop.start();
            }
        });
    }
    
    private void instructionSelected() {
        menuRoot.getChildren().add(instructionImg);
        menuScene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ESCAPE) {
                menuRoot.getChildren().remove(instructionImg);
                setSceneEventHandling();
            }
        });
    }
    
    private void creditSelected() {
        menuRoot.getChildren().add(creditsImg);
        menuScene.setOnKeyPressed(e -> {
                if(e.getCode() == KeyCode.ESCAPE) {
                menuRoot.getChildren().remove(creditsImg);
                setSceneEventHandling();
            }
        });
    }
    
    public void gameComplete() {
        
        game.gameLoop.stop();
        menuRoot.getChildren().add(levelComplete);
                menuScene.setOnKeyPressed(e -> {
                if(e.getCode() == KeyCode.ESCAPE) {
                System.exit(0);
            }
        });
        game.window.setScene(menuScene);
    }
    
    
} //end of class


