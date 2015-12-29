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
class NodeNotFoundException extends Exception {
    public NodeNotFoundException(String message) {
        super ("Node can't be found in " + message);
    }
}
