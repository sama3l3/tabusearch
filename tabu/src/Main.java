public class Main {
    public static void main(String[] args) {
        Read r= new Read();
        r.read("prova.txt");
        TabuSearch t= new TabuSearch(r.getJobs());
        t.localopt();
    }
}
