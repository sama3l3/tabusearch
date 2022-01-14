import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Main extends JFrame {
    private static File file;
    private static final Read r= new Read();
    private static final Path path = Path.of(System.getProperty("user.dir"));

    /*
        Componenti swing per grafica ( JFrame in stack senza griglia, per modificare
                                        usare x e y)
     */

    private static final JFrame f=new JFrame("Tabu Search Algorithm");
    private static final JButton b = new JButton("Calcola Ottimo");
    private static final JButton filechooser = new JButton("Scegli file ...");
    private static final JFileChooser chooser = new JFileChooser(String.valueOf(path));


    public static void main(String[] args)  {

        /*
        Bounds componenti
         */
        b.setBounds(100, 200, 150, 40);
        filechooser.setBounds(100,50,150, 40);

        /*
        aggiunti componenti al JFrame
         */
        f.add(filechooser);
        f.add(b);
        f.setSize(350,350);

        /*
        layout settato a null e reso visibile il frame
         */
        f.setLayout(null);
        f.setVisible(true);

        /*
        link JFileChooser con bottone
         */
        filechooser.addActionListener(e -> {
            chooser.showOpenDialog(null);
            file = chooser.getSelectedFile();
        });

        /*
        attivazione localopt attraverso bottone "calcola ottimo"
         */
        b.addActionListener(e -> {
            r.read(file.getPath());
            TabuSearch t= new TabuSearch(r.getJobs());
            t.localopt();

           /*
           //apre cartella del progetto
            try {
                String str = System.getProperty("os.name").toLowerCase();

                if(str.startsWith("win")) {

                    Runtime.getRuntime().exec("explorer.exe /select," + file.getPath()); //apre esplora risorse dopo aver elaborato

                }
                else
                {
                   Runtime.getRuntime().exec("nautilus " + System.getProperty("user.dir")); //ubuntu 20.4 LTS funziona
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            */
        });
    }
}
