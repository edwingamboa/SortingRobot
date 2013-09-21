package sortingrobot;

import java.util.Vector; 
import javax.swing.JOptionPane;

public class Matriz
{
    private final int ID_VACIA=0;
    private final int ID_ROBOT=-1;
    private final int ID_OBJETO_UNO=-2;
    private final int ID_OBJETO_DOS=-3;
    private final int ID_SITIO_UNO=-4;
    private final int ID_SITIO_DOS=-5;   
    
    private int[] conjuntoIds={ID_VACIA, ID_ROBOT, ID_OBJETO_UNO, ID_OBJETO_DOS,
        ID_SITIO_UNO, ID_SITIO_DOS};
    private int dimension;
    private Vector<int[]> vectorFila;
    private int[][] matriz;
    private char[] direcciones={'u','d','l','r'}; //Conjunto direcciones    
    private char[] conjuntoEstados={'0','1'};
    private SortingRobot robot=new SortingRobot();
    private SitioObjeto sitioUno=new SitioObjeto();
    private SitioObjeto sitioDos=new SitioObjeto();
    private int pesoObjetoUno, pesoObjetoDos, carga, pesoTotal;

    public Matriz(Vector<int[]> filas){
        this.dimension = filas.elementAt(0)[0];
        this.pesoObjetoUno = filas.elementAt(0)[1];
        this.pesoObjetoDos = filas.elementAt(0)[1];
        matriz = new int[dimension][dimension];
        this.vectorFila=filas;
        construirMatriz();        
    }

    public Matriz(){}
    
    public int[][] getMatriz()
    {
        return this.matriz;
    }

    
    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
    
    public void setMatriz(int[][] nuevaMatriz)
    {
        this.matriz=nuevaMatriz; 
    }
    
    public void setCargaRobot(int carga){
        this.carga = carga;
    }        
    
    public int getCargaRobot(){
        return carga;
    }        
    
    public SortingRobot getSortingRobot(){
        return this.robot;
    }
    
    public SitioObjeto getSitioUno(){
        return this.sitioUno;
    }
    
    public SitioObjeto getSitioDos(){
        return this.sitioUno;
    }
    
    public void setCoordenadasSitioUno(int fila, int columna){
        this.sitioUno.setCoordenadas(fila, columna);
    }
    
    public void setCoordenadasSitioDos(int fila, int columna){
        this.sitioDos.setCoordenadas(fila, columna);
    }
    
    public int getCargaSortingRobot()
    {
        return this.robot.getCarga();
    } 
    
    public int getDepositoSortingRobot()
    {
        return this.robot.getValorDeposito();
    }
  
    public void construirMatriz()
    {
       for(int fila=1;fila<dimension;fila++){
           for(int columna=0;columna<dimension;columna++)
               matriz[fila][columna]=vectorFila.elementAt(fila)[columna];
       }
    } 

    
    public boolean verificarSiEstaLibre(int fila,int columna)
    {
        try{
            if(estaDentroDeMatriz(fila, columna)){
                if(esObjetoUno(fila, columna)
                        || esObjetoDos(fila, columna)
                        || esSitioUno(fila, columna)
                        || esSitioDos(fila, columna))
                    return true;                
            }
        }catch(ArrayIndexOutOfBoundsException e)
        {} 
        return false;
    }

    public boolean estaDentroDeMatriz(int fila,int columna){        
         if(((fila>=0) && (fila<dimension))
                 &&((columna>=0) && (columna<dimension)))
           return true;
        return false;
    }
    
    public boolean esCeldaVacia(int fila,int columna){        
         if(matriz[fila][columna]==ID_VACIA)
           return true;
        return false;
    }  
    
    public boolean esObjetoUno(int fila,int columna){        
         if(matriz[fila][columna]==ID_OBJETO_UNO)
           return true;
        return false;
    }
    
    public boolean esObjetoDos(int fila,int columna){        
         if(matriz[fila][columna]==ID_OBJETO_DOS)
           return true;
        return false;
    }
    
    public boolean esSitioUno(int fila,int columna){        
         if(matriz[fila][columna]==ID_SITIO_UNO)
           return true;
        return false;
    }
    
    public boolean esSitioDos(int fila,int columna){        
         if(matriz[fila][columna]==ID_SITIO_DOS)
           return true;
        return false;
    } 
    
