package sortingrobot;

/*****************************************************
 * Proyecto 1: UniValle Duscart                      *
 * Integrantes:                                      *
 * 1. Maria Cristina Protilla Cortes - 0844113       *
 * 2. Franco Cundar Zambrano - 1225352               *
 * Asignatura: Inteligencia Artificial               *
 * Docente: Oscar Bedoya Leiva                       *
 * Archivo: Matriz.java                              *  
 * **************************************************/ 

import java.util.Vector; 
import javax.swing.JOptionPane;

public class Matriz
{
    //--Edwin--Debemos ponerle un atributo para la dimensión
    private Vector<String> vectorFila;
    private char[][] matriz=new char[10][10];
    private char[] direcciones={'u','d','l','r'}; //Conjunto direcciones
    /*
    0 si es una casilla libre
    1 si es un obstáculo
    2 si es la casilla que tiene el depósito de basura de 2 kilos
    3 si es la casilla que tiene el depósito de basura de 3 kilos
    4 si es el punto de inicio
    5 si es el punto de reciclaje     */
    private char[] conjuntoNombres={'0','1','2','3','4','5'};
    private char[] conjuntoEstados={'0','1'};
    private SortingRobot dust=new SortingRobot();
    private SitioObjeto deposito=new SitioObjeto();
    private int carga, fila, columna;

    public Matriz(Vector<String> filas)
    {
        this.vectorFila=filas;
        construirMatriz();        
    }

    public Matriz(){}
    
    public char[][] getMatriz()
    {
        return this.matriz;
    }

    public void setMatriz(char[][] nuevaMatriz)
    {
        this.matriz=nuevaMatriz; 
    }
    
    public void setCargaDust(int carga)
    {
        this.carga = carga;
    }        
    
    public int getCargaDust()
    {
        return carga;
    }        
            
    public void setFilaPuntoReciclaje(int fila)
    {
        this.fila = fila;
    }        
    
    public int getFilaPuntoReciclaje()
    {
        return fila;
    }
    
    public void setColumnaPuntoReciclaje(int columna)
    {
        this.columna = columna;
    }        
    
    public int getColumnaPuntoReciclaje()
    {
        return columna;
    }
    
    public SortingRobot getDustCart()
    {
        return this.dust;
    }
    
    public SitioObjeto getDeposito()
    {
        return this.deposito;
    }
    
    public int getCargaDustCart()
    {
        return this.dust.getCarga();
    } 
    
    public int getDepositoDustCart()
    {
        return this.dust.getValorDeposito();
    }
    
    //--Edwin-- Se debe modificar para que funcione para mtarices de otras dimensiones, utilizar atributo dimension
    public void construirMatriz()
    {
       for(int fila=0;fila<10;fila++)
       {
           String linea=vectorFila.elementAt(fila);
           for(int columna=0;columna<10;columna++)
               matriz[fila][columna]=linea.charAt(columna);
       }
    } 

    //--Edwin-- Se deben cambiar los 10 por n (dimension de la matriz), creo que las comparaciones no aplican, debido
    // a que esto se comprueba para un problema que tenga obstaculos, es 
    //decir para nuestro caso solo aplicaria la primera linea (Creo)
    public boolean verificarSiEstaLibre(int numeroDeFila,int numeroDeColumna)
    {
        try
        {
            //--Edwin-- Esto aplica
            if(((numeroDeFila>=0) || (numeroDeFila<10) )&&( (numeroDeColumna>=0) || (numeroDeColumna<10)))
            //--Edwin--Creo que estas comparaciones no aplican, debido a que nosotros no tenemos obstaculos o muros
            {
                //0 si es una casilla libre
                if(matriz[numeroDeFila][numeroDeColumna]=='0')
                    return true;
                //2 si es la casilla que tiene el depósito de basura de 2 kilos
                if(matriz[numeroDeFila][numeroDeColumna]=='2')
                    return true;
                //3 si es la casilla que tiene el depósito de basura de 3 kilos
                if(matriz[numeroDeFila][numeroDeColumna]=='3')
                    return true;
                //5 si es el punto de reciclaje
                if(matriz[numeroDeFila][numeroDeColumna]=='5')
                    return true;
            }
        }catch(ArrayIndexOutOfBoundsException e)
        {} 
        return false;
    }

