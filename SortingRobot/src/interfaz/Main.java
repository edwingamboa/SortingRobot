/******************************************************************************
                                Sorting Robot
                                
Inteligencia Artificial: Proyecto No 1
* Jesús Alexander Aranda Bueno

Presentado por:
* Roger Fernandez       -  201310229
* Edwin Gamboa          -  201310233
* Francisco Rojas       -  201310273
* David Zuluaga         -  201310294

Clase: Main
* Clase principal, donde se almacena la interfaz y acciones principales.
******************************************************************************/

package interfaz;

import java.io.File;
import java.util.Vector;
import javax.swing.JFrame;
import sortingrobot.*;
import busquedas.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static sortingrobot.IdObjetos.ID_SITIO_DOS;

public class Main extends javax.swing.JFrame implements IdObjetos {

    /**
     * Creates new form Main
     */
    private File ambienteArchivo;
    private Archivo archivo;
    private Matriz matriz;
    private Estado estado;
    private Problema problema;
    private long tiempoInicio, tiempoTotal;
    private Vector<OperadorEstado> parejas;
    private int _filas; //Numero de filas de la matriz
    private int _columnas; //Numero de columnas de la matriz
   // private int valores[][]; //Matriz de enteros
    private PintarfondoCeldas tablero[][]; //Matriz de Jlabel
    String path = "/Imagenes/0.jpg";  
URL url = this.getClass().getResource(path);  


//ImageIcon fondodetablero = new ImageIcon(url);  
private Image fondodetablero = new ImageIcon(url).getImage();
    
    
    //GUI
   // private JLabel arregloDeEtiquetas[][] = new JLabel[10][10];
    private ImageIcon arregloDeImagenes[] = new ImageIcon[7];
    //private PintadadeMatriz pintarmatriz;

    //Se asignna a cada etiqueta la imagen a mostrar en pantalla.
    private void cargarEstadoEnPantalla(Matriz matriz) {
        int[][] obj = matriz.getMatriz();
        matriz.imprimirMatriz();
        for (int i = 0; i < matriz.getDimension(); i++) {
            for (int j = 0; j < matriz.getDimension(); j++) {
//                arregloDeEtiquetas[i][j].setIcon(retornarImagenDeCasillas(obj[i][j]));
            }
        }
    }

    
    
    public void establecerFilas( int filas ) { this._filas = filas; }


    /*
     * ============================================
     * Establece el numero de columnas de la matriz
     * @param columnas - Numero de columnas
     * ============================================
     */
    public void establecerColumnas( int columnas ) { this._columnas = columnas; }

    //Obtiene el numero de filas de la matriz
    public int obtenerFilas() { return _filas; }

    //Obtiene el numero de columnas de la matriz
    public int obtenerColumnas() { return _columnas; }

    
    
    
    
    
    private Estado sacarEstadoCopia(Estado estado) {
        Estado retorno;
        Matriz referenciada = estado.getMatriz();
        Matriz nueva = new Matriz();

        int[][] matrizNueva = new int[referenciada.getDimension()][referenciada.getDimension()];
        for (int j = 0; j < nueva.getDimension(); j++) {
            matrizNueva[j] = Arrays.copyOf(referenciada.getMatriz()[j], referenciada.getDimension());
        }
        nueva.setMatriz(matrizNueva);
        retorno = new Estado(nueva);

        return retorno;
    }

    private void generarParOperadorEstado(Estado estadoInicial, Vector<Nodo> sol) {
        parejas = new Vector<OperadorEstado>();
        Estado copia = sacarEstadoCopia(estadoInicial);

        for (int i = 0; i < sol.size(); i++) {
            copia.moverRobot(sol.elementAt(i).getOperador());
            Estado copia2 = sacarEstadoCopia(copia);
            OperadorEstado pareja = new OperadorEstado(sol.elementAt(i).getOperador(), copia2);
            parejas.add(pareja);
        }
    }

    private Vector<Nodo> ordenarVectorSalida(Vector<Nodo> vectorSalida) {
        Vector<Nodo> retorno = new Vector();

        for (int i = (vectorSalida.size() - 1); i >= 0; i--) {
            retorno.add(vectorSalida.elementAt(i));
        }
        return retorno;
    }

