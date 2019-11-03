package com.lm.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        TaxGenerator taxer = new TaxGenerator();
        try {
            while ((line = br.readLine()) != null) {
                taxer.addProduct(line);
            }

            br.close();
            List<String> bill = taxer.generateBill();

            for (String billLine : bill) {
                System.out.println(billLine);
            }

        } catch (IOException | IllegalArgumentException ex) {
            System.err.println("There has been a problem reading the input: " + ex.getMessage());
        }
    }
}