    //--Edwin-- Supongo que este metodo tampoco aplica
    public boolean verificarSiHayMuro(int numeroDeFila,int numeroDeColumna)
    {
         if( ( (numeroDeFila>=0) && (numeroDeFila<10) )&&( (numeroDeColumna>=0) && (numeroDeColumna<10) )&&(matriz[numeroDeFila][numeroDeColumna]=='1') )
           return true;
        return false;
    }
    
    //--Edwin-- En nuestro caso sería objeto1
    public boolean verificarSiEsDepositoDeBasura2K(int numeroDeFila,int numeroDeColumna)
    {        
         if( ( (numeroDeFila>=0) && (numeroDeFila<10) )&&( (numeroDeColumna>=0) && (numeroDeColumna<10) )&&(matriz[numeroDeFila][numeroDeColumna]=='2'))
           return true;
        return false;
    }
    
    //--Edwin-- En nuestro caso sería objeto2
    public boolean verificarSiEsDepositoDeBasura3K(int numeroDeFila,int numeroDeColumna)
    {
         if( ( (numeroDeFila>=0) && (numeroDeFila<10) )&&( (numeroDeColumna>=0) && (numeroDeColumna<10) )&&(matriz[numeroDeFila][numeroDeColumna]=='3'))
           return true;
        return false;
    }
    
    //--Edwin-- En nuestro caso no es solo un punto, sino dos, uno por objeto, es decir debemos 
    // debemos ahacer un metodo igual para el otro lugar.
    public boolean verificarSiEsPuntoDeReciclaje(int numeroDeFila,int numeroDeColumna)
    {
         if( ( (numeroDeFila>=0) && (numeroDeFila<10) )&&( (numeroDeColumna>=0) && (numeroDeColumna<10) )&&(matriz[numeroDeFila][numeroDeColumna]=='5')  )
           return true;
        return false;
    }

