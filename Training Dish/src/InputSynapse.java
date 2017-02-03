
public class InputSynapse extends Synapse{
	private Perceptron out;
	private VisionLine in;
	
	public InputSynapse(VisionLine in, Perceptron out) {
		super();
		
		this.in = in;
		this.out = out;
	}
}
