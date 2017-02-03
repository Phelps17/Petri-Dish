
public class InputSynapse extends Synapse{
	private Perceptron out;
	private VisionLine in;
	
	public InputSynapse(VisionLine in, Perceptron out) {
		super();
		
		this.in = in;
		this.out = out;
	}
	
	@Override
	public double fireSynapse() {
		return this.in.checkVision() * this.getWeight();
	}
}
