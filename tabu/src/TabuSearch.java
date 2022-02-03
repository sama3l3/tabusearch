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
    public void tabuList(int j,int k){
        Element e= new Element(j,k);
        if(tabulist.size()>10) {
            tabulist.poll();
        }
        tabulist.add(e);

    }
    public void localopt(){
        for(int i=0;i<jobs.size()-1;i++){
            flag= jobs.get(i).size() == jobs.get(i + 1).size();
        }
        System.out.println("same length control passed: " + flag);
        if(flag) {
            int max = 0;
            int min=0;
            int count=0;
            for (int i = 0; i < jobs.size(); i++) {
                int localMax = Collections.max(jobs.get(i));
                int localMin= Collections.min(jobs.get(i));
                int localCount=0;
                for(Integer integer: jobs.get(i)) {
                    localCount += integer;
                }
                    if(localCount > count) {
                        if (localMax > max) {
                            max = localMax;
                        }
                        count = localCount;
                    }
                if(localMin < min)
                    min=localMin;
            }
            for(int i=0;i< jobs.size();i++) {
                if (jobs.get(i).contains(max)) {
                    int index1 = jobs.get(i).indexOf(max);
                    int job1 = jobs.get(i).get(index1);
                    for (int j = 0; j < jobs.size(); j++) {
                        if (jobs.get(j).contains(min)) {
                            int index2 = jobs.get(j).indexOf(min);
                            int job2 = jobs.get(j).get(index2);
                            boolean done=false;
                            for(Element e: tabulist)
                                if(Objects.compare(e, new Element(job1, job2), (o1, o2) -> {
                                    if(o1.val == o2.val && o1.prevval == o2.prevval)
                                        return 0;
                                    else
                                        return 1;
                                })==0)
                                {
                                    done = true;
                                    break;
                                }


                            if (!done) {
                                jobs.get(i).remove(index1);
                                jobs.get(i).add(job2);
                                jobs.get(j).remove(index2);
                                jobs.get(j).add(job1);
                                tabuList(job1,job2);
                            }


                        }


                    }
                }
            }
            System.out.println("elements in HashMap after: "+ jobs);
            int c=0;
            for (int i : jobs.keySet()) {
                c+=jobs.get(i).size();
            }System.out.println("total elements "+ c);
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