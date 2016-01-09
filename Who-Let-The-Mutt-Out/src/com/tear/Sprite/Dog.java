/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tear.Sprite;

import com.tear.Main.Game;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 *
 * @author tear
 */
public class Dog extends Sprite implements MSprite {

    private final Game game;
    private final Cat player;

    private Point2D enemyVelocity = new Point2D(0, 0);
    private double ACCLERATION = 1;
    private int acclerationCounter = 0, frameCounter = 0, frameSpeed = 11, dogWait = 0;

    private boolean running = false, canJump = false;
    private boolean animation1 = true, animation2 = false, animation3 = false, animation4 = false, animation5 = false, animation6 = false;

    public Dog(Game game, Cat player, double xPosition, double yPosition, Image... imageStates) {
        super(xPosition, yPosition, imageStates);

        this.spriteFrame.setTranslateX(xPosition);
        this.spriteFrame.setTranslateY(yPosition);

        this.game = game;
        this.player = player;
    }

    @Override
    public void Update() {
        setImageStates();
        setMovement();
        catchCat();
    }//Update

    private void setImageStates() {

        if (running) {
            if (animation1) {
                if (frameCounter >= frameSpeed) {
                    spriteFrame.setImage(imageStates.get(4));
                    animation1 = false;
                    animation2 = true;
                    frameCounter = 0;
                } else {
                    frameCounter++;
                }
            } else if (animation2) {
                if (frameCounter >= frameSpeed) {
                    spriteFrame.setImage(imageStates.get(3));
                    animation2 = false;
                    animation3 = true;
                    frameCounter = 0;
                } else {
                    frameCounter++;
                }
            } else if (animation3) {
                if (frameCounter >= frameSpeed) {
                    spriteFrame.setImage(imageStates.get(2));
                    animation3 = false;
                    animation4 = true;
                    frameCounter = 0;
                } else {
                    frameCounter++;
                }
            } else if (animation4) {
                if (frameCounter >= frameSpeed) {
                    spriteFrame.setImage(imageStates.get(1));
                    animation4 = false;
                    animation5 = true;
                    frameCounter = 0;
                } else {
                    frameCounter++;
                }
            } else if (animation5) {
                if (frameCounter >= frameSpeed) {
                    spriteFrame.setImage(imageStates.get(0));
                    animation5 = false;
                    animation1 = true;
                    frameCounter = 0;
                } else {
                    frameCounter++;
                }
            }
        }//running

    }//setImageStates

    private void setMovement() {
        if (running) {
            moveDogX(VELOCITY);
        }
        
        if (enemyVelocity.getY() < GRAVITY) {
            enemyVelocity = enemyVelocity.add(0, 1.4);
        }
        moveDogY(enemyVelocity.getY());
    }//setMovement

    private void moveDogX(double value) {
        boolean movingRight = value > 0;
        for (int i = 0; i < Math.abs(value) + ACCLERATION; i++) {
            acclerate(value);
            for (Sprite castDirector : game.castDirector.getCurrentCast()) {
                if (movingRight) {
                    if (spriteFrame.getBoundsInParent().intersects(castDirector.spriteFrame.getBoundsInParent())) {
                        if (spriteFrame.getTranslateX() + 80 == castDirector.spriteFrame.getTranslateX()) {
                            spriteFrame.setTranslateX(spriteFrame.getTranslateX() - 1);
                            jump();
                            return;
                        }
                    }
                } else {
                    if (spriteFrame.getTranslateX() == castDirector.spriteFrame.getTranslateX() + 64) {
                        spriteFrame.setTranslateX(spriteFrame.getTranslateX() + 1);
                        return;
                    }
                }
            }

            for (Sprite jumpDog : game.castDirector.getDogJump()) {
                if (spriteFrame.getBoundsInParent().intersects(jumpDog.spriteFrame.getBoundsInParent())) {
                    if (spriteFrame.getTranslateX() + 60 == jumpDog.spriteFrame.getTranslateX()) {
                        jump();
                    }
                }
            }//jumpDog
            spriteFrame.setTranslateX(spriteFrame.getTranslateX() + (movingRight ? 1 : -1));
        }//ML
    }//moveDogX

