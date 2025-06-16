package org.example;


import java.util.*;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {

    protected TAristas lasAristas = new TAristas();



    /**
     *
     * @param vertices
     * @param aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<IArista> aristas) {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);

    }

    @Override
    public boolean insertarArista(IArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }


    @Override
    public IGrafoNoDirigido Prim() {
        IGrafoNoDirigido arbolPrim = new TGrafoNoDirigido(this.getVertices().values(), new LinkedList<>());
        double costoTotal = 0.0d;

        if (this.getVertices().isEmpty()) {
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
    public IGrafoNoDirigido Kruskal() {
        LinkedList<IArista> aristasKruskal = new LinkedList<IArista>(); //Aqui se almacenaran las aristas seleccionadas.
        Map<Comparable, TVertice> vertices = getVertices();

        if (!vertices.isEmpty()) {
            desvisitarVertices();
            HashMap<Comparable, LinkedList<IVertice>> colecciones = new HashMap(vertices.size());
            LinkedList<IVertice> colTemp;

            // Creamos una "coleccion" para cada arista
            for (IVertice v : vertices.values()) {
                colTemp = new LinkedList<IVertice>();
                colTemp.add(v);
                colecciones.put(v.getEtiqueta(), colTemp);
            }

            // Ordenamos todas las aristas del grafo de menor costo a mayor
            LinkedList<IArista> aristasOrdenadas = new LinkedList<IArista>();
            forAristas:
            for (IArista a : lasAristas) {
                if (aristasOrdenadas.isEmpty() || aristasOrdenadas.getFirst().getCosto() > a.getCosto()) {
                    aristasOrdenadas.addFirst(a);
                    continue;
                }
                for (int i = 1; i < aristasOrdenadas.size(); i++) {
                    if (aristasOrdenadas.get(i).getCosto() > a.getCosto()) {
                        aristasOrdenadas.add(i - 1, a);
                        continue forAristas;
                    }
                }
                aristasOrdenadas.add(a);
            }

            // Conectamos las colecciones de vertices (no conectados) mediante la arista de menor costo posible
            IArista menorArista;
            LinkedList<IVertice> colOrigen, colDestino;
            while (!aristasOrdenadas.isEmpty()) {
                menorArista = aristasOrdenadas.pollFirst();
                colOrigen = colecciones.get(menorArista.getEtiquetaOrigen());
                colDestino = colecciones.get(menorArista.getEtiquetaDestino());
                if (colOrigen != colDestino) {
                    colOrigen.addAll(colDestino);
                    for (IVertice v : colDestino) {
                        if (colecciones.get(v.getEtiqueta()) != colOrigen) {
                            colecciones.replace(v.getEtiqueta(), colOrigen);
                        }
                    }
                    aristasKruskal.add(menorArista);
                }
            }
        }

        IGrafoNoDirigido grafo = new TGrafoNoDirigido(vertices.values(), aristasKruskal);
        return grafo;
    }




    /**
     * UT8_PD1 Ejercicio 3.
     * @param etiquetaOrigen
     */
    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        return List.of();
    }

    @Override
    public Collection<TVertice> bea() {
        List<TVertice> resultado = new LinkedList<>();
        desvisitarVertices();
        for (TVertice inicio : getVertices().values()) {
            if (!inicio.getVisitado()) {
                bfs(inicio, resultado);
            }
        }
        return resultado;
    }

    private void bfs(TVertice origen, Collection<TVertice> visitados) {
        Queue<TVertice> cola = new LinkedList<>();
        origen.setVisitado(true);
        cola.add(origen);
        while (!cola.isEmpty()) {
            TVertice v = cola.poll();
            visitados.add(v);
            for (Object hugo : v.getAdyacentes()) {
                TAdyacencia ady = (TAdyacencia) hugo;
                TVertice w = (TVertice) ady.getDestino();
                if (!w.getVisitado()) {
                    w.setVisitado(true);
                    cola.add(w);
                }
            }
        }
    }

    @Override
    public boolean esConexo(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean conectados(IVertice origen, IVertice destino) {
        return false;
    }
}