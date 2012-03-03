package com.rikhardmartins.sisaut.contextidentifier;

public class ConnectorLevel {
	private int aboveConnections;
	private int belowConnections;
	private float connections[][];

	public ConnectorLevel() {
		aboveConnections = 0;
		belowConnections = 0;
	}

	public int getAboveConnections() {
		return aboveConnections;
	}

	public int getBelowConnections() {
		return belowConnections;
	}

	public int registerAboveConnector(NeuralCell theCell) {
		if (((aboveConnections + 1) * belowConnections) > 0) {
			float oneArray[][] = new float[aboveConnections + 1][belowConnections];
			for (int i = 0; i < aboveConnections; i++)
				for (int j = 0; j < belowConnections; j++)
					oneArray[i][j] = connections[i][j];
			
		}
		aboveConnections++;
		return (aboveConnections - 1);
	}

	public int registerBelowConnector(NeuralCell theCell) {
		return 0;
	}

	public int generateRandomValues() {
		return 0;
	}
}
