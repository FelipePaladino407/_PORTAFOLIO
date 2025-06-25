package org.example;
import java.util.LinkedList;

class TNodoTrieTelefonos implements INodoTrieTelefonos {

    private TNodoTrieTelefonos[] hijos;
    private TAbonado abonado;

    public TNodoTrieTelefonos() {
        hijos = new TNodoTrieTelefonos[10];
        abonado = null;
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        TNodoTrieTelefonos nodo = this;
        String telefono = unAbonado.getTelefono();
        for (char c : telefono.toCharArray()) {
            int indice = c - '0';
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrieTelefonos();
            }
            nodo = nodo.hijos[indice];
        }
        nodo.abonado = unAbonado;
    }

    @Override
    public void buscarTelefonos(String primerosDigitos, LinkedList<TAbonado> abonados) {
        TNodoTrieTelefonos nodo = this;
        for (char c : primerosDigitos.toCharArray()) {
            int indice = c - '0';
            if (nodo.hijos[indice] == null) {
                return; // prefijo no encontrado
            }
            nodo = nodo.hijos[indice];
        }
        nodo.colectarAbonados(abonados);
    }

    private void colectarAbonados(LinkedList<TAbonado> abonados) {
        if (abonado != null) {
            abonados.add(abonado);
        }
        for (TNodoTrieTelefonos hijo : hijos) {
            if (hijo != null) {
                hijo.colectarAbonados(abonados);
            }
        }
    }
}