package org.example;

public interface IConjunto<T> extends ILista<T> {

    public IConjunto<T> union(IConjunto<T> OtroConjunto);
    public IConjunto<T> intersection(IConjunto<T> OtroConjunto);
}
