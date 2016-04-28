/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astaralgorithm;
import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author jannetim
 */
public class MapHandler {
    /**
     * Map for node-specific information
     */
    private Node[][] nodemap;
    /**
     * char-map in correlation with nodemap. Used for visual representation only.
     */
    private char[][] charmap;
    /**
     * Map width stored for further use
     */
    int width;
    /**
     * Map height stored for further use
     */
    int height;
    /**
     * Whether blocked nodes are used or not
     */
    boolean blocks;
    
    /**
     * Generates new map nodes for every map location
     * @param width
     * @param height
     * @param blocks
     * @return 
     */
    public Node[][] populateMap(int width, int height, boolean blocks) {
        this.blocks = blocks;
        this.width = width;
        this.height = height;
        nodemap = new Node[height][width];
        charmap = new char[height][width];
        for (int i = 0; i < nodemap.length; i++) {
            for (int j = 0; j < nodemap[i].length; j++) {
                nodemap[i][j] = new Node(j, i, null);
            }
        }
        generateBlockedNodes();
        return nodemap;
    }

    /**
     * Reverses traversing order from target node to start to form the path that's been traversed
     * @param target Reverse-travel to start node starts from this goal node
     */
    public void workThePath(Node target) {
        while (target != null) {
            charmap[target.y][target.x] = 'x';
            target = target.parent;
        }
        for (int i = 0; i < charmap.length; i++) {
            System.out.println("");
            for (int j = 0; j < charmap[i].length; j++) {
                System.out.print(charmap[i][j]);
            }
        }
    }

    /**
     * Generates blocked nodes that can't be traversed.
     * Method set to create a pillar of blocks and then some random blocks
     */
    public void generateBlockedNodes() {
        if (blocks) {
            for (int i = 0; i < 9; i++) {
                charmap[i][width / 5] = 'B';
                nodemap[i][width / 5].setBlocked();
            }
            
            for (int i = 0; i < 15; i++) {
                charmap[ThreadLocalRandom.current().nextInt(0, height-1)][ThreadLocalRandom.current().nextInt(0, width-1)] = 'B';
                nodemap[ThreadLocalRandom.current().nextInt(0, height-1)][ThreadLocalRandom.current().nextInt(0, width-1)].setBlocked();                
            }
        }
    }
}
