/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tear.Loader;

import javafx.scene.image.Image;

/**
 *
 * @author tear
 */
public class lImage {
    
    Image cIdel, cIdel2, cRun1, cRun2, cJump1, cJump2, cJump3;
    Image dRun1, dRun2, dRun3, dRun5, dRun6;
    public void loadCatImage() {
        
        cIdel = new Image("Resources/Cat/sit.png", 32, 32, true, false, true);
        cIdel2 = new Image("Resources/Cat/tail.png", 32, 32, true, false, true);
        cRun1 = new Image("Resources/Cat/run1.png", 32, 32, true, false, true);
        cRun2 = new Image("Resources/Cat/run2.png", 32, 32, true, false, true);
        cJump1 = new Image("Resources/Cat/jump1.png", 32, 32, true, false, true);
        cJump2 = new Image("Resources/Cat/jump2.png", 32, 32, true, false, true);
        cJump3 = new Image("Resources/Cat/jump3.png", 32, 32, true, false, true);
    }
    
    public void loadDogImage() {
        
        dRun1 = new Image("Resources/Dog/run1.gif", 80, 40, true, false, true);
        dRun2 = new Image("Resources/Dog/run2.gif", 80, 40, true, false, true);
        dRun3 = new Image("Resources/Dog/run3.gif", 80, 40, true, false, true);
        dRun5 = new Image("Resources/Dog/run5.gif", 80, 40, true, false, true);
        dRun6 = new Image("Resources/Dog/run6.gif", 80, 40, true, false, true);
    }
}
