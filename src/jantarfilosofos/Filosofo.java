package jantarfilosofos;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Felipe e Diogo
 */
public class Filosofo implements Runnable {

    public int codigo;
    public Semaphore hashiDireito, hashiEsquerdo;
    public static Semaphore extra = new Semaphore(1);

    public Filosofo(int codigo, Semaphore hashiEsquerdo,
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
                            System.out.println(codigo + " POSSUI RECURSOS - LARGOU EXTRA");
                            
                            // Come
                            System.out.println(codigo + " COMENDO");
                            Thread.sleep(500);
                            
                            hashiDireito.release();
                            System.out.println(codigo + " LARGOU DIREITO");
                            
                            hashiEsquerdo.release();
                            System.out.println(codigo + " LARGOU ESQUERDO");
                            
                            System.out.println(codigo + " PENSANDO");
                            Thread.sleep(1000);
                        } else{
                            hashiDireito.release();
                            extra.release();
                            System.out.println(codigo + " PENSANDO - LARGOU EXTRA");
                            Thread.sleep(1000);
                        }
                    } else {
                        extra.release();
                        System.out.println(codigo + " PENSANDO - LARGOU EXTRA");
                        Thread.sleep(1000);
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
