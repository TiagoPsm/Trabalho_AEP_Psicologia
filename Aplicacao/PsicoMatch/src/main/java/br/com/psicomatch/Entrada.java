package br.com.psicomatch;

import java.util.Scanner;

public class Entrada {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String ler(String mensagem) {
        System.out.print(mensagem);
        return SCANNER.nextLine().trim();
    }

    public static int lerInt(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(ler(mensagem));
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    public static double lerDouble(String mensagem) {
        while (true) {
            try {
                return Double.parseDouble(ler(mensagem).replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor válido.");
            }
        }
    }
}
