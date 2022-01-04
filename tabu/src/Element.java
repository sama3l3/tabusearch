public class Element{
    int val;
    int prevval;

    public Element(int val, int prevval) {
        this.val = val;
        this.prevval = prevval;
    }

    @Override
    public String toString() {
        return "Element{" +
                "val=" + val +
                ", prevval=" + prevval +
                '}';
    }
}
