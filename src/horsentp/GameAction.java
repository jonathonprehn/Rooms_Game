/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp;

/**
 *
 * @author Jonathon
 */
public abstract class GameAction {
    
    private String label;
    
    public GameAction(String label) {
        this.label = label;
    }
    
    public String getLabel() { return label; }
    
    public abstract void doAction(ActionMap am, CombatMap cm, GameMap gm, ValuesMap vm);
}
