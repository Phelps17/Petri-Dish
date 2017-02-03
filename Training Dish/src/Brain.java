import java.util.ArrayList;

public class Brain {
	private ArrayList<Perceptron> perceptrons;
	private ArrayList<VisionLine> input;
	private ArrayList<OutputPerceptron> output;
	private Microbe microbe;
	private OutputPerceptron turnRight, turnLeft, upX, downX, upY, downY;

	//TODO add constant weights for personality
	//TODO add dynamic weights for internal sources (hunger/health)

	public Brain(Microbe microbe) {
		this.microbe = microbe;
		buildBrain();
	}

	private void buildBrain() {
		this.perceptrons = new ArrayList<Perceptron>();

		//set the inputs
		setupInput();
		setupOutput();

		//create the hidden nodes
		for (int hiddenLevel = 0; hiddenLevel < Config.BRAIN_HIDDEN_LAYERS; hiddenLevel++) {
			for (int levelId = 0; levelId < Config.BRAIN_HIDDEN_LAYER_PERCEPTRONS; levelId++) {
				Perceptron newPerceptron = new Perceptron(this, hiddenLevel, levelId);
				this.perceptrons.add(newPerceptron);
			}
		}

		//connect perceptrons
		for (Perceptron perceptron : this.perceptrons) {
			if (perceptron.getLayerNumber() == Config.BRAIN_HIDDEN_LAYERS-1) {
				addOutputSynapses(perceptron);
			}

			for (Perceptron other : this.perceptrons) {
				if (perceptron.getLayerNumber() == 0) {
					addInputSynapses(perceptron);
				}
				else if (other.getLayerNumber()+1 == perceptron.getLayerNumber()) {
					Synapse synapse = new HiddenSynapse(other, perceptron);
					other.addOutgoing(synapse);
					perceptron.addIncoming(synapse);
				}
			}
		}
	}

	public void fireNeurons() {
		//System.out.println("FIRE");
		for (OutputPerceptron op : this.output) {
			op.processPerceptron();
		}
	}

	private void setupInput() {
		this.input = new ArrayList<VisionLine>();

		input.addAll(microbe.getVisionLines());
	}

	private void setupOutput() {
		this.output = new ArrayList<OutputPerceptron>();
		this.turnRight = new OutputPerceptron(this);
		this.turnLeft = new OutputPerceptron(this);
		this.upX = new OutputPerceptron(this);
		this.downX = new OutputPerceptron(this);
		this.upY = new OutputPerceptron(this);
		this.downY = new OutputPerceptron(this);

		this.output.add(this.turnRight);
		this.output.add(this.turnLeft);
		this.output.add(this.upX);
		this.output.add(this.downX);
		this.output.add(this.upY);
		this.output.add(this.downY);
	}

	private void addInputSynapses(Perceptron perceptron) {
		for (VisionLine sight : this.input) {
			InputSynapse synapse = new InputSynapse(sight, perceptron);
			perceptron.addIncoming(synapse);
		}
	}

	private void addOutputSynapses(Perceptron perceptron) {
		for (OutputPerceptron out : this.output) {
			Synapse synapse = new HiddenSynapse(perceptron, out);
			perceptron.addOutgoing(synapse);
			out.addIncoming(synapse);
		}
	}

	public double getXDelta() {
		return Config.sigmoid(this.upX.getOutputValue() - this.downX.getOutputValue());
	}

	public double getYDelta() {
		return Config.sigmoid(this.upY.getOutputValue() - this.downY.getOutputValue());
	}

	public double getAngleDelta() {
		return Config.sigmoid(this.turnRight.getOutputValue() - this.turnLeft.getOutputValue());
	}
}
