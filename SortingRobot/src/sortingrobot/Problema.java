package sortingrobot;


import java.util.Arrays;
import java.util.Vector;

public class Problema 
{
    private Estado estadoInicial; //Archivo ingresado
    private int carga, fila, columna;
    
    public Problema(Estado estado)
    {
        this.estadoInicial=estado;
    }

    public Estado getEstadoInicial()
    {
        return this.estadoInicial;
    }

    public Vector<ParOperadorEstado> funcionSucesor(Estado estadoInicial)
    { 
        Vector<ParOperadorEstado> retorno=new Vector();
        Vector<Operador> todosLosMovimientosPosibles=generarOperadores();

        for(int i=0;i<todosLosMovimientosPosibles.size();i++)
        {            
            Matriz referenciada=estadoInicial.getMatriz();
            Matriz nueva=new Matriz();
            nueva.setCargaDust(carga);
            //--Edwin-- en nuestro caso no solo es un punto, sino uno por cada objeto (dos)
            nueva.setFilaPuntoReciclaje(fila);
            nueva.setColumnaPuntoReciclaje(columna);

            //--Edwin-- Debe tener las mismas dimensiones de la matriz ingresada (estado iniicial)
            char[][] matrizNueva = new char[10][10];            
            for (int j=0;j<10;j++)
            {
                matrizNueva[j] = Arrays.copyOf(referenciada.getMatriz()[j],10);
            }            
            nueva.setMatriz(matrizNueva);
            Estado estadoPrueba=new Estado(nueva);

            if( estadoPrueba.verificarMovimientoValido(todosLosMovimientosPosibles.elementAt(i)))
            {                
                Operador operador=todosLosMovimientosPosibles.elementAt(i);
                estadoPrueba.moverDustCart(operador);
                ParOperadorEstado pareja=new ParOperadorEstado(operador,estadoPrueba);
                retorno.add(pareja);
                estadoPrueba=null; 
                carga += nueva.getDustCart().getCarga();
                if(nueva.getDeposito().getFila()!=0)
                    fila = nueva.getDeposito().getFila();
                if(nueva.getDeposito().getColumna()!=0)
                    columna = nueva.getDeposito().getColumna();               
            }
        }
        return retorno;
    }

    public boolean pruebaMeta(Estado estado)
    {
        Matriz matriz=estado.getMatriz();
        int[] posicion2 = matriz.retornarCoordenadaDeObjetos('2');
        int[] posicion3 = matriz.retornarCoordenadaDeObjetos('3');
        int[] posicion4 = matriz.retornarCoordenadaDeObjetos('4');
        int[] posicion5 = matriz.retornarCoordenadaDeObjetos('5');        
        if((posicion2==null)&&(posicion3==null)&&(posicion5==null)&&
        (posicion4[0]==fila)&&(posicion4[1]==columna))    
        {
            this.carga=0;
            return true;        
        }    
        return false;
    }

    public Vector<Operador> generarOperadores()
    {
        Vector<Operador> operaciones=new Vector();      
        char[] direcciones={'u','l','d','r'};
        
            for(int j=0;j<direcciones.length;j++)
            {
                Operador operador=new Operador(direcciones[j]);
                operaciones.add(operador);                
            }
            return operaciones;
    }
    
    public int getCarga()
    {
        return carga;
    }     
    
    public int getFila()
    {
        return fila;
    }        
    
    public int getColumna()
    {
        return columna;
    }
}
