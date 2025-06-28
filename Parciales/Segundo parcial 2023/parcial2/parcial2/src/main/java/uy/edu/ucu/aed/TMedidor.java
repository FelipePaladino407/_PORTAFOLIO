package uy.edu.ucu.aed;

/*
 * NO LICENCE 
 * Author: Ing. Agustin Paredes
 */

import java.util.Collection;
import java.util.LinkedList;


/**
 *
 * @author agustinp
 */
public class TMedidor
{
    public TDato obtenerMayorMedicion(TDato[] datos)
    {
       if (datos == null || datos.length == 0){
           return null;
       }
       TDato mayor = datos[0];

       for (int i = 1; i < datos.length; i++){
           if (datos[i].getValor() > mayor.getValor()){
               mayor = datos[i];
           }
       }
        return mayor;
    }


}
