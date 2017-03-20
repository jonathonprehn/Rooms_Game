/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp;

import horsentp.actions.AttackMonster;
import horsentp.actions.CollectArmor;
import horsentp.actions.CollectCrystal;
import horsentp.actions.CollectKey;
import horsentp.actions.CollectWeapon;
import horsentp.actions.JumpPortal;
import horsentp.actions.MoveAction;
import horsentp.actions.UnlockBox;
import horsentp.actions.EatFood;
import horsentp.actions.ReadAction;
import java.util.ArrayList;

/**
 *
 * @author Jonathon
 */
public class ActionMap {
    
    private ArrayList<GameAction> actions = new ArrayList<>();
    private static final int MAX_ACTIONS = 16;
    public int mouseX;
    public int mouseY;
    
    public ActionMap() {
        
    }
    
    public void refreshActions(GameMap gm, CombatMap cm, ValuesMap vm) {
        //Do we get hurt by a monster?
        GameObject monster = gm.getPlayerTile().getObject(GameObjectType.MONSTER);
        if (monster != null) {
            
            cm.log("HIT BY " + monster.getName());
            //get hurt
            if (vm.getArmor() > 0) {
                vm.addArmor(-1);
                cm.log("LOST ARMOR");
            } else {
                vm.addHealth(-1);
                cm.log("LOST HEALTH");
            }
        }
        
        clearActions();
        
        //Movement
        loadAction(new MoveAction("MOVE UP", gm.playerLocX, gm.playerLocY-1));
        loadAction(new MoveAction("MOVE DOWN", gm.playerLocX, gm.playerLocY+1));
        loadAction(new MoveAction("MOVE LEFT", gm.playerLocX-1, gm.playerLocY));
        loadAction(new MoveAction("MOVE RIGHT", gm.playerLocX+1, gm.playerLocY));
        
        //Actions on the room
        if (gm.getPlayerTile().getObject(GameObjectType.MONSTER) != null) {
            //Monster there
            if (vm.getWeapons() > 0) {
                loadAction(new AttackMonster());
            }
        } else {
            //No monster
            if (gm.getPlayerTile().getObject(GameObjectType.WEAPON) != null) {
                loadAction(new CollectWeapon());
            }
            if (gm.getPlayerTile().getObject(GameObjectType.ARMOR) != null) {
                loadAction(new CollectArmor());
            }
            if (gm.getPlayerTile().getObject(GameObjectType.FOOD) != null) {
                loadAction(new EatFood());
            }

            if (gm.getPlayerTile().getObject(GameObjectType.KEY) != null) {
                loadAction(new CollectKey());
            }
            if (gm.getPlayerTile().getObject(GameObjectType.MAGIC_BOX) != null && 
                    vm.getKeys() > 0) {
                loadAction(new UnlockBox());
            }
            if (gm.getPlayerTile().getObject(GameObjectType.PORTAL) != null) {
                loadAction(new JumpPortal());
            }
            if (gm.getPlayerTile().getObject(GameObjectType.PLAQUE) != null) {
                loadAction(new ReadAction());
            }
            if (gm.getPlayerTile().getObject(GameObjectType.CRYSTAL) != null) {
                loadAction(new CollectCrystal());
            }
        }
    }
    
    public void loadAction(GameAction action) {
        actions.add(action);
    }
    
    public void clearActions() {
        actions.clear();
    }
    
    public int actionCount() {
        return actions.size();
    }
    
    public GameAction getAction(int ac) {
        return actions.get(ac);
    }
    
    //Does the processing work to see which button was pressed
    public GameAction getClickedAction(int x, int y) {
        for (int i=0; i<actions.size(); i++) {
            if (over(x, y, i)) {
                return actions.get(i);
            }
        }
        return null;
    }
    
    public boolean over(int mx, int my, int index) {
        int x = getCoordinateXFor(index);
        int y = getCoordinateYFor(index);
        return mx > x && mx < x+getButtonWidth() && 
                my > y && my < y+getButtonHeight();
    }
    
    //Get coordinate x based off of button index
    public int getCoordinateXFor(int index) {
        int col = index%2; //2 buttons per col
        //0, 4, 8, 12 all on column 1
        //1, 5, 9, 13 all on column 2
        //2, 6, 10, 14 all on column 3
        //3, 7, 11, 15 all on column 4
        return col*231 + 34;
    }
    
    //Get coordinate x based off of button index
    public int getCoordinateYFor(int index) {
        //0, 1, 2, 3 in row 1
        //4, 5, 6, 7 in row 2
        //8, 9, 10, 11 in row 3
        //12, 13, 14, 15 in row 4
        int row = 0;
        if (index-2 < 0) {
            row = 0;
        } else if (index-4 < 0) {
            row = 1;
        } else if (index-6 < 0) {
            row = 2;
        } else if (index-8 < 0) {
            row = 3;
        }
        return row*52 + 259 + 160;
    }
    
    //Get coordinate x based off of button index
    public int getButtonWidth() {
        return 200;
    }
    
    //Get coordinate x based off of button index
    public int getButtonHeight() {
        return 40;
    }
}