    //--Edwin-- adaptarlo para matriz de cualquier dimension, utilizar atributo dimension en esta clase
    public boolean verificarMovimientoValido(int numeroDeFila,int numeroDeColumna,char direccion )
    {
        if((0<=numeroDeFila)&&(10>numeroDeFila))
        {
            if(((1<=numeroDeColumna)&&(10>numeroDeColumna)))
            {
                if((direccion=='l') && (verificarSiEstaLibre(numeroDeFila,numeroDeColumna-1)))
                    return true;                
            }
            if(((0<=numeroDeColumna)&&(9>numeroDeColumna)))
            {
                 if((direccion=='r') && (verificarSiEstaLibre(numeroDeFila,numeroDeColumna+1)))
                     return true;        
            }
        } 

        if((0<=numeroDeColumna)&&(10>numeroDeColumna))
        {
            if(((1<=numeroDeFila)&&(10>numeroDeFila)))
            {
                 if((direccion=='u') && (verificarSiEstaLibre(numeroDeFila-1,numeroDeColumna)))
                     return true;
            }
            if(((0<=numeroDeFila)&&(8>numeroDeFila)))
            {
                 if((direccion=='d') && (verificarSiEstaLibre(numeroDeFila+1,numeroDeColumna)))
                     return true;
                 
            }
        }
        return false;
    }

   
    //--Edwin-- adaptarlo para matriz de cualquier dimension, utilizar atributo dimension en esta clase
    // creo que podemos separar este metodo en, moverAIzquierda, moverADerecha, moverAbajo y moverArriba
    // y que este motodo los llame dependiendo del parametro direccion 
    public boolean moverFicha(int numeroDeFila,int numeroDeColumna,char direccion)
    { 
        if((numeroDeColumna>=0)&&(numeroDeColumna<=9))
        { 
            if((numeroDeColumna>=0)&&(numeroDeColumna<=9))
            {
                 if((direccion=='l') && (verificarSiEstaLibre(numeroDeFila,numeroDeColumna-1)))
                 {
                     
                     if(verificarSiEsDepositoDeBasura2K(numeroDeFila,numeroDeColumna-1))
                     {                         
                         char valorACorrer=matriz[numeroDeFila][numeroDeColumna];
                         actualizarCasilla(numeroDeFila,numeroDeColumna,'0');
                         this.dust.montarCarga(2);                         
                         actualizarCasilla(numeroDeFila,numeroDeColumna-1,valorACorrer);
                         if((getCargaDust()!=5)&&(retornarCoordenadaDeObjetos('5')==null))
                             actualizarCasilla(getFilaPuntoReciclaje(),getColumnaPuntoReciclaje(),'5');
                         return true;
                     }                     
                     
                     if(verificarSiEsDepositoDeBasura3K(numeroDeFila,numeroDeColumna-1))
                     {
                         char valorACorrer=matriz[numeroDeFila][numeroDeColumna];
                         actualizarCasilla(numeroDeFila,numeroDeColumna,'0');
                         this.dust.montarCarga(3);
                         actualizarCasilla(numeroDeFila,numeroDeColumna-1,valorACorrer);    
                         if((getCargaDust()!=5)&&(retornarCoordenadaDeObjetos('5')==null))
                             actualizarCasilla(getFilaPuntoReciclaje(),getColumnaPuntoReciclaje(),'5');
                         return true;
                     }
                     
                     if(this.verificarSiEsPuntoDeReciclaje(numeroDeFila,numeroDeColumna-1))
                     {
                         this.deposito.setCoordenadas(numeroDeFila,numeroDeColumna-1);
                         
                         char valorACorrer=matriz[numeroDeFila][numeroDeColumna];                         
                         actualizarCasilla(numeroDeFila,numeroDeColumna,'0'); 
                         actualizarCasilla(numeroDeFila,numeroDeColumna-1,valorACorrer);                         
                         return true;
                     }
                                          
                     char valorACorrer=matriz[numeroDeFila][numeroDeColumna];
                     actualizarCasilla(numeroDeFila,numeroDeColumna,'0');
                     actualizarCasilla(numeroDeFila,numeroDeColumna-1,valorACorrer);
                     if((getCargaDust()!=5)&&(retornarCoordenadaDeObjetos('5')==null))
                             actualizarCasilla(getFilaPuntoReciclaje(),getColumnaPuntoReciclaje(),'5');
                     return true;
                 }
            }
            if((numeroDeColumna>=0)&&(numeroDeColumna<=9))
            {
                 if((direccion=='r') && (verificarSiEstaLibre(numeroDeFila,numeroDeColumna+1)))
                 {
                     
                     if(verificarSiEsDepositoDeBasura2K(numeroDeFila,numeroDeColumna+1))
                     {
                         char valorACorrer=matriz[numeroDeFila][numeroDeColumna];
                         actualizarCasilla(numeroDeFila,numeroDeColumna,'0');
                         this.dust.montarCarga(2);
                         actualizarCasilla(numeroDeFila,numeroDeColumna+1,valorACorrer);
                         if((getCargaDust()!=5)&&(retornarCoordenadaDeObjetos('5')==null))
                             actualizarCasilla(getFilaPuntoReciclaje(),getColumnaPuntoReciclaje(),'5');
                         return true;
                     }                     
                     
                     if(verificarSiEsDepositoDeBasura3K(numeroDeFila,numeroDeColumna+1))
                     {
                         char valorACorrer=matriz[numeroDeFila][numeroDeColumna];
                         actualizarCasilla(numeroDeFila,numeroDeColumna,'0');
                         this.dust.montarCarga(3);
                         actualizarCasilla(numeroDeFila,numeroDeColumna+1,valorACorrer);
                         if((getCargaDust()!=5)&&(retornarCoordenadaDeObjetos('5')==null))
                             actualizarCasilla(getFilaPuntoReciclaje(),getColumnaPuntoReciclaje(),'5');
                         return true;
                     }
                     
                     if(this.verificarSiEsPuntoDeReciclaje(numeroDeFila,numeroDeColumna+1))
                     { 
                         this.deposito.setCoordenadas(numeroDeFila,numeroDeColumna+1);  
                         
                         char valorACorrer=matriz[numeroDeFila][numeroDeColumna];
                         actualizarCasilla(numeroDeFila,numeroDeColumna,'0'); 
                         actualizarCasilla(numeroDeFila,numeroDeColumna+1,valorACorrer);
                         return true;
                     }
                     
                     char valorACorrer=matriz[numeroDeFila][numeroDeColumna];
                     actualizarCasilla(numeroDeFila,numeroDeColumna,'0');
                     actualizarCasilla(numeroDeFila,numeroDeColumna+1,valorACorrer);
                     if((getCargaDust()!=5)&&(retornarCoordenadaDeObjetos('5')==null))
                             actualizarCasilla(getFilaPuntoReciclaje(),getColumnaPuntoReciclaje(),'5');
                     return true;
                 } 
            }
        }
        
        if((numeroDeFila>=0)&&(numeroDeFila<=9))
        {
            if((numeroDeFila>=0)&&(numeroDeFila<=9))
            {
                 if((direccion=='u') && (verificarSiEstaLibre(numeroDeFila-1,numeroDeColumna)))
                 {                     
                     
                     if(verificarSiEsDepositoDeBasura2K(numeroDeFila-1,numeroDeColumna))
                     {
                         char valorACorrer=matriz[numeroDeFila][numeroDeColumna];
                         actualizarCasilla(numeroDeFila,numeroDeColumna,'0');
                         this.dust.montarCarga(2);
                         actualizarCasilla(numeroDeFila-1,numeroDeColumna,valorACorrer);
                         if((getCargaDust()!=5)&&(retornarCoordenadaDeObjetos('5')==null))
                             actualizarCasilla(getFilaPuntoReciclaje(),getColumnaPuntoReciclaje(),'5');
                         return true;
                     }          
                    
                     if(verificarSiEsDepositoDeBasura3K(numeroDeFila-1,numeroDeColumna))
                     {
                         char valorACorrer=matriz[numeroDeFila][numeroDeColumna];
                         actualizarCasilla(numeroDeFila,numeroDeColumna,'0');
                         this.dust.montarCarga(3);
                         actualizarCasilla(numeroDeFila-1,numeroDeColumna,valorACorrer);
                         if((getCargaDust()!=5)&&(retornarCoordenadaDeObjetos('5')==null))
                             actualizarCasilla(getFilaPuntoReciclaje(),getColumnaPuntoReciclaje(),'5');
                         return true;
                     }                     
                            
                     if(this.verificarSiEsPuntoDeReciclaje(numeroDeFila-1,numeroDeColumna))
                     {
                         this.deposito.setCoordenadas(numeroDeFila-1,numeroDeColumna);                        
                        char valorACorrer=matriz[numeroDeFila][numeroDeColumna];                        
                        actualizarCasilla(numeroDeFila,numeroDeColumna,'0');
                        actualizarCasilla(numeroDeFila-1,numeroDeColumna,valorACorrer);
                        return true;
                     }
                     
                     char valorACorrer=matriz[numeroDeFila][numeroDeColumna];
                     actualizarCasilla(numeroDeFila,numeroDeColumna,'0');
                     actualizarCasilla(numeroDeFila-1,numeroDeColumna,valorACorrer);
                     if((getCargaDust()!=5)&&(retornarCoordenadaDeObjetos('5')==null))
                             actualizarCasilla(getFilaPuntoReciclaje(),getColumnaPuntoReciclaje(),'5');
                     return true;
                 }
            }
            if((numeroDeFila>=0)&&(numeroDeFila<=9))
            {
                 if((direccion=='d') && (verificarSiEstaLibre(numeroDeFila+1,numeroDeColumna)))
                 {
                     
                     if(verificarSiEsDepositoDeBasura2K(numeroDeFila+1,numeroDeColumna))
                     {
                         char valorACorrer=matriz[numeroDeFila][numeroDeColumna];
                         actualizarCasilla(numeroDeFila,numeroDeColumna,'0');
                         this.dust.montarCarga(2);
                         actualizarCasilla(numeroDeFila+1,numeroDeColumna,valorACorrer);
                         if((getCargaDust()!=5)&&(retornarCoordenadaDeObjetos('5')==null))
                             actualizarCasilla(getFilaPuntoReciclaje(),getColumnaPuntoReciclaje(),'5');
                         return true;
                     }                     
                    
                     if(verificarSiEsDepositoDeBasura3K(numeroDeFila+1,numeroDeColumna))
                     {
                         char valorACorrer=matriz[numeroDeFila][numeroDeColumna];
                         actualizarCasilla(numeroDeFila,numeroDeColumna,'0');                         
                         this.dust.montarCarga(3);
                         actualizarCasilla(numeroDeFila+1,numeroDeColumna,valorACorrer);
                         if((getCargaDust()!=5)&&(retornarCoordenadaDeObjetos('5')==null))
                             actualizarCasilla(getFilaPuntoReciclaje(),getColumnaPuntoReciclaje(),'5');
                         return true;
                     }                     
                    
                     if(this.verificarSiEsPuntoDeReciclaje(numeroDeFila+1,numeroDeColumna))
                     {
                         this.deposito.setCoordenadas(numeroDeFila+1,numeroDeColumna);                         
                         char valorACorrer=matriz[numeroDeFila][numeroDeColumna];                         
                         actualizarCasilla(numeroDeFila,numeroDeColumna,'0');
                         actualizarCasilla(numeroDeFila+1,numeroDeColumna,valorACorrer);
                         return true;
                     }
                     char valorACorrer=matriz[numeroDeFila][numeroDeColumna];
                     actualizarCasilla(numeroDeFila,numeroDeColumna,'0');
                     actualizarCasilla(numeroDeFila+1,numeroDeColumna,valorACorrer);
                     if((getCargaDust()!=5)&&(retornarCoordenadaDeObjetos('5')==null))
                             actualizarCasilla(getFilaPuntoReciclaje(),getColumnaPuntoReciclaje(),'5');
                     return true;
                 }
            }
        } 
        JOptionPane.showMessageDialog(null,"Movimiento fuera de Rango");
        return false;
    }

