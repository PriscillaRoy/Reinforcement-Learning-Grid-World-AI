package rlpd;

import java.util.Arrays;

/* ________________
 * |Operators	 |
 * |-------------|
 * |Action	Code |
 * |-------------|
 * |North -> 0	 |
 * |South -> 1	 |
 * |East  -> 2	 |
 * |West  -> 3	 |
 * |PickUp-> 4	 |
 * |Drop  -> 5	 |
 * ---------------
 */
public class QTable {

	int i; // row value
	int j; // column value
	int x; // Arm value
	int action_code; // Operator/Action code
	String actions; // Actions
	float qValue; // QValue

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString() int state; int North; int South; int
	 * East; int West; int Pick-Up; int Drop-Off;
	 * 
	 */

	@Override
	public String toString() {
		return this.getI() + "   " + this.getJ() + "   " + this.getX() + "   " + this.getAction_code() + "   "
				+ this.getActions() + "   " + this.getqValue();
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getAction_code() {
		return action_code;
	}

	public void setAction_code(int action_code) {
		this.action_code = action_code;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public float getqValue() {
		return qValue;
	}

	public void setqValue(float qValue) {
		this.qValue = qValue;
	}

	/*
	 * public void add(QTable element,int length) { QTable[] newData = new
	 * QTable[length+1]; for(int i =0; i < length; i++) { newData[i] =
	 * this[i].i; newData[list] = element; }
	 * 
	 * for(int i = index; i < list.length; i++) { newData[i+1] = list[i]; } }
	 */
	/*
	 * private static final int DEFAULT_CAPACITY = 10; private static final
	 * Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {}; public QTable[]
	 * elementData = Arrays.copyOf(elementData, size, Object[].class); public
	 * boolean add(QTable e) { ensureCapacityInternal(size + 1); // Increments
	 * modCount!! elementData[size++] = e; return true; } private void
	 * ensureCapacityInternal(int minCapacity) { if (elementData ==
	 * DEFAULTCAPACITY_EMPTY_ELEMENTDATA) { minCapacity =
	 * Math.max(DEFAULT_CAPACITY, minCapacity); }
	 * ensureExplicitCapacity(minCapacity); }
	 */

	public QTable() {
		// TODO Auto-generated constructor stub
	}

}
