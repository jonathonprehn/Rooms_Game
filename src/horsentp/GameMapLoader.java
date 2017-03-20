/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp;

import horsentp.graphics.ImageManager;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jonathon
 */
public class GameMapLoader {
      
    private static HashMap<String, GameMap> mapMap = new HashMap<>();
    
    public static void load(String srcLoc, GameMap map) {
        /*
        FORMAT OF THE TEXT FILE TO READ GAME MAPS FROM
        
        COMMENTS ARE WITH SEMICOLONS AT THE END OF THE LINE
        GRID TILES ARE DEFINED IN THE FOLLOWING FORMAT
        TILE <X> <Y> <ROOM_IMAGE> <VERTICAL_IMAGE> <HORIZONTAL_IMAGE>
        LIST OF OBJECTS IN IT...
        
        EACH OBJECT WILL HAVE THE FORMAT
        <TYPE> <NAME> <PARAMS>
        
        Object types:
            WEAPON, FOOD, KEY, DEFENSE, MONSTER
        
        WEAPON <NAME> <SPRITE>
        FOOD <NAME> <SPRITE>
        KEY <NAME> <SPRITE>
        DEFENSE <NAME> <SPRITE>
        MONSTER <NAME> <SPRITE>
        
        UP TO 9 ITEMS PER TILE
        
        THE FILE IS BROKEN UP INTO TOKENS FOR EASY READING
        */
        
        //Does it already exists?
        GameMap stored = mapMap.get(srcLoc);
        if (stored != null) {
            //System.out.println("Map already exists");
            map.roomFloor = stored.roomFloor;
            map.horizontal = stored.horizontal;
            map.vertical = stored.vertical;
            //Player movement handled by JumpPortal action
//            map.playerLocX = stored.playerLocX;
//            map.playerLocY = stored.playerLocY;
//            map.getMap()[map.playerLocX][map.playerLocY].addObject(map.player);
            map.setMap(stored.getMap());
            map.setWidth(stored.getWidth());
            map.setHeight(stored.getHeight());
            return;
        }
        
        
        String src = getSource(srcLoc);
        ArrayList<String> tokens = breakUpSource(src);
        int width = 0;
        int height = 0;
        
        
        try {
            width = Integer.parseInt(tokens.get(0));
            height = Integer.parseInt(tokens.get(1));
        } catch(NumberFormatException nfe ) {
            System.err.println("Cannot parse width and height");
        }
        
        BufferedImage roomImage = ImageManager.getManager().getImage(tokens.get(2));
        BufferedImage horizontalImage = ImageManager.getManager().getImage(tokens.get(3));
        BufferedImage verticalImage = ImageManager.getManager().getImage(tokens.get(4));
        
        GameTile[][] tileMap = new GameTile[width][height];
        
        GameTile tile = null;
        int curX = 0;
        int curY = 0;
        //NOW INTERPRET IT
        for (int i=5; i<tokens.size();
                    //Going to add things to i myself
                ) {
            
            String cur = tokens.get(i);
            
            if (cur.equals("TILE")) {
                if (tile != null) {
                    tileMap[curX][curY] = tile;
                }
            
                tile = new GameTile();
                try {
                    curX = Integer.parseInt(tokens.get(i+1));
                    curY = Integer.parseInt(tokens.get(i+2));
                    i += 3;
                } catch(NumberFormatException nfe) {
                    System.err.println("Could not parse tile location");
                }
            } else if (tile != null) {
                switch(cur) {
                    case "WEAPON":
                        tile.addObject(new GameObject(
                            tokens.get(i+1),GameObjectType.WEAPON,
                            tokens.get(i+2)
                        ));
                        i += 3;
                        break;
                    case "FOOD":
                        tile.addObject(new GameObject(
                            tokens.get(i+1),GameObjectType.FOOD,
                            tokens.get(i+2)
                        ));
                        i += 3;
                        break;
                    case "KEY":
                        tile.addObject(new GameObject(
                            tokens.get(i+1),GameObjectType.KEY,
                            tokens.get(i+2)
                        ));
                        i += 3;
                        break;
                    case "ARMOR":
                        tile.addObject(new GameObject(
                            tokens.get(i+1),GameObjectType.ARMOR,
                            tokens.get(i+2)
                        ));
                        i += 3;
                        break;
                    case "MONSTER":
                        tile.addObject(new GameObject(
                            tokens.get(i+1),GameObjectType.MONSTER,
                            tokens.get(i+2)
                        ));
                        i += 3;
                        break;
                    case "PLAYER":
                        tile.addObject(map.player);
                        //its the player!
                        map.playerLocX = curX;
                        map.playerLocY = curY;
                        i++;
                        break;
                    case "MAGIC_BOX":
                        try {
                            tile.addObject(new MagicBoxGameObject(
                                tokens.get(i+1),GameObjectType.MAGIC_BOX,
                                tokens.get(i+2), tokens.get(i+3),
                                Integer.parseInt(tokens.get(i+4)),
                                Integer.parseInt(tokens.get(i+5))
                            ));
                        } catch(NumberFormatException nfe) {
                            System.err.println("" + nfe + " with " + cur);
                        }
                        i += 6;
                        break;
                    case "PORTAL":
                        try {
                            tile.addObject(new PortalGameObject(
                                tokens.get(i+1),GameObjectType.PORTAL,
                                tokens.get(i+2), tokens.get(i+3),
                                Integer.parseInt(tokens.get(i+4)),
                                Integer.parseInt(tokens.get(i+5))
                            ));
                        } catch(NumberFormatException nfe) {
                            System.err.println("" + nfe + " with " + cur);
                        }
                        i += 6;
                        break;
                    case "PLAQUE":
                        tile.addObject(new GameObject(
                            tokens.get(i+1),GameObjectType.PLAQUE,
                            tokens.get(i+2)
                        ));
                        i += 3;
                        break;
                    case "CRYSTAL":
                        tile.addObject(new GameObject(
                            tokens.get(i+1),GameObjectType.CRYSTAL,
                            tokens.get(i+2)
                        ));
                        i += 3;
                        break;
                    default:
                    System.err.println("Could not recognize map token: " + cur);
                    i++;  
                }
            } else {
                System.err.println("Could not recognize map token: " + cur);
                i++;
            }
        }
        if (tile != null) {
            tileMap[curX][curY] = tile;
        }
        
        map.roomFloor = roomImage;
        map.horizontal = horizontalImage;
        map.vertical = verticalImage;
        map.setMap(tileMap);
        map.setHeight(height);
        map.setWidth(width);
        
        //Save it so that it does not have to be reloaded again and
        //you can keep track of the values
        GameMap storing = new GameMap();
        storing.roomFloor = roomImage;
        storing.horizontal = horizontalImage;
        storing.vertical = verticalImage;
        storing.playerLocX = map.playerLocX;
        storing.playerLocY = map.playerLocY;
        storing.setMap(tileMap);
        storing.setHeight(height);
        storing.setWidth(width);
        mapMap.put(srcLoc, storing);
    }
    
