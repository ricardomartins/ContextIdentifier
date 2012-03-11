package com.rikhardmartins.sisaut.contextidentifier;

import java.util.ArrayList;
import java.util.List;

public class ConnectorLevel {
	private List<NeuralCell> aboveConnections;
	private List<NeuralCell> belowConnections;
	private float connections[][];

	public ConnectorLevel() {
		aboveConnections = new ArrayList<NeuralCell>();
		belowConnections = new ArrayList<NeuralCell>();
	}

	public float neuralCellInput(int belowNeuralCellIndex) {
		int acSize = aboveConnections.size();
		float result = 0;
		for (int i = 0; i < acSize; i++) {
			result += aboveConnections.get(i).getLastOutput()
					* connections[i][belowNeuralCellIndex];
		}
		return result;
	}

	public int aboveConnectionsQuantity() {
		return aboveConnections.size();
	}

	public int belowConnectionsQuantity() {
		return belowConnections.size();
	}

	public int registerAboveConnector(NeuralCell theCell) {
		if (((aboveConnections.size() + 1) * belowConnections.size()) > 0) {
			float oneArray[][] = new float[aboveConnections.size() + 1][belowConnections
					.size()];
			for (int i = 0; i < aboveConnections.size(); i++)
				for (int j = 0; j < belowConnections.size(); j++)
					oneArray[i][j] = connections[i][j];
			connections = oneArray;
		}
		aboveConnections.add(theCell);
		return (aboveConnections.size() - 1);
	}

	public int registerBelowConnector(NeuralCell theCell) {
		if ((aboveConnections.size() * (belowConnections.size() + 1)) > 0) {
			float oneArray[][] = new float[aboveConnections.size()][belowConnections
					.size() + 1];
			for (int i = 0; i < aboveConnections.size(); i++)
				for (int j = 0; j < belowConnections.size(); j++)
					oneArray[i][j] = connections[i][j];
			connections = oneArray;
		}
		belowConnections.add(theCell);
		return (belowConnections.size() - 1);
	}

	public void generateRandomValues() {
		for (int i = 0; i < aboveConnections.size(); i++)
			for (int j = 0; j < belowConnections.size(); j++)
				connections[i][j] = (float) Math.random();
	}
}
