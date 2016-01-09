/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tear.Sprite;

import com.tear.Main.Game;
import java.util.Arrays;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/**
 *
 * @author tear
 */
public class Cat extends Sprite implements MSprite {

    public static final int TOP_BOUNDARY = 0;
    public static final int LEFT_BOUNDARY = 0;
    public static int RIGHT_BOUNDARY;
    public static final int BOTTOM_BOUNDARY = 600;

    public double jumpX;

    private final Game game;
    private int frameCounter = 0, acclerationCounter = 0, frameSpeed = 12;
    private Point2D playerVelocity = new Point2D(0, 0);
    private boolean canJump = false, goingDown = false, animation = false;
    int ACCLERATION = 2;
    boolean running = false;
    
    public Cat(Game game, double xPosition, double yPosition, Image... imageStates) {
        super(xPosition, yPosition, imageStates);

        this.spriteFrame.setTranslateX(xPosition);
        this.spriteFrame.setTranslateY(yPosition);
        this.game = game;
        RIGHT_BOUNDARY = game.levelWidth;
    }

    @Override
    public void Update() {
        setImageStates();
        setMovement();
        setScroller();
    }

    private void setImageStates() {

        if (game.isKeyPressed(KeyCode.RIGHT) && !game.isKeyPressed(KeyCode.UP)) {
            if (!animation) {
                if (frameCounter >= frameSpeed) {
                    spriteFrame.setImage(imageStates.get(2));
                    spriteFrame.setScaleX(1);
                    animation = true;
                    frameCounter = 0;
                } else {
                    frameCounter++;
                }
            } else if (animation) {
                if (frameCounter >= frameSpeed) {
                    spriteFrame.setImage(imageStates.get(3));
                    spriteFrame.setScaleX(1);
                    animation = false;
                    frameCounter = 0;
                } else {
                    frameCounter++;
                }
            }
        }//Right

        if (game.isKeyPressed(KeyCode.LEFT) && !game.isKeyPressed(KeyCode.UP)) {
            if (!animation) {
                if (frameCounter >= frameSpeed) {
                    spriteFrame.setImage(imageStates.get(2));
                    spriteFrame.setScaleX(-1);
                    animation = true;
                    frameCounter = 0;
                } else {
                    frameCounter++;
                }
            } else if (animation) {
                if (frameCounter >= frameSpeed) {
                    spriteFrame.setImage(imageStates.get(3));
                    spriteFrame.setScaleX(-1);
                    animation = false;
                    frameCounter = 0;
                } else {
                    frameCounter++;
                }
            }
        }//Left

        if (game.isKeyPressed(KeyCode.UP)) {
            spriteFrame.setImage(imageStates.get(4));

        }/*UP*/ else if (!game.isKeyPressed(KeyCode.LEFT) && !game.isKeyPressed(KeyCode.RIGHT)) {
            frameSpeed = 12;
            ACCLERATION = 1;
            if (!animation) {
                if (frameCounter >= frameSpeed) {
                    frameCounter = 0;
                    spriteFrame.setImage(imageStates.get(0));
                    animation = true;
                } else {
                    frameCounter++;
                }
            } else if (animation) {
                if (frameCounter >= frameSpeed) {
                    frameCounter = 0;
                    spriteFrame.setImage(imageStates.get(1));
                    animation = false;
                } else {
                    frameCounter++;
                }
            }
        } /*NoKeysPressed*/ else if (goingDown) {
            spriteFrame.setImage(imageStates.get(6));
            canJump = false;
        }
    }//setImageStates

    private void setMovement() {

        if (game.isKeyPressed(KeyCode.UP)) {
            jump();
        }
        if (game.isKeyPressed(KeyCode.LEFT)) {
            if (running) {
                moveCatX(-VELOCITY);
            }
        }
        if (game.isKeyPressed(KeyCode.RIGHT)) {
            if (running) {
                moveCatX(VELOCITY);
            }
        }
        if (playerVelocity.getY() < GRAVITY) {
            playerVelocity = playerVelocity.add(0, 1.4);
        }

        goingDown = true;
        moveCatY((int) playerVelocity.getY());

    }//setMovement

