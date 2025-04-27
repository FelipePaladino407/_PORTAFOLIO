package org.example;// EJERCICIO #3

public class Alumnetes {
    public static void main(String[] args) {

        IConjunto<TAlumno> cursoAED1 = new Conjunto<>();
        IConjunto<TAlumno> cursoPF = new Conjunto<>();


        TAlumno a1 = new TAlumno(8000, "Roberto", "Robertson");
        TAlumno a2 = new TAlumno(1999, "Ana", "Ana");
        TAlumno a3 = new TAlumno(1010, "Marcelo", "Arrarte");
        TAlumno a4 = new TAlumno(2000, "Iohan", "Azambuja");
        TAlumno a5 = new TAlumno(2001, "Felipe", "Gil");
        TAlumno a6 = new TAlumno(2002, "Mary", "Curie");
        TAlumno a7 = new TAlumno(9599, "Mateo", "Rodriguez");

        // AED1
        cursoAED1.insertar(a1, a1.getCedula());
        cursoAED1.insertar(a2, a2.getCedula());
        cursoAED1.insertar(a3, a3.getCedula());
        cursoAED1.insertar(a7, a7.getCedula());

        // PF
        cursoPF.insertar(a3, a3.getCedula());    // Está en ambos cursos.
        cursoPF.insertar(a4, a4.getCedula());
        cursoPF.insertar(a5, a5.getCedula());
        cursoPF.insertar(a6, a6.getCedula());

        IConjunto<TAlumno> union = cursoAED1.union(cursoPF);
        System.out.println("== Alumnos en AED1 o PF ==");
        System.out.println(union.imprimir("\n"));

        IConjunto<TAlumno> intersection = cursoAED1.intersection(cursoPF);    // Solo debería mostrar a Marcelo.
        System.out.println("== Alumnos en AED1 y PF ==");
        System.out.println(intersection.imprimir("\n"));


    }
}
