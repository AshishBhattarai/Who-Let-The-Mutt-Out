/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tear.Loader;

import com.tear.Main.Game;
import com.tear.Sprite.Cat;
import com.tear.Sprite.Dog;

/**
 *
 * @author tear
 */
public class Loader {
    
    private final Game game;
    public Cat player;
    public Dog enemy;
    private final lImage images = new lImage();
    
    
    public Loader(Game game) {
        this.game = game;
    }
    
    public void loadSprite() {
        //load Cat(player)
        images.loadCatImage();
        player = new Cat(game, 280, 0, images.cIdel, images.cIdel2, images.cRun1,  images.cRun2, images.cJump1, images.cJump2, images.cJump3);
        game.root.getChildren().add(player.getSpriteFrame());
        
        //load enemy(dog)
        images.loadDogImage();
        enemy = new Dog(game, player, 0, 0, images.dRun1, images.dRun2, images.dRun3, images.dRun5, images.dRun6);
        game.root.getChildren().add(enemy.getSpriteFrame());
    }
} 
