package org.northpole.workshop.base.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class controladorListas<E> {
    private Node<E> header;
    private Node<E> last;
    private Integer size;// pa que no ocupe memoria al ser llamada en otra clase

    public controladorListas(){
        header = null;
        last = null;
        size = 0;
    }

    public Boolean isEmpty(){
        return header == null || size == 0;
    }

    private Node<E> getNode(Integer pos) {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("La lista está vacía");
        }
        if (pos < 0 || pos >= size) {
            throw new ArrayIndexOutOfBoundsException("Fuera de rango");
        }else if (pos == 0) {
            return header;
        }else if ((size.intValue() - 1) == pos.intValue()){
            return last;
        }else{
            Node<E> search = header;
            Integer cont = 0;
            while (cont < pos) {
                search = search.getNext();
                cont++;
            }
            return search;
        }
    }

    
    public E get(Integer pos){
        return getNode(pos).getData();
        /*if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("La lista está vacía");
        }
        if (pos < 0 || pos >= size) {
            throw new ArrayIndexOutOfBoundsException("Fuera de rango");
        }else if (pos == 0) {
            return getDataFist();
        }else if (size.intValue() == pos.intValue()){
            return getDataLast();
        }else{
            return getNode(pos).getData();
        }*/
    }
    
    public void add(E data, Integer pos) throws Exception {
        if(isEmpty()){
            Node<E> aux = new Node<E>(data);
            header = aux;
            last = aux;
            size++;
        } else if (pos == 0 ){
            Node<E> head_old = header;
            Node<E> aux = new Node<>(data, head_old);
            header = aux;
            size++;
        } else if(size.intValue() == pos.intValue()){
            Node<E> aux = new Node<>(data);
            last.setNext(aux);
            size++;
        }else {
            Node<E> serach_preview = getNode(pos-1);
            Node<E> search = getNode(pos);
            Node<E> aux = new Node<>(data, search);
            serach_preview.setNext(aux);
            size++;
        }
    }

    private void addFirst(E data) {
        if(isEmpty()){
            Node<E> aux = new Node<>(data);
            header = aux;
            last = aux;
        } else {
            Node<E> head_old = header;
            Node<E> aux = new Node<>(data, head_old);
            header = aux;
        }
        size++;
    }

    private void addLast(E data) {
        if(isEmpty()){
            addFirst(data);
        } else{
            Node<E> aux = new Node<>(data);
            last.setNext(aux);
            last = aux;
            size++;
        }
    }

    private void addMiddle(E data, Integer pos) {
        if (pos == 0 ){
            addFirst(data);
        } else if(size.intValue() == pos.intValue()){
            addLast(data);
        }else {
            Node<E> serach_preview = getNode(pos-1);
            Node<E> search = getNode(pos);
            Node<E> aux = new Node<>(data, search);
            serach_preview.setNext(aux);
            size++;
        }
    }

    public void add(E data) throws Exception {
        addLast(data);    
    }

    
    public String print(){
        if (isEmpty()) {
            return "Ta vacia tu vaina oe"; 
        }else{
            StringBuilder txt = new StringBuilder();
            Node<E> help = header;
            while (help != null) {
                txt.append(help.getData()).append(" - ");
                help = help.getNext();
            }
            txt.append("\n");
            return txt.toString();
        }
    } 

    public void update(E data, Integer pos) throws Exception {
        getNode(pos).setData(data);
    }

    public void deleteFirst(){
        if (isEmpty()) {
            System.out.println("La lista esta vacia");
        }
        Node<E> aux = header.getNext();
        header = aux;
        size--;
        if (header == null) {
            last = null;
        }
    }

    public void deleteLast(){
        if (isEmpty()) {
            System.out.println("La lista esta vacia");
        }else if (size == 1) {
            deleteFirst();
        }
        
        Node<E> aux = getNode(size-2);
        last = aux;
        last.setNext(null);
        size--;
    }

    public void delete(Integer pos){
        if (pos == 0){
            deleteFirst();
        } else if (size.intValue() == pos.intValue()){
            deleteLast();
        } else if (pos < 0 || pos >= size){
            System.out.println("Fuera de rango");
        } else{
            Node<E> previo = getNode(pos-1);
            previo.setNext(getNode(pos).getNext());
            size--;
        }
    }

    public void clear() {
        header = null;
        last = null;
        size = 0;
    }

    public void loadFromFile(String direccion) throws IOException {
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(direccion))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    E data = (E) line;
                    this.add(data);
                } catch (Exception e) {
                    System.out.println("Error al agregar el dato: " + line);
                }
            }
        }
    }

    public  String elementosRepetidos(String direccion) throws IOException {
        Map<E, Integer> datos = new HashMap<>();
        Node<E> actual = header;
        while (actual != null) {
            E dato = actual.getData();
            datos.put(dato, datos.getOrDefault(dato, 0) + 1);
            actual = actual.getNext();
        }
        
        StringBuilder resultado = new StringBuilder("Elementos repetidos:\n");
        for (Map.Entry<E, Integer> entry : datos.entrySet()) {
            if (entry.getValue() > 1) {
                resultado.append(entry.getKey()).append(": ").append(entry.getValue()).append(" veces\n");
            }
        }
        return resultado.toString();
    }

    public static void mostrarTiempoEjecucion(long startTime2, long endTime2) {
        long duration = endTime2 - startTime2;
        System.out.println("Tiempo de ejecución: " + duration + " nanosegundos");
    }
   
    public Integer getSize() {
        return this.size;
    }
    
}