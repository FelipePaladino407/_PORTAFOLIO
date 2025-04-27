

public class Lista<T> implements ILista<T> {

    private Nodo<T> primero;
    private int cantElementos;

    public Lista() {
        primero = null;
    }
    public Nodo<T> getPrimero() {
        return primero;
    }


    @Override
    public void insertar(T dato, Comparable clave) {
        if (esVacia()) {
            primero = new Nodo<>(clave, dato);
            cantElementos++;
        }
        else{
            Nodo<T> actual = primero;
            while(actual.getSiguiente() != null){
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(new Nodo<>(clave, dato));
            cantElementos++; }
    }

    @Override
    public T buscar(Comparable clave) {
        Nodo<T> actual = primero;
        while(actual != null){
            if(actual.getEtiqueta().equals(clave)){
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null; }

    @Override
    public boolean eliminar(Comparable clave) {
        Nodo<T> actual = primero;
        while(actual.getSiguiente() != null){
            if(actual.getSiguiente().getEtiqueta().equals(clave)){
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                cantElementos--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;}

    @Override
    public String imprimir() {
        Nodo<T> actual = primero;
        String cadena = "";
        while(actual != null){
            cadena += actual.getDato() + " ";
            actual = actual.getSiguiente();
        }
        return cadena;
    }

    @Override
    public String imprimir(String separador) {
        Nodo<T> actual = primero;
        String cadena = "";
        while(actual != null){
            cadena += actual.getDato() + separador;
            actual = actual.getSiguiente();
        }
        return cadena;
    }

    @Override
    public int cantElementos() {
        return this.cantElementos;
    }

    @Override
    public boolean esVacia() {
        return primero == null;
    }


}
