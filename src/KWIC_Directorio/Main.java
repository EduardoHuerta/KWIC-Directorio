package kwicDirectorio;
/*Alumno: Eduardo Alfonso Huerta Mora
* Materia: Arquitectura de Software
* Proyecto: KWIC Directorio
* Docente: Dra. María Lucía Barrón Estrada*/

import kwic.KWIC;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main {

    private static List<String> salidas = new ArrayList<>();
    private static String nombre = "";
    private static String ruta="C:/Users";
    //Método main y subrutinas
    public static void main(String[] args) throws IOException {
        ingresarTexto();
        leerDirectorio();
        KWIC.kwicIndex = salidas;
        KWIC.alphabetize();
        KWIC.writeToOutput();
    }
    //Método para leer el directorio llamando al método recursivo obtenerDirectorio
    private static void leerDirectorio() throws IOException {
        String archivo = "src/input.txt";
        PrintWriter pw = new PrintWriter(archivo);

        File folderFile = new File(ruta);
        if (folderFile.exists()) {
            File[] archivosEnCarpeta = folderFile.listFiles();
            obtenerDirectorio(archivosEnCarpeta);
        }
        for (String texto : salidas) {
            pw.println(texto);
        }
        pw.close();
    }
    //Método recursivo que abre carpetas hasta encontrar el archivo
    private static void obtenerDirectorio(File[] arregloDeArchivos) {
        for (File archivo : arregloDeArchivos) {
            boolean esCarpeta = archivo.isDirectory();
            if (esCarpeta) {
                File[] files = archivo.listFiles();
                obtenerDirectorio(files);
            }
            if (archivo.getName().contains(nombre)){
                salidas.add(archivo.getAbsolutePath());
            }
        }
    }
 //Función para solicitar al usuario el ingreso de la palabra a buscar
    private static void ingresarTexto(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese palabra a buscar: ");
        nombre = scanner.nextLine();
        System.out.println("Palabra a buscar: \""+nombre+"\"");
    }
}