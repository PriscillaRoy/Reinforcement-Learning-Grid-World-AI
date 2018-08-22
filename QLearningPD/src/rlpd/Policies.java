package rlpd;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.TreeMap;

/* Operators
 * North -> 0
 * South -> 1
 * East  -> 2
 * West  -> 3
 * PickUp-> 4
 * Drop  -> 5
 */
/* Pre-Defined States : Given in Problem Statement
 * PickUp : (0,0), (2,2), (3,0), (4,4)
 * Drop   : (4,0), (3,3)
 */
public class Policies {

	ArrayList<Float> qValuesList = new ArrayList<Float>();

	public Policies() {
		// TODO Auto-generated constructor stub
	}

	public int PRandom(int[] applicable_operators, int i, int j, int x) {
		for (int k = 0; k < applicable_operators.length; k++) {
			if (applicable_operators[k] == 4 && isPickUpPossible(i, j, x)) {
				updatePICKDROP(applicable_operators[k], i, j);
				return (applicable_operators[k]);
			} else if (applicable_operators[k] == 5 && isDropOffPossible(i, j, x)) {
				updatePICKDROP(applicable_operators[k], i, j);
				return (applicable_operators[k]);
			}
		}
		Random rand = new Random();
		while (Boolean.TRUE) {
			int value = rand.nextInt(applicable_operators.length);
			for (int k = 0; k < applicable_operators.length; k++) {
				if (applicable_operators[k] == value) {
					return (applicable_operators[k]);
				}
			}
		}
		return (-1);

	}

	public boolean isPickUpPossible(int i, int j, int x) {
		if (x == 0) {
			if (i == 0 && j == 0 && Experiment1.PICK[0] > 0) {
				return (true);
			}
			if (i == 2 && j == 2 && Experiment1.PICK[1] > 0) {
				return (true);
			}
			if (i == 3 && j == 0 && Experiment1.PICK[2] > 0) {
				return (true);
			}
			if (i == 4 && j == 4 && Experiment1.PICK[3] > 0) {
				return (true);
			}
		}
		return (false);

	}

	public boolean isDropOffPossible(int i, int j, int x) {
		if (x == 1) {
			if (i == 4 && j == 0 && Experiment1.DROP[0] < 7) {
				return (true);
			}
			if (i == 3 && j == 3 && Experiment1.DROP[1] < 7) {
				return (true);
			}

		}
		return (false);

	}

	public int PGreedy(int[] applicable_operators, int i, int j, int x) {
		for (int k = 0; k < applicable_operators.length; k++) {
			if (applicable_operators[k] == 4 && isPickUpPossible(i, j, x)) {
				updatePICKDROP(applicable_operators[k], i, j);
				return (applicable_operators[k]);
			} else if (applicable_operators[k] == 5 && isDropOffPossible(i, j, x)) {
				updatePICKDROP(applicable_operators[k], i, j);
				return (applicable_operators[k]);
			}
		}
		ArrayList<Float> qValuesList = new ArrayList<Float>();
		int[] temp = new int[3];
		temp[0] = i;
		temp[1] = j;
		temp[2] = x;
		for (int k = 0; k < applicable_operators.length; k++) {

			qValuesList.add(k, findQFromTable(temp, applicable_operators[k]));

		}
		float maxQValue = Collections.max(qValuesList);
		for (int k = 0; k < applicable_operators.length; k++) {
			if (maxQValue == qValuesList.get(k)) {
				return (applicable_operators[k]);
			}

		}

		return (-1);
	}

