
public class OutputPerceptron extends Perceptron {
	private double outputValue;
	
	public OutputPerceptron(Brain brain) {
		super(brain, Config.BRAIN_HIDDEN_LAYERS, Config.BRAIN_HIDDEN_LAYER_PERCEPTRONS);
		
		this.outputValue = 0;
	}
	
	public double processPerceptron() {
		double total = 0;
		
		System.out.println("------ OUTPUT PERCEPTRON ------");
		for (Synapse in : this.getIncoming()) {
			double inValue = in.fireSynapse();
			System.out.println("+ " + inValue);
			total += inValue;
		}
		System.out.println("-------------------------------");
		System.out.println("TOTAL: " + total);
		System.out.println("-------------------------------\n");
		
		this.outputValue = total;
		return total;
	}
	
	public double getOutputValue() {
		return this.outputValue;
	}
}
