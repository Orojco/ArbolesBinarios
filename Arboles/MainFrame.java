import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private ListaEnlazada<String> listaEnlazada;
    private ListaDobleEnlazada<String> listaDobleEnlazada;
    private JTextArea outputArea;

    public MainFrame() {
        listaEnlazada = new ListaEnlazada<>();
        listaDobleEnlazada = new ListaDobleEnlazada<>();
        outputArea = new JTextArea(10, 40);

        // Configuración básica de la ventana
        setTitle("Diagrama de Clases - Estructuras de Datos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Crear paneles específicos para cada estructura de datos
        JPanel controlPanel = createControlPanel();
        JScrollPane outputPanel = createOutputPanel();

        // Agregar paneles al panel principal
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(outputPanel, BorderLayout.CENTER);

        // Agregar panel principal a la ventana
        add(mainPanel);
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Control Panel"));

        JTextField inputField = new JTextField(10);
        JButton insertButton = new JButton("Insertar");
        JButton deleteButton = new JButton("Eliminar");
        JButton displayButton = new JButton("Mostrar");

        panel.add(new JLabel("Elemento:"));
        panel.add(inputField);
        panel.add(insertButton);
        panel.add(deleteButton);
        panel.add(displayButton);

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = inputField.getText();
                if (!data.isEmpty()) {
                    listaEnlazada.insertar(data);
                    listaDobleEnlazada.insertar(data);
                    inputField.setText("");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = inputField.getText();
                if (!data.isEmpty()) {
                    listaEnlazada.eliminar(data);
                    listaDobleEnlazada.eliminar(data);
                    inputField.setText("");
                }
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("Lista Enlazada:\n");
                listaEnlazada.imprimir();
                outputArea.append("\nLista Doble Enlazada:\n");
                listaDobleEnlazada.imprimir();
            }
        });
        

        return panel;
    }

    private JScrollPane createOutputPanel() {
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output Panel"));
        outputArea.setEditable(false);
        return scrollPane;
    }

    public static void main(String[] args) {
        // Crear y mostrar la ventana principal
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
