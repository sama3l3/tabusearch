import java.io.*;
import java.util.*;
public class Read {

    private HashMap<Integer, List<Integer>> jobs= new LinkedHashMap<>();
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
                f.readLine();
                ArrayList<Integer> strings = new ArrayList<>();
                for(int i=0;i<lines;i++) {
                    s = f.readLine();
                    String[] str = s.split("\t+");
                    int key;
                    int value;

                    for (int j = 1; j < str.length; j += 2) {
                        List<Integer> l = new ArrayList<>();
                        key=Integer.parseInt(str[j]);
                        value=Integer.parseInt(str[j+1]);
                        if(!jobs.containsKey(key)) {
                            l.add(value);
                            jobs.put(key, l);
                        }
                        else{
                            jobs.get(key).add(value);
                        }
                    }
                }
                for (int i : jobs.keySet()) {
                    System.out.println("machine " + i);
                    System.out.println(jobs.get(i).toString());
                    System.out.println("********************************************************************************************************************************************************************************************************************************************************************************");
                }
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
