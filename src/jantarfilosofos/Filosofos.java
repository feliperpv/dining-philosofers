package jantarfilosofos;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Felipe e Diogo
 */
public class Filosofos implements Runnable {

    public int codigo;
    public Semaphore hashiDireito, hashiEsquerdo;
    public static Semaphore extra = new Semaphore(1);
    public boolean comendo = false;

    public Filosofos(int codigo, Semaphore hashiEsquerdo,
            Semaphore hashiDireito) {

        this.codigo = codigo;
        this.hashiDireito = hashiDireito;
        this.hashiEsquerdo = hashiEsquerdo;

    }

    @Override
    public void run() {

        while (true) {
            try {
                
                //Pega extra
                if(extra.tryAcquire()){
                    System.out.println(codigo + " PEGOU EXTRA");
                    
                    //Pega hashi direito
                    if (hashiDireito.tryAcquire()){
                        System.out.println(codigo + " PEGOU DIREITO");
                        
                        //Pega hashi esquerdo
                        if(hashiEsquerdo.tryAcquire()){
                            System.out.println(codigo + " PEGOU ESQUERDO");
                            
                            extra.release();
                            System.out.println(codigo + " LARGOU EXTRA");
                            
                            // Come
                            System.out.println(codigo + " COMENDO");
                            Thread.sleep(400);
                            
                            hashiDireito.release();
                            System.out.println(codigo + " LARGOU DIREITO");
                            
                            hashiEsquerdo.release();
                            System.out.println(codigo + " LARGOU ESQUERDO");
                            
                            System.out.println(codigo + " PENSANDO");
                            Thread.sleep(500);
                        } else{
                            hashiDireito.release();
                            extra.release();
                            System.out.println(codigo + " PENSANDO");
                            Thread.sleep(300);
                        }
                    } else {
                        extra.release();
                        System.out.println(codigo + " PENSANDO");
                        Thread.sleep(300);
                    }
                } else {
                    System.out.println(codigo + " PENSANDO");
                    Thread.sleep(1000);
                    
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
