package cisco.java.challenge;

import static cisco.java.challenge.GNodeUtils.paths;

public class App {

    public static void main(String[] args) {
        //without cyclic references
        GNode A = new GNodeImpl("A",
                new GNodeImpl("B", new GNodeImpl("E"), new GNodeImpl("F")),
                new GNodeImpl("C", new GNodeImpl("G"), new GNodeImpl("H"), new GNodeImpl("I")),
                new GNodeImpl("D"));
        paths(A).forEach(System.out::println);

        //with cyclic references
        GNode A1 = new GNodeImpl("A1",
                new GNodeImpl("B", new GNodeImpl("E"), new GNodeImpl("F", new GNodeImpl("A1"))),
                new GNodeImpl("C", new GNodeImpl("G"), new GNodeImpl("H"), new GNodeImpl("I")),
                new GNodeImpl("D"),
                new GNodeImpl("A1"));
        paths(A1).forEach(System.out::println);
    }
}
