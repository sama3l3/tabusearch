import java.io.*;
import java.util.*;
public class Read {

    FileWriter myWriter;

    {
        try {
            myWriter = new FileWriter("res.lpt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private HashMap<Integer, List<Integer>> jobs= new LinkedHashMap<>();
    public void initList(HashMap<Integer, List<Integer>> job,int key){
        List<Integer> empty= new ArrayList<>();
        job.put(key,empty);
    }
    private int lines = 0;
    private int machines = 0;
    private int n = 0;

    public HashMap<Integer, List<Integer>> getJobs() {
        return jobs;
    }

    public void setJobs(HashMap<Integer, List<Integer>> jobs) {
        this.jobs = jobs;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public int getMachines() {
        return machines;
    }

    public void setMachines(int machines) {
        this.machines = machines;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void read(String name) {
        if (name != null) {
            try {
                BufferedReader f = new BufferedReader(new FileReader(name));
                String s = f.readLine();
                String[] ss;
                ss = s.split("[ ]");

                lines = Integer.parseInt(ss[0]);
                machines = Integer.parseInt(ss[1]);
                n = Integer.parseInt(ss[2]) * machines * lines;

                System.out.println("total jobs: " + n);
                myWriter.write("Minimize R\n");
                f.readLine();

                for(int i=0;i<lines;i++) {
                    s = f.readLine();
                    String[] str = s.split("\t+");
                    int key;
                    int time;
                    List<Integer> l= new ArrayList<>();
                    for (int j = 1; j < str.length; j += 2) {
                        time=Integer.parseInt(str[j+1]);
                        key=Integer.parseInt(str[j]);
                        l.add(time);
                        if(i==0)
                            initList(jobs,key);
                    }
                    int min=Collections.min(l);
                    boolean used = false;

                    for(int j=0;j<l.size();j++){
                        if(l.get(j)== min && !used) {
                            jobs.get(j).add(min);
                            used = true;  //per risolvere ripetizioni di job nelle altre macchine
                        }
                        else
                            jobs.get(j).add(0);
                    }
                }
                for (int i : jobs.keySet()) {
                    System.out.println("machine " + i);

                    System.out.println(jobs.get(i).toString());
                    System.out.println("********************************************************************************************************************************************************************************************************************************************************************************");

                    /*
                    per il file res, funzione obiettivo
                     */
                    myWriter.write("x_"+ i + "*" + jobs.get(i).get(0) + " +" + " x_"+ i + "*" + jobs.get(i).get(1) + " +" + " x_"+ i + "*" + jobs.get(i).get(2) + "\n");
                    if(i < machines-1) myWriter.write("+" + "\n"); //controllo per evitare di stampare un + di troppo
                    else myWriter.write("\n"); //per formattazione cplex


                }

                /*
                funzione subject to
                 */
                myWriter.write("Subject To\n");
                for(int i : jobs.keySet()) {
                    int n = 0;
                    myWriter.write(jobs.get(i).get(n) + "*" + "x_" + i );
                    if(i < machines-1) myWriter.write(" + ");
                    n++;
                }
                myWriter.write(" >=1");


                myWriter.close(); //close filewriter

            } catch (FileNotFoundException e) {
                //e.printStackTrace();
                System.out.println("File not found");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: file name is empty");
        }
    }
}
