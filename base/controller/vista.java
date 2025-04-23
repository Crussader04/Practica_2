package org.northpole.workshop.base.controller;

import java.io.IOException;

public class vista extends controladorArreglo{
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        String rutaArchivo = "/home/cristian/Descargas/data.txt";
        String resultado = elementosRepetidos(rutaArchivo);
        System.out.println(resultado);
        long endTime = System.nanoTime();
        mostrarTiempoEjecucion(startTime, endTime);

        long startTime2 = System.nanoTime();
        controladorListas<String> lista = new controladorListas<>();
        lista.loadFromFile(rutaArchivo);
        String result = "";
        result = lista.elementosRepetidos(result);
        System.out.println(result);
        long endTime2 = System.nanoTime();
        mostrarTiempoEjecucion(startTime2, endTime2);
        System.out.println("El tiempo de ejecución del arreglo es: " + (endTime - startTime) + " nanosegundos");
        System.out.println("El tiempo de ejecución de la lista enlazada es: " + (endTime2 - startTime2) + " nanosegundos");
    }    
}

