package org.example;

import java.io.*;

public class CrearLibro {
    public static void main(String[] args) throws IOException {
        String texto = """
            Había una vez un libro. El libro hablaba de aventuras, de magia y de amistad.
            La amistad era lo más importante. Magia, libros, y más magia.
            Este libro tenía palabras, muchas palabras. Palabras que contaban historias.
            """;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("libro.txt"))) {
            writer.write(texto);
        }

        System.out.println("Archivo 'libro.txt' creado correctamente.");
    }
}


