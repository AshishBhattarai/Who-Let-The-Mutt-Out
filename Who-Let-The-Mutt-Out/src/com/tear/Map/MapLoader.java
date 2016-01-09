/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tear.Map;

import com.tear.Main.Game;
import com.tear.Sprite.Texture;
import javafx.scene.image.Image;

/**
 *
 * @author tear
 */
public class MapLoader {

    private final Game game;
    private final Image tile1 = new Image("Resources/Texture/Tiles/LvlDemo/1.gif", 64, 64, true, false, true);
    private final Image tile2 = new Image("Resources/Texture/Tiles/LvlDemo/2.gif", 64, 64, true, false, true);
    private final Image tile3 = new Image("Resources/Texture/Tiles/LvlDemo/3.gif", 64, 64, true, false, true);
    private final Image tile4 = new Image("Resources/Texture/Tiles/LvlDemo/4.gif", 64, 64, true, false, true);
    private final Image tile5 = new Image("Resources/Texture/Tiles/LvlDemo/5.gif", 64, 64, true, false, true);
    private final Image tile6 = new Image("Resources/Texture/Tiles/LvlDemo/pillar1.gif", 64, 64, true, false, true);
    private final Image tile7 = new Image("Resources/Texture/Tiles/LvlDemo/pillar2.gif", 64, 64, true, false, true);
    private final Image tile8 = new Image("Resources/Texture/Tiles/LvlDemo/pillar3.gif", 64, 64, true, false, true);
    private final Image tile9 = new Image("Resources/Texture/Tiles/LvlDemo/6.gif", 64, 64, true, false, true);
    private final Image coin = new Image("Resources/Texture/Coins/coin1.gif", 32, 32, true, false, true);
    private final Image collImg = new Image("Resources/Texture/coll.gif", 64, 64, true, false, true);
    public final int levelWidth = MapData.Level1[0].length() * 64;

    public MapLoader(Game game) {

        this.game = game;
    }

    public void loadMap() {

        for (int i = 0; i < MapData.Level1.length; i++) { //Colums
            String line = MapData.Level1[i]; //Rows

            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case '0':
                        break;
                    case '1':
                        createTile(j * 64, i * 64, tile1);
                        break;
                    case '2':
                        noCollTile(j * 64, i * 64, tile2);
                        break;
                    case '3':
                        createTile(j * 64, i * 64, tile3);
                        break;
                    case '4':
                        createCollide(j * 64, i * 64, collImg);
                        break;
                    case '5':
                        createTile(j * 64, i * 64, tile4);
                        break;
                    case '!':
                        createTile(j * 64, i * 64, tile9);
                        break;
                    case '6':
                        noCollTile(j * 64, i * 64, tile5);
                        break;
                    case '7':
                        noCollTile(j * 64, i * 64, tile6);
                        break;
                    case '8':
                        noCollTile(j * 64, i * 64, tile7);
                        break;
                    case '9':
                        noCollTile(j * 64, i * 64, tile8);
                        break;
                    case 'C':
                        loadCoin(j * 64, i * 68, coin);
                        break;
                }
            }
        }
    }

    private void createTile(double xPosition, double yPosition, Image image) {
        Texture texture = new Texture(xPosition, yPosition, image);
        game.root.getChildren().add(texture.getSpriteFrame());
        game.castDirector.addCurrentCast(texture);
    } //createTile

    private void noCollTile(double xPosition, double yPosition, Image image) {
        Texture texture = new Texture(xPosition, yPosition, image);
        game.root.getChildren().add(texture.getSpriteFrame());
    }

    private void createCollide(double xPostion, double yPosition, Image image) {
        Texture collide = new Texture(xPostion, yPosition, image);
        game.root.getChildren().add(collide.getSpriteFrame());
        game.castDirector.addDogJump(collide);
    }

    private void loadCoin(double xPosition, double yPostion, Image image) {
        Texture coin = new Texture(xPosition, yPostion, image);
        game.root.getChildren().add(coin.getSpriteFrame());
        game.castDirector.addDynamicCast(coin);
    }

}
