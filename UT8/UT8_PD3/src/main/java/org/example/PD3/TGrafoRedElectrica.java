package org.example.PD3;


import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ocamp
 */
public class TGrafoRedElectrica<T> extends TGrafoNoDirigido<T> implements IGrafoRedElectrica {

    public TGrafoRedElectrica(Collection<IVertice<T>> vertices,
                              Collection<IArista> aristas) {
        super(vertices, aristas);
    }


    @Override
    public TAristas mejorRedElectrica() {

        TGrafoNoDirigido<T> mst = this.PrimEficiente();

        return mst.getLasAristas();
    }

}