/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 *
 * @author Usuario Asus
 */
public class Resultados extends javax.swing.JFrame {

    /**
     * Creates new form Resultados
     */
    Vector<String> ruta;
    private ImageIcon arregloDeImagenes[] = new ImageIcon[4];
    private PintarfondoCeldas movimientos[][]; //Matriz de Jlabel
    int limite = 0, filas = 0, columnas = 0;

    public Resultados(Vector<String> ruta, int tamanoSolucion) {
        initComponents();
        //imagenes
        arregloDeImagenes[0] = new ImageIcon("src/Imagenes/ar.png");
        arregloDeImagenes[1] = new ImageIcon("src/Imagenes/ab.png");
        arregloDeImagenes[2] = new ImageIcon("src/Imagenes/izq.png");
        arregloDeImagenes[3] = new ImageIcon("src/Imagenes/der.png");
        this.ruta = ruta;
        limite = tamanoSolucion;
        obtenerFilasColumnas(limite);
        movimientos = new PintarfondoCeldas[filas][columnas];
        crearTablero(ruta);
    }

    private void obtenerFilasColumnas(int tamano) {
        if (tamano <= 10) {
            filas = 1;
            columnas = tamano;
        } else {
            columnas = 10;
            filas = (int) Math.ceil(tamano / 10);
        }
    }

    private Icon retornarMovimiento(String movimiento) {
        switch (movimiento) {
            case "Arriba":
                return arregloDeImagenes[0];
            case "Abajo":
                return arregloDeImagenes[1];
            case "Izquierda":
                return arregloDeImagenes[2];
            case "Derecha":
                return arregloDeImagenes[3];
        }
        return null;
    }

    public void crearTablero(Vector<String> ruta) {
        panelResultado.removeAll();
        panelResultado.setLayout(new GridLayout(filas, columnas));
        int x = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (x < limite) {
                    movimientos[i][j] = new PintarfondoCeldas();
                    movimientos[i][j].setIcon(retornarMovimiento(ruta.elementAt(x)));
                    movimientos[i][j].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    movimientos[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                    movimientos[i][j].setFont(new Font("Arial", 1, 12));
                    panelResultado.add(movimientos[i][j]);
                    x++;
                }
            }
        }
        panelResultado.updateUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelResultado = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout panelResultadoLayout = new javax.swing.GroupLayout(panelResultado);
        panelResultado.setLayout(panelResultadoLayout);
        panelResultadoLayout.setHorizontalGroup(
            panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        panelResultadoLayout.setVerticalGroup(
            panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelResultado;
    // End of variables declaration//GEN-END:variables
}
