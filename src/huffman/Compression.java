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
public class Compression {
     Node root = new Node("" , 0 , 100);
    Node current = root;
    Tree t = new Tree();

    public String encode(String input)
    {
        for(int i=0 ; i<input.length() ; i++)
        {
            t.getNode(input.charAt(i) , root);
            Node get = t.get();
            t.updateTree(input.charAt(i) , get , root , true);
        }
        return t.getResult();
    }
}
