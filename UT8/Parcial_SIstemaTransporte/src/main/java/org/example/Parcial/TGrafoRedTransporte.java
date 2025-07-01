package org.example.Parcial;

import org.example.*;
import java.util.*;

/**
 * Grafo especializado para red de transporte público:
 * - Dijkstra para consultaTiempoMinimo
 * - Prim eficiente para redDeMantenimiento
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class TGrafoRedTransporte
        extends TGrafoNoDirigido<TEstacionDeLaRed>
        implements IGrafoRedTransporte
{
    public TGrafoRedTransporte(Collection<IVertice<TEstacionDeLaRed>> vertices,
                               Collection<IArista> aristas)
    {
        super(vertices, aristas);
    }

    @Override
    public int consultaTiempoMinimo(Comparable origen, Comparable destino) {
        // Usamos Floyd para aprovechar lo que ya está hecho
        Double[][] mat = this.floyd();
        // Mapear etiquetas a índices
        List<Comparable> etiquetas = new ArrayList<>(this.getVertices().keySet());
        int i = etiquetas.indexOf(origen);
        int j = etiquetas.indexOf(destino);
        if (i < 0 || j < 0) return -1;
        Double costo = mat[i][j];
        return (costo == Double.MAX_VALUE || costo < 0) ? -1 : costo.intValue();
    }

    @Override
    public TAristas redDeMantenimiento() {
        // PrimEficiente deja el MST en un nuevo TGrafoNoDirigido
        TGrafoNoDirigido<TEstacionDeLaRed> mst = this.PrimEficiente();
        // Sus aristas son la “mejor red” de mantenimiento
        return mst.getLasAristas();
    }
}