          public void crearTablero( JPanel panel ) {
   
        panel.setLayout( new GridLayout( obtenerFilas(), obtenerColumnas() ) );
        for( int i = 0; i < obtenerFilas(); i++ ) {
            
            for( int j = 0; j < obtenerColumnas(); j++ ) {          
                
                
                tablero[i][j] = new PintarfondoCeldas();
               // matriz[i][j].setSize(50,50);
                //Icon icono = new ImageIcon(icon.getImage().getScaledInstance(matriz[i][j].getWidth(), matriz[i][j].getHeight(), Image.SCALE_SMOOTH));
                
              //tablero[i][j].setImage(retornarImagenDeCasillas(matriz.getMatriz()[i][j]));
              
              tablero[i][j].setImage(fondodetablero);
              tablero[i][j].setIcon(retornarImagenDeCasillas(matriz.getMatriz()[i][j]));
         if ((matriz.getMatriz()[i][j])>0)       
            tablero[i][j].setText(""+matriz.getMatriz()[i][j]);
                
                
                tablero[i][j].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
           //     tablero[i][j].setVerticalAlignment(PintarfondoCeldas.BOTTOM);
              
                tablero[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                tablero[i][j].setFont( new Font( "Arial", 1, 12 ) );  
            //  matriz[i][j].setHorizontalAlignment( JLabel.CENTER );
              
                panel.add( tablero[i][j] );
            }
        }
    }
    
    
    
    
    
    //Le asigna a cada posicion la imagen que le corresponda.
    //private Image retornarImagenDeCasillas(int posicion) {
       private ImageIcon retornarImagenDeCasillas(int posicion) {    
        ImageIcon retorno = null;

        if (posicion == ID_VACIA) {
            return arregloDeImagenes[0];
        }

        if (posicion == ID_ROBOT) {
            return arregloDeImagenes[1];
        }

        if (posicion == ID_OBJETO_UNO) {
            return arregloDeImagenes[2];
        }

        if (posicion == ID_OBJETO_DOS) {
            return arregloDeImagenes[3];
        }

        if (posicion == ID_SITIO_UNO) {
            return arregloDeImagenes[4];
        }

        if (posicion == ID_SITIO_DOS) {
            return arregloDeImagenes[5];
        }
                
        if (posicion >0) {
            return arregloDeImagenes[6];
        }
        return retorno;
    }

    public Main() {

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        
        initComponents();

        //Enlazando etiquetas
       
        //Cargando imagenes
        arregloDeImagenes[0] = null;
        arregloDeImagenes[1] = new ImageIcon("src/Imagenes/robot.png");
        arregloDeImagenes[2] = new ImageIcon("src/Imagenes/objeto1.png");
        arregloDeImagenes[3] = new ImageIcon("src/Imagenes/objeto2.png");
        arregloDeImagenes[4] = new ImageIcon("src/Imagenes/3.jpg");
        arregloDeImagenes[5] = new ImageIcon("src/Imagenes/cajon.png");
        arregloDeImagenes[6] = new ImageIcon("src/Imagenes/ayuda.png");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        matrizGeneral = new javax.swing.JPanel();
        controladores = new javax.swing.JPanel();
        atras = new javax.swing.JButton();
        adelante = new javax.swing.JButton();
        tableroBusquedas = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        cargarArchivo = new javax.swing.JButton();
        busquedas = new javax.swing.JPanel();
        bAmplitud = new javax.swing.JButton();
        bProfundidad = new javax.swing.JButton();
        bCostoUniforme = new javax.swing.JButton();
        bAEstrella = new javax.swing.JButton();
        bAvara = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        solucion = new javax.swing.JPanel();
        informacion = new javax.swing.JButton();
        ayuda = new javax.swing.JButton();
        labelNodos = new javax.swing.JLabel();
        nodos = new javax.swing.JLabel();
        profundidad = new javax.swing.JLabel();
        labelProfundidad = new javax.swing.JLabel();
        labelTiempo = new javax.swing.JLabel();
        tiempo = new javax.swing.JLabel();
        resultado = new javax.swing.JScrollPane();
        listaSolucion = new javax.swing.JList();
        algoritmo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 51, 51));

