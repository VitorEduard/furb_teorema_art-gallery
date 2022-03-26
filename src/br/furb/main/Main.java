package br.furb.main;

import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Main {

    private static class Ponto {
        public Ponto(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
        private Integer x;
        private Integer y;

        public Integer getY() {
            return y;
        }

        public Integer getX() {
            return x;
        }
    }

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o caminho do arquivo teste!");
        String path = scan.next();
        
        List<Ponto[]> lista = getListaPontos(path);
        lista.forEach(envoltoria -> {
            boolean envoltoriaConvesa = true;
            for (int i = 0; i < envoltoria.length; i++) {
                Ponto p1 = getPonto(envoltoria, i);
                Ponto p2 = getPonto(envoltoria, i+1);
                Ponto p3 = getPonto(envoltoria, i+2);
                if (CCW(p1, p2, p3)) {
                    envoltoriaConvesa = false;
                    break;
                }
            }

            if (envoltoriaConvesa) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        });

        System.out.println(lista.get(0));
        System.out.println(lista.get(1));

    }

    private static Ponto getPonto(Ponto[] envoltoria, int iterator){
        if (iterator >= envoltoria.length) {
            return envoltoria[iterator - envoltoria.length];
        } else {
            return envoltoria[iterator];
        }
    }

    private static boolean CCW(Ponto p1, Ponto p2, Ponto p3){
        double value = (p2.getX()-p1.getX()) * (p3.getY()-p1.getY()) - (p2.getY()-p1.getY()) * (p3.getX()-p1.getX());
        if ( value < 0.000001 )
            return true;
        else
            return false;
    }

    private boolean isAnguloMaiorQue180Graus(Ponto p1, Ponto p2, Ponto p3) {
        if (p2.getX() < (p1.getX()+p3.getX())/2) {
            return true;
        }
        if (p2.getY() < (p1.getY()+p3.getY())/2) {
            return true;
        }
        return false;
    }

    private static List<Ponto[]> getListaPontos(String path) {
        List<Ponto[]> lista = new ArrayList<>();
        File entradaFile = new File(path);
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
                        int x = Integer.parseInt(numerosLinha[0]);
                        int y = Integer.parseInt(numerosLinha[1]);
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
