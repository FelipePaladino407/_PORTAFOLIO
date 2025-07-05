package uy.edu.ucu.aed.parcial2;

import java.util.Collection;

import uy.edu.ucu.aed.*;

@SuppressWarnings({"unchecked", "rawtypes"})
public class TGrafoDeLaRed extends TGrafoNoDirigido<TEstacionDeLaRed> implements IGrafoDeLaRed {

    public TGrafoDeLaRed(Collection<TVerticeDeLaRed> vertices, Collection<IArista> aristas) {
        super((Collection<IVertice<TEstacionDeLaRed>>) (Collection<?>) vertices, aristas);
    }

    @Override
    public TCaminos<TEstacionDeLaRed> caminosDesdeHasta(Comparable nodoOrigen, Comparable nodoDestino) {

        TCaminos<TEstacionDeLaRed> todosLosCaminos = new TCaminos<>();

        IVertice<TEstacionDeLaRed> origen = buscarVertice(nodoOrigen);
        IVertice<TEstacionDeLaRed> destino = buscarVertice(nodoDestino);

        if (origen == null || destino == null || origen.getDatos().getTipo() != TipoDeEstacion.AUTOBUSES ||
                destino.getDatos().getTipo() != TipoDeEstacion.AUTOBUSES){
            return todosLosCaminos;
        }

        desvisitarVertices();
        origen.setVisitado(true);

        TCamino<TEstacionDeLaRed> caminoActual = new TCamino<>(origen);

        dfsRuta(origen, destino, caminoActual, todosLosCaminos);
        return todosLosCaminos;


    }

    private void dfsRuta(IVertice<TEstacionDeLaRed> u, IVertice<TEstacionDeLaRed> destino,
                         TCamino<TEstacionDeLaRed> caminoActual, TCaminos<TEstacionDeLaRed>
                         todosLosCaminos){
        if (u.getEtiqueta().equals(destino.getEtiqueta())){
            todosLosCaminos.getCaminos().add(caminoActual.copiar());
            return;
        }
        for (IAdyacencia<TEstacionDeLaRed> ady : u.getAdyacentes()){
            IVertice<TEstacionDeLaRed> v = ady.getDestino();
            if (!v.getVisitado()){
                if ((u.getDatos().getTipo() == TipoDeEstacion.AUTOBUSES) &&
                v.getDatos().getTipo() == TipoDeEstacion.AUTOBUSES){
                    continue;
                }
                v.setVisitado(true);
                caminoActual.agregarAdyacencia(ady);

                dfsRuta(v, destino, caminoActual, todosLosCaminos);

                caminoActual.eliminarAdyacencia(ady);
                v.setVisitado(false);
            }
        }
    }
}