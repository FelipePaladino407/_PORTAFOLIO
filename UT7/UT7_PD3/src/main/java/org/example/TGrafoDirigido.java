package org.example;


import jdk.jshell.execution.Util;

import java.util.*;

public class TGrafoDirigido implements IGrafoDirigido {

    private Map<Comparable, TVertice> vertices; // vertices del grafo.-

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     *
     */
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }


    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @return True si existe la adyacencia, false en caso contrario
     */
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Metodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    public boolean insertarArista(TArista arista) {
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse vertices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override

    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    /**
     * @return the vertices
     */
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    /**
     * UT7_PD3 Ejercicio 1
     */
    @Override
    public Comparable centroDelGrafo() {
        double menorExcentricidad = Double.MAX_VALUE;
        Comparable centro = null;

        for (TVertice vertice : getVertices().values()) {
            double excentricidad = (double) this.obtenerExcentricidad(vertice.getEtiqueta());
            if (excentricidad < menorExcentricidad) {
                menorExcentricidad = excentricidad;
                centro = vertice.getEtiqueta();
            }
        }
        return centro;
    }

    /**
     * UT7_PD3 Ejercicio 1.
     */
    @Override
    public Double[][] floyd() {
        int n = this.getVertices().size();
        Double[][] dist = UtilGrafos.obtenerMatrizCostos(this.getVertices());

        // Tambien podria ser: Object[] etiquetas = this.getVertices().keySet().toArray()
        Comparable[] etiquetas = this.getVertices().keySet().toArray(new Comparable[0]);

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (dist[i][k] != Double.MAX_VALUE && dist[k][j] != Double.MAX_VALUE) {
                        if (dist[i][j] > dist[i][k] + dist[k][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
        }
        return dist;


    }

    /**
     * UT7_PD3 Ejercicio 1
     */
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        Double[][] distancias = this.floyd();
        Object[] etiquetas = this.getVertices().keySet().toArray();
        int index = -1;

        for (int i = 0; i < etiquetas.length; i++) {
            if (etiquetas[i].equals(etiquetaVertice)) {
                index = i;
                break;
            }
        }

        if (index == -1) return -1;

        double max = -1;
        for (int j = 0; j < distancias.length; j++) {
            if (distancias[j][index] == Double.MAX_VALUE) return -1;
            if (distancias[j][index] > max) {
                max = distancias[j][index];
            }
        }
        return max;
    }


    /**
     * UT7_PD3 Ejercicio 2.
     */
    @Override
    public boolean[][] warshall() {
       int n = this.getVertices().size();
       boolean[][] accesibilidad = new boolean[n][n];

       Comparable[] etiquetas = this.getVertices().keySet().toArray(new Comparable[0]);

       for (int pp = 0; pp < n; pp++) {
           TVertice verticePP = this.getVertices().get(etiquetas[pp]);
           for (int j = 0; j < n; j++) {
               TVertice verticeJ = this.getVertices().get(etiquetas[j]);
               // Aclaracion: Double.MAX_VALUE es como poner "infito", ya que considero
               // el numero Double mas alto posible.
               accesibilidad[pp][j] = verticePP.obtenerCostoAdyacencia(verticeJ) < Double.MAX_VALUE;
           }
           // Warshall en si.
           for (int k = 0; k < n; k++) {
               for (int i = 0; i < n; i++) {
                   for (int j = 0; j < n; j++) {


                   }
               }
           }
       }
        return accesibilidad;
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * UT7_PD3 Ejercicio 3.
     * Orden del tiempo de ejecucion??? O(N).
     */
    @Override
    public void bpf(Comparable unaEtiqueta) {

        for (TVertice v : this.getVertices().values()) {
            v.setVisitado(false);
        }

        TVertice verticeOrigen = this.getVertices().get(unaEtiqueta);

        if (verticeOrigen == null)
        { System.out.println("El vertice " + unaEtiqueta + " no existe en el grafo.");
        return;}

        System.out.println("Iniciando bpf desde: " + unaEtiqueta);
        bpfRecursivo(verticeOrigen);

    }

    private void bpfRecursivo(TVertice vertice) {
        vertice.setVisitado(true);
        System.out.println(vertice.getEtiqueta());

        // Tengo que castearlo por que no hay forma de que me deje poner "TAdyacencia" en vez de "Object".
        for (Object obj : vertice.getAdyacentes()) {
            TAdyacencia adyacente = (TAdyacencia) obj;
            TVertice destino = adyacente.getDestino();
            if (!destino.getVisitado()) {
                bpfRecursivo(destino);
            }
        }

    }

    public void bpfCompleto(Comparable unaEtiqueta) {
        for (TVertice v : this.getVertices().values()) {
            v.setVisitado(false);
        }

        TVertice verticeOrigen = this.getVertices().get(unaEtiqueta);

        if (verticeOrigen == null)
        { System.out.println("El vertice " + unaEtiqueta + " no existe en el grafo.");
            return;}

        System.out.println("Iniciando bpf desde: " + unaEtiqueta);
        bpfRecursivo(verticeOrigen);

        for (TVertice v : this.getVertices().values()) {
            if (!v.getVisitado()) {
                System.out.println("Caminos completo desde : "+ v.getEtiqueta());
                bpfRecursivo(v);
            }
        }
    }

    public List<List<Comparable>> obtenerTodosLosCaminos(Comparable origen, Comparable destino) {
        List<List<Comparable>> caminos = new LinkedList<>();
        LinkedList<Comparable> caminoActual = new LinkedList<>();
        Set<Comparable> visitados = new HashSet<>();

        obtenerTodosLosCaminosRec(origen, destino, visitados, caminoActual, caminos);
        return caminos;
    }

    private void obtenerTodosLosCaminosRec(
            Comparable actual,
            Comparable destino,
            Set<Comparable> visitados,
            LinkedList<Comparable> caminoActual,
            List<List<Comparable>> caminos
    ) {
        visitados.add(actual);
        caminoActual.add(actual);

        if (actual.equals(destino)) {
            caminos.add(new ArrayList<>(caminoActual));
        } else {
            TVertice verticeActual = this.getVertices().get(actual);
            if (verticeActual != null) {
                for (Object objete : verticeActual.getAdyacentes()) {
                    TAdyacencia adyacente = (TAdyacencia) objete;
                    Comparable siguiente = adyacente.getDestino().getEtiqueta();
                    if (!visitados.contains(siguiente)) {
                        obtenerTodosLosCaminosRec(siguiente, destino, visitados, caminoActual, caminos);
                    }
                }
            }
        }

        visitados.remove(actual);
        caminoActual.removeLast();
    }



}