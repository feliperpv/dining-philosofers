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
        
        System.out.print("Digite o número de filosofos: ");
        Scanner entrada = new Scanner(System.in);
        
        int numeroFilosofos = entrada.nextInt();
        
        List<Filosofos> listaFilosofos = new ArrayList<>();
        List<Semaphore> listaSemaforos = new ArrayList<>();
        
        System.out.println("Iniciando Jantar...");
        
        for (int i = 0; i < numeroFilosofos ; i++){
            listaSemaforos.add(new Semaphore(1));
        }
        
        for (int i = 0; i < numeroFilosofos ; i++){
            
            if(i == numeroFilosofos - 1){
                listaFilosofos.add(new Filosofos(i, listaSemaforos.get(i),
                            listaSemaforos.get(0)));
            } else {
                listaFilosofos.add(new Filosofos(i, listaSemaforos.get(i),
                            listaSemaforos.get(i + 1)));
            }
            
            new Thread(listaFilosofos.get(i)).start();
        }
        
    }
    
}
