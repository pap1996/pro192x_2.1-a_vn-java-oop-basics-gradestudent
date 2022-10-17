import java.util.ArrayList;
import java.util.Scanner;

public class gradeStudent {

    // Fields
    private static ArrayList<Integer> weightArr = new ArrayList<>();
    private static ArrayList<Integer> totalScoreArr = new ArrayList<>();
    private static ArrayList<Double> weightScoreArr = new ArrayList<>();

    // Main
    public static void main (String args[]) {
        begin();
        midTerm();
        finalTerm();
        homework();
        report();
    }

    // Methods

    /* checkInput() - check if the input is between specified min and max
    */
    private static int checkInput( int min, int max) {
        Scanner input = new Scanner(System.in);
        int weight = input.nextInt();
        while (weight < min || weight > max ) {
            System.out.print("Invalid input! Please enter valid weight: ");
            weight = input.nextInt();
        }
        return weight;
    }

    /* Core methods include
        begin() - dislay the welcome
        midTerm() - get and calculate MidTerm grade
        finalTerm() - get and calculate FinalTerm grade
        homework() - get and calculate Homework grade
        report() - summarize GPA and comment on the total grade
     */
    private static void begin() {
        System.out.println("This program reads exam/homework scores and reports your overall course grade.");
        System.out.println();
    }


    private static void midTerm() {
        System.out.println("Midterm:");

        System.out.print("Weight (0-100)? ");
        weightArr.add(checkInput(0,100));

        System.out.print("Score earned? ");
        int midTermScore = checkInput(0,100);

        System.out.print("Were scores shifted (1=yes, 2=no)? ");
        int midTermShift = checkInput(1,2);
        if (midTermShift ==1) {
            System.out.print("Shift amount? ");
            midTermScore = Math.min(midTermScore + checkInput(0,100),100);
        }
        // In kết quả
        System.out.println("Total points = " + midTermScore + " / 100");
        totalScoreArr.add(midTermScore);
        System.out.println("Weighted score = " + Math.round((midTermScore*1.0/100)*weightArr.get(0) * 10.0)/10.0 + " / " + weightArr.get(0));
        weightScoreArr.add(Math.round((midTermScore*1.0/100)*weightArr.get(0) * 10.0)/10.0);

        System.out.println();
    }

    private static void finalTerm() {
        System.out.println("Final:");

        System.out.print("Weight (0-100)? ");
        weightArr.add(checkInput(0,100 - weightArr.get(0)));

        System.out.print("Score earned? ");
        int finalTermScore = checkInput(0,100);

        System.out.print("Were scores shifted (1=yes, 2=no)? ");
        int finalTermShift = checkInput(1,2);
        if (finalTermShift ==1) {
            System.out.print("Shift amount? ");
            finalTermScore = Math.min(finalTermScore + checkInput(0,100),100);
        }
        // In kết quả
        System.out.println("Total points = " + finalTermScore + " / 100");
        totalScoreArr.add(finalTermScore);
        System.out.println("Weighted score = " + Math.round((finalTermScore*1.0/100)*weightArr.get(1) * 10.0)/10.0 + " / "+weightArr.get(1));
        weightScoreArr.add(Math.round((finalTermScore*1.0/100)*weightArr.get(1) * 10.0)/10.0);

        System.out.println();
    }

    private static void homework() {
        System.out.println("Homework:");

        System.out.print("Weight (0-100)? ");
        weightArr.add(checkInput(100 - weightArr.get(0) - weightArr.get(1),100 - weightArr.get(0) - weightArr.get(1)));

        System.out.print("Number of assigments? ");
        int numAssigment = checkInput(0,100);
        int count = 1;
        int totalPoint = 0;
        int totalMaxPoint = 0;
        Scanner input = new Scanner(System.in);
        while (count <= numAssigment) {
            System.out.print("Assignment " + count + " score and max? ");
            totalPoint += input.nextInt();
            totalMaxPoint += input.nextInt();
            count ++;
        }

        System.out.print("How many sections did you attend? ");
        int sectionPoint = Math.min(checkInput(0,100)*5,30);

        System.out.println("Section points = " + sectionPoint + " / 30");
        System.out.println("Total points = " + (totalPoint + sectionPoint) + " / " + (totalMaxPoint + 30));
        System.out.println("Weighted score = " + Math.round(((totalPoint + sectionPoint)*weightArr.get(2)*1.0/(totalMaxPoint + 30))*10.0)/10.0 + " / " + weightArr.get(2));
        weightScoreArr.add(Math.round((((totalPoint + sectionPoint)*weightArr.get(2)*1.0/(totalMaxPoint + 30)))*10.0)/10.0);
        System.out.println();
    }

    private static void report() {
        double overallPercent = Math.round(weightScoreArr.stream().mapToDouble(a -> a).sum() *10.0)/10.0;
        double grade = 0.0;
        String message = "";
        System.out.println("Overall percentage = " + overallPercent);
        if (overallPercent >= 85) {
            grade = 3.0;
            message = "Good job!";
        } else if (overallPercent >= 75) {
            grade = 2.0;
            message = "Moderate, need more effort";
        } else if (overallPercent >= 60) {
            grade = 0.7;
            message = "Please more concentrate!";
        } else {
            grade = 0.0;
            message = "You need take this course again!";
        }
        System.out.println("Your grade will be at least: " + grade);
        System.out.println(message);
    }
}
