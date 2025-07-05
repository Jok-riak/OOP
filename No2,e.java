import java.util.Scanner;

public class UgandaCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book description:");
        String description = scanner.nextLine().toLowerCase();

        String target = "uganda";
        int count = 0;
        int index = description.indexOf(target);

        while (index != -1) {
            count++;
            index = description.indexOf(target, index + target.length());
        }

        System.out.println("The word 'Uganda' appears " + count + " times.");
    }
}