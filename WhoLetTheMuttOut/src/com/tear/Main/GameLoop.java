/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tear.Main;

import com.tear.Sprite.Cat;
import com.tear.Sprite.Dog;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;

/**
 *
 * @author tear
 */
public class GameLoop extends AnimationTimer {
    
    public Timeline animation;
    Cat player;
    Dog enemy;
    
    GameLoop(Cat player, Dog enemy) {
    
        this.player = player;
        this.enemy = enemy;
    }
    
    @Override
    public void handle(long now) {
        
        animation = new Timeline(now);
        player.Update();
        enemy.Update();
    }
    
    @Override
    public void start() {
        super.start();
    }
    
    @Override
    public void stop() {
        super.stop();
    }
    
}
