
public abstract class Synapse {
	private double weight;
	
	public Synapse() {
		this.weight = Math.random();
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
}
