/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.actions;

import horsentp.ActionMap;
import horsentp.CombatMap;
import horsentp.GameAction;
import horsentp.GameMap;
import horsentp.GameObject;
import horsentp.GameObjectType;
import horsentp.ValuesMap;

/**
 *
 * @author Jonathon
 */
public class CollectArmor extends GameAction {

    public CollectArmor() {
        super("COLLECT ARMOR");
    }

    @Override
    public void doAction(ActionMap am, CombatMap cm, GameMap gm, ValuesMap vm) {
        GameObject armor = gm.getPlayerTile().getObject(GameObjectType.ARMOR);
        vm.addArmor(1);
        gm.getPlayerTile().removeObject(armor);
        cm.log("COLLECTED " + armor.getName());
        am.refreshActions(gm, cm, vm);
    }
    
}
