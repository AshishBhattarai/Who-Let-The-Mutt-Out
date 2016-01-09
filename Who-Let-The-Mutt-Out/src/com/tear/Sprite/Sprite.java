/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tear.Sprite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.SVGPath;

/**
 *
 * @author tear
 */
public abstract class Sprite {

    protected List<Image> imageStates = new ArrayList<>();
    protected ImageView spriteFrame = new ImageView();
    protected double xPosition;
    protected double yposition;
    protected boolean isFixed;
    protected boolean isAlive;

    Sprite(double xPosition, double yPosition, Image image) {
        this.xPosition = xPosition;
        this.yposition = yPosition;
        this.spriteFrame.setImage(image);
    }

    Sprite(double xPosition, double yPosition, Image... imageStates) {
        this.xPosition = xPosition;
        this.yposition = yPosition;
        this.imageStates = Arrays.asList(imageStates);
        this.spriteFrame.setImage(imageStates[0]);
    }

    public ImageView getSpriteFrame() {
        return spriteFrame;
    }

    public double getxPosition() {
        return xPosition;
    }

    public double getYposition() {
        return yposition;
    }

    public boolean isIsFixed() {
        return isFixed;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

}
