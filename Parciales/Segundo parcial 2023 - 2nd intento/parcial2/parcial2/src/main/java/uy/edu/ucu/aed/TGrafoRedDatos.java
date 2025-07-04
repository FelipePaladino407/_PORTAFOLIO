package uy.edu.ucu.aed;


/*
 * NO LICENCE
 * Author: Ing. Agustin Paredes
 */

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;


/**
 *
 * @author agustinp
 */
public class TGrafoRedDatos extends TGrafoDirigido implements ITGrafoRedDatos
{

    public TGrafoRedDatos(Collection<TVertice> vertices, Collection<TArista> aristas)
    {
        super(vertices, aristas);
    }

    @Override
    public boolean conectados(Comparable a, Comparable b)
    {
        IVertice nodoX = this.buscarVertice(a);
        IVertice nodoY = this.buscarVertice(b);

        if (nodoX == null || nodoY == null){
            return false;
        }
        if (nodoX.getEtiqueta().equals(nodoY.getEtiqueta())){
            return true;
        }

        desvisitarVertices();

        Queue<IVertice> colita = new LinkedList<>();
        nodoX.setVisitado(true);
        colita.add(nodoX);

        while (!colita.isEmpty()){
            IVertice actual = colita.poll();
            for (IAdyacencia ady : actual.getAdyacentes()){
                IVertice w = ady.getDestino();
                if (!w.getVisitado()){
                    if (w.getEtiqueta().equals(nodoY.getEtiqueta())){
                        return true;
                    }
                }
                w.setVisitado(true);
                colita.add(w);

            }
        }
        return false;
    }


}
