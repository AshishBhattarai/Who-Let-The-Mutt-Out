/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tear.Sprite;

import javafx.scene.image.Image;

/**
 *
 * @author tear
 */
public class Texture extends Sprite {

    public Texture(double xPosition, double yPosition, Image image) {
        super(xPosition, yPosition, image);
        
        this.spriteFrame.setTranslateX(xPosition);
        this.spriteFrame.setTranslateY(yPosition);
    }
    
}
