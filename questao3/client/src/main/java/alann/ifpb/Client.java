/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alann.ifpb;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author alann
 */
public class Client {

    public static void main(String[] args) {

        int qtdaRequest = 10000;
        //executar10Threads(); metodo pedido na questao
        executar1Thread(qtdaRequest); //metodo usado para melhoria

    }
    
    public static void executar10Threads(){
           
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        
        for(int k = 0; k < 10; k++)
            executorService.execute(new OperacaoClient(10000));
        
        executorService.shutdown();
             
    }
    
    public static void executar1Thread(int qtdaRequest){
        
        Thread t = new Thread(new OperacaoClient(qtdaRequest));
        t.start();
    }

}
