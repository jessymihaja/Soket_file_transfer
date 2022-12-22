import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.border.*;
import java.lang.*;
import java.net.*;

public class Client extends JFrame {
//partie affichage frame
    JScrollPane scrollPane;

    public Client() {
        super("Socket transfert de fichiers");


        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e){
            System.out.println("erreur,restauration par defaut");
        }

//regulation liste deroualnte
        JTextArea log = new JTextArea(5,55); // 5,60
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);


        JFileChooser fc = new JFileChooser();

        JButton openButton = new JButton("Open");
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(Client.this);

                log.append("Opening ...");

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    log.append(file.getName() + "\n");


                    Client_socket client = new Client_socket();
                    if (client.send(file.getPath().toString(),file.getName()))
                        log.append("reussi" + "\n");
                    else
                        log.append("echouer" + "\n");
                }
            }
        });


        JPanel h1=new JPanel();
        JLabel h1label=new JLabel("Bienvenu sur la plateforme de transfert de fichier");
         h1label.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        h1.add(h1label);
        JPanel buttonPanel = new JPanel();
        JPanel labelpanel=new JPanel();
        JLabel label=new JLabel("selectioner un fichier ici    =>");
       buttonPanel.add(label);
        buttonPanel.add(openButton);
        label.setBorder(new LineBorder(Color.black));


        Container contentPane = getContentPane();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints grid = new GridBagConstraints();
        contentPane.setLayout(gridbag);
//redimensionnement de l'affichage
        grid.fill = GridBagConstraints.VERTICAL;
       grid.weightx = 0.0;
       grid.gridx = 0;
       grid.gridy = 0;
       
        gridbag.setConstraints(h1, grid);
        contentPane.add(h1);

       grid.fill = GridBagConstraints.VERTICAL;
       grid.weightx = 0.0;
       grid.gridx = 0;
       grid.gridy = 1;
       
        gridbag.setConstraints(buttonPanel, grid);
        contentPane.add(buttonPanel);

       grid.weightx = 0.0;
       grid.gridwidth = 60;
       grid.gridx = 0;
       grid.gridy = 2;
        gridbag.setConstraints(logScrollPane, grid);
        logScrollPane.setBorder(new LineBorder(Color.black));
        contentPane.add(logScrollPane);
    }

//partie main
    public static void main(String[] args) {
        JFrame frame = new Client();
        frame.pack();
        frame.setBounds(700,400,700,500 );
        frame.setVisible(true);
    }

}