	public int PExploit(int[] applicable_operators, int i, int j, int x) {

		for (int k = 0; k < applicable_operators.length; k++) {
			if (applicable_operators[k] == 4 && isPickUpPossible(i, j, x)) {
				updatePICKDROP(applicable_operators[k], i, j);
				return (applicable_operators[k]);
			} else if (applicable_operators[k] == 5 && isDropOffPossible(i, j, x)) {
				updatePICKDROP(applicable_operators[k], i, j);
				return (applicable_operators[k]);
			}
		}
		ArrayList<Float> qValuesList = new ArrayList<Float>();
		for (int k = 0; k < applicable_operators.length; k++) {
			qValuesList.add(k, calculateQValue(i, j, x, applicable_operators[k]));

		}
		float maxQValue = Collections.max(qValuesList);
		int maxOperator = 0;
		int flag = 0;
		int otherOperators[] = new int[applicable_operators.length - 1];
		for (int k = 0, l = 0; k < applicable_operators.length; k++) {

			if (maxQValue == qValuesList.get(k) && flag == 0) {
				maxOperator = applicable_operators[k];
				flag = 1;
			} else {
				otherOperators[l] = applicable_operators[k];
				l++;
			}

		}

		/*
		 * ArrayList<Double> chance = new ArrayList<Double>(); chance.add(0.85);
		 * chance.add(0.15); // Build the tree map TreeMap<Double, Integer> map
		 * = new TreeMap<>(); double total = 0.0d; map.put(total += 0.15,
		 * maxOperator); for (int l = 0; i < otherOperators.length; l++) {
		 * 
		 * map.put(total += 0.15, otherOperators[l]); }
		 * 
		 * // The generator of random numbers Random generator = new Random();
		 * // Generate a random value between 0 and 1 double value =
		 * generator.nextDouble(); // Get the object that matches with the
		 * generated number int operator = map.ceilingEntry(value).getValue();
		 * 
		 */
		int operator = otherOperators[0];
		Random r = new Random();
		double createdRanNum = 0.0 + (1.0) * r.nextDouble();
		if (createdRanNum < 0.85) {
			operator = maxOperator;
			// System.out.println("Max Operator >>" + maxOperator + ">> Operator
			// >>" + operator);
		} else {
			// System.out.println("In else >> length is " +
			// otherOperators.length );
			int randNum = 0;
			if (otherOperators.length - 1 > 0)
				randNum = r.nextInt(otherOperators.length - 1);

			// System.out.println("After Random Number Gen >> " + randNum);
			// for(int otlen = 0; otlen < otherOperators.length ; otlen++)
			// System.out.println(otherOperators[otlen]);
			operator = otherOperators[randNum];
			// System.out.println("randNum >>" + randNum + ">> Operator >>" +
			// operator + ">> other operator >>" + otherOperators[randNum]);
		}
		return (operator);
	}

	public void updatePICKDROP(int operator, int i, int j) {
		if (operator == 4) {
			if (i == 0 && j == 0) {
				Experiment1.PICK[0] -= 1;
			}
			if (i == 2 && j == 2) {
				Experiment1.PICK[1] -= 1;
			}
			if (i == 3 && j == 0) {
				Experiment1.PICK[2] -= 1;
			}
			if (i == 4 && j == 4) {
				Experiment1.PICK[0] -= 1;
			}
		}
		if (operator == 5) {
			if (i == 4 && j == 0) {
				Experiment1.DROP[0] -= 1;
			}
			if (i == 3 && j == 3) {
				Experiment1.DROP[1] -= 1;
			}
		}
	}

	public float findmaxQValue(int[] state, int[] operators) {
		float maxQ = Float.NEGATIVE_INFINITY;
		for (int i = 0; i < operators.length; i++) {
			float qValue = findQFromTable(state, operators[i]);
			if (maxQ < qValue) {
				maxQ = qValue;
			}
		}

		return (maxQ);

	}

	public float findQFromTable(int[] state, int action) {
		float qValueNextState = 0.0f;
		int qupdate = 0;
		Iterator itr = Experiment1.values.iterator();
		for (int l = 0; l < Experiment1.values.size(); l++) {

			int i = 0, j = 0, x = 0, action_code = 0;
			float qValue = 0.0f;

			// traverse elements of ArrayList object
			if (itr.hasNext()) {
				QTable tempQTable = (QTable) itr.next();
				i = tempQTable.i;
				j = tempQTable.j;
				x = tempQTable.x;
				action_code = tempQTable.action_code;
				qValue = tempQTable.qValue;

			}
			if (state[0] == i && state[1] == j && state[2] == x && action == action_code) {
				qValueNextState = qValue;
				qupdate = 1;
				break;
			}
		}
		if (qupdate == 0) {
			qValueNextState = 0.0f;
			QTable temp = new QTable();
			temp.i = state[0];
			temp.j = state[1];
			temp.x = state[2];
			temp.action_code = action;
			temp.qValue = 0.0f;
			// values.add(temp, values.length);
			Experiment1.values.add(temp);
		}

		return (qValueNextState);
	}

	public void setQTable(int[] state, int action, float qValue) {

		int qupdate = 0;
		int i = 0, j = 0, x = 0, action_code = 0;
		ListIterator itr = Experiment1.values.listIterator();

		for (int l = 0; l < Experiment1.values.size(); l++) {

			QTable tempQTable = new QTable();
			// traverse elements of ArrayList object
			if (itr.hasNext()) {
				tempQTable = (QTable) itr.next();
				i = tempQTable.i;
				j = tempQTable.j;
				x = tempQTable.x;
				action_code = tempQTable.action_code;

			}
			if (state[0] == i && state[1] == j && state[2] == x && action == action_code) {
				tempQTable.qValue = qValue;
				itr.set(tempQTable);
				qupdate = 1;
				break;
			}
		}
		if (qupdate == 0) {
			QTable temp = new QTable();
			temp.i = state[0];
			temp.j = state[1];
			temp.x = state[2];
			temp.action_code = action;
			temp.qValue = qValue;
			// values.add(temp, values.length);
			Experiment1.values.add(temp);
		}

	}

