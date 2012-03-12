package com.rikhardmartins.sisaut.contextidentifier;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import android.os.Environment;

public class NeuralNetwork {
	private List<NeuralCell> inputs;
	private List<NeuralCell> hiddens;
	private List<NeuralCell> outputs;
	private ConnectorLevel upperLevel;
	private ConnectorLevel lowerLever;

	public NeuralNetwork(int inputQuantity, int hiddenQuantity,
			int outputQuantity, TransferFunction transferFunction) {
		NeuralCell.transferFunction = transferFunction;
		inputs = new ArrayList<NeuralCell>();
		hiddens = new ArrayList<NeuralCell>();
		outputs = new ArrayList<NeuralCell>();
		upperLevel = new ConnectorLevel();
		lowerLever = new ConnectorLevel();

		for (int i = 0; i < inputQuantity; i++)
			inputs.add(new NeuralCell(null, upperLevel));
		for (int i = 0; i < hiddenQuantity; i++)
			hiddens.add(new NeuralCell(upperLevel, lowerLever));
		for (int i = 0; i < hiddenQuantity; i++)
			outputs.add(new NeuralCell(lowerLever, null));
	}

	public NeuralNetwork(TransferFunction transferFunction) {
		File homeFolder = new File(Environment.getRootDirectory()
				.getAbsolutePath() + "ContextIdentifier/");

		if (!homeFolder.exists()) {
			homeFolder.mkdirs();
		}
		String[] files = homeFolder.list(new FilenameFilter() {

			public boolean accept(File dir, String filename) {
				if (filename.toLowerCase().startsWith("CI"))
					return true;
				if (filename.toLowerCase().endsWith(".nn"))
					return true;
				return false;
			}
		});
		String lastestFile = "";
		int max = -1;
		for (String f : files) {
			int n = Integer.parseInt(f.substring(2, 7));
			if (n > max){
				max = n;
				lastestFile=f;				
			}
		}
		readNNFile(homeFolder.getAbsolutePath()+lastestFile);
	}
	
	private void readNNFile(String theFile){
		
	}

	public void setInputs(List<Float> inputs) {
		int size = inputs.size();
		if (size != this.inputs.size())
			return;
		for (int i = 0; i < size; i++)
			this.inputs.get(i).overrideCalculation(inputs.get(i));
	}

	public List<Float> getOutputs() {
		List<Float> result = new ArrayList<Float>();
		for (NeuralCell nc : outputs)
			result.add(nc.getLastOutput());
		return result;
	}
}
