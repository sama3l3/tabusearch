import java.util.*;

public class TabuSearch {
    private Queue<Element> tabulist = new ArrayDeque<>();
    private HashMap<Integer, List<Integer>> jobs;
    private boolean flag=false;
    public TabuSearch(HashMap<Integer, List<Integer>> jobs) {
        this.jobs = jobs;
        System.out.println("elements in HashMap before: "+ jobs);
        int count=0;
        for (int i : jobs.keySet()) {
            count+=jobs.get(i).size();

        }System.out.println("total elements " + count);
    }
    public void tabuList(int j, int k){
        Element el= new Element(j,k);
        if(tabulist.size()<1000) {
            System.out.println("ok");
        }
        else {
            tabulist.poll();
        }
        tabulist.add(el);

    }
    public void localopt(){
        for(int i=0;i<jobs.size()-1;i++){
                flag= jobs.get(i).size() == jobs.get(i + 1).size();
        }
        System.out.println("same length control passed: " + flag);
        if(flag) {
            for (int i = 0; i < jobs.size() - 1; i++) {
                for (int j = 0; j < jobs.get(i).size(); j++) {
                    if (jobs.get(i + 1).get(j) > jobs.get(i).get(j)) {
                        Element el1= new Element(jobs.get(i).get(j),jobs.get(i + 1).get(j));
                        Element el2= new Element(jobs.get(i + 1).get(j),jobs.get(i).get(j));
                        if(!tabulist.contains(el1) && !tabulist.contains(el2)) {
                            int tmp = jobs.get(i).get(j);
                            jobs.get(i).remove(jobs.get(i).get(j));
                            jobs.get(i).add(jobs.get(i + 1).get(j));
                            jobs.get(i + 1).remove(jobs.get(i + 1).get(j));
                            jobs.get(i + 1).add(tmp);
                            tabuList(jobs.get(i).get(j), jobs.get(i + 1).get(j));
                        }
                        else
                            System.out.println("Tabu");

                    }
                }
            }
            System.out.println("elements in HashMap after: "+ jobs);
            int count=0;
            for (int i : jobs.keySet()) {
                count+=jobs.get(i).size();
            }System.out.println("total elements "+ count);
        }
    }

    public Queue<Element> getTabulist() {
        return tabulist;
    }

    public void setTabulist(Queue<Element> tabulist) {
        this.tabulist = tabulist;
    }

    public HashMap<Integer, List<Integer>> getJobs() {
        return jobs;
    }

    public void setJobs(HashMap<Integer, List<Integer>> jobs) {
        this.jobs = jobs;
    }
}