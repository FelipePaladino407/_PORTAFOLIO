package org.example;

import java.util.LinkedList;

public class TArbolTrieTelefonos implements IArbolTrieTelefonos {

    private TNodoTrieTelefonos raiz;

    public TArbolTrieTelefonos() {
        this.raiz = new TNodoTrieTelefonos();
    }

    @Override
    public LinkedList<TAbonado> buscarTelefonos(String pais, String area) {
        LinkedList<TAbonado> lista = new LinkedList<>();
        String prefijo = pais + area;
        raiz.buscarTelefonos(prefijo, lista);
        lista.sort(null); // usa compareTo de TAbonado
        return lista;
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        raiz.insertar(unAbonado);
    }
}

