package org.example;

import java.util.*;

public class TGrafoDirigido implements IGrafoDirigido {

    private Map<Comparable, IVertice> vertices; // vertices del grafo.-

    public TGrafoDirigido(Collection<IVertice> vertices, Collection<IArista> aristas) {
        this.vertices = new HashMap<>();
        for (IVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (IArista arista : aristas) {
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
            IVertice vertOrigen = buscarVertice(nomVerticeOrigen);
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
        IVertice vertOrigen = buscarVertice(etiquetaOrigen);
        IVertice vertDestino = buscarVertice(etiquetaDestino);
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
    private IVertice buscarVertice(Comparable unaEtiqueta) {
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
    public boolean insertarArista(IArista arista) {
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            IVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            IVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
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
            IVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override
    public boolean insertarVertice(IVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, IVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    /**
     * @return the vertices
     */
    public Map<Comparable, IVertice> getVertices() {
        return vertices;
    }

    public Comparable centroDelGrafo() {
        int n = vertices.size();
        Comparable[] etiquetas = vertices.keySet().toArray(new Comparable[n]);

        Comparable centro = null;
        Comparable excentricidadMin = null;

        for (Comparable etiqueta : etiquetas) {
            Comparable excentricidad = obtenerExcentricidad(etiqueta);

            if (excentricidad != null) {
                if (excentricidadMin == null || excentricidad.compareTo(excentricidadMin) < 0) {
                    excentricidadMin = excentricidad;
                    centro = etiqueta;
                }
            }
        }
        return centro;
    }

    /**
     * UT7_TA3 Ejercicio 2 - Desarrollo floyd.
     */
    @Override
    public Double[][] floyd() {
        int n = this.getVertices().size();
        Double[][] dist = UtilGrafos.obtenerMatrizCostos(this.getVertices());
        Object[] etiquetas = this.getVertices().keySet().toArray();

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
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

    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        int n = vertices.size();
        Comparable[] etiquetas = vertices.keySet().toArray(new Comparable[n]);
        int indice = -1;

        // busca el indice del vertice con la etiqueta dada
        for (int i = 0; i < n; i++) {
            if (etiquetas[i].equals(etiquetaVertice)) {
                indice = i;
            }
        }

        if (indice == -1) {
            return null;
        }

        Double[][] matrizFloyd = floyd();
        double maxDistancia = -1;

        for (int j = 0; j < n; j++) {
            Double valor = matrizFloyd[j][indice];

            if (valor != null && !valor.isInfinite() && valor > maxDistancia) {
                maxDistancia = valor;
            }
        }

        if (maxDistancia == -1) {
            return null;
        } else {
            return maxDistancia;
        }
    }

    public Collection<IVertice> bpf() {
        int n = vertices.size();
        Comparable[] etiquetas = vertices.keySet().toArray(new Comparable[n]);
        Collection<IVertice> bpfList = new LinkedList<>();
        this.bpf(etiquetas[0], bpfList);
        desvisitarVertices();
        return bpfList;
    }



    public Collection<IVertice> bpf(Comparable etiquetaOrigen, Collection<IVertice> bpfList) {
        buscarVertice(etiquetaOrigen).setVisitado(true);
        bpfList.add(buscarVertice(etiquetaOrigen));

        Collection<IAdyacencia> adyacentes = buscarVertice(etiquetaOrigen).getAdyacentes();
        for (IAdyacencia adyacencia : adyacentes) {
            IVertice w = adyacencia.getDestino();
            if (!w.getVisitado()) {
                this.bpf(w.getEtiqueta(), bpfList);
            }
        }
        return bpfList;
        }



    public Collection<IVertice> bpf(IVertice vertice) {
        Collection<IVertice> bpfList = new LinkedList<>();
        bpf(vertice.getEtiqueta(), bpfList);
        desvisitarVertices();
        return bpfList;
    }


    @Override
    public boolean[][] warshall() {
        int n = this.getVertices().size();
        boolean[][] accesibilidad = new boolean[n][n];

        Comparable[] etiquetas = this.getVertices().keySet().toArray(new Comparable[0]);

        for (int pp = 0; pp < n; pp++) {
            TVertice verticePP = (TVertice) this.getVertices().get(etiquetas[pp]);
            for (int j = 0; j < n; j++) {
                TVertice verticeJ = (TVertice) this.getVertices().get(etiquetas[j]);
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

    @Override
    public void desvisitarVertices() {
       for (IVertice vertice : vertices.values()) {
           vertice.setVisitado(false);
       }
    }
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TCaminos todosLosCaminos = new TCaminos();
        TVertice v = (TVertice) buscarVertice(etiquetaOrigen);
        if(v != null){
            TCamino caminoPrevio = new TCamino(v);
            v.todosLosCaminos(etiquetaDestino, caminoPrevio, todosLosCaminos);
            return todosLosCaminos;
        }
        return null;
    }
}
