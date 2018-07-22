/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alann.ifpb;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author alann
 */
public class Server {

    private static int cont = 0;

    public static void main(String args[]) throws Exception {

        DatagramSocket serverSocket = new DatagramSocket(9876);

        while (true) {

            int tamanho = Integer.BYTES * 2 + Character.BYTES;
            byte[] receiveData = new byte[tamanho];

            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);
            serverSocket.receive(receivePacket);

            //server5Threads(receivePacket, serverSocket); //metodo pedido na questao
            server1Thread(receivePacket, serverSocket); // metodo usado para melhoria 

        }
    }

    public static void server5Threads(DatagramPacket receivePacket, DatagramSocket serverSocket) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.execute(new OperacaoServer(receivePacket, serverSocket));

    }
    
    public static void server1Thread(DatagramPacket receivePacket, DatagramSocket serverSocket){
        
        Thread t = new Thread(new OperacaoServer(receivePacket, serverSocket));
        t.start();
    }

}