    private void moveDogY(double value) {
        boolean movingDown = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Sprite castDirector : game.castDirector.getCurrentCast()) {
                if (spriteFrame.getBoundsInParent().intersects(castDirector.spriteFrame.getBoundsInParent())) {
                    if (movingDown) {
                        if (spriteFrame.getTranslateY() + 40 == castDirector.spriteFrame.getTranslateY()) {
                            spriteFrame.setTranslateY(spriteFrame.getTranslateY() - 1);
                            canJump = true;
                            
                            if(dogWait >= 40) {
                            running = true;
                            dogWait = 0;
                            } else {
                                dogWait++;
                            }
                            return;
                        }
                    }
                }

            }

            for (Sprite jumpDog : game.castDirector.getDogJump()) {
                if (spriteFrame.getBoundsInParent().intersects(jumpDog.spriteFrame.getBoundsInParent())) {
                    if (spriteFrame.getTranslateY() + 40 == jumpDog.spriteFrame.getTranslateY()) {
                        jump();
                    }
                }
            }//jumpDog

            spriteFrame.setTranslateY(spriteFrame.getTranslateY() + (movingDown ? 1 : -1));
        }//ML
    }//moveDogY

    private void AI() {
        if (player.jumpX - 45 == spriteFrame.getTranslateX()) {
            jump();
        }
    }//AI

    private void catchCat() {
        if (spriteFrame.getTranslateX() + 80 >= player.getSpriteFrame().getTranslateX()) {
            gameOver();
        }
        if (game.spriteLoader.player.spriteFrame.getTranslateY() > Cat.BOTTOM_BOUNDARY) {
            gameOver();
        }
    } //catchCat

    private void jump() {
        if (canJump) {
            if (ACCLERATION > 4) {
                enemyVelocity = enemyVelocity.add(0, -35);
            } else {
                enemyVelocity = enemyVelocity.add(0, -30);
            }
            canJump = false;
        }
    }//Jump

    private void acclerate(double value) {

        if (acclerationCounter >= 120) {

            if (ACCLERATION <= 5) {
                ACCLERATION = ACCLERATION + Math.abs(value);
            }

            if (spriteFrame.getTranslateX() + 150 < game.spriteLoader.player.spriteFrame.getTranslateX()) {
                if (ACCLERATION <= 7) {
                    ACCLERATION = ACCLERATION + 2;
                }
            }

            if (spriteFrame.getTranslateX() + 150 >= game.spriteLoader.player.spriteFrame.getTranslateX()) {
                if (ACCLERATION > 5) {
                    ACCLERATION = 6;
                }
            }

            if (frameSpeed >= 7) {
                frameSpeed--;
            }
            acclerationCounter = 0;
        } else {
            acclerationCounter++;
        }
    }//acclerate

    public void gameOver() {
        running = false;
        game.spriteLoader.player.running = false;
        ACCLERATION = 1;
        game.spriteLoader.player.ACCLERATION = 1;
        game.gameLoop.stop();
        removeAllCoins();
        game.castDirector.resetDynamicCast();
        game.loadMap();
        game.score = 0;
        game.scoreLabel.setText(Integer.toString(game.score));
        game.spriteLoader.player.jumpX = 0;
        game.spriteLoader.enemy.spriteFrame.setTranslateX(0);
        game.spriteLoader.enemy.spriteFrame.setTranslateY(0);
        game.spriteLoader.player.spriteFrame.setTranslateX(280);
        game.spriteLoader.player.spriteFrame.setTranslateY(0);
        game.root.setTranslateX(0);
        game.gameLoop.start();
    }//gameOver
    
    private void removeAllCoins() {
        for(Sprite dynamicCast : game.castDirector.getDynamicCast()) {
            game.root.getChildren().remove(dynamicCast.getSpriteFrame());
        }
    }

}//Class
