
public class HiddenSynapse extends Synapse{
	private Perceptron in, out;
	
	public HiddenSynapse(Perceptron in, Perceptron out) {
		super();
		
		this.in = in;
		this.out = out;
	}
	
	@Override
	public double fireSynapse() {
		return this.in.processPerceptron() * this.getWeight();
	}
}
