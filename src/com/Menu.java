/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Menu<E> {

    public int int_getChoice(ArrayList<E> options) {
        int reponse;
        int numChoice = options.size();
        for (int index = 0; index < numChoice; index++) {
            System.out.println(index + 1 + ". " + options.get(index));
        }
        reponse = Validator.inputInt(1, numChoice);
        return reponse;
    }

    public E ref_getChoice(ArrayList<E> options) {
        int reponse;
        int numChoice = options.size();
        do {
            reponse = int_getChoice(options);
        } while (reponse < 0 || reponse > numChoice);
        return options.get(reponse - 1);
    }
}

