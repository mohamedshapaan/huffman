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
public class Decompression {

    String code = "";
    Node root = new Node("", 0, 100);
    Tree t = new Tree();
    boolean check = true;
    String Result = "";
    int num;

    public String decode(String input) {
        for (int i = 0; i < input.length(); i++) {
            // Short Code Table
            if (check) {
                while (!t.sc.contains(code)) {
                    code += input.charAt(i);
                    i += 1;
                }
                check = false;
                num = Integer.parseInt(code, 2);
                //System.out.println("Symbol : " + (char) num);
                t.getNode((char) num, root);
                Node get = t.get();
                t.updateTree((char) num, get, root, false);
                Result += (char) num;
            }
            //-------------
            code = "";
            boolean Break = false;
            t.recDe(root);

            while (!Break) {
                if (i != input.length()) {
                    code += input.charAt(i);
                    i += 1;
                }

                for (int j = 0; j < t.nodes.size(); j++) {
                    if (t.nodes.get(j).code.equals(code)) {
                        Break = true;
                    }
                }

            }
            char c = t.check(code, root);
            if (c == '~') {
                check = true;
            } else {
                t.getNode(c, root);
                Node get = t.get();
                t.updateTree(c, get, root, false);
                Result += c;
            }
            code = "";
            i -= 1;
            t.nodes.removeAll(t.nodes);
        }
        return Result;
    }

    public void print() {
        System.out.println(Result);
    }
}