    public boolean verificarMovimientoValido(int fila,int columna,char direccion )
    {
        if((0<=fila)&&(dimension>fila))
        {
            if(((1<=columna)&&(dimension>columna)))
            {
                if((direccion=='l') && (verificarSiEstaLibre(fila,columna-1)))
                    return true;                
            }
            if(((0<=columna)&&(dimension-2>columna)))
            {
                 if((direccion=='r') && (verificarSiEstaLibre(fila,columna+1)))
                     return true;        
            }
        } 

        if((0<=columna)&&(dimension>columna))
        {
            if(((1<=fila)&&(dimension>fila))){
                 if((direccion=='u') && (verificarSiEstaLibre(fila-1,columna)))
                     return true;
            }
            if(((0<=fila)&&(dimension-2>fila))){
                 if((direccion=='d') && (verificarSiEstaLibre(fila+1,columna)))
                     return true;                 
            }
        }
        return false;
    }

   public boolean moverAIzquierda(int fila,int columna){
       if(esObjetoUno(fila,columna-1)){                         
             int valorACorrer=matriz[fila][columna];
             actualizarCasilla(fila,columna, ID_VACIA);
             this.robot.montarCarga(pesoObjetoUno);                         
             actualizarCasilla(fila,columna-1,valorACorrer);
             if((getCargaRobot()!= pesoTotal)&&(retornarCoordenadaDeObjetos(ID_SITIO_UNO)==null))
                 actualizarCasilla(sitioUno.getFila(), sitioUno.getColumna(), ID_SITIO_UNO);
             return true;
         }                     

         if(esObjetoDos(fila,columna-1)){
             int valorACorrer=matriz[fila][columna];
             actualizarCasilla(fila,columna,ID_VACIA);
             this.robot.montarCarga(pesoObjetoDos);
             actualizarCasilla(fila,columna-1,valorACorrer);    
             if((getCargaRobot()!=pesoTotal)&&(retornarCoordenadaDeObjetos(ID_SITIO_DOS)==null))
                 actualizarCasilla(sitioDos.getFila(),sitioDos.getColumna(),ID_SITIO_DOS);
             return true;
         }

         if(esSitioUno(fila,columna-1)){
             this.sitioUno.setCoordenadas(fila,columna-1);
             int valorACorrer=matriz[fila][columna];                         
             //En lugar de ID_VACIA habia un 1
             actualizarCasilla(fila,columna,ID_VACIA); 
             actualizarCasilla(fila,columna-1,valorACorrer);                         
             return true;
         }
         
         if(esSitioDos(fila,columna-1)){
             this.sitioDos.setCoordenadas(fila,columna-1);
             int valorACorrer=matriz[fila][columna];                         
             actualizarCasilla(fila,columna,ID_VACIA); 
             actualizarCasilla(fila,columna-1,valorACorrer);                         
             return true;
         }

         int valorACorrer=matriz[fila][columna];
         actualizarCasilla(fila,columna,ID_VACIA);
         actualizarCasilla(fila,columna-1,valorACorrer);
         if((getCargaRobot()!= pesoTotal)&&(retornarCoordenadaDeObjetos(ID_SITIO_UNO)==null))
             actualizarCasilla(sitioUno.getFila(), sitioUno.getColumna(), ID_SITIO_UNO);
         else if((getCargaRobot()!=pesoTotal)&&(retornarCoordenadaDeObjetos(ID_SITIO_DOS)==null))
             actualizarCasilla(sitioDos.getFila(),sitioDos.getColumna(),ID_SITIO_DOS);
         return true;
   }
   
   public boolean moverADerecha (int fila,int columna){
         if(esObjetoUno(fila,columna+1)){
             int valorACorrer=matriz[fila][columna];
             actualizarCasilla(fila,columna, ID_VACIA);
             this.robot.montarCarga(pesoObjetoUno);
             actualizarCasilla(fila,columna+1,valorACorrer);
             if((getCargaRobot()!=pesoTotal)&&(retornarCoordenadaDeObjetos(ID_SITIO_UNO)==null))
                 actualizarCasilla(sitioUno.getFila(), sitioUno.getColumna(), ID_SITIO_UNO);
             return true;
         }                     

         if(esObjetoDos(fila,columna+1)){
             int valorACorrer=matriz[fila][columna];
             actualizarCasilla(fila,columna, ID_VACIA);
             this.robot.montarCarga(pesoObjetoDos);
             actualizarCasilla(fila,columna+1,valorACorrer);
             if((getCargaRobot()!=pesoTotal)&&(retornarCoordenadaDeObjetos(ID_SITIO_DOS)==null))
                 actualizarCasilla(sitioDos.getFila(),sitioDos.getColumna(),ID_SITIO_DOS);
             return true;
         }

         if(esSitioUno(fila,columna+1)){ 
             this.sitioUno.setCoordenadas(fila,columna+1);
             int valorACorrer=matriz[fila][columna];
             actualizarCasilla(fila,columna, ID_VACIA); 
             actualizarCasilla(fila,columna+1,valorACorrer);
             return true;
         }

         if(esSitioDos(fila,columna+1)){ 
             this.sitioDos.setCoordenadas(fila,columna+1);
             int valorACorrer=matriz[fila][columna];
             actualizarCasilla(fila,columna, ID_VACIA); 
             actualizarCasilla(fila,columna+1, valorACorrer);
             return true;
         }

         int valorACorrer=matriz[fila][columna];
         actualizarCasilla(fila,columna,ID_VACIA);
         actualizarCasilla(fila,columna+1,valorACorrer);
         if((getCargaRobot()!= pesoTotal)&&(retornarCoordenadaDeObjetos(ID_SITIO_UNO)==null))
             actualizarCasilla(sitioUno.getFila(), sitioUno.getColumna(), ID_SITIO_UNO);
         else if((getCargaRobot()!=pesoTotal)&&(retornarCoordenadaDeObjetos(ID_SITIO_DOS)==null))
             actualizarCasilla(sitioDos.getFila(),sitioDos.getColumna(),ID_SITIO_DOS);
         return true;
   }
   
