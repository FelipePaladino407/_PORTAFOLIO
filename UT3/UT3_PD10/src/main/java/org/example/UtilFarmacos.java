// UtilFarmacos.java
package org.example;

public class UtilFarmacos {
    private final Lista<Suero> sueros       = new Lista<>();
    private final Lista<Farmaco> farmacos   = new Lista<>();
    private final Lista<Integer> listaBlanca= new Lista<>();
    private final Lista<ParNegro> listaNegra= new Lista<>();

    public UtilFarmacos() {
        cargarSueroYFarmacos();
        cargarListasBlancaNegra();
    }

    private void cargarSueroYFarmacos() {
        // sueros.txt → Lista<Suero>
        String[] lines = ManejadorArchivosGenerico.leerArchivo("sueros.txt");
        for (String l : lines) {
            String[] f = l.split(",", 2);
            int id = Integer.parseInt(f[0].trim());
            sueros.insertar(new Nodo<>(id, new Suero(id, f[1].trim())));
        }
        // farmacos.txt → Lista<Farmaco>
        lines = ManejadorArchivosGenerico.leerArchivo("farmacos.txt");
        for (String l : lines) {
            String[] f = l.split(",", 2);
            int id = Integer.parseInt(f[0].trim());
            farmacos.insertar(new Nodo<>(id, new Farmaco(id, f[1].trim())));
        }
    }

    private void cargarListasBlancaNegra() {
        // listablanca.txt → Lista<Integer>
        String[] wb = ManejadorArchivosGenerico.leerArchivo("listaBlanca.txt");
        for (String l : wb) {
            int idF = Integer.parseInt(l.trim());
            listaBlanca.insertar(new Nodo<>(idF, idF));
        }
        // listanegra.txt → Lista<ParNegro>
        String[] bl = ManejadorArchivosGenerico.leerArchivo("listaNegra.txt");
        for (String l : bl) {
            String[] p = l.split(",");
            int idS = Integer.parseInt(p[0].trim());
            int idF = Integer.parseInt(p[1].trim());
            listaNegra.insertar(new Nodo<>(idS, new ParNegro(idS, idF)));
        }
    }

    public Suero obtenerSuero(int idSuero) {
        Nodo<Suero> n = sueros.buscar(idSuero);
        return n == null ? null : n.getDato();
    }

    public Farmaco obtenerFarmaco(int idFarmaco) {
        Nodo<Farmaco> n = farmacos.buscar(idFarmaco);
        return n == null ? null : n.getDato();
    }

    private boolean esBlanco(int idF) {
        return listaBlanca.buscar(idF) != null;
    }

    private boolean esNegro(int idS, int idF) {
        Nodo<ParNegro> aux = listaNegra.getPrimero();
        while (aux != null) {
            ParNegro p = aux.getDato();
            if (p.getSueroId() == idS && p.getFarmacoId() == idF) return true;
            aux = aux.getSiguiente();
        }
        return false;
    }

    /**
     * Firma: preparadoViable(Suero: idSuero, Farmacos: Lista<Integer>) : boolean
     */
    public boolean preparadoViable(int sueroId, Lista<Integer> farmacosPropuestos) {
        Nodo<Integer> aux = farmacosPropuestos.getPrimero();
        while (aux != null) {
            int idF = (int) aux.getEtiqueta();
            if (esBlanco(idF)) {
                aux = aux.getSiguiente();
                continue;
            }
            if (esNegro(sueroId, idF)) {
                return false;
            }
            return false;
        }
        return true;
    }
}
