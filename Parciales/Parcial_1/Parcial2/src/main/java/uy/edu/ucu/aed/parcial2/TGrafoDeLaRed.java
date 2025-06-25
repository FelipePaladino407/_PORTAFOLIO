package uy.edu.ucu.aed.parcial2;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import uy.edu.ucu.aed.*;

@SuppressWarnings({"unchecked", "rawtypes"})
public class TGrafoDeLaRed extends TGrafoNoDirigido<TNodoDeLaRed> implements IGrafoDeLaRed {

    public TGrafoDeLaRed(Collection<TVerticeDeLaRed> vertices, Collection<IArista> aristas) {
        super((Collection<IVertice<TNodoDeLaRed>>) (Collection<?>) vertices, aristas);
    }

    @Override
    public TCaminos<TNodoDeLaRed> caminosDesdeHasta(Comparable nodoOrigen, Comparable nodoDestino) {

        // Hay que buscar los vertices de origen y destino:
        IVertice<TNodoDeLaRed> verticeOrigen = this.buscarVertice(nodoOrigen);
        IVertice<TNodoDeLaRed> verticeDestino = this.buscarVertice(nodoDestino);

        TCaminos<TNodoDeLaRed> caminosEncontrados = new TCaminos<>();

        if (verticeOrigen != null && verticeDestino != null) {
            TCamino<TNodoDeLaRed> caminoInicial = new TCamino<>(verticeOrigen);

            buscarCamino(verticeOrigen, verticeDestino, caminosEncontrados, caminoInicial);
        }


        return caminosEncontrados;
    }

    private void buscarCamino(
            IVertice<TNodoDeLaRed> actual,
            IVertice<TNodoDeLaRed> destino,
            TCaminos<TNodoDeLaRed> caminosResultantes,
            TCamino<TNodoDeLaRed> camino){

        if (actual.getEtiqueta().equals(destino.getEtiqueta())) {
            caminosResultantes.getCaminos().add(camino.copiar());
            return;
        }

        // Exploramos cada arista saliente:
        for (IAdyacencia<TNodoDeLaRed> adyacencia : actual.getAdyacentes()){
            IVertice<TNodoDeLaRed> vecino = adyacencia.getDestino();

            // Si ya esta no lo meto, asi evito ciclos:
            if (camino.getOtrosVertices().contains(vecino.getEtiqueta())) {
                continue;
            }

            // Aplico la movida de los switch de arnaud:
            // Si ambos son procesadores, repito hasta que uno sea switch.
            TipoDeNodo tipo = actual.getDatos().getTipo();
            TipoDeNodo tipoVecino = vecino.getDatos().getTipo();

            if (tipo == TipoDeNodo.NODO_DE_PROCESAMIENTO &&
            tipoVecino == TipoDeNodo.NODO_DE_PROCESAMIENTO) {
                continue;
            }

            camino.agregarAdyacencia(adyacencia);

            // Recurre desde el vecino:
            buscarCamino(vecino, destino, caminosResultantes, camino);

            // Backtrack: eliminamos la ultima adyacencia:
            camino.eliminarAdyacencia(adyacencia);

        }
    }




}