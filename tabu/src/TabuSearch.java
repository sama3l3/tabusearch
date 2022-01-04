import java.util.*;

public class TabuSearch {
    private Queue<Integer> list= new ArrayDeque<>();
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
    public void localopt(){
        for(int i=0;i<jobs.size()-1;i++){
                flag= jobs.get(i).size() == jobs.get(i + 1).size();
        }
        System.out.println("same length control passed: " + flag);
        if(flag) {
            for (int i = 0; i < jobs.size() - 1; i++) {
                for (int j = 0; j < jobs.get(i).size(); j++) {
                    if (jobs.get(i + 1).get(j) > jobs.get(i).get(j)) {
                        int tmp = jobs.get(i).get(j);
                        jobs.get(i).remove(tmp);
                        jobs.get(i).add(jobs.get(i + 1).get(j));
                        jobs.get(i + 1).remove(jobs.get(i + 1).get(j));
                        jobs.get(i + 1).add(tmp);
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

    public Queue<Integer> getList() {
        return list;
    }

    public void setList(Queue<Integer> list) {
        this.list = list;
    }

    public HashMap<Integer, List<Integer>> getJobs() {
        return jobs;
    }

    public void setJobs(HashMap<Integer, List<Integer>> jobs) {
        this.jobs = jobs;
    }
}
