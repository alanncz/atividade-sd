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

        int qtdaRequest = 1000;
        executar5Thread(qtdaRequest); 

    }
 
    public static void executar5Thread(int qtdaRequest){
        
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int k = 0; k < 10; k++)
            executorService.execute(new OperacaoClient(qtdaRequest));
        executorService.shutdown();
    }

}
