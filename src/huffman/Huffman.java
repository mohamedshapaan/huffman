/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author mohamed shaban
 */
public class Huffman {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
      String read , input = "";
        int choose;
        Scanner cin = new Scanner(System.in);

        Compression c = new Compression();
        Decompression d = new Decompression();
        Tree t = new Tree();

        File file = new File("C:\\Users\\mohamed shaban\\Desktop\\file1.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\mohamed shaban\\Desktop\\file2.txt"));

        while ((read = br.readLine()) != null){input += read;}

        System.out.println("1- Compression");
        System.out.println("2- Decompression");
        choose = cin.nextInt();
        if(choose == 1)
        {
            String result = c.encode(input);
            System.out.println("Compression code : " + result);
            writer.write(result);
            writer.close();
        }
        else
        {
            String result = d.decode(input);
            System.out.println("Decompression code : " + result);
            writer.write(result);
            writer.close();
        }
    }
    
}
