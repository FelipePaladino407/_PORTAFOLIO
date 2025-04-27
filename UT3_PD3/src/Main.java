public class Main {
    public static void main(String[] args) {

        ILista<String> lista = new Lista<>();


        lista.insertar("COC", "95");
        lista.insertar("COC", "98");
        lista.insertar("COC", "99");

        System.out.println(lista.imprimir());
    }
}