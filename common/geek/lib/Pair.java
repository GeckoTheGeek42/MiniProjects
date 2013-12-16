package geek.lib;

public class Pair <T1, T2> {
    public Pair (T1 o1, T2 o2) {
        this.one = o1;
        this.two = o2;
    }
    
    public T1 one;
    public T2 two;
    
    public T1 getOne () {
        return one;
    }
    public void setOne (T1 o1) {
        this.one = o1;
    }
    public T2 getTwo () {
        return two;
    }
    public void setTwo (T2 o2) {
        this.two = o2;
    }
    
    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder();
        builder.append("Pair [one=").append(one).append(", two=").append(two)
                .append("]");
        return builder.toString();
    }
}
