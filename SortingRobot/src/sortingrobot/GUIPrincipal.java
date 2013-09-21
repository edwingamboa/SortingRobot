package sortingrobot;

import java.io.File;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class GUIPrincipal extends javax.swing.JFrame {

    private File ambienteArchivo;
    private LectorDeArchivo archivo;
    private Matriz matriz;
    private Estado estado;
    private Problema problema;
    private long tiempoInicio,tiempoTotal;
    private Vector<ParOperadorEstado> parejas; 
    private final int ID_VACIA=0;
    private final int ID_ROBOT=-1;
    private final int ID_OBJETO_UNO=-2;
    private final int ID_OBJETO_DOS=-3;
    private final int ID_SITIO_UNO=-4;
    private final int ID_SITIO_DOS=-5; 
    //GUI
    private JLabel arregloDeEtiquetas[][]=new JLabel[10][10];
    private Icon arregloDeImagenes[]=new ImageIcon[6];
 
    public GUIPrincipal()
    {
        initComponents();   
        //Enlazando etiquetas
        arregloDeEtiquetas[0][0]=jLabel1;
        arregloDeEtiquetas[0][1]=jLabel2;
        arregloDeEtiquetas[0][2]=jLabel3;
        arregloDeEtiquetas[0][3]=jLabel4;
        arregloDeEtiquetas[0][4]=jLabel5;
        arregloDeEtiquetas[0][5]=jLabel6;
        arregloDeEtiquetas[0][6]=jLabel7;        
        arregloDeEtiquetas[0][7]=jLabel8;
        arregloDeEtiquetas[0][8]=jLabel9;
        arregloDeEtiquetas[0][9]=jLabel10;
        
        arregloDeEtiquetas[1][0]=jLabel11;
        arregloDeEtiquetas[1][1]=jLabel12;
        arregloDeEtiquetas[1][2]=jLabel13;
        arregloDeEtiquetas[1][3]=jLabel14;
        arregloDeEtiquetas[1][4]=jLabel15;
        arregloDeEtiquetas[1][5]=jLabel16;
        arregloDeEtiquetas[1][6]=jLabel17;
        arregloDeEtiquetas[1][7]=jLabel18;
        arregloDeEtiquetas[1][8]=jLabel19;        
        arregloDeEtiquetas[1][9]=jLabel20;
        
        arregloDeEtiquetas[2][0]=jLabel21;
        arregloDeEtiquetas[2][1]=jLabel22;
        arregloDeEtiquetas[2][2]=jLabel23;
        arregloDeEtiquetas[2][3]=jLabel24;
        arregloDeEtiquetas[2][4]=jLabel25;
        arregloDeEtiquetas[2][5]=jLabel26;
        arregloDeEtiquetas[2][6]=jLabel27;
        arregloDeEtiquetas[2][7]=jLabel28;
        arregloDeEtiquetas[2][8]=jLabel29;
        arregloDeEtiquetas[2][9]=jLabel30;
        
        arregloDeEtiquetas[3][0]=jLabel31;
        arregloDeEtiquetas[3][1]=jLabel32;
        arregloDeEtiquetas[3][2]=jLabel33;
        arregloDeEtiquetas[3][3]=jLabel34;
        arregloDeEtiquetas[3][4]=jLabel35;
        arregloDeEtiquetas[3][5]=jLabel36;
        arregloDeEtiquetas[3][6]=jLabel37;
        arregloDeEtiquetas[3][7]=jLabel38;
        arregloDeEtiquetas[3][8]=jLabel39;
        arregloDeEtiquetas[3][9]=jLabel40;
        
        arregloDeEtiquetas[4][0]=jLabel41;
        arregloDeEtiquetas[4][1]=jLabel42;
        arregloDeEtiquetas[4][2]=jLabel43;
        arregloDeEtiquetas[4][3]=jLabel44;
        arregloDeEtiquetas[4][4]=jLabel45;
        arregloDeEtiquetas[4][5]=jLabel46;
        arregloDeEtiquetas[4][6]=jLabel47;
        arregloDeEtiquetas[4][7]=jLabel48;
        arregloDeEtiquetas[4][8]=jLabel49;
        arregloDeEtiquetas[4][9]=jLabel50;
        
        arregloDeEtiquetas[5][0]=jLabel51;
        arregloDeEtiquetas[5][1]=jLabel52;
        arregloDeEtiquetas[5][2]=jLabel53;
        arregloDeEtiquetas[5][3]=jLabel54;
        arregloDeEtiquetas[5][4]=jLabel55;
        arregloDeEtiquetas[5][5]=jLabel56;
        arregloDeEtiquetas[5][6]=jLabel57;
        arregloDeEtiquetas[5][7]=jLabel58;
        arregloDeEtiquetas[5][8]=jLabel59;
        arregloDeEtiquetas[5][9]=jLabel60;
        
        arregloDeEtiquetas[6][0]=jLabel61;
        arregloDeEtiquetas[6][1]=jLabel62;
        arregloDeEtiquetas[6][2]=jLabel63;
        arregloDeEtiquetas[6][3]=jLabel64;
        arregloDeEtiquetas[6][4]=jLabel65;
        arregloDeEtiquetas[6][5]=jLabel66;
        arregloDeEtiquetas[6][6]=jLabel67;
        arregloDeEtiquetas[6][7]=jLabel68;
        arregloDeEtiquetas[6][8]=jLabel69;
        arregloDeEtiquetas[6][9]=jLabel70;
        
        arregloDeEtiquetas[7][0]=jLabel71;
        arregloDeEtiquetas[7][1]=jLabel72;
        arregloDeEtiquetas[7][2]=jLabel73;
        arregloDeEtiquetas[7][3]=jLabel74;
        arregloDeEtiquetas[7][4]=jLabel75;
        arregloDeEtiquetas[7][5]=jLabel76;
        arregloDeEtiquetas[7][6]=jLabel77;
        arregloDeEtiquetas[7][7]=jLabel78;
        arregloDeEtiquetas[7][8]=jLabel79;
        arregloDeEtiquetas[7][9]=jLabel80;
        
        arregloDeEtiquetas[8][0]=jLabel81;
        arregloDeEtiquetas[8][1]=jLabel82;
        arregloDeEtiquetas[8][2]=jLabel83;
        arregloDeEtiquetas[8][3]=jLabel84;
        arregloDeEtiquetas[8][4]=jLabel85;
        arregloDeEtiquetas[8][5]=jLabel86;
        arregloDeEtiquetas[8][6]=jLabel87;
        arregloDeEtiquetas[8][7]=jLabel88;
        arregloDeEtiquetas[8][8]=jLabel89;
        arregloDeEtiquetas[8][9]=jLabel90;
        
        arregloDeEtiquetas[9][0]=jLabel91;
        arregloDeEtiquetas[9][1]=jLabel92;
        arregloDeEtiquetas[9][2]=jLabel93;
        arregloDeEtiquetas[9][3]=jLabel94;
        arregloDeEtiquetas[9][4]=jLabel95;
        arregloDeEtiquetas[9][5]=jLabel96;
        arregloDeEtiquetas[9][6]=jLabel97;
        arregloDeEtiquetas[9][7]=jLabel98;
        arregloDeEtiquetas[9][8]=jLabel99;
        arregloDeEtiquetas[9][9]=jLabel100;        

        //Cargando imagenes
        arregloDeImagenes[0]=new ImageIcon("src/Imagenes/0.jpg");
        arregloDeImagenes[1]=new ImageIcon("src/Imagenes/1.jpg");
        arregloDeImagenes[2]=new ImageIcon("src/Imagenes/2.jpg");
        arregloDeImagenes[3]=new ImageIcon("src/Imagenes/3.jpg"); 
        arregloDeImagenes[4]=new ImageIcon("src/Imagenes/4.jpg");
        arregloDeImagenes[5]=new ImageIcon("src/Imagenes/5.jpg");       
    } 

    //Se asignna a cada etiqueta la imagen a mostrar en pantalla.
    private void cargarEstadoEnPantalla(Matriz matriz)
    {
        int[][] obj = matriz.getMatriz();
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                arregloDeEtiquetas[i][j].setIcon(retornarImagenDeCasillas(obj[i][j]));
            }
        }
    }

    private Estado sacarEstadoCopia(Estado estado)
    {
        Estado retorno;
        Matriz referenciada=estado.getMatriz();
        Matriz nueva=new Matriz();

        int[][] matrizNueva = new int[referenciada.getDimension()][referenciada.getDimension()];
        for (int j=0;j<10;j++)
        {
            matrizNueva[j] = Arrays.copyOf(referenciada.getMatriz()[j],referenciada.getDimension());
        }
        nueva.setMatriz(matrizNueva);
        retorno=new Estado(nueva);

        return retorno;
    }

    private void generarParOperadorEstado(Estado estadoInicial, Vector<Operador> sol)
    {
        parejas=new Vector<ParOperadorEstado>();
        Estado copia=sacarEstadoCopia(estadoInicial);        
        
        for(int i=0;i<sol.size();i++)
        {
            copia.moverRobot(sol.elementAt(i));            
            Estado copia2=sacarEstadoCopia(copia);
            ParOperadorEstado pareja=new ParOperadorEstado(sol.elementAt(i),copia2);
            parejas.add(pareja);
        }
    }

    private Vector<Operador> ordenarVectorSalida(Vector<Operador> vectorSalida)
    {
        Vector<Operador> retorno=new Vector();

        for(int i=(vectorSalida.size()-1);i>=0;i--)
        {
            retorno.add(vectorSalida.elementAt(i));
        }
        return retorno;
    }
        
    //Le asigna a cada posicion la imagen que le corresponda.
    private Icon retornarImagenDeCasillas(int posicion)
    {
        Icon retorno=null;

        if(posicion==ID_VACIA)
            return arregloDeImagenes[0];

        if(posicion==ID_ROBOT)
            return arregloDeImagenes[1];

        if(posicion==ID_OBJETO_UNO)
            return arregloDeImagenes[2];

        if(posicion==ID_OBJETO_DOS)
            return arregloDeImagenes[3];

        if(posicion==ID_SITIO_UNO)
            return arregloDeImagenes[4];

        if(posicion==ID_SITIO_DOS)
            return arregloDeImagenes[5];
        return retorno;
    }

   

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        algoritmo = new javax.swing.JLabel();
        jLabel250 = new javax.swing.JLabel();
        jLabel251 = new javax.swing.JLabel();
        jLabel252 = new javax.swing.JLabel();
        tiempoDeComputo = new javax.swing.JLabel();
        profundidadArbol = new javax.swing.JLabel();
        nodosExpandidos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaSolucion = new javax.swing.JList();
        nodos = new javax.swing.JLabel();
        etiquetaProfundidad = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        amplitud = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        profundidad = new javax.swing.JMenuItem();
        costo = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        avara = new javax.swing.JMenuItem();
        a = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DustCart");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(240, 226, 230));

        jLabel1.setBackground(new java.awt.Color(253, 250, 243));
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setOpaque(true);
        jLabel1.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel2.setBackground(new java.awt.Color(253, 250, 243));
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel2.setOpaque(true);
        jLabel2.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel11.setBackground(new java.awt.Color(253, 250, 243));
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel11.setOpaque(true);
        jLabel11.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel21.setBackground(new java.awt.Color(253, 250, 243));
        jLabel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel21.setOpaque(true);
        jLabel21.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel31.setBackground(new java.awt.Color(253, 250, 243));
        jLabel31.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel31.setOpaque(true);
        jLabel31.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel41.setBackground(new java.awt.Color(253, 250, 243));
        jLabel41.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel41.setOpaque(true);
        jLabel41.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel51.setBackground(new java.awt.Color(253, 250, 243));
        jLabel51.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel51.setOpaque(true);
        jLabel51.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel61.setBackground(new java.awt.Color(253, 250, 243));
        jLabel61.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel61.setOpaque(true);
        jLabel61.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel71.setBackground(new java.awt.Color(253, 250, 243));
        jLabel71.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel71.setOpaque(true);
        jLabel71.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel81.setBackground(new java.awt.Color(253, 250, 243));
        jLabel81.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel81.setOpaque(true);
        jLabel81.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel91.setBackground(new java.awt.Color(253, 250, 243));
        jLabel91.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel91.setOpaque(true);
        jLabel91.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel3.setBackground(new java.awt.Color(253, 250, 243));
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel3.setOpaque(true);
        jLabel3.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel5.setBackground(new java.awt.Color(253, 250, 243));
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel5.setOpaque(true);
        jLabel5.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel4.setBackground(new java.awt.Color(253, 250, 243));
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel4.setOpaque(true);
        jLabel4.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel6.setBackground(new java.awt.Color(253, 250, 243));
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel6.setOpaque(true);
        jLabel6.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel7.setBackground(new java.awt.Color(253, 250, 243));
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel7.setOpaque(true);
        jLabel7.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel8.setBackground(new java.awt.Color(253, 250, 243));
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel8.setOpaque(true);
        jLabel8.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel9.setBackground(new java.awt.Color(253, 250, 243));
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel9.setOpaque(true);
        jLabel9.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel10.setBackground(new java.awt.Color(253, 250, 243));
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel10.setOpaque(true);
        jLabel10.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel12.setBackground(new java.awt.Color(253, 250, 243));
        jLabel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel12.setOpaque(true);
        jLabel12.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel13.setBackground(new java.awt.Color(253, 250, 243));
        jLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel13.setOpaque(true);
        jLabel13.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel14.setBackground(new java.awt.Color(253, 250, 243));
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel14.setOpaque(true);
        jLabel14.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel15.setBackground(new java.awt.Color(253, 250, 243));
        jLabel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel15.setOpaque(true);
        jLabel15.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel16.setBackground(new java.awt.Color(253, 250, 243));
        jLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel16.setOpaque(true);
        jLabel16.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel17.setBackground(new java.awt.Color(253, 250, 243));
        jLabel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel17.setOpaque(true);
        jLabel17.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel18.setBackground(new java.awt.Color(253, 250, 243));
        jLabel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel18.setOpaque(true);
        jLabel18.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel19.setBackground(new java.awt.Color(253, 250, 243));
        jLabel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel19.setOpaque(true);
        jLabel19.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel20.setBackground(new java.awt.Color(253, 250, 243));
        jLabel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel20.setOpaque(true);
        jLabel20.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel22.setBackground(new java.awt.Color(253, 250, 243));
        jLabel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel22.setOpaque(true);
        jLabel22.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel23.setBackground(new java.awt.Color(253, 250, 243));
        jLabel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel23.setOpaque(true);
        jLabel23.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel24.setBackground(new java.awt.Color(253, 250, 243));
        jLabel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel24.setOpaque(true);
        jLabel24.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel25.setBackground(new java.awt.Color(253, 250, 243));
        jLabel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel25.setOpaque(true);
        jLabel25.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel26.setBackground(new java.awt.Color(253, 250, 243));
        jLabel26.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel26.setOpaque(true);
        jLabel26.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel27.setBackground(new java.awt.Color(253, 250, 243));
        jLabel27.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel27.setOpaque(true);
        jLabel27.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel28.setBackground(new java.awt.Color(253, 250, 243));
        jLabel28.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel28.setOpaque(true);
        jLabel28.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel29.setBackground(new java.awt.Color(253, 250, 243));
        jLabel29.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel29.setOpaque(true);
        jLabel29.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel30.setBackground(new java.awt.Color(253, 250, 243));
        jLabel30.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel30.setOpaque(true);
        jLabel30.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel32.setBackground(new java.awt.Color(253, 250, 243));
        jLabel32.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel32.setOpaque(true);
        jLabel32.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel33.setBackground(new java.awt.Color(253, 250, 243));
        jLabel33.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel33.setOpaque(true);
        jLabel33.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel34.setBackground(new java.awt.Color(253, 250, 243));
        jLabel34.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel34.setOpaque(true);
        jLabel34.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel35.setBackground(new java.awt.Color(253, 250, 243));
        jLabel35.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel35.setOpaque(true);
        jLabel35.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel36.setBackground(new java.awt.Color(253, 250, 243));
        jLabel36.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel36.setOpaque(true);
        jLabel36.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel37.setBackground(new java.awt.Color(253, 250, 243));
        jLabel37.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel37.setOpaque(true);
        jLabel37.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel38.setBackground(new java.awt.Color(253, 250, 243));
        jLabel38.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel38.setOpaque(true);
        jLabel38.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel39.setBackground(new java.awt.Color(253, 250, 243));
        jLabel39.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel39.setOpaque(true);
        jLabel39.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel40.setBackground(new java.awt.Color(253, 250, 243));
        jLabel40.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel40.setOpaque(true);
        jLabel40.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel42.setBackground(new java.awt.Color(253, 250, 243));
        jLabel42.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel42.setOpaque(true);
        jLabel42.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel43.setBackground(new java.awt.Color(253, 250, 243));
        jLabel43.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel43.setOpaque(true);
        jLabel43.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel44.setBackground(new java.awt.Color(253, 250, 243));
        jLabel44.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel44.setOpaque(true);
        jLabel44.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel45.setBackground(new java.awt.Color(253, 250, 243));
        jLabel45.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel45.setOpaque(true);
        jLabel45.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel46.setBackground(new java.awt.Color(253, 250, 243));
        jLabel46.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel46.setOpaque(true);
        jLabel46.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel47.setBackground(new java.awt.Color(253, 250, 243));
        jLabel47.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel47.setOpaque(true);
        jLabel47.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel48.setBackground(new java.awt.Color(253, 250, 243));
        jLabel48.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel48.setOpaque(true);
        jLabel48.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel49.setBackground(new java.awt.Color(253, 250, 243));
        jLabel49.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel49.setOpaque(true);
        jLabel49.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel50.setBackground(new java.awt.Color(253, 250, 243));
        jLabel50.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel50.setOpaque(true);
        jLabel50.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel52.setBackground(new java.awt.Color(253, 250, 243));
        jLabel52.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel52.setOpaque(true);
        jLabel52.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel53.setBackground(new java.awt.Color(253, 250, 243));
        jLabel53.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel53.setOpaque(true);
        jLabel53.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel54.setBackground(new java.awt.Color(253, 250, 243));
        jLabel54.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel54.setOpaque(true);
        jLabel54.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel55.setBackground(new java.awt.Color(253, 250, 243));
        jLabel55.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel55.setOpaque(true);
        jLabel55.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel56.setBackground(new java.awt.Color(253, 250, 243));
        jLabel56.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel56.setOpaque(true);
        jLabel56.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel57.setBackground(new java.awt.Color(253, 250, 243));
        jLabel57.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel57.setOpaque(true);
        jLabel57.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel58.setBackground(new java.awt.Color(253, 250, 243));
        jLabel58.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel58.setOpaque(true);
        jLabel58.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel59.setBackground(new java.awt.Color(253, 250, 243));
        jLabel59.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel59.setOpaque(true);
        jLabel59.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel60.setBackground(new java.awt.Color(253, 250, 243));
        jLabel60.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel60.setOpaque(true);
        jLabel60.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel62.setBackground(new java.awt.Color(253, 250, 243));
        jLabel62.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel62.setOpaque(true);
        jLabel62.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel63.setBackground(new java.awt.Color(253, 250, 243));
        jLabel63.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel63.setOpaque(true);
        jLabel63.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel64.setBackground(new java.awt.Color(253, 250, 243));
        jLabel64.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel64.setOpaque(true);
        jLabel64.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel65.setBackground(new java.awt.Color(253, 250, 243));
        jLabel65.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel65.setOpaque(true);
        jLabel65.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel66.setBackground(new java.awt.Color(253, 250, 243));
        jLabel66.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel66.setOpaque(true);
        jLabel66.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel67.setBackground(new java.awt.Color(253, 250, 243));
        jLabel67.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel67.setOpaque(true);
        jLabel67.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel68.setBackground(new java.awt.Color(253, 250, 243));
        jLabel68.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel68.setOpaque(true);
        jLabel68.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel69.setBackground(new java.awt.Color(253, 250, 243));
        jLabel69.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel69.setOpaque(true);
        jLabel69.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel70.setBackground(new java.awt.Color(253, 250, 243));
        jLabel70.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel70.setOpaque(true);
        jLabel70.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel72.setBackground(new java.awt.Color(253, 250, 243));
        jLabel72.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel72.setOpaque(true);
        jLabel72.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel73.setBackground(new java.awt.Color(253, 250, 243));
        jLabel73.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel73.setOpaque(true);
        jLabel73.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel74.setBackground(new java.awt.Color(253, 250, 243));
        jLabel74.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel74.setOpaque(true);
        jLabel74.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel75.setBackground(new java.awt.Color(253, 250, 243));
        jLabel75.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel75.setOpaque(true);
        jLabel75.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel76.setBackground(new java.awt.Color(253, 250, 243));
        jLabel76.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel76.setOpaque(true);
        jLabel76.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel77.setBackground(new java.awt.Color(253, 250, 243));
        jLabel77.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel77.setOpaque(true);
        jLabel77.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel78.setBackground(new java.awt.Color(253, 250, 243));
        jLabel78.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel78.setOpaque(true);
        jLabel78.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel79.setBackground(new java.awt.Color(253, 250, 243));
        jLabel79.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel79.setOpaque(true);
        jLabel79.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel80.setBackground(new java.awt.Color(253, 250, 243));
        jLabel80.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel80.setOpaque(true);
        jLabel80.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel82.setBackground(new java.awt.Color(253, 250, 243));
        jLabel82.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel82.setOpaque(true);
        jLabel82.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel83.setBackground(new java.awt.Color(253, 250, 243));
        jLabel83.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel83.setOpaque(true);
        jLabel83.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel84.setBackground(new java.awt.Color(253, 250, 243));
        jLabel84.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel84.setOpaque(true);
        jLabel84.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel85.setBackground(new java.awt.Color(253, 250, 243));
        jLabel85.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel85.setOpaque(true);
        jLabel85.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel86.setBackground(new java.awt.Color(253, 250, 243));
        jLabel86.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel86.setOpaque(true);
        jLabel86.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel87.setBackground(new java.awt.Color(253, 250, 243));
        jLabel87.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel87.setOpaque(true);
        jLabel87.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel88.setBackground(new java.awt.Color(253, 250, 243));
        jLabel88.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel88.setOpaque(true);
        jLabel88.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel89.setBackground(new java.awt.Color(253, 250, 243));
        jLabel89.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel89.setOpaque(true);
        jLabel89.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel90.setBackground(new java.awt.Color(253, 250, 243));
        jLabel90.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel90.setOpaque(true);
        jLabel90.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel92.setBackground(new java.awt.Color(253, 250, 243));
        jLabel92.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel92.setOpaque(true);
        jLabel92.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel93.setBackground(new java.awt.Color(253, 250, 243));
        jLabel93.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel93.setOpaque(true);
        jLabel93.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel94.setBackground(new java.awt.Color(253, 250, 243));
        jLabel94.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel94.setOpaque(true);
        jLabel94.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel95.setBackground(new java.awt.Color(253, 250, 243));
        jLabel95.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel95.setOpaque(true);
        jLabel95.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel96.setBackground(new java.awt.Color(253, 250, 243));
        jLabel96.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel96.setOpaque(true);
        jLabel96.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel97.setBackground(new java.awt.Color(253, 250, 243));
        jLabel97.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel97.setOpaque(true);
        jLabel97.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel98.setBackground(new java.awt.Color(253, 250, 243));
        jLabel98.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel98.setOpaque(true);
        jLabel98.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel99.setBackground(new java.awt.Color(253, 250, 243));
        jLabel99.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel99.setOpaque(true);
        jLabel99.setPreferredSize(new java.awt.Dimension(95, 71));

        jLabel100.setBackground(new java.awt.Color(253, 250, 243));
        jLabel100.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel100.setOpaque(true);
        jLabel100.setPreferredSize(new java.awt.Dimension(95, 71));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion de la Busqueda", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        algoritmo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        algoritmo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Algoritmo Usado", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel250.setText("Nodos expandidos:");

        jLabel251.setText("Profundidad:");

        jLabel252.setText("Tiempo de Computo: ");

        tiempoDeComputo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        listaSolucion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Solucion", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jScrollPane1.setViewportView(listaSolucion);

        nodos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        etiquetaProfundidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(algoritmo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel250, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel251, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel252, javax.swing.GroupLayout.PREFERRED_SIZE, 148, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etiquetaProfundidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tiempoDeComputo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(nodos, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nodosExpandidos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(208, 208, 208)
                                .addComponent(profundidadArbol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(187, 187, 187))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(algoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel250)
                    .addComponent(nodos, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel251))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(nodosExpandidos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(profundidadArbol))
                    .addComponent(jLabel252)
                    .addComponent(tiempoDeComputo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Controles", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/anterior.jpg"))); // NOI18N
        jButton7.setBorderPainted(false);
        jButton7.setFocusPainted(false);
        jButton7.setFocusable(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/finalizar.jpg"))); // NOI18N
        jButton10.setBorderPainted(false);
        jButton10.setFocusPainted(false);
        jButton10.setFocusable(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/siguiente.jpg"))); // NOI18N
        jButton9.setBorderPainted(false);
        jButton9.setFocusPainted(false);
        jButton9.setFocusable(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iniciar.jpg"))); // NOI18N
        jButton11.setBorderPainted(false);
        jButton11.setFocusPainted(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jMenu1.setText("Archivo");

        jMenuItem2.setMnemonic('A');
        jMenuItem2.setText("Abrir ambiente");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setMnemonic('S');
        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Tipo de Busqueda");

        amplitud.setText("Busqueda No Informada");

        jMenuItem5.setMnemonic('A');
        jMenuItem5.setText("Preferente por Amplitud");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        amplitud.add(jMenuItem5);

        profundidad.setMnemonic('A');
        profundidad.setText("Preferente por Profundidad");
        profundidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profundidadActionPerformed(evt);
            }
        });
        amplitud.add(profundidad);

        costo.setMnemonic('A');
        costo.setText("Costo Uniforme");
        costo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costoActionPerformed(evt);
            }
        });
        amplitud.add(costo);

        jMenu2.add(amplitud);

        jMenu5.setText("Busqueda Informada");

        avara.setMnemonic('A');
        avara.setText("Avara");
        avara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avaraActionPerformed(evt);
            }
        });
        jMenu5.add(avara);

        a.setMnemonic('A');
        a.setText("A*");
        a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aActionPerformed(evt);
            }
        });
        jMenu5.add(a);

        jMenu2.add(jMenu5);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ayuda");

        jMenuItem3.setText("Informacion");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Acerca de...");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 26, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    
    //Metodo para abrir el ambiente
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
            
        try
        {           
            this.setVisible(false);
            JFileChooser selectorDeAmbiente=new JFileChooser();
            selectorDeAmbiente.setFileSelectionMode(JFileChooser.FILES_ONLY);
            
            JTextArea areaDeTexto=new JTextArea();
            int seleccion=selectorDeAmbiente.showOpenDialog(areaDeTexto);
            if (seleccion == JFileChooser.APPROVE_OPTION)
            {
                ambienteArchivo=selectorDeAmbiente.getSelectedFile();
            }
            if (seleccion == JFileChooser.CANCEL_OPTION)
            {
                JOptionPane.showMessageDialog(null,"Debe escoger un ambiente");
            }
            archivo=new  LectorDeArchivo(ambienteArchivo);
            matriz=new Matriz(archivo.leerArchivo());
            estado=new Estado(matriz);
            problema=new Problema(estado);        
        }
        catch(NullPointerException e){ }       
        JOptionPane.showMessageDialog(null,"Ha selecionado el archivo ubicado en :\n"+ambienteArchivo.getPath());
        this.setVisible(true);
        cargarEstadoEnPantalla(matriz);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        GUIAcercaDe info=new GUIAcercaDe();  
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       new GUIInformacion();

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        int seleccionado=listaSolucion.getSelectedIndex();
        if(seleccionado>0)
        {
            cargarEstadoEnPantalla(parejas.elementAt(seleccionado-1).getEstado().getMatriz());
            listaSolucion.setSelectedIndex(seleccionado-1);
        }
        else
        {    
            cargarEstadoEnPantalla(matriz);
            listaSolucion.removeSelectionInterval(0,parejas.size());
        }    
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        cargarEstadoEnPantalla(matriz);
        listaSolucion.removeSelectionInterval(0,parejas.size());
    }//GEN-LAST:event_jButton11ActionPerformed

    //Busqueda preferente por amplitud
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        cargarEstadoEnPantalla(matriz);
        Vector<String> operadoresDePareja = new Vector();        
        algoritmo.setText("Busqueda Preferente Por Amplitud");
        BusquedaPreferentePorAmplitud bus = new  BusquedaPreferentePorAmplitud(problema);
        tiempoInicio=System.currentTimeMillis();
        Vector<Operador> res = bus.aplicarAlgoritmo();
        tiempoTotal= System.currentTimeMillis()-tiempoInicio;
        Vector<Operador> salida=ordenarVectorSalida(res);
        for(int i=0;i<salida.size();i++)
        {
            operadoresDePareja.add(salida.elementAt(i).toStringOperador());
        }
        listaSolucion.setListData(operadoresDePareja);
        etiquetaProfundidad.setText(bus.getProfundidadDelArbol()+"");
        tiempoDeComputo.setText(tiempoTotal+" milisegundos");
        nodos.setText(bus.getcantidadDeNodosExpandidos()+"");

        /**eventos en botones*/
        generarParOperadorEstado(estado,salida);

    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void profundidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profundidadActionPerformed
        cargarEstadoEnPantalla(matriz);
        Vector<String> operadoresDePareja=new Vector();
        algoritmo.setText("Busqueda Preferente Por Profundidad");
        BusquedaPreferentePorProfundidad bus=new  BusquedaPreferentePorProfundidad(problema);
        tiempoInicio=System.currentTimeMillis();
        Vector<Operador> res=bus.aplicarAlgoritmo();
        tiempoTotal= System.currentTimeMillis()-tiempoInicio;
               
        Vector<Operador> salida=ordenarVectorSalida(res);
        for(int i=0;i<salida.size();i++)
        {
            operadoresDePareja.add(salida.elementAt(i).toStringOperador());
        }
        listaSolucion.setListData(operadoresDePareja);
        etiquetaProfundidad.setText(bus.getProfundidadDelArbol()+"");
        tiempoDeComputo.setText(tiempoTotal+" milisegundos");
        nodos.setText(bus.getcantidadDeNodosExpandidos()+"");
        
        generarParOperadorEstado(estado,salida);

    }//GEN-LAST:event_profundidadActionPerformed

    private void costoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costoActionPerformed
        cargarEstadoEnPantalla(matriz);
        Vector<String> operadoresDePareja=new Vector();
        algoritmo.setText("Busqueda De Costo Uniforme"); 
        BusquedaDeCostoUniforme bus=new  BusquedaDeCostoUniforme(problema);
        tiempoInicio=System.currentTimeMillis();
        Vector<Operador> res=bus.aplicarAlgoritmo();
        tiempoTotal= System.currentTimeMillis()-tiempoInicio;        
        tiempoTotal= System.currentTimeMillis()-tiempoInicio;
        Vector<Operador> salida=ordenarVectorSalida(res);
        for(int i=0;i<salida.size();i++)
        {
            operadoresDePareja.add(salida.elementAt(i).toStringOperador());
        }        
        listaSolucion.setListData(operadoresDePareja);
        etiquetaProfundidad.setText(bus.getProfundidadDelArbol()+"");
        tiempoDeComputo.setText(tiempoTotal+" milisegundos");
        nodos.setText(bus.getcantidadDeNodosExpandidos()+"");
        
        generarParOperadorEstado(estado,salida);

    }//GEN-LAST:event_costoActionPerformed

    private void avaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avaraActionPerformed
        cargarEstadoEnPantalla(matriz);
        Vector<String> operadoresDePareja=new Vector();
        algoritmo.setText("Busqueda Avara");
        BusquedaAvara bus=new  BusquedaAvara(problema);
        tiempoInicio=System.currentTimeMillis();
        Vector<Operador> res=bus.aplicarAlgoritmo();
        tiempoTotal= System.currentTimeMillis()-tiempoInicio;

        tiempoTotal= System.currentTimeMillis()-tiempoInicio;
        Vector<Operador> salida=ordenarVectorSalida(res);
        for(int i=0;i<salida.size();i++)
        {
            operadoresDePareja.add(salida.elementAt(i).toStringOperador());
        }
        listaSolucion.setListData(operadoresDePareja);
        etiquetaProfundidad.setText(bus.getProfundidadDelArbol()+"");
        tiempoDeComputo.setText(tiempoTotal+" milisegundos");
        nodos.setText(bus.getcantidadDeNodosExpandidos()+"");

        generarParOperadorEstado(estado,salida);
    }//GEN-LAST:event_avaraActionPerformed

    private void aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aActionPerformed
        cargarEstadoEnPantalla(matriz);
        Vector<String> operadoresDePareja=new Vector();
        algoritmo.setText("Busqueda A*");
        BusquedaAEstrella bus=new BusquedaAEstrella(problema); 
        tiempoInicio=System.currentTimeMillis();
        Vector<Operador> res=bus.aplicarAlgoritmo();
        tiempoTotal= System.currentTimeMillis()-tiempoInicio;

        tiempoTotal= System.currentTimeMillis()-tiempoInicio;
        Vector<Operador> salida=ordenarVectorSalida(res);
        for(int i=0;i<salida.size();i++)
        {
            operadoresDePareja.add(salida.elementAt(i).toStringOperador());
        }
        listaSolucion.setListData(operadoresDePareja);
        etiquetaProfundidad.setText(bus.getProfundidadDelArbol()+"");
        tiempoDeComputo.setText(tiempoTotal+" milisegundos");
        nodos.setText(bus.getcantidadDeNodosExpandidos()+"");

        generarParOperadorEstado(estado,salida);
    }//GEN-LAST:event_aActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        cargarEstadoEnPantalla(parejas.lastElement().getEstado().getMatriz());
        listaSolucion.setSelectedIndex(parejas.size()-1); 
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int seleccionado=listaSolucion.getSelectedIndex();
        int[] coordenadas4 = new int[2];
        int[] coordenadas5 = new int[2];
        int[] coordenada5 = new int[2];
        Matriz matriz1;
        coordenadas5 = matriz.retornarCoordenadaDeObjetos('5');                
        if(seleccionado<(parejas.size()-1))
        {
            coordenadas4 = parejas.elementAt(seleccionado+1).getEstado().getMatriz().retornarCoordenadaDeObjetos('4');
            coordenada5 = parejas.elementAt(seleccionado+1).getEstado().getMatriz().retornarCoordenadaDeObjetos('5');
           
            if((coordenadas4[0]==coordenadas5[0])&&(coordenadas4[1]==coordenadas5[1]))
            {
                matriz1 = parejas.elementAt(seleccionado+1).getEstado().getMatriz();
                matriz1.actualizarCasilla(coordenadas5[0],coordenadas5[1],'4');
                if((coordenada5!=null)&&(coordenada5[0]!=coordenadas5[0])&&(coordenada5[1]!=coordenadas5[1])&&(coordenada5[0]==0)&&(coordenada5[1]==0))
                    matriz1.actualizarCasilla(0,0,'0');
                cargarEstadoEnPantalla(matriz1);
            }
            else
            {
                matriz1 = parejas.elementAt(seleccionado+1).getEstado().getMatriz();
                matriz1.actualizarCasilla(coordenadas5[0],coordenadas5[1],'5');
                if((coordenada5!=null)&&(coordenada5[0]!=coordenadas5[0])&&(coordenada5[1]!=coordenadas5[1])&&(coordenada5[0]==0)&&(coordenada5[1]==0))
                    matriz1.actualizarCasilla(0,0,'0');
                cargarEstadoEnPantalla(matriz1);
            }
            listaSolucion.setSelectedIndex(seleccionado+1);
        }        
    }//GEN-LAST:event_jButton9ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem a;
    private javax.swing.JLabel algoritmo;
    private javax.swing.JMenu amplitud;
    private javax.swing.JMenuItem avara;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JMenuItem costo;
    private javax.swing.JLabel etiquetaProfundidad;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel250;
    private javax.swing.JLabel jLabel251;
    private javax.swing.JLabel jLabel252;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listaSolucion;
    private javax.swing.JLabel nodos;
    private javax.swing.JLabel nodosExpandidos;
    private javax.swing.JMenuItem profundidad;
    private javax.swing.JLabel profundidadArbol;
    private javax.swing.JLabel tiempoDeComputo;
    // End of variables declaration//GEN-END:variables

}
