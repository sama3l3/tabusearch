import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Read {
    String[] ss;
    int lines=0;
    int machines=0;
    int jobs=0;
    @Override
    public String toString() {
        return "Read{" +
                "ss=" + Arrays.toString(ss) +
                '}';
    }

    public void read(String name){
        if(name != null){
            try {
                BufferedReader f= new BufferedReader(new FileReader(name));
                String s= f.readLine();
                ss= s.split("[ ]");
                //System.out.println(Arrays.toString(ss));
                lines=Integer.parseInt(ss[0]);
                machines = Integer.parseInt(ss[1]);
                jobs=Integer.parseInt(ss[2])*machines*lines;
                System.out.println(jobs);
                f.readLine();
                ArrayList<String> strings= new ArrayList<>();
                s= f.readLine();
                while(s != null){
                    strings.add(s);
                    s= f.readLine();
                }

            } catch (FileNotFoundException e) {
                //e.printStackTrace();
                System.out.println("File not found");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else{
            System.out.println("Error: file name is empty");
        }
    }
}
