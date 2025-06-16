package org.example;


import java.util.Collection;
import java.util.LinkedList;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {
    protected TAristas lasAristas = new TAristas();

    /**
     * Constructor de la clase TGrafoNoDirigido.
     * @param vertices Colección de vértices del grafo.
     * @param aristas Colección de aristas del grafo.
     */
    public TGrafoNoDirigido(Collection<IVertice> vertices, Collection<IArista> aristas) {
        super(vertices, new LinkedList<>());
        this.lasAristas = new TAristas();
        this.lasAristas.insertarAmbosSentidos(aristas);

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
        // Verificar si la arista ya existe
        if (this.lasAristas.buscar(arista.getEtiquetaOrigen(), arista.getEtiquetaDestino()) != null) {
            return false;
        }

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
    public IGrafoNoDirigido Prim() {
        IGrafoNoDirigido arbolPrim = new TGrafoNoDirigido(this.getVertices().values(), new LinkedList<>());
        double costoTotal = 0.0d;

        if (this.getVertices().isEmpty()) {
            System.out.println(this.getVertices());
            return arbolPrim;
        }

        TAristas aristas = this.getLasAristas();
        LinkedList<Comparable> vertices = new LinkedList<>();
        for (IVertice vertice : this.getVertices().values()) {
            vertices.add(vertice.getEtiqueta());
        }

        Collection<Comparable> U = new LinkedList<>();

        U.add(vertices.removeFirst());
        while (!vertices.isEmpty()) {
            IArista a = aristas.buscarMin(U, vertices);
            if (a == null) {
                throw new IllegalStateException("No se encontró una arista válida entre U y V-U. ¿El grafo es conexo?");
            }
            IVertice v = this.getVertices().get(a.getEtiquetaDestino());
            vertices.remove(v.getEtiqueta());
            U.add(v.getEtiqueta());
            arbolPrim.insertarArista(a);
            costoTotal += a.getCosto();
        }
        System.out.println(costoTotal);
        return arbolPrim;
    }


    /**
     * Metodo con Big O (n log(n)).
     *
    public TGrafoNoDirigido PrimEficiente() {
        Map<Comparable, TVertice> vertices = this.getVertices();
        if (vertices.isEmpty()) return new TGrafoNoDirigido(Collections.emptyList(), Collections.emptyList());

        // Conjunto en donde se almacenan los vertices ya visitados para el spanning tree.
        Set<Comparable> enSpanningTree = new HashSet<>();
        // La dichosa cola de prioridad que nos baja el Big O.
        PriorityQueue<AristaPQ> pq = new PriorityQueue<>();

        Comparable start = vertices.keySet().iterator().next();
        enSpanningTree.add(start);

        // Agrega bordes iniciales.
        TVertice v0 = vertices.get(start);
        for (Object hugo : v0.getAdyacentes()) {
            TAdyacencia ady = (TAdyacencia) hugo;
            pq.offer(new AristaPQ(start, ady.getDestino().getEtiqueta(), ady.getCosto()));
        }

        List<TArista> mstEdges = new LinkedList<>();

        while (!pq.isEmpty() && enSpanningTree.size() < vertices.size()) {
            AristaPQ vertice = pq.poll();
            if (enSpanningTree.contains(vertice.getTo())) continue;

            enSpanningTree.add(vertice.getTo());
            mstEdges.add(new TArista(vertice.getFrom(), vertice.getTo(), vertice.getCosto()));

            TVertice newV = vertices.get(vertice.getTo());;
            for (Object hugo : newV.getAdyacentes()) {
                TAdyacencia ady = (TAdyacencia) hugo;
                if (!enSpanningTree.contains(ady.getDestino().getEtiqueta())) {
                    pq.offer(new AristaPQ(vertice.getTo(), ady.getDestino().getEtiqueta(), ady.getCosto()));
                }
            }
        }

        // Se devuelven solo las aristas de MST
        return new TGrafoNoDirigido(vertices.values(), mstEdges);
    }
     **/

    @Override
    public TGrafoNoDirigido Kruskal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    /**
     * UT8_PD1 Ejercicio 3.
     * @param etiquetaOrigen
     */
    @Override
    public Collection<IVertice> bea(Comparable etiquetaOrigen) {
        if (this.getVertices().isEmpty()) {
            return null;
        } else {
            this.desvisitarVertices();
            if(this.existeVertice(etiquetaOrigen))
            {
                IVertice vert = super.buscarVertice(etiquetaOrigen);
                Collection<IVertice> verts = new LinkedList<IVertice>();
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
    public boolean conectados(IVertice origen, IVertice destino) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}