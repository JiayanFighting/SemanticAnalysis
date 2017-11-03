package Entity;

public class DFATable {
	private int state;
	private String[] input;
	private int nextState;
	private String type;
	private boolean isFinish;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getNextState() {
		return nextState;
	}
	public void setNextState(int nextState) {
		this.nextState = nextState;
	}
	public String[] getInput() {
		return input;
	}
	public void setInput(String[] input) {
		this.input = input;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isFinish() {
		return isFinish;
	}
	public void setFinish(boolean isFinish) {
		this.isFinish = isFinish;
	}
	

}
