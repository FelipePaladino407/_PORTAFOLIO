package org.example;

import org.example.Utils.AristaPQ;

import java.util.*;

/**
 * Clase TGrafoNoDirigido que extiende de TGrafoDirigido e implementa IGrafoNoDirigido.
 * Esta clase representa un grafo no dirigido.
 * @param <T> El tipo de los datos que se guardan en cada vértice.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class TGrafoNoDirigido<T> extends TGrafoDirigido<T> implements IGrafoNoDirigido<T> {
    protected TAristas lasAristas = new TAristas();

    /**
     * Constructor de la clase TGrafoNoDirigido.
     * @param vertices Colección de vértices del grafo.
     * @param aristas Colección de aristas del grafo.
     */
    public TGrafoNoDirigido(Collection<IVertice<T>> vertices, Collection<IArista> aristas) {
        super(vertices, new LinkedList<>());
        this.lasAristas = new TAristas();

        for(IArista arista : aristas) {
            this.insertarArista(arista);
        }

    }

    /**
     * Método para insertar una arista en el grafo.
     * @param arista La arista a insertar.
     * @return Verdadero si la arista se insertó correctamente, falso en caso contrario.
     */
    @Override
    public boolean insertarArista(IArista arista) {
        // Verificar si la arista ya existe.
        if (this.lasAristas.buscar(arista.getEtiquetaOrigen(), arista.getEtiquetaDestino()) != null ||
        this.lasAristas.buscar(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen()) != null) {
            return false;
        }

        // Creación de la arista inversa.
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        boolean success = super.insertarArista(arista) && super.insertarArista(arInv);

        if (success) {
            this.lasAristas.add(arista);
            this.lasAristas.add(arInv);
        }
        return success;
    }

    /**
     * Método para obtener las aristas del grafo.
     * @return Las aristas del grafo.
     */
    public TAristas getLasAristas() {
        return lasAristas;
    }

    /**
     * Método para obtener el árbol de expansión mínima del grafo utilizando el algoritmo de Prim.
     * @return El árbol de expansión mínima del grafo.
     */
    @Override
    public IGrafoNoDirigido<T> Prim() {

        Collection<IVertice<T>> mstVertices = new LinkedList<>();
        for(IVertice<T> vertice: this.getVertices().values()) {
            mstVertices.add(new TVertice<>(vertice.getEtiqueta()));
        }

        IGrafoNoDirigido<T> arbolPrim = new TGrafoNoDirigido(mstVertices, new LinkedList<>());
        double costoTotal = 0.0d;

        if (this.getVertices().isEmpty()) {
            return arbolPrim;
        }

        TAristas aristas = this.getLasAristas();
        LinkedList<Comparable> vertices = new LinkedList<>();
        for (IVertice<T> vertice : this.getVertices().values()) {
            vertices.add(vertice.getEtiqueta());
        }

        Collection<Comparable> U = new LinkedList<>();
        U.add(vertices.removeFirst());

        while (!vertices.isEmpty()) {
            IArista a = aristas.buscarMin(U, vertices);
            if (a == null) {
                throw new IllegalStateException("No se encontró una arista válida entre U y V-U. ¿El grafo es conexo?");
            }
            vertices.remove(a.getEtiquetaDestino());
            U.add(a.getEtiquetaDestino());
            arbolPrim.insertarArista(a);
            costoTotal += a.getCosto();
        }
        System.out.println(costoTotal);
        return arbolPrim;
    }

    /**
     * Algoritmo de prim mamadisimo (N Log(N)).
     */
    @Override
    public TGrafoNoDirigido<T> PrimEficiente() {
        Map<Comparable, IVertice<T>> vertices = this.getVertices();
        if (vertices.isEmpty()) {
            return new TGrafoNoDirigido<>(Collections.emptyList(), Collections.emptyList());
        }

        // Crear nuevos vértices para el MST
        Map<Comparable, IVertice<T>> mstVerticesMap = new HashMap<>();
        for (IVertice<T> vertice : vertices.values()) {
            mstVerticesMap.put(vertice.getEtiqueta(), new TVertice<>(vertice.getEtiqueta()));
        }

        // Conjunto para vértices ya en el MST
        Set<Comparable> enSpanningTree = new HashSet<>();
        PriorityQueue<AristaPQ> pq = new PriorityQueue<>();
        double costoTotal = 0.0d;  // Como en el prim() original.

        // Inicializar con el primer vértice
        Comparable start = vertices.keySet().iterator().next();
        enSpanningTree.add(start);

        // Agregar aristas iniciales a la cola de prioridad
        IVertice<T> v0 = vertices.get(start);
        for (IAdyacencia ady : v0.getAdyacentes()) {
            pq.offer(new AristaPQ(start, ady.getDestino().getEtiqueta(), ady.getCosto()));
        }

        List<IArista> mstEdges = new LinkedList<>();

        while (!pq.isEmpty() && enSpanningTree.size() < vertices.size()) {
            AristaPQ minArista = pq.poll();
            Comparable destino = minArista.getTo();

            // Si el destino ya está en el MST, saltar
            if (enSpanningTree.contains(destino)) continue;

            // Agregar vértice al MST y registrar arista
            enSpanningTree.add(destino);
            IArista nuevaArista = new TArista(minArista.getFrom(), destino, minArista.getCosto());
            mstEdges.add(nuevaArista);
            costoTotal += minArista.getCosto(); // Acumulo el costo totality.

            // Procesar adyacentes del nuevo vértice
            IVertice<T> nuevoVertice = vertices.get(destino);
            for (IAdyacencia ady : nuevoVertice.getAdyacentes()) {
                Comparable vecino = ady.getDestino().getEtiqueta();
                if (!enSpanningTree.contains(vecino)) {
                    pq.offer(new AristaPQ(destino, vecino, ady.getCosto()));
                }
            }
        }

        // Construir y retornar el MST
        TGrafoNoDirigido<T> mst = new TGrafoNoDirigido<>(mstVerticesMap.values(), mstEdges);

        System.out.println("Costo total con prim eficiente: " + costoTotal);
        return mst;
    }

    /**
     * Método para obtener el árbol de expansión mínima del grafo utilizando el algoritmo de Kruskal.
     * @return El árbol de expansión mínima del grafo.
     */
    @Override
    public IGrafoNoDirigido<T> Kruskal() {

        return null;
    }

    /**
     * Método para realizar una búsqueda en amplitud en el grafo.
     * @param etiquetaOrigen La etiqueta del vértice de origen.
     * @return Una colección de vértices que representa el resultado de la búsqueda.
     */
    @Override
    public Collection<IVertice<T>> bea(Comparable etiquetaOrigen) {
        if (this.getVertices().isEmpty()) {
            return null;
        } else {
            this.desvisitarVertices();
            if(this.existeVertice(etiquetaOrigen))
            {
                IVertice<T> vert = super.buscarVertice(etiquetaOrigen);
                Collection<IVertice<T>> verts = new LinkedList<IVertice<T>>();
                vert.bea(verts);
                return verts;
            }
            return null;
        }
    }

    /**
     * Método para determinar si el grafo es conexo.
     * @return Verdadero si el grafo es conexo, falso en caso contrario.
     */
    @Override
    public boolean esConexo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Método para determinar si dos vértices están conectados.
     * @param origen El vértice de origen.
     * @param destino El vértice de destino.
     * @return Verdadero si los vértices están conectados, falso en caso contrario.
     */
    @Override
    public boolean conectados(IVertice<T> origen, IVertice<T> destino) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
