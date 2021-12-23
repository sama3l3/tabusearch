import java.io.*;
import java.util.*;
public class Read {

    HashMap<Integer, List<Integer>> jobs= new LinkedHashMap<>();
    HashMap<Integer, List<Integer>> map = new LinkedHashMap<>();
    int lines = 0;
    int machines = 0;
    int n = 0;

    public void read(String name) {
        if (name != null) {
            try {
                BufferedReader f = new BufferedReader(new FileReader(name));
                String s = f.readLine();
                String[] ss;
                ss = s.split("[ ]");
                //System.out.println(Arrays.toString(ss));
                lines = Integer.parseInt(ss[0]);
                machines = Integer.parseInt(ss[1]);
                n = Integer.parseInt(ss[2]) * 2 * machines * lines;
                System.out.println(n);
                f.readLine();
                ArrayList<Integer> strings = new ArrayList<>();
                for(int i=0;i<lines;i++) {
                    s = f.readLine();
                    String[] str = s.split("\t+");
                    System.out.println(Arrays.toString(s.split("\t+")));
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
                    System.out.print(i + " ");
                    System.out.println(jobs.get(i).toString());
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
