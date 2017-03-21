package org.egordorichev.lasttry.util;

import org.egordorichev.lasttry.LastTry;

public class Noise {
	private float persistance = 0.5f;
	private float amplitude = 0.5f;
	private int width;
	private int height;

	public Noise(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public float[][] generateWhiteNoise() {
		float[][] noise = new float[this.width][this.height];

		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				noise[i][j] = (float) (LastTry.random.nextInt(1001)) / 1000;
			}
		}

		return noise;
	}

	public float interpolate(float x0, float x1, float alpha) {
		return x0 * (1 - alpha) + alpha * x1;
	}

	public float[][] generateSmoothNoise(float[][] baseNoise, int octave) {
		float[][] noise = new float[this.width][this.height];
		int period = 1 << octave;
		float frequency = 1.0f / period;

		for (int i = 0; i < this.width; i++) {
			int i0 = (i / period) * period;
			int i1 = (i0 + period) % this.width;
			float hBlend = (i - i0) * frequency;

			for (int j = 0; j < this.height; j++){
				int j0 = (j / period) * period;
				int j1 = (j0 + period) % this.height;
				float vBlend = (j - j0) * frequency;

				float top = this.interpolate(baseNoise[i0][j0], baseNoise[i1][j0], hBlend);
				float bottom = this.interpolate(baseNoise[i0][j1], baseNoise[i1][j1], hBlend);

				noise[i][j] = interpolate(top, bottom, vBlend);
			}
		}

		return noise;
	}

	public float[][] generatePerlinNoise(float[][] baseNoise, int octaveCount) {
		float[][][] noise = new float[this.width][this.height][octaveCount];
		float[][] perlinNoise = new float[this.width][this.height];
		float totalAmplitude = 0.0f;

		for(int octave = octaveCount - 1; octave >= 0; octave--){
			noise[octave] = generateSmoothNoise(baseNoise, octave); // FIXME

			this.amplitude *= this.persistance;
			totalAmplitude += this.amplitude;

			for(int i = 0; i < this.width; i++){
				for(int j = 0; j < this.height; j++){
					perlinNoise[i][j] += noise[octave][i][j] * this.amplitude;
				}
			}
		}

		for(int i = 0; i < this.width; i++){
			for(int j = 0; j < this.height; j++){
				perlinNoise[i][j] /= totalAmplitude;
			}
		}

		return perlinNoise;
	}

	public float[][] generatePerlinNoise(int octaveCount) {
		return this.generatePerlinNoise(this.generateWhiteNoise(), octaveCount);
	}
}