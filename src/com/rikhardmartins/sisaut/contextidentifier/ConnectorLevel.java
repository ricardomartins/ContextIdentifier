package com.rikhardmartins.sisaut.contextidentifier;

public class ConnectorLevel {
	private List<NeuralCell> aboveConnections;
	private List<NeuralCell> belowConnections;
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
		if (((aboveConnections.length + 1) * belowConnections.length) > 0) {
			float oneArray[][] = new float[aboveConnections.length + 1][belowConnections.length ];
			for (int i = 0; i < aboveConnections .length ; i++)
				for (int j = 0; j < belowConnections .length ; j++)
					oneArray[i][j] = connections[i][j];
			 connections=oneArray;
		}
		aboveConnections++;
		return (aboveConnections - 1);
	}

	public int registerBelowConnector(NeuralCell theCell) {
		if ((aboveConnections .length * (belowConnections .length + 1)) > 0) {
			float oneArray[][] = new float[aboveConnections .length ][belowConnections .length + 1];
			for (int i = 0; i < aboveConnections .length ; i++)
				for (int j = 0; j < belowConnections .length ; j++)
					oneArray[i][j] = connections[i][j];
			connections=oneArray;
		}
		 belowConnections++;
		return ( belowConnections .length - 1); 	}

	public void generateRandomValues() {
			for (int i = 0; i < aboveConnections .length ; i++)
				for (int j = 0; j < belowConnections .length ; j++)
					connections[i][j]=Math.rand();	
	}
}