    /**
     * Breaks the source code into tokens
     * @param src
     * @return 
     */
    private static ArrayList<String> breakUpSource(String src) {
        ArrayList<String> list = new ArrayList<>();
        String buf = "";
        char[] arr = src.toCharArray();
        int cur = 0;
        while(cur < arr.length) {
            char c = arr[cur];
            if (c == ' ') {
                if (!buf.equals("")) {
                    list.add(buf);
                    buf = "";
                }
            } else {
                buf += c;
            }
            cur++;
        }
        if (!buf.equals("")) {
            list.add(buf);
        }
        return list;
    }
    
    /**
     * Loads the source file and chips off comments
     * @param src the source file location
     * @return the text in the source file with no comments
     */
    private static String getSource(String src) {
        File sourceFile = new File(src);
        
        StringBuilder builder = new StringBuilder(1000);
        try {
            BufferedReader rdr = new BufferedReader(new InputStreamReader(GameMapLoader.class.getResourceAsStream("/" + src)));
            String line;
            while ( (line = rdr.readLine()) != null) {
                char[] arr = line.toCharArray();
                for (int i =0; i < arr.length && arr[i] != ';' && arr[i] != '\n' && arr[i] != '\r'; i++) {
                    builder.append(arr[i]);
                }
                builder.append(' ');
            }
        } catch(IOException ioe) {
            System.err.println("Error reading source code for file: " + ioe);
        }
        return builder.toString();
    }
}
