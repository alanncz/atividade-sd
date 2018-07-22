/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alann.ifpb;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Random;

/**
 *
 * @author alann
 */
public class Client {

    private static int valor;
    private static boolean primeiraRequisicao = true;

    public static void main(String args[]) throws Exception {

        valor = 0;

        DatagramSocket clientSocket = new DatagramSocket();

        String servidor = "localhost";
        int porta = 9876;

        InetAddress IPAddress = InetAddress.getByName(servidor);

        while (true) {

            byte[] sendData = criaOperacao(valor);

            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, IPAddress, porta);

            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[Integer.BYTES];

            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);

            clientSocket.receive(receivePacket);

            atualizarValor(receivePacket);

        }

    }

    public static byte[] criaOperacao(int valor) {

        Random gerador = new Random();
        
        int valor1;
        int valor2;

        if (primeiraRequisicao == true) {
            
            valor1 = gerador.nextInt(10);
            valor2 = gerador.nextInt(10);
            primeiraRequisicao = false;
        }
        
        else{
            
            valor1 = valor;
            valor2 = gerador.nextInt(10);
        }
            
        char operacao = 'n';
        int operacaoEscolha = gerador.nextInt(2);

        if (operacaoEscolha == 0) {
            operacao = '+';
        } else if (operacaoEscolha == 1) {
            operacao = '-';
        }

        int tamanho = Integer.BYTES * 2 + Character.BYTES;

        ByteBuffer bff = ByteBuffer.allocate(tamanho);

        bff.putInt(valor1);
        bff.putInt(valor2);
        bff.putChar(operacao);

        byte[] sendData = bff.array();

        return sendData;

    }

    public static void atualizarValor(DatagramPacket receivePacket) {

        ByteBuffer bff = ByteBuffer.wrap(receivePacket.getData());
        int resultado = bff.getInt();
        valor = resultado;
        System.out.println("O valor atual é " + resultado);

    }

}
