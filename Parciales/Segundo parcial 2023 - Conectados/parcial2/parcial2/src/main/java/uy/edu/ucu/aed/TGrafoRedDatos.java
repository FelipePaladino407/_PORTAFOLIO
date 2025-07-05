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
    public boolean conectados(IVertice nodoX, IVertice nodoY)
    {
       if (nodoX== null || nodoY == null){
           return false;
       }
       if (nodoX.getEtiqueta().equals(nodoY.getEtiqueta())){
           return true;
       }

       desvisitarVertices();

        Queue<IVertice> cola = new LinkedList<>();
        nodoX.setVisitado(true);
        cola.add(nodoX);

        while (!cola.isEmpty()){
            IVertice actual = cola.poll();
            for (IAdyacencia ady : actual.getAdyacentes()){
                IVertice w = ady.getDestino();
                if (!w.getVisitado()){
                    if (w.getEtiqueta().equals(nodoY.getEtiqueta())){
                        return true;
                    }
                    w.setVisitado(true);
                    cola.add(w);
                }
            }
        }

        return false;
    }


}
