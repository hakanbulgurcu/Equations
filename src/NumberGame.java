import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NumberGame {
    private final List<String> OPERATORS = Arrays.asList ("?", "+", "-", "/", "X");
    private final List<Integer> NUMBERS = Arrays.asList (3, 8, 9, 17, 25, 80);
    private final double GOAL = 230;
    private final double EPSILON = 0.0001;
    private static int cnt = 0;
    private boolean found = false;
    private List<Integer> list = new ArrayList<> ();

    public static void main(String[] args) {
        new NumberGame ();
    }

    public NumberGame() {
        findNumbers();
    }

    public void findNumbers() {
        iterateEquation (NUMBERS, GOAL, "");
    }

    public void iterateEquation(List<Integer> list, double goal, String EQUATION) {
        if(!found) {
            if(Math.abs (goal) < EPSILON) {
                found = true;
                System.out.println ("\n" + cnt + "-Found " + EQUATION);
            } else if (list.size () == 1) {
                if (Math.abs(list.get (0) - goal) < EPSILON) {
                    found = true;
                    System.out.println ("\n" + cnt + "-Found " + EQUATION + "->" + goal);
                } else {
//                    System.out.print ("\nNot Found " + EQUATION);
                }
            } else {
                OPERATORS.forEach (e -> {
                    if(!found) {
                        String EQ = EQUATION;
                        ++cnt;
                        if(e != "?") {
                            EQ += "->" + goal + e + list.get (list.size () - 1);
                        }
                        iterateEquation (list.subList (0, list.size () - 1), calculateGoal (goal, list.get (list.size () - 1), e), EQ);
                    }
                });
            }
        }
    }

    public double calculateGoal(double goal, int number, String operator) {
//        System.out.print (goal + operator + number);
        switch (operator) {
            case "+" : return goal + number;
            case "-" : return goal - number;
            case "X" : return goal*number;
            case "/" : return goal/number;
            case "?" : return goal;
            default : return 0;
        }
    }
}

