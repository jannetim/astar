/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astaralgorithm;

/**
 *
 * @author jannetim
 */
public interface PriorityQueue {
    boolean isEmpty();
    
    void add(Object o);
    
    Object removeMin();
}
