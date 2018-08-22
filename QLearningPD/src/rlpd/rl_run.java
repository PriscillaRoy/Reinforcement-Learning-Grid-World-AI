package rlpd;

import java.util.Scanner;

import javax.swing.JFrame;

public class rl_run extends JFrame {

	public rl_run() {

	}

	public static void main(String[] args) {

		while (true) {
			System.out.println("Choose the experiment to run : \n 1. PRandom-3000, PGreedy-3000,Q-Learning"
					+ "\n 2.PRandom - 200, PExploit - 5800, Q-Learning"
					+ "\n 2.PRandom - 200, PExploit - 5800, SARSA Q-Learning");
			Scanner in = new Scanner(System.in);
			int expnum = in.nextInt();
			// System.out.println("Start Time ::");
			// System.out.println(System.currentTimeMillis());
			switch (expnum) {
			case 1:
				Experiment1 exp = new Experiment1();
				exp.initialiseValues();
				exp.beginExp();
				break;
			case 2:
				Experiment2 exp2 = new Experiment2();
				exp2.initialiseValues();
				exp2.beginExp();
				break;
			case 3:
				Experiment3 exp3 = new Experiment3();
				exp3.initialiseValues();
				exp3.beginExp();
				break;
			default:
				System.out.println("Please select valid num");
			}
		}
		/*
		
		*/
		/*
		*/
		// exp.printTable();
		// System.out.println("End Time ::");
		// System.out.println(System.currentTimeMillis());

	}

}
