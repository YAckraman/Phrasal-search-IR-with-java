/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phrasalsearch;

import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Unit {
    public int thekey=0;
    public ArrayList<Integer>value=new ArrayList<>();
    public Unit(int key,ArrayList<Integer>val){
        this.thekey=key;
        this.value=val;
    }
    
}
