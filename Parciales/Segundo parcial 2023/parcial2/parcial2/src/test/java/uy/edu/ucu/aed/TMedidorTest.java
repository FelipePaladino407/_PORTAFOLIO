package uy.edu.ucu.aed;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TMedidorTest {

    @Test
    public void testMayorMedicionNormal() {
        TDato[] datos = new TDato[] {
                new TDato(20.0, 20230101),
                new TDato(85.5, 20230102),
                new TDato(42.3, 20230103)
        };

        TMedidor medidor = new TMedidor();
        TDato resultado = medidor.obtenerMayorMedicion(datos);

        assertNotNull(resultado);
        assertEquals(85.5, resultado.getValor(), 0.001);
        assertEquals(20230102, resultado.getFecha());
    }

    @Test
    public void testArrayVacio() {
        TMedidor medidor = new TMedidor();
        TDato resultado = medidor.obtenerMayorMedicion(new TDato[0]);

        assertNull(resultado);
    }

    @Test
    public void testArrayNull() {
        TMedidor medidor = new TMedidor();
        TDato resultado = medidor.obtenerMayorMedicion(null);

        assertNull(resultado);
    }

    @Test
    public void testUnSoloElemento() {
        TDato[] datos = new TDato[] {
                new TDato(99.9, 20240101)
        };

        TMedidor medidor = new TMedidor();
        TDato resultado = medidor.obtenerMayorMedicion(datos);

        assertNotNull(resultado);
        assertEquals(99.9, resultado.getValor(), 0.001);
        assertEquals(20240101, resultado.getFecha());
    }
}
