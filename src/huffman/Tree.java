/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.ArrayList;

/**
 *
 * @author mohamed shaban
 */
public class Tree {

    ArrayList<Character> appeared = new ArrayList<Character>();
    ArrayList<String> sc = new ArrayList<String>();
    ArrayList<Node> nodes = new ArrayList<Node>();
    Node temp = new Node();
    String Result = "";

    public Node get() {
        return temp;
    }

    Tree() {
        for (int i = 0; i < 128; i++) {
            String code = Integer.toBinaryString(i);
            while (code.length() < 7) {
                code = '0' + code;
            }
            sc.add(code);
        }
    }

    public int getNode(char search, Node root) {
        if (appeared.contains(search)) {
            if (root != null) {
                if (root.symbol == search) {
                    temp = root;
                    return 0;
                }
                getNode(search, root.left);
                getNode(search, root.right);
            }
        } else {
            if (root != null) {
                if (root.count == 0) {
                    temp = root;
                    return 0;
                }
                getNode(search, root.left);
                getNode(search, root.right);
            }
        }
        return 0;
    }
//----------------------------------------------------------------------------------------------------------------------

    public void updateTree(char symbol, Node node, Node root, boolean CD) {
        Tree t = new Tree();
        if (!appeared.contains(symbol)) {
            int index = symbol;

            if (CD) {
                Result += node.code + sc.get(index);
            }

            // NYT
            Node temp = node;
            node.count = 1;
            node.symbol = '*';
            node.right = new Node();
            node.left = new Node();
            while (temp.number != 100) {
                temp = temp.parent;
                temp.count += 1;
            }

            // Right Node
            node.right.symbol = symbol;
            node.right.count = 1;
            node.right.parent = node;
            node.right.number = node.right.parent.number - 1;
            node.right.code = node.right.parent.code + "1";

            // Left Node
            node.left.symbol = '~';
            node.left.parent = node;
            node.left.code = node.left.parent.code + "0";
            node.left.number = node.right.number - 1;
            node.left.count = 0;

            appeared.add(symbol);
        } else {
            if (CD) {
                Result += node.code;
            }
            node.count += 1;
            t.rec(root);
            boolean check = t.swapV();

            while (node.number != 100) {
                node = node.parent;
                node.count += 1;
            }

        }
        t.swapH(root);
    }

    public void swapH(Node root) {
        if (root.left.count > root.right.count) {
            // Swap
            Node temp = root.right.parent.left;
            root.right.parent.left = root.right.parent.right;
            root.right.parent.right = temp;

            // Handel Number
            int number;
            number = root.left.number;
            root.left.number = root.right.number;
            root.right.number = number;

            //Update Code
            handleCode(root);
        }
        if (root.left.symbol == '*') {
            swapH(root.left);
        } else if (root.right.symbol == '*') {
            swapH(root.right);
        }
    }

    public boolean swapV() {
        boolean firstRight, secondRight;
        firstRight = secondRight = false;
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (i != j) {
                    if (nodes.get(i).count > nodes.get(j).count && nodes.get(i).number < nodes.get(j).number) {
                        //Check Right or Left
                        if (nodes.get(i) == nodes.get(i).parent.right) {
                            firstRight = true;
                        }
                        if (nodes.get(j) == nodes.get(j).parent.right) {
                            secondRight = true;
                        }

                        //Swap
                        if (firstRight) {
                            nodes.get(i).parent.right = nodes.get(j);
                        } else {
                            nodes.get(i).parent.left = nodes.get(j);
                        }
                        if (secondRight) {
                            nodes.get(j).parent.right = nodes.get(i);
                        } else {
                            nodes.get(j).parent.left = nodes.get(i);
                        }

                        //Handel Parent
                        Node firstParent, secondParent;
                        firstParent = nodes.get(i).parent;
                        secondParent = nodes.get(j).parent;
                        nodes.get(i).parent = secondParent;
                        nodes.get(j).parent = firstParent;

                        //Handel Number
                        int number;
                        number = nodes.get(i).number;
                        nodes.get(i).number = nodes.get(j).number;
                        nodes.get(j).number = number;

                        // Handel Code
                        String copy = nodes.get(i).code;
                        nodes.get(i).code = nodes.get(j).code;
                        nodes.get(j).code = copy;

                        //Remove All nodes
                        nodes.removeAll(nodes);
                        return true;
                    }
                }
            }
        }
        nodes.removeAll(nodes);
        return false;
    }

    public void rec(Node root) {
        if (root != null) {
            if (root.symbol != '*' && root.symbol != '~') {
                nodes.add(root);
            }
            rec(root.left);
            rec(root.right);
        }
    }

    public void recDe(Node root) {
        if (root == null) {
            return;
        }
        if (root.symbol != '*') {
            nodes.add(root);
        }
        recDe(root.left);
        recDe(root.right);

    }

    public char check(String code, Node root) {
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '0') {
                root = root.left;
            } else if (code.charAt(i) == '1') {
                root = root.right;
            }
        }
        return root.symbol;
    }

    public void handleCode(Node current) {
        current.left.code = current.code + "0";
        current.right.code = current.code + "1";
        if (current.left.symbol == '*') {
            handleCode(current.left);
        } else if (current.right.symbol == '*') {
            handleCode(current.right);
        }
    }
//----------------------------------------------------------------------------------------------------------------------

    public void print() {
        System.out.println(Result);
    }

    public String getResult() {
        return Result;
    }

}
