/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: Matriz
******************************************************************************/

package sortingrobot;

import java.util.Vector; 
import javax.swing.JOptionPane;
import static sortingrobot.IdObjetos.ID_SITIO_UNO_CON_ROBOT;

public class Matriz implements IdObjetos{    
    private int[] conjuntoIds={ID_VACIA, ID_ROBOT, ID_OBJETO_UNO, ID_OBJETO_DOS,
        ID_SITIO_UNO, ID_SITIO_DOS, ID_CELDA_INVALIDA};
    private int dimension;
    private Vector<int[]> vectorFila;
    private int[][] matriz;
    private char[] direcciones={'u','d','l','r'}; //Conjunto direcciones    
    private char[] conjuntoEstados={'0','1'};
    private SortingRobot robot=new SortingRobot();
    private SitioObjeto sitioUno=new SitioObjeto();
    private SitioObjeto sitioDos=new SitioObjeto();
    private int pesoObjetoUno, pesoObjetoDos, carga, pesoTotal;
    private int[][] matrizPenalizaciones;

    public Matriz(Vector<int[]> filas){
        this.dimension = filas.elementAt(0)[0];
        this.pesoObjetoUno = filas.elementAt(0)[1];
        this.pesoObjetoDos = filas.elementAt(0)[2];
        pesoTotal = pesoObjetoUno + pesoObjetoDos;
        matriz = new int[dimension][dimension];
        matrizPenalizaciones = new int[dimension][dimension];
        this.vectorFila=filas;
        construirMatriz();        
    }

    public Matriz(){        
    }
    
    
    
