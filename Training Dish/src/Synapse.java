
public abstract class Synapse {
	private double weight;
	private boolean activated;
	
	public Synapse() {
		this.weight = -10 + (20 * Math.random());
		this.activated = false;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public boolean getIsActived() {
		return this.activated;
	}
	
	public void setIsActivated(boolean state) {
		this.activated = state;
	}
	
	public double fireSynapse() {
		return 0;
	}
}
