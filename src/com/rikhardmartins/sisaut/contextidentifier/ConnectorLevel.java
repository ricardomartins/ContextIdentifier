package com.rikhardmartins.sisaut.contextidentifier;

public class ConnectorLevel {
	private int aboveConnections;
	private int belowConnections;
	private float array[];
	
	public int getAboveConnections(){
		return aboveConnections;
	}

	public int getBelowConnections(){
		return belowConnections;
	}
	
	public int registerAboveConnector(NeuralCell theCell){
		return 0;
	}

	public int registerBelowConnector(NeuralCell theCell){
		return 0;
	}
}
