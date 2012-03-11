package com.rikhardmartins.sisaut.contextidentifier;

public class SigmoidTransferFunction implements TransferFunction {

	public float transfer(float value) {
		float result;
		result = 1.0f / (1 + ((float) Math.exp(-value)));
		return result;
	}

}
