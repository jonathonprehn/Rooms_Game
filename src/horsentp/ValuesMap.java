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
public class ValuesMap {
    
    private int weapons;
    private int armor;
    private int keys;
    private int health;
    private int crystals;
    
    public ValuesMap() {
        weapons = 0;
        armor = 0;
        keys = 0;
        crystals = 0;
        health = 10;
    }

    public int getCrystals() {
        return crystals;
    }

    public void setCrystals(int crystals) {
        this.crystals = crystals;
    }
    
    public void addCrystals(int crystals) {
        this.crystals += crystals;
    }

    public int getWeapons() {
        return weapons;
    }

    public void setWeapons(int weapons) {
        this.weapons = weapons;
    }
    
    public void addWeapons(int weapons) {
        this.weapons += weapons;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
    
    public void addArmor(int armor) {
        this.armor += armor;
    }

    public int getKeys() {
        return keys;
    }

    public void setKeys(int keys) {
        this.keys = keys;
    }
    
    public void addKeys(int keys) {
        this.keys += keys;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    public void addHealth(int health) {
        this.health += health;
    }
    
    
}
