/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jantarfilosofos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Felipe e Diogo Reis Pavan
 */
public class JantarFilosofos {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.print("Digite o número de filósofos: ");
        int numeroFilosofos = new Scanner(System.in).nextInt();
        
        List<Semaphore> listaHashi = new ArrayList<>();
        
        System.out.println("Iniciando Jantar...");
        
        for (int i = 0; i < numeroFilosofos ; i++){
            listaHashi.add(new Semaphore(1));
        }
        
        for (int i = 0; i < numeroFilosofos ; i++){
            
            if(i == numeroFilosofos - 1){
                new Thread(new Filosofo(i, listaHashi.get(i), listaHashi.get(0))).start();
            } else {
                new Thread(new Filosofo(i, listaHashi.get(i), listaHashi.get(i+1))).start();
            }
            
        }
        
    }
    
}