        matrizGeneral.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)), "Matriz", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10))); // NOI18N

        controladores.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)), "Controles", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        atras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/izq.png"))); // NOI18N
        atras.setBorderPainted(false);
        atras.setFocusPainted(false);
        atras.setFocusable(false);
        atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasActionPerformed(evt);
            }
        });

        adelante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/der.png"))); // NOI18N
        adelante.setBorderPainted(false);
        adelante.setFocusPainted(false);
        adelante.setFocusable(false);
        adelante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adelanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controladoresLayout = new javax.swing.GroupLayout(controladores);
        controladores.setLayout(controladoresLayout);
        controladoresLayout.setHorizontalGroup(
            controladoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controladoresLayout.createSequentialGroup()
                .addContainerGap(246, Short.MAX_VALUE)
                .addComponent(atras, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(adelante, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(246, Short.MAX_VALUE))
        );
        controladoresLayout.setVerticalGroup(
            controladoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adelante, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(atras, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tableroBusquedas.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 626, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
        );

        tableroBusquedas.setViewportView(jPanel1);

        javax.swing.GroupLayout matrizGeneralLayout = new javax.swing.GroupLayout(matrizGeneral);
        matrizGeneral.setLayout(matrizGeneralLayout);
        matrizGeneralLayout.setHorizontalGroup(
            matrizGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controladores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(matrizGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableroBusquedas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        matrizGeneralLayout.setVerticalGroup(
            matrizGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, matrizGeneralLayout.createSequentialGroup()
                .addComponent(tableroBusquedas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controladores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menu.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)), "Menú", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10))); // NOI18N

        cargarArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregar archivo.png"))); // NOI18N
        cargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarArchivoActionPerformed(evt);
            }
        });

        busquedas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Búsquedas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        bAmplitud.setText("Amplitud");
        bAmplitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAmplitudActionPerformed(evt);
            }
        });

        bProfundidad.setText("Profundidad");
        bProfundidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProfundidadActionPerformed(evt);
            }
        });

        bCostoUniforme.setText("Costo Uniforme");
        bCostoUniforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCostoUniformeActionPerformed(evt);
            }
        });

        bAEstrella.setText("A*");
        bAEstrella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAEstrellaActionPerformed(evt);
            }
        });

        bAvara.setText("Avara");
        bAvara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAvaraActionPerformed(evt);
            }
        });

        jLabel1.setText("No Informada");

        jLabel2.setText("Informada");

        javax.swing.GroupLayout busquedasLayout = new javax.swing.GroupLayout(busquedas);
        busquedas.setLayout(busquedasLayout);
        busquedasLayout.setHorizontalGroup(
            busquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, busquedasLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(busquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(busquedasLayout.createSequentialGroup()
                        .addComponent(bAmplitud, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bCostoUniforme))
                    .addGroup(busquedasLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(busquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(busquedasLayout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(jLabel2))
                            .addGroup(busquedasLayout.createSequentialGroup()
                                .addComponent(bAEstrella, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bAvara, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(busquedasLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLabel1)))
                .addGap(29, 29, 29))
        );
        busquedasLayout.setVerticalGroup(
            busquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, busquedasLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(busquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAmplitud, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCostoUniforme, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(busquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAvara, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAEstrella, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        solucion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Solución", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        informacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/informacion.png"))); // NOI18N
        informacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informacionActionPerformed(evt);
            }
        });

        ayuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ayuda.png"))); // NOI18N
        ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ayudaActionPerformed(evt);
            }
        });

        labelNodos.setText("Nodos Expandidos:");

        nodos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        profundidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelProfundidad.setText("Profundidad:");

        labelTiempo.setText("Tiempo de Computo: ");

        tiempo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        listaSolucion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Solucion", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        resultado.setViewportView(listaSolucion);

        javax.swing.GroupLayout solucionLayout = new javax.swing.GroupLayout(solucion);
        solucion.setLayout(solucionLayout);
        solucionLayout.setHorizontalGroup(
            solucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(solucionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resultado, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, solucionLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(solucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, solucionLayout.createSequentialGroup()
                        .addComponent(ayuda, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(informacion, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(188, 188, 188))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, solucionLayout.createSequentialGroup()
                        .addGroup(solucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(solucionLayout.createSequentialGroup()
                                .addGroup(solucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelNodos)
                                    .addComponent(labelProfundidad))
                                .addGap(48, 48, 48))
                            .addGroup(solucionLayout.createSequentialGroup()
                                .addComponent(labelTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)))
                        .addGroup(solucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(profundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nodos, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(83, 83, 83))))
            .addGroup(solucionLayout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(algoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        solucionLayout.setVerticalGroup(
            solucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(solucionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(solucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nodos, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(solucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(profundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelProfundidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(solucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTiempo))
                .addGap(18, 18, 18)
                .addComponent(algoritmo, javax.swing.GroupLayout.DEFAULT_SIZE, 11, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(resultado, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(solucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ayuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(informacion, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(solucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addComponent(cargarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(busquedas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(busquedas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(cargarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(solucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(matrizGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(matrizGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasActionPerformed
        int seleccionado = listaSolucion.getSelectedIndex();
        if (seleccionado > 0) {
            cargarEstadoEnPantalla(parejas.elementAt(seleccionado - 1).getEstado().getMatriz());
            listaSolucion.setSelectedIndex(seleccionado - 1);
        } else {
            cargarEstadoEnPantalla(matriz);
            listaSolucion.removeSelectionInterval(0, parejas.size());
        }
    }//GEN-LAST:event_atrasActionPerformed

    private void adelanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adelanteActionPerformed
        int seleccionado = listaSolucion.getSelectedIndex();
        int[] coordenadas4 = new int[2];
        int[] coordenadas5 = new int[2];
        int[] coordenada5 = new int[2];
        Matriz matriz1;
        coordenadas5 = matriz.retornarCoordenadaDe('5');
        if (seleccionado < (parejas.size() - 1)) {
            coordenadas4 = parejas.elementAt(seleccionado + 1).getEstado().getMatriz().retornarCoordenadaDe('4');
            coordenada5 = parejas.elementAt(seleccionado + 1).getEstado().getMatriz().retornarCoordenadaDe('5');

            if ((coordenadas4[0] == coordenadas5[0]) && (coordenadas4[1] == coordenadas5[1])) {
                matriz1 = parejas.elementAt(seleccionado + 1).getEstado().getMatriz();
                matriz1.actualizarCasilla(coordenadas5[0], coordenadas5[1], '4');
                if ((coordenada5 != null) && (coordenada5[0] != coordenadas5[0]) && (coordenada5[1] != coordenadas5[1]) && (coordenada5[0] == 0) && (coordenada5[1] == 0)) {
                    matriz1.actualizarCasilla(0, 0, '0');
                }
                cargarEstadoEnPantalla(matriz1);
            } else {
                matriz1 = parejas.elementAt(seleccionado + 1).getEstado().getMatriz();
                matriz1.actualizarCasilla(coordenadas5[0], coordenadas5[1], '5');
                if ((coordenada5 != null) && (coordenada5[0] != coordenadas5[0]) && (coordenada5[1] != coordenadas5[1]) && (coordenada5[0] == 0) && (coordenada5[1] == 0)) {
                    matriz1.actualizarCasilla(0, 0, '0');
                }
                cargarEstadoEnPantalla(matriz1);
            }
            listaSolucion.setSelectedIndex(seleccionado + 1);
        }
    }//GEN-LAST:event_adelanteActionPerformed

    private void ayudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ayudaActionPerformed
        // TODO add your handling code here:
        new Ayuda();
    }//GEN-LAST:event_ayudaActionPerformed

    private void informacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informacionActionPerformed
        // TODO add your handling code here:
        new Informacion();
    }//GEN-LAST:event_informacionActionPerformed

    private void bAvaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAvaraActionPerformed
        // TODO add your handling code here:
        cargarEstadoEnPantalla(matriz);
        Vector<String> operadoresDePareja = new Vector();
        algoritmo.setText("Busqueda Avara");
        Avara bus = new Avara(problema);
        tiempoInicio = System.currentTimeMillis();
        Vector<Nodo> res = bus.aplicarAlgoritmo();
        tiempoTotal = System.currentTimeMillis() - tiempoInicio;

        tiempoTotal = System.currentTimeMillis() - tiempoInicio;
        Vector<Nodo> salida = ordenarVectorSalida(res);
        for (int i = 0; i < salida.size(); i++) {
            operadoresDePareja.add(salida.elementAt(i).getOperador().toStringOperador());
        }
        listaSolucion.setListData(operadoresDePareja);
        profundidad.setText(bus.getProfundidadDelArbol() + "");
        tiempo.setText(tiempoTotal + " milisegundos");
        nodos.setText(bus.getcantidadDeNodosExpandidos() + "");

        generarParOperadorEstado(estado, salida);
    }//GEN-LAST:event_bAvaraActionPerformed

    private void bAEstrellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAEstrellaActionPerformed
        // TODO add your handling code here:
        cargarEstadoEnPantalla(matriz);
        Vector<String> operadoresDePareja = new Vector();
        algoritmo.setText("Busqueda A*");
        AEstrella bus = new AEstrella(problema);
        tiempoInicio = System.currentTimeMillis();
        Vector<Nodo> res = bus.aplicarAlgoritmo();
        tiempoTotal = System.currentTimeMillis() - tiempoInicio;

        tiempoTotal = System.currentTimeMillis() - tiempoInicio;
        Vector<Nodo> salida = ordenarVectorSalida(res);
        for (int i = 0; i < salida.size(); i++) {
            operadoresDePareja.add(salida.elementAt(i).getOperador().toStringOperador());
        }
        listaSolucion.setListData(operadoresDePareja);
        profundidad.setText(bus.getProfundidadDelArbol() + "");
        tiempo.setText(tiempoTotal + " milisegundos");
        nodos.setText(bus.getcantidadDeNodosExpandidos() + "");

        generarParOperadorEstado(estado, salida);
    }//GEN-LAST:event_bAEstrellaActionPerformed

    private void bCostoUniformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCostoUniformeActionPerformed
        // TODO add your handling code here:
        cargarEstadoEnPantalla(matriz);
        Vector<String> operadoresDePareja = new Vector();
        algoritmo.setText("Busqueda De Costo Uniforme");
        CostoUniforme bus = new CostoUniforme(problema);
        tiempoInicio = System.currentTimeMillis();
        Vector<Nodo> res = bus.aplicarAlgoritmo();
        tiempoTotal = System.currentTimeMillis() - tiempoInicio;
        tiempoTotal = System.currentTimeMillis() - tiempoInicio;
        Vector<Nodo> salida = ordenarVectorSalida(res);
        for (int i = 0; i < salida.size(); i++) {
            operadoresDePareja.add(salida.elementAt(i).getOperador().toStringOperador());
        }
        listaSolucion.setListData(operadoresDePareja);
        profundidad.setText(bus.getProfundidadDelArbol() + "");
        tiempo.setText(tiempoTotal + " milisegundos");
        nodos.setText(bus.getcantidadDeNodosExpandidos() + "");

        generarParOperadorEstado(estado, salida);
    }//GEN-LAST:event_bCostoUniformeActionPerformed

    private void bProfundidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProfundidadActionPerformed
        // TODO add your handling code here:
        cargarEstadoEnPantalla(matriz);
        Vector<String> operadoresDePareja = new Vector();
        algoritmo.setText("Busqueda Preferente Por Profundidad");
        Profundidad bus = new Profundidad(problema);
        tiempoInicio = System.currentTimeMillis();
        Vector<Nodo> res = bus.aplicarAlgoritmo();
        tiempoTotal = System.currentTimeMillis() - tiempoInicio;

        Vector<Nodo> salida = ordenarVectorSalida(res);
        for (int i = 0; i < salida.size(); i++) {
            operadoresDePareja.add(salida.elementAt(i).getOperador().toStringOperador());
        }
        listaSolucion.setListData(operadoresDePareja);
        profundidad.setText(bus.getProfundidadDelArbol() + "");
        tiempo.setText(tiempoTotal + " milisegundos");
        nodos.setText(bus.getcantidadDeNodosExpandidos() + "");

        generarParOperadorEstado(estado, salida);
    }//GEN-LAST:event_bProfundidadActionPerformed

    private void bAmplitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAmplitudActionPerformed
    // TODO add your handling code here:
       cargarEstadoEnPantalla(matriz);
       Vector<String> operadoresDePareja = new Vector();
       algoritmo.setText("Busqueda Preferente por Amplitud");
       Amplitud bus = new Amplitud(problema);
       tiempoInicio = System.currentTimeMillis();
       Vector<Nodo> res = bus.aplicarAlgoritmo();
       tiempoTotal = System.currentTimeMillis() - tiempoInicio;

       tiempoTotal = System.currentTimeMillis() - tiempoInicio;
       Vector<Nodo> salida = ordenarVectorSalida(res);
       for (int i = 0; i < salida.size(); i++) {
           operadoresDePareja.add(salida.elementAt(i).getOperador().toStringOperador());
       }
       listaSolucion.setListData(operadoresDePareja);
       profundidad.setText(bus.getProfundidadDelArbol() + "");
       tiempo.setText(tiempoTotal + " milisegundos");
       nodos.setText(bus.getcantidadDeNodosExpandidos() + "");

       generarParOperadorEstado(estado, salida);
    }//GEN-LAST:event_bAmplitudActionPerformed

    private void cargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarArchivoActionPerformed
        // TODO add your handling code here:
        try {
            this.setVisible(false);
            JFileChooser selectorDeAmbiente = new JFileChooser();
            selectorDeAmbiente.setFileSelectionMode(JFileChooser.FILES_ONLY);

            JTextArea areaDeTexto = new JTextArea();
            int seleccion = selectorDeAmbiente.showOpenDialog(areaDeTexto);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                ambienteArchivo = selectorDeAmbiente.getSelectedFile();
            }
            if (seleccion == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(null, "Debe escoger un ambiente");
            }
            archivo = new Archivo(ambienteArchivo);
            matriz = new Matriz(archivo.leerArchivo());
             
        establecerFilas( matriz.getDimension() );
        establecerColumnas( matriz.getDimension() );
        tablero = new PintarfondoCeldas[ obtenerFilas() ][ obtenerColumnas()];
            crearTablero(jPanel1);
            estado = new Estado(matriz);
            problema = new Problema(estado);
        } catch (NullPointerException e) {
        }
        JOptionPane.showMessageDialog(null, "Ha selecionado el archivo ubicado en :\n" + ambienteArchivo.getPath());
        this.setVisible(true);
        cargarEstadoEnPantalla(matriz);
    }//GEN-LAST:event_cargarArchivoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2400);
                } catch (Exception e) {
                }
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adelante;
    private javax.swing.JLabel algoritmo;
    private javax.swing.JButton atras;
    private javax.swing.JButton ayuda;
    private javax.swing.JButton bAEstrella;
    private javax.swing.JButton bAmplitud;
    private javax.swing.JButton bAvara;
    private javax.swing.JButton bCostoUniforme;
    private javax.swing.JButton bProfundidad;
    private javax.swing.JPanel busquedas;
    private javax.swing.JButton cargarArchivo;
    private javax.swing.JPanel controladores;
    private javax.swing.JButton informacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelNodos;
    private javax.swing.JLabel labelProfundidad;
    private javax.swing.JLabel labelTiempo;
    private javax.swing.JList listaSolucion;
    private javax.swing.JPanel matrizGeneral;
    private javax.swing.JPanel menu;
    private javax.swing.JLabel nodos;
    private javax.swing.JLabel profundidad;
    private javax.swing.JScrollPane resultado;
    private javax.swing.JPanel solucion;
    private javax.swing.JScrollPane tableroBusquedas;
    private javax.swing.JLabel tiempo;
    // End of variables declaration//GEN-END:variables
}
