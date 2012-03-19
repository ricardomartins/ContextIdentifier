package com.rikhardmartins.sisaut.contextidentifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeuralNetwork {

	public final static int CELL_INTRO_ZEROS = 0;
	public final static int CELL_INTRO_RANDOM = 7;

	public int cellIntroMethod;

	private Map<String, NeuralCell> index;
	private List<NeuralCell> inputs;
	private List<NeuralCell> hiddens;
	private List<NeuralCell> outputs;

	public NeuralNetwork(List<String> inputs, List<String> hiddens,
			List<String> outputs, int cellIntroMethod, TransferFunction transferFunction) {

		this.cellIntroMethod = cellIntroMethod;
		NeuralCell.transferFunction = transferFunction;
		this.index = new HashMap<String, NeuralCell>();

		this.outputs = new ArrayList<NeuralCell>();
		for (String s : outputs) {
			NeuralCell newCell = new NeuralCell(s);
			this.outputs.add(newCell);
			this.index.put(s, newCell);
		}

		this.hiddens = new ArrayList<NeuralCell>();
		for (String s : hiddens) {
			Map<NeuralCell, Float> oLevel = new HashMap<NeuralCell, Float>();
			for (NeuralCell nc : this.outputs) {
				oLevel.put(nc, newConnection());
			}
			NeuralCell newCell = new NeuralCell(s, oLevel);
			this.hiddens.add(newCell);
			this.index.put(s, newCell);
		}

		this.inputs = new ArrayList<NeuralCell>();
		for (String s : inputs) {
			Map<NeuralCell, Float> oLevel = new HashMap<NeuralCell, Float>();
			for (NeuralCell nc : this.hiddens) {
				oLevel.put(nc, newConnection());
			}
			NeuralCell newCell = new NeuralCell(s, oLevel);
			this.inputs.add(newCell);
			this.index.put(s, newCell);
		}
	}

	private float newConnection() {
		switch (cellIntroMethod) {
		case CELL_INTRO_ZEROS:
			return 0.0f;
		case CELL_INTRO_RANDOM:
			return (float) ((Math.random() * 2.0f) - 1.0f);
		default:
			return 0;
		}
	}
/*
	public List<Float> forwardPass() {
		for (NeuralCell nc : hiddens) {
			nc.calculateInput();
			nc.calculateOutput();
		}
		for (NeuralCell nc : outputs) {
			nc.calculateInput();
			nc.calculateOutput();
		}
		return getOutputs();
	}

	public void backwardPass() {

	}

	public void setInputs(List<Float> inputs) {
		int size = inputs.size();
		if (size != this.inputs.size())
			return;
		for (int i = 0; i < size; i++)
			this.inputs.get(i).overrideCalculation(inputs.get(i));
	}
*/
	public List<Float> getOutputs() {
		List<Float> result = new ArrayList<Float>();
		for (NeuralCell nc : outputs)
			result.add(nc.getLastOutput());
		return result;
	}
}
