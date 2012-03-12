package com.rikhardmartins.sisaut.contextidentifier;

public class StepTransferFunction implements TransferFunction {

	public float transfer(float value) {
		float result;
		result = (value >= 0.0f) ? 1.0f : 0.0f;
		return result;
	}

}
