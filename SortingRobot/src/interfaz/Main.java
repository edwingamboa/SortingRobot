/**
 * ****************************************************************************
 * Sorting Robot
 *
 * Inteligencia Artificial: Proyecto No 1 Jesús Alexander Aranda Bueno
 *
 * Presentado por: Roger Fernandez - 201310229 Edwin Gamboa - 201310233
 * Francisco Rojas - 201310273 David Zuluaga - 201310294
 *
 * Clase: Main Clase principal, donde se almacena la interfaz y acciones
 * principales.
 * ****************************************************************************
 */
package interfaz;

import java.io.File;
import java.util.Vector;
import sortingrobot.*;
import busquedas.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Main extends javax.swing.JFrame implements IdObjetos {

    /**
     * Creates new form Main
     */
    private File ambienteArchivo;
    private Archivo archivo;
    private Matriz matriz;
    private Estado estado;
    private Problema problema;
    private Vector<Nodo> camino;
    private int nodoCaminoSeleccionado = -1;
    private long tiempoInicio, tiempoTotal;
    private Vector<String> movimientoResultado;
    private int _filas; //Numero de filas de la matriz
    private int _columnas; //Numero de columnas de la matriz
    private int _tamanoSolucion = 0;
    private int pararHilo = 2;
    //GUI
    private ImageIcon arregloDeImagenes[] = new ImageIcon[7];
    private PintarfondoCeldas tablero[][]; //Matriz de Jlabel
    String path = "/Imagenes/fondo.jpg";
    URL url = this.getClass().getResource(path);
    private Image fondodetablero = new ImageIcon(url).getImage();

    public Main() {
        initComponents();
        bAmplitud.setEnabled(false);
        bAEstrella.setEnabled(false);
        bCostoUniforme.setEnabled(false);
        bAvara.setEnabled(false);
        bProfundidad.setEnabled(false);
        stop.setEnabled(false);
        bPlay.setEnabled(false);
        bMostrarSolucion.setEnabled(false);
        animacionRobot.start();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);

        //Cargando imagenes

        arregloDeImagenes[0] = null;
        arregloDeImagenes[1] = new ImageIcon("src/Imagenes/robot.png");
        arregloDeImagenes[2] = new ImageIcon("src/Imagenes/objeto1.png");
        arregloDeImagenes[3] = new ImageIcon("src/Imagenes/objeto2.png");
        arregloDeImagenes[4] = new ImageIcon("src/Imagenes/lugarobjeto1.png");
        arregloDeImagenes[5] = new ImageIcon("src/Imagenes/lugarobjeto2.png");
        arregloDeImagenes[6] = new ImageIcon("src/Imagenes/ayuda.png");
    }

    public Integer playRobot() {
        //  System.out.println(nodoCaminoSeleccionado+" y "+(camino.size() - 1));
        if (nodoCaminoSeleccionado < (camino.size() - 1)) {
            nodoCaminoSeleccionado++;
            crearTablero(camino.elementAt(nodoCaminoSeleccionado).getEstado().getMatriz());
            pararHilo = 1;
            
        } else {
            pararHilo = 0;
        }

        return pararHilo;

    }
    //hilo animacionRobot
    private Thread animacionRobot = new Thread(new Runnable() {
        public void run() {
            while (true) {
                if (pararHilo != 2) {

                    if (playRobot() == 1) {
                        synchronized (animacionRobot) {
                            try {
                                animacionRobot.wait(500);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else if (playRobot() == 0) {
                        synchronized (animacionRobot) {
                            try {
                                // System.out.println(playRobot()+" se duerme");
                                stop.setEnabled(true);
                                
                                animacionRobot.wait();

                            } catch (InterruptedException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                } else {
                    synchronized (animacionRobot) {
                        try {
                            // System.out.println(" se duerme");
                            animacionRobot.wait();

                        } catch (InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    });

    //Se crea el tablero a manejar, el conjunto de labels
    public void crearTablero(Matriz _matriz) {
        jPanel1.removeAll();
        jPanel1.setLayout(new GridLayout(obtenerFilas(), obtenerColumnas()));
        for (int i = 0; i < obtenerFilas(); i++) {
            for (int j = 0; j < obtenerColumnas(); j++) {
                tablero[i][j] = new PintarfondoCeldas();
                tablero[i][j].setImage(fondodetablero);
                if ((_matriz.getMatrizPenalizaciones()[i][j]) == 0)
                tablero[i][j].setIcon(retornarImagenDeCasillas(_matriz.getMatriz()[i][j]));
                
                if ((_matriz.getMatriz()[i][j]) == -1)
                tablero[i][j].setIcon(retornarImagenDeCasillas(_matriz.getMatriz()[i][j]));
                else
                if ((_matriz.getMatrizPenalizaciones()[i][j]) > 0) {
                    tablero[i][j].setIcon(retornarImagenDeCasillas(_matriz.getMatrizPenalizaciones()[i][j]));
                    tablero[i][j].setText("" + _matriz.getMatrizPenalizaciones()[i][j]);
                }
                

                tablero[i][j].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                tablero[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                tablero[i][j].setFont(new Font("Arial", 1, 14));

                jPanel1.add(tablero[i][j]);
            }
        }
        jPanel1.updateUI();
    }

    //Se arma el vector salida o resultado
    private Vector<Nodo> vectorSalida(Vector<Nodo> vectorSalida) {
        Vector<Nodo> retorno = new Vector();

        for (int i = (vectorSalida.size() - 1); i >= 0; i--) {
            retorno.add(vectorSalida.elementAt(i));
        }
        return retorno;
    }

    //Le asigna a cada posicion la imagen que le corresponda.
    private Icon retornarImagenDeCasillas(int posicion) {
        if (posicion > 0) {
            return arregloDeImagenes[6];
        } else {
            switch (posicion) {
                case ID_VACIA:
                    return arregloDeImagenes[0];
                case ID_ROBOT:
                    return arregloDeImagenes[1];
                case ID_OBJETO_UNO:
                    return arregloDeImagenes[2];
                case ID_OBJETO_DOS:
                    return arregloDeImagenes[3];
                case ID_SITIO_UNO:
                    return arregloDeImagenes[4];
                case ID_SITIO_DOS:
                    return arregloDeImagenes[5];
                default:
                    return null;
            }
        }
    }

    //Secuencia encargada de ejecutar cada algoritmo
    private void ejecutarAlgoritmo(Algoritmo _algoritmo) {
        crearTablero(matriz);
        labelAlgoritmo.setText("Algoritmo: " + _algoritmo.getNombre());
        Vector<String> operadoresDePareja = new Vector();
        tiempoInicio = System.currentTimeMillis();
        Vector<Nodo> respuesta = _algoritmo.aplicarAlgoritmo();
        tiempoTotal = System.currentTimeMillis() - tiempoInicio;

        camino = vectorSalida(respuesta);
        for (int i = 0; i < camino.size(); i++) {
            operadoresDePareja.add(camino.elementAt(i).getOperador().toStringOperador());
            _tamanoSolucion++;
        }
        this.movimientoResultado = operadoresDePareja;
        txtProfundidad.setText(_algoritmo.getProfundidadDelArbol() + "");
        txtTiempo.setText(tiempoTotal + " milisegundos");
        txtNodos.setText(_algoritmo.getcantidadDeNodosExpandidos() + "");

        /* BOTONES */
        stop.setEnabled(false);
        bPlay.setEnabled(true);
        bMostrarSolucion.setEnabled(true);
    }

    public void establecerFilas(int filas) {
        this._filas = filas;
    }

    public void establecerColumnas(int columnas) {
        this._columnas = columnas;
    }

    public int obtenerFilas() {
        return _filas;
    }

    public int obtenerColumnas() {
        return _columnas;
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
        stop = new javax.swing.JButton();
        bPlay = new javax.swing.JButton();
        tableroBusquedas = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        labelAlgoritmo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtCosto = new javax.swing.JLabel();
        txtTiempo = new javax.swing.JLabel();
        labelCosto = new javax.swing.JLabel();
        labelTiempo = new javax.swing.JLabel();
        txtProfundidad = new javax.swing.JLabel();
        labelProfundidad = new javax.swing.JLabel();
        txtNodos = new javax.swing.JLabel();
        labelNodos = new javax.swing.JLabel();
        menu = new javax.swing.JPanel();
        cargarArchivo = new javax.swing.JButton();
        busquedas = new javax.swing.JPanel();
        bAmplitud = new javax.swing.JButton();
        bProfundidad = new javax.swing.JButton();
        bCostoUniforme = new javax.swing.JButton();
        bAvara = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bAEstrella = new javax.swing.JButton();
        bMostrarSolucion = new javax.swing.JButton();
        bAyuda = new javax.swing.JButton();
        bInformacion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 51, 51));

        matrizGeneral.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)), "Matriz", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10))); // NOI18N

        controladores.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)), "Controles", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/stop.png"))); // NOI18N
        stop.setBorderPainted(false);
        stop.setFocusPainted(false);
        stop.setFocusable(false);
        stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopActionPerformed(evt);
            }
        });

        bPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/play.png"))); // NOI18N
        bPlay.setBorderPainted(false);
        bPlay.setFocusPainted(false);
        bPlay.setFocusable(false);
        bPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPlayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controladoresLayout = new javax.swing.GroupLayout(controladores);
        controladores.setLayout(controladoresLayout);
        controladoresLayout.setHorizontalGroup(
            controladoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controladoresLayout.createSequentialGroup()
                .addContainerGap(408, Short.MAX_VALUE)
                .addComponent(stop, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(bPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(436, Short.MAX_VALUE))
        );
        controladoresLayout.setVerticalGroup(
            controladoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(stop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tableroBusquedas.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1140, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 542, Short.MAX_VALUE)
        );

        tableroBusquedas.setViewportView(jPanel1);

        txtCosto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtTiempo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelCosto.setText("Costo total:");

        labelTiempo.setText("Tiempo de Computo: ");

        txtProfundidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelProfundidad.setText("Profundidad:");

        txtNodos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelNodos.setText("Nodos Expandidos:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(labelNodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNodos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelProfundidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelTiempo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelCosto)
                .addGap(0, 0, 0)
                .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTiempo)
                    .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCosto)
                    .addComponent(labelProfundidad)
                    .addComponent(txtProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNodos, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNodos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout matrizGeneralLayout = new javax.swing.GroupLayout(matrizGeneral);
        matrizGeneral.setLayout(matrizGeneralLayout);
        matrizGeneralLayout.setHorizontalGroup(
            matrizGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controladores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(matrizGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableroBusquedas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(matrizGeneralLayout.createSequentialGroup()
                .addGap(380, 380, 380)
                .addComponent(labelAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, matrizGeneralLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );
        matrizGeneralLayout.setVerticalGroup(
            matrizGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, matrizGeneralLayout.createSequentialGroup()
                .addComponent(labelAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(tableroBusquedas, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        bAvara.setText("Avara");
        bAvara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAvaraActionPerformed(evt);
            }
        });

        jLabel1.setText("No Informada");

        jLabel2.setText("Informada");

        bAEstrella.setText("A*");
        bAEstrella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAEstrellaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout busquedasLayout = new javax.swing.GroupLayout(busquedas);
        busquedas.setLayout(busquedasLayout);
        busquedasLayout.setHorizontalGroup(
            busquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(busquedasLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(busquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bCostoUniforme)
                    .addGroup(busquedasLayout.createSequentialGroup()
                        .addGroup(busquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, busquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(bAmplitud, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(busquedasLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(busquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(busquedasLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jLabel2))
                            .addGroup(busquedasLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(busquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bAvara, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bAEstrella, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        busquedasLayout.setVerticalGroup(
            busquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(busquedasLayout.createSequentialGroup()
                .addGroup(busquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(busquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAmplitud, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAEstrella, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(busquedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAvara, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bCostoUniforme, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bMostrarSolucion.setText("Mostrar Solucion");
        bMostrarSolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMostrarSolucionActionPerformed(evt);
            }
        });

        bAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ayuda.png"))); // NOI18N
        bAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAyudaActionPerformed(evt);
            }
        });

        bInformacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/informacion.png"))); // NOI18N
        bInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInformacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(busquedas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(cargarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(bInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bMostrarSolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(cargarArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(busquedas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bMostrarSolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bAyuda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bInformacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(matrizGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(matrizGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAmplitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAmplitudActionPerformed
        // TODO add your handling code here:
        ejecutarAlgoritmo(new Amplitud(problema));
    }//GEN-LAST:event_bAmplitudActionPerformed

    private void bProfundidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProfundidadActionPerformed
        // TODO add your handling code here:
        ejecutarAlgoritmo(new Profundidad(problema));
    }//GEN-LAST:event_bProfundidadActionPerformed

    private void bCostoUniformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCostoUniformeActionPerformed
        // TODO add your handling code here:
        ejecutarAlgoritmo(new CostoUniforme(problema));
    }//GEN-LAST:event_bCostoUniformeActionPerformed

    private void bAEstrellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAEstrellaActionPerformed
        // TODO add your handling code here:
        ejecutarAlgoritmo(new AEstrella(problema));
    }//GEN-LAST:event_bAEstrellaActionPerformed

    private void bAvaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAvaraActionPerformed
        // TODO add your handling code here:
        ejecutarAlgoritmo(new Avara(problema));
    }//GEN-LAST:event_bAvaraActionPerformed
    private void cargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarArchivoActionPerformed
        // TODO add your handling code here:
        try {
            this.setVisible(false);
            JFileChooser selectorArchivo = new JFileChooser();
            selectorArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

            JTextArea texto = new JTextArea();
            int opcion = selectorArchivo.showOpenDialog(texto);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                ambienteArchivo = selectorArchivo.getSelectedFile();
                bAmplitud.setEnabled(true);
                bAEstrella.setEnabled(true);
                bCostoUniforme.setEnabled(true);
                bAvara.setEnabled(true);
                bProfundidad.setEnabled(true);
            }
            if (opcion == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(null, "Debe escoger un ambiente");
            }
            archivo = new Archivo(ambienteArchivo);
            matriz = new Matriz(archivo.leerArchivo());
            estado = new Estado(matriz);
            problema = new Problema(estado);

            //se crea el tablero
            establecerFilas(matriz.getDimension());
            establecerColumnas(matriz.getDimension());
            tablero = new PintarfondoCeldas[obtenerFilas()][obtenerColumnas()];
            crearTablero(matriz);
            pararHilo = 0;

        } catch (NullPointerException e) {
        }
        this.setVisible(true);
    }//GEN-LAST:event_cargarArchivoActionPerformed
    private void bPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlayActionPerformed
        synchronized (animacionRobot) {
            bPlay.setEnabled(false);
            animacionRobot.notify();
        }
    }//GEN-LAST:event_bPlayActionPerformed

    private void stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopActionPerformed
        nodoCaminoSeleccionado = 0;
        crearTablero(camino.elementAt(nodoCaminoSeleccionado).getEstado().getMatriz());
        stop.setEnabled(false);
        bPlay.setEnabled(true);

    }//GEN-LAST:event_stopActionPerformed

    private void bMostrarSolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMostrarSolucionActionPerformed
        // TODO add your handling code here:
        Resultados resultado = new Resultados(movimientoResultado, _tamanoSolucion);
        resultado.setVisible(true);
    }//GEN-LAST:event_bMostrarSolucionActionPerformed

    private void bInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInformacionActionPerformed
        // TODO add your handling code here:
        new Informacion();
    }//GEN-LAST:event_bInformacionActionPerformed

    private void bAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAyudaActionPerformed
        // TODO add your handling code here:
        new Ayuda();
    }//GEN-LAST:event_bAyudaActionPerformed

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
    private javax.swing.JButton bAEstrella;
    private javax.swing.JButton bAmplitud;
    private javax.swing.JButton bAvara;
    private javax.swing.JButton bAyuda;
    private javax.swing.JButton bCostoUniforme;
    private javax.swing.JButton bInformacion;
    private javax.swing.JButton bMostrarSolucion;
    private javax.swing.JButton bPlay;
    private javax.swing.JButton bProfundidad;
    private javax.swing.JPanel busquedas;
    private javax.swing.JButton cargarArchivo;
    private javax.swing.JPanel controladores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelAlgoritmo;
    private javax.swing.JLabel labelCosto;
    private javax.swing.JLabel labelNodos;
    private javax.swing.JLabel labelProfundidad;
    private javax.swing.JLabel labelTiempo;
    private javax.swing.JPanel matrizGeneral;
    private javax.swing.JPanel menu;
    private javax.swing.JButton stop;
    private javax.swing.JScrollPane tableroBusquedas;
    private javax.swing.JLabel txtCosto;
    private javax.swing.JLabel txtNodos;
    private javax.swing.JLabel txtProfundidad;
    private javax.swing.JLabel txtTiempo;
    // End of variables declaration//GEN-END:variables
}
