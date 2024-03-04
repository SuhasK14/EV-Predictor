import java.util.Scanner;

public class ExpectedValue
{
    public static void main(String[] args)
    {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for the number of props
        System.out.print("Enter the number of props: ");
        int n = scanner.nextInt();

        // Gambling hotline
        if (n >= 100)
        {
            System.out.println("1-800-GAMBLING");
            scanner.close();
            return;
        }

        // Prompt the user for the multiplier of 0 props missing (all hitting)
        System.out.print("Enter the multiplier for 0 props missing: ");
        double m0 = scanner.nextDouble();

        // Prompt the user for the multiplier of 1 missing prop
        System.out.print("Enter the multiplier for 1 prop missing: ");
        double m1 = scanner.nextDouble();

        double m2 = 0; // Initialize m2 to 0
        // If the number of props is 5 or greater, prompt the user for m2
        if (n >= 5)
        {
            System.out.print("Enter the multiplier for 2 props missing: ");
            m2 = scanner.nextDouble();
        }

        // Prompt the user for the percentage that a prop hits
        System.out.print("Enter the percentage that a prop hits: ");
        double pHit = scanner.nextDouble();

        // Calculate the expected value
        double expectedValue = calculateExpectedValue(n, m0, m1, m2, pHit);

        // Print the result using printf to truncate argument expectedValue to 4 decimal places
        System.out.printf("Expected Value: %.8f", expectedValue);

        // Close the scanner
        scanner.close();
    }

    // Method to calculate the expected value
    public static double calculateExpectedValue(int n, double m0, double m1, double m2, double pHit)
    {
        // Calculate the probability of all props hitting
        double p0 = binomialCoefficient(n, n) * Math.pow(pHit, n) * Math.pow(1 - pHit, 0);
        //System.out.println(p0);

        // Calculate the probability of exactly one prop missing
        double p1 = binomialCoefficient(n, n - 1) * Math.pow(pHit, n - 1) * Math.pow(1 - pHit, 1);
        //System.out.println(p1);

        // Calculate the probability of exactly two props missing
        double p2 = binomialCoefficient(n, n - 2) * Math.pow(pHit, n - 2) * Math.pow(1 - pHit, 2);
        //System.out.println(p2);

        // Calculate the expected value by multiplying the multipliers with the corresponding probabilities
        return m0 * p0 + m1 * p1 + m2 * p2;
    }

    // Method to calculate binomial coefficient (n choose k)
    public static double binomialCoefficient(int n, int k)
    {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    // Method to calculate factorial
    public static double factorial(int n)
    {
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}