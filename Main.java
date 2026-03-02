import java.util.Scanner;

class Subject {
    int credits;
    double grade;

    Subject(double grade) {
        this.grade = grade;
    }

    double getGradePoints() {
        double point;
        if (grade >= 96) point = 4.0;
        else if (grade >= 92) point = 3.7;
        else if (grade >= 88) point = 3.4;
        else if (grade >= 84) point = 3.2;
        else if (grade >= 80) point = 3.0;
        else if (grade >= 76) point = 2.8;
        else if (grade >= 72) point = 2.6;
        else if (grade >= 68) point = 2.4;
        else if (grade >= 64) point = 2.2;
        else if (grade >= 60) point = 2.0;
        else if (grade >= 55) point = 1.5;
        else if (grade >= 50) point = 1.0;
        else point = 0.0;

        return point * credits;
    }
}

class TwoHourSubject extends Subject {
    TwoHourSubject(double grade) {
        super(grade);
        this.credits = 2;
    }
}

class ThreeHourSubject extends Subject {
    ThreeHourSubject(double grade) {
        super(grade);
        this.credits = 3;
    }
}

class Student {
    Subject[] subjects;
    double CurrentGpa;
    int CurrentHours;

    Student(Subject[] subjects, double currentGpa, int currentHours) {
        this.subjects = subjects;
        this.CurrentGpa = currentGpa;
        this.CurrentHours = currentHours;
    }
    double calculateGPA() {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Subject s : subjects) {
            totalPoints += s.getGradePoints();
            totalCredits += s.credits;
        }
        if (CurrentHours == 0)
            return totalPoints / totalCredits;
        else {
            return (totalPoints + (CurrentGpa * CurrentHours)) / (CurrentHours + totalCredits);
        }
    }
}

public class Main {
    static void main() {
        Scanner input = new Scanner(System.in);

        System.out.println("===== GPA Calculator (4.0 scale) =====");
        System.out.println("Enter your current GPA: ");
        double currentGpa = input.nextDouble();
        System.out.println("How many hours have you completed in college ? ");
        int currentHours = input.nextInt();


        int n;
        while (true) {
            System.out.print("Enter the number of subjects: ");
            if (input.hasNextInt()) {
                n = input.nextInt();
                if (n > 0) break;
                else System.out.println("Invalid input! Must be a positive number.");
            } else {
                System.out.println("Invalid input! Enter a valid integer.");
                input.next();
            }
        }

        Subject[] subjects = new Subject[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Subject " + (i + 1) + " ---");

            double grade;
            while (true) {
                System.out.print("Enter grade (0-100): ");
                if (input.hasNextDouble()) {
                    grade = input.nextDouble();
                    if (grade >= 0 && grade <= 100) break;
                    else System.out.println("Invalid grade! Enter a value between 0 and 100.");
                } else {
                    System.out.println("Invalid input! Enter a number.");
                    input.next();
                }
            }

            int hours;
            while (true) {
                System.out.print("Is this a 2-hour or 3-hour subject?: ");
                if (input.hasNextInt()) {
                    hours = input.nextInt();
                    if (hours == 2 || hours == 3) break;
                    else System.out.println("Invalid input! Enter 2 or 3 only.");
                } else {
                    System.out.println("Invalid input! Enter a number.");
                    input.next();
                }3.19
            }

            if (hours == 2)
                subjects[i] = new TwoHourSubject(grade);
            else
                subjects[i] = new ThreeHourSubject(grade);
        }

        Student s1 = new Student(subjects, currentGpa, currentHours);
        System.out.printf("\nStudent GPA: %.2f\n", s1.calculateGPA());
        input.close();
    }
}
