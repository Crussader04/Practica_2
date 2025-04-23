package org.northpole.workshop.base.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class controladorArreglo {

    public static String[] Contenido(String nombreArchivo) throws IOException {
        FileReader fr = new FileReader(nombreArchivo);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        String linea;
        while ((linea = br.readLine()) != null) {
            sb.append(linea).append("\n");
        }
        br.close();
        return sb.toString().split("\n");
    }

    public static int[] procesarArchivo(String rutaArchivo) throws IOException {
        String[] datos = Contenido(rutaArchivo);
        int[] array = new int[datos.length];
        for (int i = 0; i < datos.length; i++) {
            if (!datos[i].isEmpty()) {
                array[i] = Integer.parseInt(datos[i]);
            } else {
                array[i] = 0;
            }
        }
        return array;
    }

    public static String elementosRepetidos(String rutaArchivo) throws IOException {
        String[] datos = Contenido(rutaArchivo);
        Map<Integer, Integer> mapa = new HashMap<>();
        for (String dato : datos) {
            if (!dato.isEmpty()) {
                int numero = Integer.parseInt(dato);
                mapa.put(numero, mapa.getOrDefault(numero, 0) + 1);
            }
        }
        
        StringBuilder resultado = new StringBuilder("Elementos repetidos:\n");
        for (Map.Entry<Integer, Integer> entry : mapa.entrySet()) {
            if (entry.getValue() > 1) {
                resultado.append(entry.getKey()).append(": ").append(entry.getValue()).append(" veces\n");
            }
        }
        return resultado.toString();
    }

    public static void mostrarTiempoEjecucion(long startTime, long endTime) {
        long duration = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + duration + " nanosegundos");
    }

}
