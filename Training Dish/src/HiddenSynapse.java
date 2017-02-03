
public class HiddenSynapse extends Synapse{
	private Perceptron in, out;
	
	public HiddenSynapse(Perceptron in, Perceptron out) {
		super();
		
		this.in = in;
		this.out = out;
	}
}