   public boolean moverArriba(int fila, int columna){
         if(esObjetoUno(fila-1,columna)){
             int valorACorrer=matriz[fila][columna];
             actualizarCasilla(fila,columna,ID_VACIA);
             this.robot.montarCarga(pesoObjetoUno);
             actualizarCasilla(fila-1,columna,valorACorrer);
             if((getCargaRobot()!=pesoTotal)&&(retornarCoordenadaDeObjetos(ID_SITIO_UNO)==null))
                 actualizarCasilla(sitioUno.getFila(), sitioUno.getColumna(), ID_SITIO_UNO);
             return true;
         }          

         if(esObjetoDos(fila-1,columna)){
             int valorACorrer=matriz[fila][columna];
             actualizarCasilla(fila,columna, ID_VACIA);
             this.robot.montarCarga(pesoObjetoDos);
             actualizarCasilla(fila-1,columna,valorACorrer);
             if((getCargaRobot()!=pesoTotal)&&(retornarCoordenadaDeObjetos(ID_SITIO_DOS)==null))
                 actualizarCasilla(sitioDos.getFila(),sitioDos.getColumna(),ID_SITIO_DOS);
             return true;
         }                     

         if(esSitioUno(fila-1,columna)){
            this.sitioUno.setCoordenadas(fila-1,columna);                        
            int valorACorrer=matriz[fila][columna];                        
            actualizarCasilla(fila,columna,ID_VACIA);
            actualizarCasilla(fila-1,columna,valorACorrer);
            return true;
         }
         
         if(esSitioDos(fila-1,columna)){
            this.sitioDos.setCoordenadas(fila-1,columna);                        
            int valorACorrer=matriz[fila][columna];                        
            actualizarCasilla(fila,columna,ID_VACIA);
            actualizarCasilla(fila-1,columna,valorACorrer);
            return true;
         }

         int valorACorrer=matriz[fila][columna];
         actualizarCasilla(fila,columna,ID_VACIA);
         actualizarCasilla(fila-1,columna,valorACorrer);
         if((getCargaRobot()!= pesoTotal)&&(retornarCoordenadaDeObjetos(ID_SITIO_UNO)==null))
             actualizarCasilla(sitioUno.getFila(), sitioUno.getColumna(), ID_SITIO_UNO);
         else if((getCargaRobot()!=pesoTotal)&&(retornarCoordenadaDeObjetos(ID_SITIO_DOS)==null))
             actualizarCasilla(sitioDos.getFila(),sitioDos.getColumna(),ID_SITIO_DOS);
         return true;
   }
   
