import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Grade Calculator ===");
        int subjectsCount = readPositiveInt(scanner, "Enter the number of subjects: ");

        double totalMarks = 0.0;
        for (int i = 1; i <= subjectsCount; i++) {
            double mark = readMark(scanner, "Enter marks for subject " + i + " (0-100): ");
            totalMarks += mark;
        }

        double averagePercentage = totalMarks / subjectsCount;
        String grade = calculateGrade(averagePercentage);

        System.out.println();
        System.out.println("=== Results ===");
        System.out.printf("Total Marks: %.2f / %.2f%n", totalMarks, subjectsCount * 100.0);
        System.out.printf("Average Percentage: %.2f%%%n", averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Invalid input. Please enter a positive whole number.");
        }
    }

    private static double readMark(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value >= 0.0 && value <= 100.0) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Invalid input. Please enter a number between 0 and 100.");
        }
    }

    private static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90.0) return "A";
        if (averagePercentage >= 80.0) return "B";
        if (averagePercentage >= 70.0) return "C";
        if (averagePercentage >= 60.0) return "D";
        return "F";
    }
}
