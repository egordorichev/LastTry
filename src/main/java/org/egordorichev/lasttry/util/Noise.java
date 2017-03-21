package org.egordorichev.lasttry.util;

import org.egordorichev.lasttry.LastTry;

public class Noise {
	private float roughness;
	private float[][] grid;

	public Noise(float roughness, int width, int height) {
		this.roughness = roughness / width;
		this.grid = new float[width][height];
	}

	public void initialise() {
		int xh = this.grid.length - 1;
		int yh = this.grid[0].length - 1;

		this.grid[0][0] = LastTry.random.nextFloat() - 0.5f;
		this.grid[0][yh] = LastTry.random.nextFloat() - 0.5f;
		this.grid[xh][0] = LastTry.random.nextFloat() - 0.5f;
		this.grid[xh][yh] = LastTry.random.nextFloat() - 0.5f;

		this.generate(0, 0, xh, yh);
	}

	private float roughen(float v, int l, int h) {
		return v + this.roughness * (float) (LastTry.random.nextGaussian() * (h - l));
	}

	private void generate(int xl, int yl, int xh, int yh) {
		int xm = (xl + xh) / 2;
		int ym = (yl + yh) / 2;

		if ((xl == xm) && (yl == ym)) {
			return;
		}

		this.grid[xm][yl] = 0.5f * (this.grid[xl][yl] + this.grid[xh][yl]);
		this.grid[xm][yh] = 0.5f * (this.grid[xl][yh] + this.grid[xh][yh]);
		this.grid[xl][ym] = 0.5f * (this.grid[xl][yl] + this.grid[xl][yh]);
		this.grid[xh][ym] = 0.5f * (this.grid[xh][yl] + this.grid[xh][yh]);

		float v = roughen(0.5f * (this.grid[xm][yl] + this.grid[xm][yh]), xl + yl, yh + xh);

		this.grid[xm][ym] = v;
		this.grid[xm][yl] = roughen(this.grid[xm][yl], xl, xh);
		this.grid[xm][yh] = roughen(this.grid[xm][yh], xl, xh);
		this.grid[xl][ym] = roughen(this.grid[xl][ym], yl, yh);
		this.grid[xh][ym] = roughen(this.grid[xh][ym], yl, yh);

		this.generate(xl, yl, xm, ym);
		this.generate(xm, yl, xh, ym);
		this.generate(xl, ym, xm, yh);
		this.generate(xm, ym, xh, yh);
	}

	public boolean[][] toBooleans(float threshold) {
		int w = this.grid.length;
		int h = this.grid[0].length;

		boolean[][] bool = new boolean[w][h];

		for (int i = 0; i < w;i++) {
			for (int j = 0; j < h;j++) {
				bool[i][j] = this.grid[i][j] < threshold;
			}
		}

		return bool;
	}
}