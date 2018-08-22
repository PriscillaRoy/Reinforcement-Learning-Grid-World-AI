package rlpd;

import java.util.ArrayList;
import java.util.Arrays;

public class Experiment3 {
	int state[][] = new int[5][5];
	public static int DROP[] = new int[2];
	public static int PICK[] = new int[4];
	// int[] ARM = new int[1];
	int ARM;
	int utility[][] = new int[5][5];
	public static ArrayList<QTable> values = new ArrayList<QTable>();
	public static double bankBalance = 0.0;

	public void initialiseValues() {

		// Arrays.fill(utility, 0);
		Arrays.fill(DROP, 0);
		Arrays.fill(PICK, 4);
		ARM = 0;

	}

	public void beginExp() {
		int i = 0, j = 4;
		int x = 0;
		int k;
		int currState[] = new int[3];
		int nextState[] = new int[3];
		float maxQValue, nextQValue = 0.0f;
		Policies3 policies = new Policies3();
		int curr_operator = 0, next_operator = 0;

		for (k = 0; k < 200; k++) {
			curr_operator = policies.PRandom(policies.aplop(i, j, x), i, j, x);
			nextState = policies.findNextState(i, j, x, curr_operator);
			currState[0] = i;
			currState[1] = j;
			currState[2] = x;
			float currQValue = policies.findQFromTable(currState, curr_operator);
			// maxQValue =
			// policies.findmaxQValue(nextState,policies.aplop(nextState[0],
			// nextState[1], nextState[1]));
			next_operator = policies.PRandom(policies.aplop(nextState[0], nextState[1], nextState[2]), nextState[0],
					nextState[1], nextState[2]);
			nextQValue = policies.findQFromTable(nextState, next_operator);
			float updatedQValue = currQValue
					+ (float) 0.3 * (policies.findReward(curr_operator) + (float) 0.5 * nextQValue - currQValue);
			bankBalance += policies.findReward(curr_operator);
			i = nextState[0];
			j = nextState[1];
			x = nextState[2];
			policies.updateQActions();
			policies.setQTable(currState, curr_operator, updatedQValue);
			if (Experiment1.DROP[0] == 8 && Experiment1.DROP[1] == 8) {
				i = 0;
				j = 4;
				x = 0;
				Arrays.fill(DROP, 0);
				Arrays.fill(PICK, 4);
			}
		}

		for (k = 0; k < 3000; k++) {
			curr_operator = policies.PExploit(policies.aplop(i, j, x), i, j, x);
			nextState = policies.findNextState(i, j, x, curr_operator);
			currState[0] = i;
			currState[1] = j;
			currState[2] = x;
			float currQValue = policies.findQFromTable(currState, curr_operator);
			// maxQValue =
			// policies.findmaxQValue(nextState,policies.aplop(nextState[0],
			// nextState[1], nextState[1]));
			next_operator = policies.PRandom(policies.aplop(nextState[0], nextState[1], nextState[2]), nextState[0],
					nextState[1], nextState[2]);
			nextQValue = policies.findQFromTable(nextState, next_operator);
			float updatedQValue = currQValue
					+ (float) 0.3 * (policies.findReward(curr_operator) + (float) 0.5 * nextQValue - currQValue);
			bankBalance += policies.findReward(curr_operator);
			i = nextState[0];
			j = nextState[1];
			x = nextState[2];
			policies.updateQActions();
			policies.setQTable(currState, curr_operator, updatedQValue);
			if (Experiment1.DROP[0] == 8 && Experiment1.DROP[1] == 8) {
				i = 0;
				j = 4;
				x = 0;
				Arrays.fill(DROP, 0);
				Arrays.fill(PICK, 4);

			}
		}

		
		printTable();
		System.out.println("Bank Balance >>" + bankBalance);
		reset();

	}

	public void printTable() {
		// System.out.println("Inside Print Table >> size >> " + values.size());
		for (int i = 0; i < values.size(); i++) {
			System.out.println(values.get(i));
		}

	}
	public void reset(){
		bankBalance =0;
		values.clear();
		Arrays.fill(DROP, 0);
		Arrays.fill(PICK, 4);
		
	}

	public Experiment3() {
		// TODO Auto-generated constructor stub

	}

}
