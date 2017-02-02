import java.util.ArrayList;

public class Brain {
	private ArrayList<Perceptron> perceptrons;
	private ArrayList<Synapse> synapses;
	private ArrayList<VisionLine> input;
	private Microbe microbe;

	public Brain(Microbe microbe) {
		this.microbe = microbe;
		this.perceptrons = new ArrayList<Perceptron>();
		this.input = new ArrayList<VisionLine>();

		buildBrain();
	}

	private void buildBrain() {
		//set the inputs
		setupInput();

		//create the hidden nodes
		for (int hiddenLevel = 0; hiddenLevel < Config.BRAIN_HIDDEN_LAYERS; hiddenLevel++) {
			for (int levelId = 0; levelId < Config.BRAIN_HIDDEN_LAYER_PERCEPTRONS; levelId++) {
				Perceptron newPerceptron = new Perceptron(this, hiddenLevel, levelId);
				this.perceptrons.add(newPerceptron);
			}
		}

		//connect hidden nodes to each other
		for (Perceptron perceptron : this.perceptrons) {
			for (Perceptron other : this.perceptrons) {
				if (other.getLayerNumber()+1 == perceptron.getLayerNumber()) {
					Synapse synapse = new Synapse(other, perceptron);
					other.addOutgoing(synapse);
					perceptron.addIncoming(synapse);
					System.out.println("Synapse : " + other.getLayerNumber() + 
							" " + other.getlevelId() + " -> " + perceptron.getLayerNumber()
							+ " " + perceptron.getlevelId());
				}
			}
		}

		//create the outputs
		//connect inputs and outputs
	}

	private void setupInput() {
		input.add(microbe.sight1);
		input.add(microbe.sight2);
		input.add(microbe.sight3);
		input.add(microbe.sight4);
		input.add(microbe.sight5);
		input.add(microbe.sight6);
		input.add(microbe.sight7);
	}
}
