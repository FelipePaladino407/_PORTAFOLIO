

public class Lista<T> implements ILista<T> {

    private INodo<T> cabeza;
    private int cantElementos;

    public Lista() {
        this.cabeza = null;
        this.cantElementos = 0;
    }



    @Override
    public void insertar(INodo<T> pepe) {

        if (cabeza == null) {
            cabeza = pepe;
        }
        else {
            INodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(pepe);    // setSiguiente hace hace que pepe sea igual al actual.siguiente
            cantElementos++;
        }



    }

    @Override
    public void insertar(Comparable etiqueta, Object dato) {

    }



    @Override
    public T buscar(Comparable clave) {
        INodo<T> actual = cabeza;
        while (actual != null) {
            if (clave.equals(actual.getSiguiente().getDato())) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    @Override
    public boolean eliminar(Comparable clave) {
        INodo<T> anterior = cabeza;
        INodo<T> actual = anterior.getSiguiente();

        if (actual == null) {
            anterior = null;
            System.out.println("La lista solo tenia un elemento y fue exterminado.");
        }
        else {
            while (actual != null) {
                if (clave.equals(actual.getDato())) {
                    anterior.setSiguiente(actual.getSiguiente());   // Elimina al del medio.
                    cantElementos--;
                    return true;

                }
                anterior = actual;
                actual = actual.getSiguiente();
            }
        }
        return false;
    }

    @Override
    public String imprimir() {
        return "";
    }


    @Override
    public String imprimir(String separador) {
        StringBuilder sb = new StringBuilder();
        INodo<T> actual = cabeza;
        while (actual != null) {
            sb.append(actual.getEtiqueta());
            if (actual.getSiguiente() != null) {
                sb.append(separador);
            }
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }

    @Override
    public int cantElementos() {
        int contador = 0;
        INodo<T> actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    @Override
    public boolean esVacia() {
        return false;
    }

    @Override
    public void setPrimero(INodo unNodo) {

    }
}
