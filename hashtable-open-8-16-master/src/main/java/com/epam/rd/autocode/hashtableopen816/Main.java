package com.epam.rd.autocode.hashtableopen816;

public class Main {
    public static void main(String[] args) {
        HashtableOpen8to16 hashtable = HashtableOpen8to16.getInstance();

        // Pruebas de inserción
        try {
            hashtable.insert(10, "Value10");
            hashtable.insert(18, "Value18");
            hashtable.insert(34, "Value34");
            
            // Imprimir valores antes de eliminar
            System.out.println("Valor para la llave 10: " + hashtable.search(10));
            System.out.println("Valor para la llave 18: " + hashtable.search(18));
            System.out.println("Valor para la llave 34: " + hashtable.search(34));
            
            // Eliminar elementos
            hashtable.remove(18);
            System.out.println("Después de remover la llave 18:");
            System.out.println("Valor para la llave 10: " + hashtable.search(10));
            System.out.println("Valor para la llave 18: " + hashtable.search(18));
            System.out.println("Valor para la llave 34: " + hashtable.search(34));
            
            hashtable.remove(10);
            System.out.println("Después de remover la llave 10:");
            System.out.println("Valor para la llave 10: " + hashtable.search(10));
            System.out.println("Valor para la llave 18: " + hashtable.search(18));
            System.out.println("Valor para la llave 34: " + hashtable.search(34));

            // Pruebas de loop infinito
            System.out.println("Iniciando pruebas de capacidad máxima...");

            // Llena la tabla hasta el máximo (16)
            for (int i = 0; i < 16; i++) {
                hashtable.insert(i, "Value" + i);
            }

            // Intentar insertar un elemento adicional para causar la excepción
            try {
                hashtable.insert(17, "Value17");
            } catch (IllegalStateException e) {
                System.out.println("Excepción capturada: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("Excepción en main: " + e.getMessage());
        }
    }
}