	public void updateQActions() {
		int action_code;
		String action;
		for (int len = 0; len < Experiment1.values.size(); len++) {
			action_code = Experiment1.values.get(len).getAction_code();
			// System.out.println("Action : Code" + action_code);
			action = getAction(action_code);
			// System.out.println("Action " + action);
			Experiment1.values.get(len).setActions(action);
			// System.out.println("Action in QTable" +
			// Experiment1.values.get(len).getActions());

		}

	}

	public String getAction(int actionCode) {
		String temp = "";
		switch (actionCode) {
		case 0:
			temp = "North";
			break;
		case 1:
			temp = "South";
			break;
		case 2:
			temp = "East";
			break;
		case 3:
			temp = "West";
			break;
		case 4:
			temp = "Pick-Up";
			break;
		case 5:
			temp = "Drop-Off";
			break;
		}
		return (temp);
	}

	public float calculateQValue(int i, int j, int x, int op) {
		float QValue = 0.0f;
		int[] nextState = findNextState(i, j, x, op);
		int[] allActionsNextState = aplop(nextState[0], nextState[1], nextState[2]);
		float[] allActionsQValues = new float[allActionsNextState.length];
		for (int k = 0; k < allActionsNextState.length; k++) {
			allActionsQValues[k] = findQFromTable(nextState, allActionsNextState[k]);
		}
		float maxQValue = findMax(allActionsQValues);
		int[] currState = { i, j, x };
		float currQValue = findQFromTable(currState, op);
		// float updatedQValue = currQValue + (float)0.3*(findReward(op)+(float)
		// 0.3*maxQValue -currQValue );

		// return (updatedQValue);
		return (currQValue);
	}

	public float findReward(int action) {
		if (action == 4 || action == 5) {
			return (12.0f);
		} else {
			return (-1.0f);
		}
	}

	public void addIntoQTable(ArrayList<QTable> values) {

	}

	public float findMax(float[] allActionsQValues) {
		float max = allActionsQValues[0];
		for (int k = 1; k < allActionsQValues.length; k++) {
			if (allActionsQValues[k] > max) {
				max = allActionsQValues[k];
			}
		}
		return max;

	}

	public int[] findNextState(int i, int j, int x, int op) {
		int newState[] = new int[3];
		newState[0] = i;
		newState[1] = j;
		newState[2] = x;
		switch (op) {
		case 0:
			newState[0] = i - 1;
			break;
		case 1:
			newState[0] = i + 1;
			break;
		case 2:
			newState[1] = j + 1;
			break;
		case 3:
			newState[1] = j - 1;
			break;
		case 4:
			newState[2] = 1;
			break;
		case 5:
			newState[2] = 0;
			break;

		}

		return (newState);
	}

	public int[] aplop(int i, int j, int x) {
		// int[] applicable_operators = null;
		ArrayList<Integer> applicable_operators = new ArrayList();

		int k = 0;
		if (i < 1) {
			// For all row 0 states
			// Operator South can be applied
			applicable_operators.add(k, 1);
			k++;
		} else if (i > 3) {
			// For all row 4 states
			// Operator North can be applied
			applicable_operators.add(k, 0);
			k++;
		} else {
			applicable_operators.add(k, 0);
			k++;
			applicable_operators.add(k, 1);
			k++;

		}
		if (j < 1) {
			// Checking for first cell in middle rows
			// Operator East can be applied.
			applicable_operators.add(k, 2);
			k++;
		} else if (j > 3) {
			// Checking for last cell in middle rows
			// Operator West can be applied.
			applicable_operators.add(k, 3);
			k++;
		} else {
			// For the middle cells in middle rows
			// Operators East, West, North, South can be applied(North, South
			// already added)
			applicable_operators.add(k, 3);
			k++;
			applicable_operators.add(k, 2);
			k++;
		}
		// Checking for PickUp and Drop States
		if (x == 0) {
			// Arm doesn't have a block, PickUp possible
			if ((i == 0 && j == 0 && Experiment1.PICK[0] > 0) || (i == 2 && j == 2 && Experiment1.PICK[1] > 0)
					|| (i == 3 && j == 0 && Experiment1.PICK[2] > 0) || (i == 4 && j == 4 && Experiment1.PICK[3] > 0)) {
				applicable_operators.add(k, 4);
				k++;
			}
		} else if (x == 1) {
			if ((i == 4 && j == 0 && Experiment1.DROP[0] < 8) || (i == 3 && j == 3 && Experiment1.DROP[1] < 8)) {
				applicable_operators.add(k, 5);
				k++;
			}

		}
		int[] operators = new int[applicable_operators.size()];
		for (int size = 0; size < applicable_operators.size(); size++)
			operators[size] = applicable_operators.get(size);
		return (operators);

	}

}
