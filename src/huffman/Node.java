/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 *
 * @author mohamed shaban
 */
public class Node {

    Node left, right, parent;
    char symbol;
    String code;
    int number;
    int count = 0;

    Node() {
        right = left = parent = null;
    }

    //Root
    Node(String code, int count, int number) {
        right = left = parent = null;
        this.code = code;
        this.count = count;
        this.number = number;
    }
}
