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
public class CollectWeapon extends GameAction {

    public CollectWeapon() {
        super("COLLECT WEAPON");
    }

    @Override
    public void doAction(ActionMap am, CombatMap cm, GameMap gm, ValuesMap vm) {
        GameObject weap = gm.getPlayerTile().getObject(GameObjectType.WEAPON);
        vm.addWeapons(1);
        gm.getPlayerTile().removeObject(weap);
        cm.log("COLLECTED " + weap.getName());
        am.refreshActions(gm, cm, vm);
    }
}
