import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Equations {
    private final String EQUAL_SIGN = "=";

    private final String EQUATION = "44/748X666=9/2X8";
    private String LEFT_EQUATION = "";
    private String RIGHT_EQUATION = "";
    private int EQUAL_INDEX = -1;
    private String LEFT_EQUATION_UPDATED = "";
    private String RIGHT_EQUATION_UPDATED = "";

    private final List<String> OPERATORS = Arrays.asList ("X", "/", "-", "+");

    public static void main(String[] args) {
        new Equations ();
    }

    public Equations() {
        findIndexes();
    }

    public void findIndexes() {
        EQUAL_INDEX = EQUATION.indexOf (EQUAL_SIGN);
        String [] equations = EQUATION.split (EQUAL_SIGN);
        LEFT_EQUATION = equations[0];
        RIGHT_EQUATION = equations[1];
        decideDeletedIndexes();
    }

    public void decideDeletedIndexes() {
        for(int first = 0 ; first < EQUATION.length () ; first++) {
            if(first != EQUAL_INDEX ) {
                for (int second = 0; second < EQUATION.length (); second++) {
                    if(second != EQUAL_INDEX && second > first) {
                        if(deleteIndexes (first, second)) {
                            return;
                        }
                    }
                }
            }
        }
    }

    public boolean deleteIndexes(int first, int second) {
        StringBuffer sbRight = new StringBuffer ();
        StringBuffer sbLeft = new StringBuffer ();
        LEFT_EQUATION_UPDATED = LEFT_EQUATION;
        RIGHT_EQUATION_UPDATED = RIGHT_EQUATION;

        if (first < EQUAL_INDEX && second < EQUAL_INDEX) {
            for(int i = 0; i < LEFT_EQUATION.length (); i++) {
                if(i != first && i != second ) {
                    sbLeft.append (LEFT_EQUATION.charAt (i));
                }
            }
            LEFT_EQUATION_UPDATED = sbLeft.toString ();
        } else if (first > EQUAL_INDEX && second > EQUAL_INDEX) {
            for(int i = EQUAL_INDEX + 1; i < EQUATION.length (); i++) {
                if(i != first && i != second ) {
                    sbRight.append (RIGHT_EQUATION.charAt (i - EQUAL_INDEX - 1));
                }
            }
            RIGHT_EQUATION_UPDATED = sbRight.toString ();
        }  else if (first < EQUAL_INDEX && second > EQUAL_INDEX) {
            for(int i = 0; i < LEFT_EQUATION.length (); i++) {
                if(i != first ) {
                    sbLeft.append (LEFT_EQUATION.charAt (i));
                }
            }
            LEFT_EQUATION_UPDATED = sbLeft.toString ();

            for(int i = EQUAL_INDEX + 1; i < EQUATION.length (); i++) {
                if(i != second ) {
                    sbRight.append (RIGHT_EQUATION.charAt (i - EQUAL_INDEX - 1));
                }
            }
            RIGHT_EQUATION_UPDATED = sbRight.toString ();
        }
        System.out.println ("Updated : " + LEFT_EQUATION_UPDATED + " = " + RIGHT_EQUATION_UPDATED);
        int leftValue = calculateEquation (LEFT_EQUATION_UPDATED),
                rightValue = calculateEquation (RIGHT_EQUATION_UPDATED);
        System.out.println (">> Results : " + leftValue + " >=< " + rightValue);

        if( leftValue == rightValue) {
            System.out.println (">>> Values : " + LEFT_EQUATION_UPDATED + " = " + leftValue + " <=> " + RIGHT_EQUATION_UPDATED + " = " + rightValue);
            return true;
        }

        return false;
    }

    public int calculateEquation(String equation) {
        int result = 0;
        String [] variablesStr = equation.split ("[X/+-]+");
        List<String> operands = new ArrayList<> ();
        List<Integer> variables = new ArrayList<> ();

        int index = 0;
        for(String s: variablesStr) {
            index += s.length ();
            if(index < equation.length ()) {
                operands.add (equation.substring (index, index + 1));
            }

            if(s.isEmpty ())
                return Integer.MIN_VALUE;

            variables.add (Integer.parseInt (s));
            index++;
        }

        //Rearrange Lists
        if((operands.contains ("+") || operands.contains ("-")) && (operands.contains ("X") || operands.contains ("/"))) {

        }

        //Calculate Result
        Double sum = Double.valueOf (variables.get (0));
        for(int i = 0 ; i < operands.size (); i++) {
            switch(operands.get (i)) {
                case "X": sum *= variables.get (i +1); break;
                case "/": sum /= variables.get (i +1); break;
                case "+": sum += variables.get (i +1); break;
                case "-": sum -= variables.get (i +1); break;
            }
        }

        return sum.intValue ();
    }

}
