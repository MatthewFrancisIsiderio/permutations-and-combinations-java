package main;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
public class Main {
	
	static Scanner input = new Scanner(System.in);
	static Queue<String> queue = new LinkedList<>();

	public static void main(String[] args) {
		int choice;
		
		do {
			System.out.println("\n===== PERMUTATION AND COMBINATION =====");
			System.out.println("[1] Add Elements to Queue");
			System.out.println("[2] Display Queue");
			System.out.println("[3] Generate Permutations");
			System.out.println("[4] Generate Combinations");
			System.out.println("[5] Exit");
			System.out.print("Enter choice: ");
			
			choice = input.nextInt();
			input.nextLine();
			
			switch(choice) {
				case 1:
					addElements();
					break;
				case 2:
					displayQueue();
					break;
				case 3:
					generatePermutations();
					break;
				case 4:
					generateCombinations();
					break;
				case 5:
					System.out.println("Program terminated.");
					break;
				default:
					System.out.println("Invalid choice!");
			}
		} while (choice != 5);

	}
	
	static void addElements() {
		System.out.println("\n=== ADD ELEMENTS ===");
		
		for(int i = 1; i <= 5; i++) {
			System.out.print("Add the " + i + "st Element: ");
			String element = input.nextLine();
			
			if(!element.isEmpty()) {
				queue.add(element);
			}
		}
		
		System.out.println("\n=== ELEMENTS SUCCESSFULLY ADDED ===");
	}
	
	static void displayQueue() {
		if (!queueCheck()) {
			System.out.println("\n=== QUEUE ELEMENTS ===");
			System.out.println(queue);
			
			System.out.println("\n=== DEQUEUING TWO ELEMENTS ===");
			
			for(int i = 1; i <= 2; i++) {
				if(!queue.isEmpty()) {
					System.out.println("Removed: " + queue.poll());
				} else {
					System.out.println("Queue is empty. Cannot dequeue.");
					break;
				}
			}
			
			System.out.println("\n=== QUEUE AFTER DEQUEUE ===");
			System.out.println(queue);
		}
	}
	
	static void generatePermutations() {
		if(queueCheck()) {
			return;
		}
		String[] permutationArray = queue.toArray(new String[0]);
		System.out.print("Enter number of elements to arrange: ");
		int num = input.nextInt();
		input.nextLine();
		
		if(numCheck(num, permutationArray)) {
			return;
		}
		
		System.out.println("\n=== PERMUTATIONS ===");
		
		int[] count = {0};
		permute(permutationArray, new String[num], new boolean[permutationArray.length], 0, num, count);
		
		System.out.println("\nTotal Permutations: " + count[0]);
	}
	
	static void permute(String[] arr, String[] current, boolean[] used, int depth, int num, int[] count) {
		if(depth == num) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < num; i++) {
				sb.append(current[i]);
			}
			System.out.println(sb.toString());
			count[0]++;
			return;
		}
		
		for(int i = 0; i < arr.length; i++) {
			if(!used[i]) {
				used[i] = true;
				current[depth] = arr[i];
				permute(arr, current, used, depth + 1, num, count);
				used[i] = false;
			}
		}
	}
	
	static void generateCombinations() {
		if(queueCheck()) {
			return;
		}
		
		String[] combinationArray = queue.toArray(new String[0]);
		
		System.out.print("Enter number of elements to select: ");
		int num = input.nextInt();
		input.nextLine();
		
		if(numCheck(num, combinationArray)) {
			return;
		}
		
		System.out.println("\n=== COMBINATIONS ===");
		
		int[] count = {0};
		combine(combinationArray, new String[num], 0, 0, num, count);
		
		System.out.println("\nTotal Combinations: " + count[0]);
	}
	
	static void combine(String[] arr, String[] current, int start, int depth, int num, int[] count) {
		if(depth == num) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < num; i++) {
				sb.append(current[i]);
			}
			System.out.println(sb.toString());
			count[0]++;
			return;
		}
		
		for(int i = start; i < arr.length; i++) {
			current[depth] = arr[i];
			combine(arr, current, i + 1, depth + 1, num, count);
		}
	}
	
	static boolean queueCheck() {
		if(queue.isEmpty()) {
			System.out.println("\n=== NO ELEMENTS IN QUEUE ===");
			return true;
		} else {
			return false;
		}
	}
	
	static boolean numCheck(int num, String[] array) {
		if(num > array.length) {
			System.out.println("Number is larger than the queue. Invalid input");
			return true;
		} else {
			return false;
		}
	}
	
}