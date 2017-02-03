import java.util.ArrayList;

public class Brain {
	private ArrayList<Perceptron> perceptrons;
	private ArrayList<VisionLine> input;
	private ArrayList<OutputPerceptron> output;
	private Microbe microbe;
	
	//TODO add constant weights for personality
	//TODO add dynamic weights for internal sources (hunger/health)

	public Brain(Microbe microbe) {
		this.microbe = microbe;
		this.perceptrons = new ArrayList<Perceptron>();
		this.input = new ArrayList<VisionLine>();
		this.output = new ArrayList<OutputPerceptron>();

		buildBrain();
	}

	private void buildBrain() {
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
			for (Perceptron other : this.perceptrons) {
				if (perceptron.getLayerNumber() == 0) {
					addInputSynapses(perceptron);
				}
				else if (other.getLayerNumber()+1 == perceptron.getLayerNumber()) {
					Synapse synapse = new HiddenSynapse(other, perceptron);
					other.addOutgoing(synapse);
					perceptron.addIncoming(synapse);
					System.out.println("Synapse : " + other.getLayerNumber() + 
							" " + other.getlevelId() + " -> " + perceptron.getLayerNumber()
							+ " " + perceptron.getlevelId());
				}
				
				if (perceptron.getLayerNumber() == Config.BRAIN_HIDDEN_LAYERS-1) {
					addOutputSynapses(perceptron);
				}
			}
		}
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
	
	private void setupOutput() {
		//TODO we are going to need multiple outputs
		this.output.add(new OutputPerceptron(this));
	}
	
	private void addInputSynapses(Perceptron perceptron) {
		for (VisionLine sight : this.input) {
			Synapse synapse = new InputSynapse(sight, perceptron);
			perceptron.addIncoming(synapse);
		}
	}
	
	private void addOutputSynapses(Perceptron perceptron) {
		for (OutputPerceptron out : this.output) {
			Synapse synapse = new HiddenSynapse(perceptron, out);
			perceptron.addOutgoing(synapse);
		}
	}
}