   public boolean moverAbajo(int fila, int columna){
         if(esObjetoUno(fila+1,columna)){
             int valorACorrer=matriz[fila][columna];
             actualizarCasilla(fila,columna,ID_VACIA);
             this.robot.montarCarga(pesoObjetoUno);
             actualizarCasilla(fila+1,columna,valorACorrer);
             if((getCargaRobot()!=pesoTotal)&&(retornarCoordenadaDeObjetos(ID_SITIO_UNO)==null))
                 actualizarCasilla(sitioUno.getFila(), sitioUno.getColumna(), ID_SITIO_UNO);
             return true;
         }                     

         if(esObjetoDos(fila+1,columna)){
             int valorACorrer=matriz[fila][columna];
             actualizarCasilla(fila,columna,ID_VACIA);                         
             this.robot.montarCarga(pesoObjetoDos);
             actualizarCasilla(fila+1,columna,valorACorrer);
             if((getCargaRobot()!=pesoTotal)&&(retornarCoordenadaDeObjetos(ID_SITIO_DOS)==null))
                 actualizarCasilla(sitioDos.getFila(),sitioDos.getColumna(),ID_SITIO_DOS);
             return true;
         }                     

         if(esSitioUno(fila+1,columna)){
             this.sitioUno.setCoordenadas(fila+1,columna);                         
             int valorACorrer=matriz[fila][columna];                         
             actualizarCasilla(fila,columna,ID_VACIA);
             actualizarCasilla(fila+1,columna,valorACorrer);
             return true;
         }
         if(esSitioDos(fila+1,columna)){
             this.sitioDos.setCoordenadas(fila+1,columna);                         
             int valorACorrer=matriz[fila][columna];                         
             actualizarCasilla(fila,columna,ID_VACIA);
             actualizarCasilla(fila+1,columna,valorACorrer);
             return true;
         }
         int valorACorrer=matriz[fila][columna];
         actualizarCasilla(fila,columna, ID_VACIA);
         actualizarCasilla(fila+1,columna,valorACorrer);
         if((getCargaRobot()!= pesoTotal)&&(retornarCoordenadaDeObjetos(ID_SITIO_UNO)==null))
             actualizarCasilla(sitioUno.getFila(), sitioUno.getColumna(), ID_SITIO_UNO);
         else if((getCargaRobot()!=pesoTotal)&&(retornarCoordenadaDeObjetos(ID_SITIO_DOS)==null))
             actualizarCasilla(sitioDos.getFila(),sitioDos.getColumna(),ID_SITIO_DOS);
         return true;
   }
   
    public boolean moverFicha(int fila,int columna,char direccion){ 
        //Si es movimiento horizontal
        if((columna>=0)&&(columna<dimension)){ 
            if((columna>=0)&&(columna<dimension)){
                 if((direccion=='l') && (verificarSiEstaLibre(fila,columna-1))){
                     moverAIzquierda(fila, columna);
                 }
            }
            if((columna>=0)&&(columna<dimension)){
                 if((direccion=='r') && (verificarSiEstaLibre(fila,columna+1))){
                     moverADerecha(fila, columna);                    
                 } 
            }
        }
        //Si es movimiento vertical
        if((fila>=0)&&(fila<dimension)){
            if((fila>=0)&&(fila<dimension)){
                 if((direccion=='u') && (verificarSiEstaLibre(fila-1,columna))){ 
                     moverArriba(fila, columna);
                 }
            }
            if((fila>=0)&&(fila<dimension)){
                 if((direccion=='d') && (verificarSiEstaLibre(fila+1,columna))){
                     moverAbajo(fila, columna);
                 }
            }
        } 
        JOptionPane.showMessageDialog(null,"Movimiento fuera de Rango");
        return false;
    }

    public void actualizarCasilla(int fila,int columna,int nuevoValor){
        matriz[fila][columna]=nuevoValor;
    }
    

    /**
     * Retorna las coordenadas de los objetos en la matriz, 
     * ubicandolos de acuerdo a su nombre (Código asignado).
     * @param idObjeto Nombre que identifica al objeto.
     * @return 
     */
    public int[] retornarCoordenadaDeObjetos(int idObjeto)
    { 
       int[] posicion=null;
        for(int i=0;i<dimension;i++)
        {
            for(int j=0;j<dimension;j++)
            {
               if(matriz[i][j]==idObjeto)
               {
                   posicion=new int[2];
                   posicion[0]=i;
                   posicion[1]=j;
                   
                   return posicion;
               }
            }
        }
        return posicion;

    } 

    public boolean perteneceAlConjuntoEstados(char letra)
    {
        for(int i=0;i<conjuntoEstados.length;i++)
        {
            if(conjuntoEstados[i]==letra)
                return true; 
        }
        return false;
    }

    public boolean perteneceAlConjuntoIds(int id)
    {
        for(int i=0;i<conjuntoIds.length;i++)
        {
            if(conjuntoIds[i]==id)
                return true;
        }
        return false;
    }

    public boolean perteneceAlConjuntoDirecciones(char letra)
    {
        for(int i=0;i<direcciones.length;i++)
        {
            if(direcciones[i]==letra)
                return true;
        }
        return false;
    }

    public void imprimirMatriz()
    {
        for(int i=0;i<dimension;i++)
        {
            for(int j=0;j<dimension;j++)
               System.out.print(matriz[i][j]+"\t");
            System.out.print("\n"); 
        }
    }
} 
