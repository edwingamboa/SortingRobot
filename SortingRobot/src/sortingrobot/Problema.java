package sortingrobot;


import java.util.Arrays;
import java.util.Vector;

public class Problema 
{
    private final int ID_VACIA=0;
    private final int ID_ROBOT=-1;
    private final int ID_OBJETO_UNO=-2;
    private final int ID_OBJETO_DOS=-3;
    private final int ID_SITIO_UNO=-4;
    private final int ID_SITIO_DOS=-5; 
    private Estado estadoInicial; //Archivo ingresado
    private int carga, filaSitioUno, columnaSitioUno, filaSitioDos, columnaSitioDos;
    
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
        
        for(int i=0;i<todosLosMovimientosPosibles.size();i++){
            Matriz referenciada=estadoInicial.getMatriz();
            Matriz nueva=new Matriz();
            nueva.setCargaRobot(carga);
            nueva.setCoordenadasSitioUno(filaSitioUno, columnaSitioUno);
            nueva.setCoordenadasSitioDos(filaSitioDos, columnaSitioDos);
            
            int dimension=referenciada.getDimension();
            int[][] matrizNueva = new int[dimension][dimension];            
            for (int j=0;j<dimension;j++){
                matrizNueva[j] = Arrays.copyOf(referenciada.getMatriz()[j],dimension);
            }            
            nueva.setDimension(dimension);
            nueva.setMatriz(matrizNueva);
            Estado estadoPrueba=new Estado(nueva);

            if( estadoPrueba.verificarMovimientoValido(todosLosMovimientosPosibles.elementAt(i))){                
                Operador operador=todosLosMovimientosPosibles.elementAt(i);
                estadoPrueba.moverRobot(operador);
                ParOperadorEstado pareja=new ParOperadorEstado(operador,estadoPrueba);
                retorno.add(pareja);
                estadoPrueba=null; 
                carga += nueva.getSortingRobot().getCarga();
                if(nueva.getSitioUno().getFila()!=0)
                    filaSitioUno = nueva.getSitioUno().getFila();
                if(nueva.getSitioUno().getColumna()!=0)
                    columnaSitioUno = nueva.getSitioUno().getColumna();
                if(nueva.getSitioDos().getFila()!=0)
                    filaSitioUno = nueva.getSitioDos().getFila();
                if(nueva.getSitioDos().getColumna()!=0)
                    columnaSitioUno = nueva.getSitioDos().getColumna();
            }
        }
        return retorno;
    }

    public boolean pruebaMeta(Estado estado){
        Matriz matriz=estado.getMatriz();
        int[] posObjetoUno = matriz.retornarCoordenadaDeObjetos(ID_OBJETO_UNO);
        int[] posObjetoDos = matriz.retornarCoordenadaDeObjetos(ID_OBJETO_DOS);
        int[] posRobot = matriz.retornarCoordenadaDeObjetos(ID_ROBOT);
        int[] posSitioUno = matriz.retornarCoordenadaDeObjetos(ID_SITIO_UNO);        
        int[] posSitioDos = matriz.retornarCoordenadaDeObjetos(ID_SITIO_DOS);        
        if(posObjetoUno==null && posObjetoDos==null && posSitioUno==null && posSitioDos==null
                &&((posRobot[0]==filaSitioUno && posRobot[1]==columnaSitioUno)
                || (posRobot[0]==filaSitioDos && posRobot[1]==columnaSitioDos) ))    
        {
            this.carga=0;
            return true;        
        }    
        return false;
    }

    public Vector<Operador> generarOperadores(){
        Vector<Operador> operaciones=new Vector();      
        char[] direcciones={'u','d','l','r'};         
        for(int j=0;j<direcciones.length;j++){
            Operador operador=new Operador(direcciones[j]);
            operaciones.add(operador);                
        }
        return operaciones;
    }
    
    public int getCarga(){
        return carga;
    }     
    
    /*Revisar
     public int getFila()
    {
        return filaSitioUno;
    }        
    
    public int getColumna()
    {
        return columnaSitioUno;
    }*/
}
