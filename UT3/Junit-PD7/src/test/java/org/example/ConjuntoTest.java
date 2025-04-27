package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConjuntoTest {

    @Test
    public void testUnionEInterseccion() {
        IConjunto<TAlumno> aed1 = new Conjunto<>();
        IConjunto<TAlumno> pf = new Conjunto<>();

        TAlumno a1 = new TAlumno(1001, "Ana", "Ana");
        TAlumno a2 = new TAlumno(1002, "Javier", "Wagner");
        TAlumno a3 = new TAlumno(1003, "Jude", "Bellingham");
        TAlumno a4 = new TAlumno(1004, "Cuz", "Cuz");

        aed1.insertar(a1, a1.getCedula());
        aed1.insertar(a2, a2.getCedula());
        aed1.insertar(a3, a3.getCedula());

        pf.insertar(a3, a3.getCedula());
        pf.insertar(a4, a4.getCedula());

        IConjunto<TAlumno> union = aed1.union(pf);
        IConjunto<TAlumno> interseccion = aed1.intersection(pf);

        assertEquals(4, union.cantElementos());
        assertEquals(1, interseccion.cantElementos());
    }

}