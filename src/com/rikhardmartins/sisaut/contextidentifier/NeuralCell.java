package com.rikhardmartins.sisaut.contextidentifier;

import java.util.HashMap;
import java.util.Map;

public class NeuralCell {
	private String cellName;
	private float lastInput;
	private float lastOutput;
	private Map<NeuralCell, Float> outputs;

	private float inputAccumulator;

	public static TransferFunction transferFunction;

	public NeuralCell(String cellName) {
		this.cellName = cellName;
		inputAccumulator = 0.0f;
	}

	public NeuralCell(String cellName, Map<NeuralCell, Float> outputs) {
		this.cellName = cellName;
		this.outputs = outputs;
		inputAccumulator = 0.0f;
	}

	public int addOutput(NeuralCell cell, float weight){
		if(outputs==null){
			outputs = new HashMap<NeuralCell, Float>();
		}
		outputs.put(cell, weight);
		return outputs.size();
	}
	
	public void inputValue(float value) {
		inputAccumulator += value;
	}

	public float calculateOutput() {
		lastOutput = transferFunction.transfer(lastInput = inputAccumulator);
		inputAccumulator = 0.0f;
		return lastOutput;
	}

	public float injectOutput(float inOut) {
		lastInput = lastOutput = inOut;
		inputAccumulator = 0.0f;
		return inOut;
	}

	public float getLastInput() {
		return lastInput;
	}

	public float getLastOutput() {
		return lastOutput;
	}
	
	public String getCellName(){
		return cellName;
	}
}