    private void moveCatX(int value) {
        boolean movingRight = value > 0;
        for (int i = 0; i < Math.abs(value) + ACCLERATION; i++) {
            accleration(value);
            for (Sprite currentCast : game.castDirector.getCurrentCast()) {
                if (spriteFrame.getBoundsInParent().intersects(currentCast.getSpriteFrame().getBoundsInParent())) {
                    if (movingRight) {
                        if (spriteFrame.getTranslateX() + 32 == currentCast.spriteFrame.getTranslateX()) {
                            spriteFrame.setTranslateX(spriteFrame.getTranslateX() - 1);
                            if (canJump) {
                                ACCLERATION = 1;
                                frameSpeed = 12;
                            }
                            return;
                        }
                    } else {
                        if (spriteFrame.getTranslateX() == currentCast.spriteFrame.getTranslateX() + 64) {
                            spriteFrame.setTranslateX(spriteFrame.getTranslateX() + 1);
                            return;
                        }
                    }
                }
            }

            if (spriteFrame.getTranslateX() <= LEFT_BOUNDARY) {
                spriteFrame.setTranslateX(LEFT_BOUNDARY);
            }

            if (spriteFrame.getTranslateX() >= game.levelWidth - 30) {
                spriteFrame.setTranslateX(game.levelWidth - 30);
                game.mainMenu.gameComplete();
            }
            spriteFrame.setTranslateX(spriteFrame.getTranslateX() + (movingRight ? 1 : -1));

        }//Main Loop
        
        upScore();
    } //MoveX

    private void moveCatY(double value) {
        boolean movingDown = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite currentCast : game.castDirector.getCurrentCast()) {
                if (spriteFrame.getBoundsInParent().intersects(currentCast.getSpriteFrame().getBoundsInParent())) {

                    if (movingDown) {
                        if ((spriteFrame.getTranslateY()) + 32 == currentCast.spriteFrame.getTranslateY()) {
                            spriteFrame.setTranslateY(spriteFrame.getTranslateY() - 1);
                            goingDown = false;
                            running = true;
                            canJump = true;
                            return;
                        }
                    }
                }
            }
            spriteFrame.setTranslateY(spriteFrame.getTranslateY() + (movingDown ? 1 : -1));
        }//MainLoop
    }//MoveY

    private void accleration(int value) {
        if (acclerationCounter >= 120) {
            if (ACCLERATION <= 5) {
                ACCLERATION = Math.abs(value) + ACCLERATION;
                acclerationCounter = 0;
                if (frameSpeed >= 8) {
                    frameSpeed--;
                }
            }
        } else {
            acclerationCounter++;
        }
    }//accleration

    private void jump() {
        if (canJump) {
            jumpX = spriteFrame.getTranslateX();
            if (ACCLERATION > 4) {
                playerVelocity = playerVelocity.add(0, -35);
            } else {
                playerVelocity = playerVelocity.add(0, -32);
            }
            canJump = false;
        }
    }//Jump

    private void upScore() {

        for (int i = 0; i < game.castDirector.getDynamicCast().size(); i++) {

            Sprite tempSprite = game.castDirector.getDynamicCast().get(i);
            if (colCoin(tempSprite)) {

                game.score += 1;
                game.scoreLabel.setText(Integer.toString(game.score));
                game.castDirector.addToRemoveSprites(tempSprite);
                game.root.getChildren().remove(tempSprite.getSpriteFrame());
                game.castDirector.resetRemovedSprites();
            }
        }

    }

    private boolean colCoin(Sprite tempSprite) {

        return spriteFrame.getBoundsInParent().intersects(tempSprite.getSpriteFrame().getBoundsInParent());
    } // colCoin
    
    
        
    

    private void setScroller() {
        spriteFrame.translateXProperty().addListener((v, oldValue, newValue) -> {

            int offSet = newValue.intValue();
            if (offSet > 400 && offSet < game.levelWidth - 400) {

                game.root.setTranslateX(-(offSet - 400));
            }
        });

    } //Scroller

}//Class
