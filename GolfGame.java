
/* Author: Zubair Ab Aziz
 * Assignment: Project 2
 * Lab Section: MW, 1230-1345
 * TA Name: Sofia
 * Collaboration: I did not collaborate with anyone on this assignment
 */

import java.util.Scanner;
import java.util.Random;

public class GolfGame {

	static Scanner scan = new Scanner(System.in);

	// arrays for tee number, distance, par, club number, mean, sd of every hole
	static int[] teeg = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };
	static int[] distg = new int[] { 0, 530, 305, 331, 201, 500, 226, 409, 410, 229, 433, 363, 174, 545, 419, 512, 410,
			320, 170 };
	static int[] parg = new int[] { 0, 5, 4, 4, 3, 5, 3, 4, 4, 3, 4, 4, 3, 5, 4, 5, 4, 4, 3 };
	static int[] teea = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };
	static int[] dista = new int[] { 0, 376, 453, 397, 480, 568, 412, 371, 175, 352, 386, 174, 348, 465, 618, 455, 423,
			495, 357 };
	static int[] para = new int[] { 0, 4, 4, 4, 4, 5, 4, 4, 3, 4, 4, 3, 4, 4, 5, 4, 4, 4, 4 };
	static int[] mean = new int[] { 0, 50, 110, 125, 135, 145, 155, 170, 180, 215, 230 };
	static int[] sd = new int[] { 0, 10, 10, 15, 15, 15, 15, 17, 20, 20, 30 };
	static int[] power = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	static int[] puttmean = new int[] { 0, 1, 2, 4, 8, 12, 16, 20, 25, 30, 40 };
	static int[] puttsd = new int[] { 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5 };
	static int[] puttpow = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	// Variables
	static int stroke = 0; // number of strokes
	static int score = 0; // score in round
	static int totalScore = 0; // total score in all holes

	public static void Genesee(int x) { // Genesee Valley
		System.out.println("\nYou are at tee number " + teeg[x] + ".\nThe distance is " + distg[x]
				+ " yards, and the par is " + parg[x] + ".");
	}

	public static void Andrews(int x) { // Genesee Valley
		System.out.println("You are at tee number " + teea[x] + ".\nThe distance is " + dista[x]
				+ " yards, and the par is " + para[x] + ".");
	}

	public static int getClub() { // getter for club number
		System.out.println("Select club number [1-10] (10 being the srongest)");
		int club = scan.nextInt();
		return club;
	}

	public static int getPow() { // getter for power
		System.out.println("Select Power [1-10] (10 being the strongest)");
		int p = scan.nextInt();
		return p;
	}

	public static int puttPow() { // getter for power
		System.out.println("Select Putt Power [1-10]");
		int putt = scan.nextInt();
		return putt;
	}

	// method to return positive values from the Gaussian distribution:
	public static int nextDistance() {

		int club = getClub();
		int pow = getPow();
		int[] mean = { 0, 50, 110, 125, 135, 145, 155, 170, 180, 215, 230 };
		int[] sd = { 0, 10, 10, 15, 15, 15, 15, 17, 20, 20, 30 };
		int[] power = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		Random random = new Random();
		double mean_adj = (mean[club] * power[pow] / 10.0);
		double sd_adj = (sd[club] * power[pow] / 10.0);
		int distance;
		do {
			double val = (random.nextGaussian() * sd_adj) + mean_adj;
			distance = (int) val;
		} while (distance < 0);
		return distance;
	}

	// measures the distance hit after each stroke
	public static void DistHit() {
		System.out.println("The ball went " + nextDistance() + " yards.");
	}

	// measures remaining distance
	public static void RemDist(int x) {
		int Dist1 = distg[x] - nextDistance();
		System.out.println("You are " + Dist1 + " yards to the hole.");
	}

	// calculation for putting distance
	public static int puttDist() {
		int putt = puttPow();
		int[] puttmean = { 0, 1, 3, 5, 8, 12, 16, 20, 25, 30, 40 };
		int[] puttsd = { 0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5 };
		Random random = new Random();
		double mean_adj = (puttmean[putt] * putt / 10.0);
		double sd_adj = (puttsd[putt] * putt / 10.0);
		int distance2;
		do {
			double val = (random.nextGaussian() * sd_adj) + mean_adj;
			distance2 = (int) val;
		} while (distance2 < 0);
		return distance2;
	}

	public static void run() { // basically this whole class is the game

		// welcome statement for the player
		System.out.println(
				"Welcome to TTY Golf Simulator!\nPlease select a course:\n1. Genesee Valley Park North Course\n2. The Old Course at St. Andrews\nYour choice [1-2]");

		totalScore = 0; // sets total score to 0
		int y = scan.nextInt(); // Scanner for player choice (and generally
								// everything else)

		if (y == 1) { // Runs Genesee Valley Course
			System.out.println("You are playing at Genesee Valley Park North Course");
			for (int i = 1; i <= 18; i++) {
				Genesee(i);
				int v = distg[i];
				int par = parg[i];
				int feet = v * 3;
				int dist = 0;
				int s = 0;
				while (feet != 0) {
					s++;
					stroke = 0; // resets stroke after every round
					System.out.println("Shot number " + s);
					System.out.println("You are " + v + " yards to the hole.");
					dist = nextDistance();
					System.out.println("Your shot went " + dist + " yards.");
					v = Math.abs(v - dist);
					System.out.println("Remaining distance is " + v + " yards.");
					feet = v * 3;
					stroke++;
					if (feet <= 60 && feet > 0) { // loop to start putt
						int puttDist = 0;
						while (feet != 0) {
							s++;
							System.out.println("You are on the green!");
							System.out.println("Shot number " + s + ". \nYou are " + feet + " feet away.");
							puttDist = (Math.abs(puttDist()));
							feet = Math.abs(feet - puttDist);
							System.out.println("The ball went " + puttDist + " feet.");
							stroke++;
						}
						System.out.println("It went into the hole!");
					}
					score = s - par;
					System.out.println("Score for this round = " + score);
				}
				totalScore = totalScore + score;
				System.out.println("Your total score is currently " + totalScore);
			}
			System.out.println("\nYour total score is " + totalScore);

		} else if (y == 2) { // Runs St Andrews Course
			System.out.println("You are playing at The Old Course at St. Andrews.");
			for (int i = 1; i <= 18; i++) {
				Andrews(i);
				int v = dista[i];
				int par = para[i];
				int feet = v * 3;
				int dist = 0;
				int s = 0;
				while (feet != 0) {
					s++;
					stroke = 0; // resets stroke after every round
					System.out.println("Shot number " + s);
					System.out.println("You are " + v + " yards to the hole.");
					dist = nextDistance();
					System.out.println("Your shot went " + dist + " yards.");
					v = Math.abs(v - dist);
					System.out.println("Remaining distance is " + v + " yards.");
					feet = v * 3;
					stroke++;
					if (feet <= 60 && feet > 0) { // loop to start putt
						int puttDist = 0;
						while (feet != 0) {
							s++;
							System.out.println("You are on the green!");
							System.out.println("Shot number " + s + ". \nYou are " + feet + " feet away.");
							puttDist = (Math.abs(puttDist()));
							feet = Math.abs(feet - puttDist);
							System.out.println("The ball went " + puttDist + " feet.");
							stroke++;
						}
						System.out.println("It went into the hole!");
					}
					score = s - par;
					System.out.println("Score for this round = " + score);
				}
				totalScore = totalScore + score;
				System.out.println("Your total score is currently " + totalScore);
			}
			System.out.println("\nYour total score is " + totalScore);
		}
	}

	// main method to run the game
	public static void main(String[] args) {
		run();
		System.out.println("Game Over \n Thank You For Playing");
		// System.out.println("Would you like to play again?");
	}
}
