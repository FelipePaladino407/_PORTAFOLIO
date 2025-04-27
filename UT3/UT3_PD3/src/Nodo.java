public class Nodo<T> implements INodo<T> {

    private T dato;
    private Comparable etiqueta;          // Importante considerar esto del Comparable.
    private INodo<T> siguiente;

    public Nodo(T dato, Comparable etiqueta, INodo<T> siguiente) {
        this.dato = dato;
        this.siguiente = null;
        this.etiqueta = etiqueta;
    }


    @Override
    public T getDato() {
        return this.dato;
    }

    @Override
    public INodo<T> getSiguiente() {
        return this.siguiente;
    }

    @Override
    public void setSiguiente(INodo<T> nodo) {
        this.siguiente = nodo;
    }

    @Override
    public void imprimir() {
        System.out.println(this.dato);
    }

    @Override
    public void imprimirEtiqueta() {
        System.out.println(this.etiqueta);
    }

    @Override
    public Comparable getEtiqueta() {
        return this.etiqueta;
    }

    @Override
    public int compareTo(Comparable otraEtiqueta) {
        return this.etiqueta.compareTo(otraEtiqueta);
    }
}