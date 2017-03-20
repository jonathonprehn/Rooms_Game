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
import horsentp.GameMapLoader;
import horsentp.GameObjectType;
import horsentp.PortalGameObject;
import horsentp.ValuesMap;

/**
 *
 * @author Jonathon
 */
public class JumpPortal extends GameAction {
    
    public JumpPortal() {
        super("JUMP THRU PORTAL");
    }

    @Override
    public void doAction(ActionMap am, CombatMap cm, GameMap gm, ValuesMap vm) {
        PortalGameObject portal = (PortalGameObject)gm.getPlayerTile().getObject(GameObjectType.PORTAL);
        gm.getPlayerTile().removeObject(gm.player);
        GameMapLoader.load(portal.getPortalLoc(), gm);
        gm.playerLocX = portal.getPortalX();
        gm.playerLocY = portal.getPortalY();
        if (gm.getPlayerTile().getObject(GameObjectType.PLAYER) == null) {
            gm.getPlayerTile().addObject(gm.player);
        }
        cm.log("JUMPED THRU PORTAL");
        am.refreshActions(gm, cm, vm);
    }
    
    
}
