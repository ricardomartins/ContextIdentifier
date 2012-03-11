package com.rikhardmartins.sisaut.contextidentifier;

public class NeuralCell {
	private ConnectorLevel aboveConnectorLevel;
	private int aboveConnectorLevelIndex;
	private float lastInput;
	private float lastOutput;

	public static TransferFunction transferFunction;

	public NeuralCell(ConnectorLevel aboveConnections,
			ConnectorLevel belowConnections) {
		aboveConnectorLevel = aboveConnections;
		if (aboveConnections != null)
			aboveConnectorLevelIndex = aboveConnections
					.registerBelowConnector(this);
		else
			aboveConnectorLevelIndex = -1;
		if (belowConnections != null)
			belowConnections.registerAboveConnector(this);
	}

	public float calculateInput() {
		if (aboveConnectorLevelIndex == -1) {
			return 0.0f;
		}
		lastInput = aboveConnectorLevel
				.neuralCellInput(aboveConnectorLevelIndex);
		return lastInput;
	}

	public float calculateOutput() {
		lastOutput = transferFunction.transfer(lastInput);
		return lastOutput;
	}

	public float overrideCalculation(float inOut) {
		lastInput = lastOutput = inOut;
		return inOut;
	}

	public float getLastInput() {
		return lastInput;
	}

	public float getLastOutput() {
		return lastOutput;
	}
}