    public int[][] getMatriz()
    {
        return this.matriz;
    }

    
    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension){
        this.dimension = dimension;
    }
    
    public void setMatriz(int[][] nuevaMatriz){
        this.matriz=nuevaMatriz; 
    }

    public int[][] getMatrizPenalizaciones() {
        return matrizPenalizaciones;
    }

    public void setMatrizPenalizaciones(int[][] _matrizPenalizaciones){
        this.matrizPenalizaciones = _matrizPenalizaciones;
    }
    
    public void setCargaRobot(int carga){
        robot.setCarga(carga);
    }        
    
    public int getCargaRobot(){
        return robot.getCarga();
    }        
    
    public SortingRobot getSortingRobot(){
        return this.robot;
    }
    
    public void setSortingRobot(SortingRobot _robot){
        this.robot = _robot;
    }
    
    public SitioObjeto getSitioUno(){
        return this.sitioUno;
    }
    
    public SitioObjeto getSitioDos(){
        return this.sitioDos;
    }

    public void setPesoObjetoUno(int pesoObjetoUno) {
        this.pesoObjetoUno = pesoObjetoUno;
    }

    public void setPesoObjetoDos(int pesoObjetoDos) {
        this.pesoObjetoDos = pesoObjetoDos;
    }
    
    public int getPesoObjetoUno() {
        return pesoObjetoUno;
    }

    public int getPesoObjetoDos() {
        return pesoObjetoDos;
    }

    public int getPesoTotal() {
        return pesoTotal;
    }
        
    public void setCoordenadasSitioUno(int fila, int columna){
        this.sitioUno.setCoordenadas(fila, columna);
    }
    
    public void setCoordenadasSitioDos(int fila, int columna){
        this.sitioDos.setCoordenadas(fila, columna);       
        
    }
    
    public void verificarSitios(){
        int[] coorRobot = retornarCoordenadaDe(ID_SITIO_UNO_CON_ROBOT);
        if(coorRobot !=null){
            if(sitioUno.getFila() != coorRobot[0] 
                    || sitioUno.getColumna() != coorRobot[1]){
                matriz[sitioUno.getFila()][sitioUno.getColumna()] = ID_SITIO_UNO;
                if(matriz[coorRobot[0]][coorRobot[1]] == ID_SITIO_DOS)
                    matriz[coorRobot[0]][coorRobot[1]] = ID_SITIO_DOS_CON_ROBOT;
                else
                    matriz[coorRobot[0]][coorRobot[1]] = ID_ROBOT;
            }
        }else {
            coorRobot = retornarCoordenadaDe(ID_SITIO_DOS_CON_ROBOT);
            if(coorRobot !=null){          
                if(sitioDos.getFila() != coorRobot[0] 
                        || sitioDos.getColumna() != coorRobot[1]){
                    matriz[sitioDos.getFila()][sitioDos.getColumna()] = ID_SITIO_DOS;
                    if(matriz[coorRobot[0]][coorRobot[1]] == ID_SITIO_UNO)
                    matriz[coorRobot[0]][coorRobot[1]] = ID_SITIO_UNO_CON_ROBOT;
                else
                    matriz[coorRobot[0]][coorRobot[1]] = ID_ROBOT;
                }
            }
        }
    }
    
    public int getDepositoSortingRobot(){
        return this.robot.getValorDeposito();
    }

   
  
    public void construirMatriz(){
       for(int fila=1;fila<vectorFila.size();fila++){
           for(int columna=0;columna<dimension;columna++){
               matriz[fila-1][columna]=vectorFila.elementAt(fila)[columna];
               if(vectorFila.elementAt(fila)[columna] < 0){
                   matrizPenalizaciones[fila-1][columna]=ID_VACIA;
                   if(vectorFila.elementAt(fila)[columna] == ID_SITIO_UNO){
                       sitioUno.setCoordenadas(fila-1, columna);
                   }
                   else if(vectorFila.elementAt(fila)[columna] == ID_SITIO_DOS){
                       sitioDos.setCoordenadas(fila-1, columna);
                   }
               }
               else 
                   matrizPenalizaciones[fila-1][columna]=vectorFila.elementAt(fila)[columna];
           }
       }
    } 

    public boolean estaDentroDeMatriz(int fila,int columna){        
         if(((fila>=0) && (fila<dimension))
                 &&((columna>=0) && (columna<dimension)))
           return true;
        return false;
    }
    
    public boolean esPenalizacion(int fila,int columna){        
         if(matriz[fila][columna]>=1)
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
         if(matriz[fila][columna]==ID_SITIO_UNO || matriz[fila][columna]==ID_SITIO_UNO_CON_ROBOT)
           return true;
        return false;
    }
    
    public boolean esSitioDos(int fila,int columna){        
         if(matriz[fila][columna]==ID_SITIO_DOS || matriz[fila][columna]==ID_SITIO_DOS_CON_ROBOT)
           return true;
        return false;
    } 
    
    public boolean verificarMovimientoValido(int fila,int columna,char direccion )
    {
        if((0<=fila)&&(dimension>fila))
        {
            if(((1<=columna)&&(dimension>columna)))
            {
                if((direccion=='l') && (estaDentroDeMatriz(fila,columna-1)))
                    return true;                
            }
            if(((0<=columna)&&(dimension-2>columna)))
            {
                 if((direccion=='r') && (estaDentroDeMatriz(fila,columna+1)))
                     return true;        
            }
        } 

        if((0<=columna)&&(dimension>columna))
        {
            if(((1<=fila)&&(dimension>fila))){
                 if((direccion=='u') && (estaDentroDeMatriz(fila-1,columna)))
                     return true;
            }
            if(((0<=fila)&&(dimension-2>fila))){
                 if((direccion=='d') && (estaDentroDeMatriz(fila+1,columna)))
                     return true;                 
            }
        }
        return false;
    }
   
    /**
     * Permite mover un objeto de una celda origen a otra destino.
     * @param deFila Fila de la celda origen
     * @param deColumna Cluman de la celda origen 
     * @param aFila Fila de la celda destino
     * @param aColumna Columna de la celda destino
     * @return el valor de la celda destino, antes de ser movido el objeto.
     */
    public int moverDeA(int deFila, int deColumna, int aFila,int aColumna){
        if(esObjetoUno(aFila, aColumna)){
             int valorACorrer=matriz[deFila][deColumna];
             actualizarCasilla(deFila,deColumna, ID_VACIA);
             actualizarCasilla(aFila,aColumna, valorACorrer);
             robot.setCarga(pesoObjetoUno);
             if(retornarCoordenadaDe(ID_SITIO_UNO)==null)
                 actualizarCasilla(sitioUno.getFila(), sitioUno.getColumna(), ID_SITIO_UNO);
             return calcularCostoMovimiento(aFila, aColumna);
         }                     

         if(esObjetoDos(aFila, aColumna)){
             int valorACorrer=matriz[deFila][deColumna];
             actualizarCasilla(deFila,deColumna,ID_VACIA);
             actualizarCasilla(aFila, aColumna,valorACorrer);
             robot.setCarga(pesoObjetoDos);
             if(retornarCoordenadaDe(ID_SITIO_DOS)==null)
                 actualizarCasilla(sitioDos.getFila(),sitioDos.getColumna(),ID_SITIO_DOS);
             return calcularCostoMovimiento(aFila, aColumna);
         }

         if(esSitioUno(aFila,aColumna)){
             int valorACorrer=matriz[deFila][deColumna];
             this.sitioUno.setCoordenadas(aFila, aColumna);
             actualizarCasilla(deFila,deColumna,ID_VACIA);
             if(retornarCoordenadaDe(ID_OBJETO_UNO)==null)
                 actualizarCasilla(aFila,aColumna,valorACorrer);
             else 
                 actualizarCasilla(aFila, aColumna,ID_SITIO_UNO_CON_ROBOT);
             return calcularCostoMovimiento(aFila, aColumna);
         }
         
         if(esSitioDos(aFila,aColumna)){
             this.sitioDos.setCoordenadas(aFila, aColumna);
             actualizarCasilla(deFila,deColumna,ID_VACIA);
             if(retornarCoordenadaDe(ID_OBJETO_DOS)==null)
                 actualizarCasilla(aFila, aColumna,ID_ROBOT);
             else
                 actualizarCasilla(aFila, aColumna, ID_SITIO_DOS_CON_ROBOT);
             return calcularCostoMovimiento(aFila, aColumna);
         }
         int valorACorrer=matriz[deFila][deColumna];
         actualizarCasilla(deFila,deColumna, ID_VACIA);
         actualizarCasilla(aFila, aColumna,valorACorrer);
         return calcularCostoMovimiento(aFila, aColumna);
   }
    
    
    public int calcularCostoMovimiento(int aFila,int aColumna){ 
       //Verificar si el robot ha cargado algún objeto para sumarlo al costo
       //Si solo ha cargado el ObjetoUno pero no la ha puesto en su sitio.
       if((retornarCoordenadaDe(ID_OBJETO_UNO)==null)
               &&(retornarCoordenadaDe(ID_OBJETO_DOS)!=null)
               &&(retornarCoordenadaDe(ID_SITIO_UNO)!=null)){
           return pesoObjetoUno + matrizPenalizaciones[aFila][aColumna] + VALOR_POR_DEFECTO_CASILLAS;
       }
       //Si solo ha cargado el ObjetoDos pero no la ha puesto en su sitio.
       else if((retornarCoordenadaDe(ID_OBJETO_UNO)!=null)
               &&(retornarCoordenadaDe(ID_OBJETO_DOS)==null)
               && (retornarCoordenadaDe(ID_SITIO_DOS)!=null)){
           return pesoObjetoDos + matrizPenalizaciones[aFila][aColumna] + VALOR_POR_DEFECTO_CASILLAS;
       }
       //Si ha cargado los dos objetos
       else if((retornarCoordenadaDe(ID_OBJETO_UNO)==null)
               &&(retornarCoordenadaDe(ID_OBJETO_DOS)==null)){
           //Pero ya descargo ObjetoUno
           if(retornarCoordenadaDe(ID_SITIO_UNO)==null){
               return pesoObjetoDos + matrizPenalizaciones[aFila][aColumna] + VALOR_POR_DEFECTO_CASILLAS;
           }
           //O ya descargo ObjetoDos
           else if(retornarCoordenadaDe(ID_SITIO_DOS)==null){
               return pesoObjetoUno + matrizPenalizaciones[aFila][aColumna] + VALOR_POR_DEFECTO_CASILLAS;
           }
           //No ha descargado ningun objeto
           else{
               return pesoObjetoUno+pesoObjetoDos+ matrizPenalizaciones[aFila][aColumna] + VALOR_POR_DEFECTO_CASILLAS;
           }
       }else{
           return matrizPenalizaciones[aFila][aColumna] + VALOR_POR_DEFECTO_CASILLAS;
       }
    }
    
    public int moverFicha(int fila,int columna,char direccion){ 
        //Si es movimiento horizontal
        if((columna>=0)&&(columna<dimension)){ 
            if((columna>=0)&&(columna<dimension)){
                 if((direccion=='l') && (estaDentroDeMatriz(fila,columna-1))){
                     return moverDeA(fila, columna, fila,columna-1);
                 }
            }
            if((columna>=0)&&(columna<dimension)){
                 if((direccion=='r') && (estaDentroDeMatriz(fila,columna+1))){
                     return moverDeA(fila, columna, fila,columna+1);
                 } 
            }
        }
        //Si es movimiento vertical
        if((fila>=0)&&(fila<dimension)){
            if((fila>=0)&&(fila<dimension)){
                 if((direccion=='u') && (estaDentroDeMatriz(fila-1,columna))){ 
                     return moverDeA(fila, columna, fila-1,columna);
                 }
            }
            if((fila>=0)&&(fila<dimension)){
                 if((direccion=='d') && (estaDentroDeMatriz(fila+1,columna))){
                     return moverDeA(fila, columna, fila+1,columna);
                 }
            }
        } 
        JOptionPane.showMessageDialog(null,"Movimiento fuera de Rango");
        return ID_CELDA_INVALIDA;
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
    public int[] retornarCoordenadaDe(int idObjeto){ 
        int[] posicion=null;
        for(int i=0;i<dimension;i++){
            for(int j=0;j<dimension;j++){
               if(matriz[i][j]==idObjeto){
                   posicion=new int[2];
                   posicion[0]=i;
                   posicion[1]=j;                   
                   return posicion;
               }else if((idObjeto == ID_ROBOT || idObjeto == ID_SITIO_UNO )
                       && matriz[i][j]==ID_SITIO_UNO_CON_ROBOT){
                   posicion=new int[2];
                   posicion[0]=i;
                   posicion[1]=j;
                   
                   return posicion;
               }else if((idObjeto == ID_ROBOT || idObjeto == ID_SITIO_DOS )
                       && matriz[i][j]==ID_SITIO_DOS_CON_ROBOT){
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
