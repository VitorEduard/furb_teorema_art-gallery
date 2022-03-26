package br.furb.main;

import java.io.*;
import java.util.*;

public class Main {

    private static class Ponto {
        public Ponto(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
        public Integer x;
        public Integer y;
    }

    public static void main(String[] args) {
        List<Ponto[]> lista = getListaPontos();
        System.out.println("Hello World!!!");
        System.out.println(lista.get(0));
        System.out.println(lista.get(1));

    }

    private static List<Ponto[]> getListaPontos() {
        List<Ponto[]> lista = new ArrayList<>();
        File entradaFile = new File("D:\\ArquivoTEste.txt");
        if (entradaFile.canRead()){
            Ponto[] lPontos = null;
            int i = 0;
            try (Scanner scanner = new Scanner(entradaFile)) {
                while (scanner.hasNext()){
                    String linha = scanner.nextLine();
                    if (linha.length() == 1) {
                        if (lPontos != null) lista.add(lPontos);
                        Integer numero = Integer.parseInt(linha);
                        if (Objects.equals(numero, 0)) {
                            break;
                        } else {
                            i = 0;
                            lPontos = new Ponto[numero];
                        }
                    } else {
                        String[] numerosLinha = linha.split(" ");
                        var x = Integer.parseInt(numerosLinha[0]);
                        var y = Integer.parseInt(numerosLinha[1]);
                        lPontos[i++] = new Ponto(x, y);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

}
