import java.util.ArrayList;

public class Perceptron {
	private ArrayList<Synapse> incoming, outgoing;
	private int layerNumber, levelId;
	private Brain brain;

	public Perceptron(Brain brain, int layerNumber, int levelId) {
		this.brain = brain;
		this.layerNumber = layerNumber;
		this.levelId = levelId;

		this.incoming = new ArrayList<Synapse>();
		this.outgoing = new ArrayList<Synapse>();
	}

	public int getLayerNumber() {
		return this.layerNumber;
	}

	public int getlevelId() {
		return this.levelId;
	}

	public ArrayList<Synapse> getIncoming() {
		return this.incoming;
	}

	public ArrayList<Synapse> getOutgoing() {
		return this.outgoing;
	}

	public void addIncoming(Synapse s) {
		this.incoming.add(s);
	}

	public void addOutgoing(Synapse s) {
		this.outgoing.add(s);
	}

	public double processPerceptron() {
		double total = 0;
		
		for (Synapse in : this.getIncoming()) {
			double inValue = in.fireSynapse();
			total += inValue;
		}
		
		return total;
	}
}
