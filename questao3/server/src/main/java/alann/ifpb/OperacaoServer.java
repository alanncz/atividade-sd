/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alann.ifpb;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alann
 */
public class OperacaoServer implements Runnable {

    private final DatagramPacket receivePacket;
    private final DatagramSocket serverSocket;
    private static int cont = 0;

    public OperacaoServer(DatagramPacket receivePacket, DatagramSocket serverSocket) {
        this.receivePacket = receivePacket;
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {

        byte[] receiveData = receivePacket.getData();

        InetAddress IPAddress = receivePacket.getAddress();

        byte[] sendData = operacao(receiveData);

        int port = receivePacket.getPort();

        DatagramPacket sendPacket = new DatagramPacket(sendData,
                sendData.length, IPAddress, port);

        try {
            serverSocket.send(sendPacket);
            
        } catch (IOException ex) {
            Logger.getLogger(OperacaoServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static byte[] operacao(byte[] receiveData) {

        ByteBuffer bff = ByteBuffer.wrap(receiveData);

        int valor1 = bff.getInt();
        int valor2 = bff.getInt();
        char operacao = bff.getChar();
        
        cont ++;
        System.out.println(cont + " " + valor1 + "-" + valor2 + "-" + operacao);

        int resultadoOperacao;

        if (operacao == '+') {
            resultadoOperacao = soma(valor1, valor2);
        } else {
            resultadoOperacao = subtracao(valor1, valor2);
        }

        int tamanho = Integer.BYTES;

        ByteBuffer bff2 = ByteBuffer.allocate(tamanho);

        bff2.putInt(resultadoOperacao);

        byte[] sendData = bff2.array();

        return sendData;
    }

    public static int soma(int valor1, int valor2) {

        return valor1 + valor2;

    }

    public static int subtracao(int valor1, int valor2) {

        return valor1 - valor2;

    }

}
