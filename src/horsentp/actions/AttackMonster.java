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
public class AttackMonster extends GameAction {
    
    public AttackMonster() {
        super("ATTACK MONSTER");
    }

    @Override
    public void doAction(ActionMap am, CombatMap cm, GameMap gm, ValuesMap vm) {
        GameObject monster = gm.getPlayerTile().getObject(GameObjectType.MONSTER);
        gm.getPlayerTile().removeObject(monster);
        vm.addWeapons(-1);
        cm.log("ATTACKED " + monster.getName());
        am.refreshActions(gm, cm, vm);
    }
}