    public void actualizarCasilla(int numeroDeFila,int numeroDeColumna,char nuevoValor)
    {
        if(perteneceAlConjuntoNombres(nuevoValor)||perteneceAlConjuntoEstados(nuevoValor))
           matriz[numeroDeFila][numeroDeColumna]=nuevoValor;
    }
    
    //--Edwin-- este metodo al parecer no se usa lo comente, guarde y no genero problema
//    public char ubicandoPosicionDeObjetos(char nombreObjeto)
//    {
//        if(perteneceAlConjuntoNombres(nombreObjeto))
//        {
//            for(int i=0;i<10;i++)
//            {
//                for(int j=1;j<10;j++)
//                {
//                    if(nombreObjeto==matriz[i][j])
//                        return 't';
//                }
//            }
//        }       
//        return 'f';
//    }

    /**
     * Retorna las coordenadas de los objetos en la matriz, 
     * ubicandolos de acuerdo a su nombre (Código asignado).
     * @param nombreObjeto Nombre que identifica al objeto.
     * @return 
     */
    public int[] retornarCoordenadaDeObjetos(char nombreObjeto)
    { 
       int[] posicion=null;
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
               if(matriz[i][j]==nombreObjeto)
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

    public boolean perteneceAlConjuntoNombres(char letra)
    {
        for(int i=0;i<conjuntoNombres.length;i++)
        {
            if(conjuntoNombres[i]==letra)
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
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
               System.out.print(matriz[i][j]+"\t");
            System.out.print("\n"); 
        }
    }
} 
