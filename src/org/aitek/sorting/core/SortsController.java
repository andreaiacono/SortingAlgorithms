package org.aitek.sorting.core;

import java.util.Arrays;

import org.aitek.sorting.gui.Main;

public class SortsController implements Runnable {

	private long speed;
	private SortContext[] sortContexts;
	private boolean isStopClicked;
	private Main main;
	private Randomness randomness;

	public void run() {

		boolean isAllFinished = false;
		try {
			while (!isAllFinished && !isStopClicked()) {

				isAllFinished = true;
				for (SortContext sortContext : sortContexts) {

					sortContext.sortPanel.invalidate();
					sortContext.sortPanel.repaint();
					if (!sortContext.sortStrategy.isFinished()) isAllFinished = false;
				}
				Thread.sleep(speed);
				synchronized (this) {
					notifyAll();
				}
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		main.finish();
	}

	public void shuffle() throws Exception {

		int[] random_values = getRandomValues();
		for (SortContext sortContext : sortContexts) {

			sortContext.renewStrategy(this);
			int[] values = Arrays.copyOf(random_values, random_values.length);
			sortContext.getSortStrategy().setData(values);
			sortContext.getSortPanel().setSortStrategy(sortContext.sortStrategy);
			Thread t = new Thread(sortContext.sortStrategy);
			t.start();
			sortContext.sortPanel.repaint();

		}

	}

	private int[] getRandomValues() {

		int[] values = new int[Constants.SORT_SIZE];

		switch (randomness) {
			case RANDOM:
				for (int j = 0; j < Constants.SORT_SIZE; j++) {
					values[j] = (int) (Math.random() * Constants.SORT_MAX_VALUE);
				}
			break;

			case ALMOST_SORTED:
				for (int j = 0; j < Constants.SORT_SIZE; j++) {
					values[j] = (int) (Math.random() * Constants.SORT_MAX_VALUE);
				}
				Arrays.sort(values);
				for (int j = 0; j < Constants.SORT_SIZE; j++) {
					if (Math.random() > 0.80) values[j] = (int) (Math.random() * Constants.SORT_MAX_VALUE);
				}
			break;

			case REVERSE:
				int[] tmp = Arrays.copyOf(values, values.length);
				for (int j = 0; j < Constants.SORT_SIZE; j++) {
					tmp[j] = (int) (Math.random() * Constants.SORT_MAX_VALUE);
				}
				Arrays.sort(tmp);
				for (int j = 1; j <= Constants.SORT_SIZE; j++) {
					values[Constants.SORT_SIZE - j] = tmp[j - 1];
				}
			break;

			case FEW_VALUES:
				for (int j = 0; j < Constants.SORT_SIZE; j++) {
					values[j] = (int) (Math.random() * Constants.SORT_MAX_VALUE) % 5 * (Constants.SORT_MAX_VALUE / 5) + 20;
				}
			break;

		}
		// int range = Constants.SORT_MAX_VALUE / randomness;
		// for (int j = 0; j < Constants.SORT_SIZE; j++) {
		// boolean isAbove = Math.random() * Constants.SORT_MAX_VALUE > j;
		// if (isAbove) values[j] = (int) (j + (Math.random() * range) % (Constants.SORT_MAX_VALUE -
		// j));
		// else values[j] = (int) (j - (Math.random() * range) % j);
		// }
		return values;
	}

	public void setStopClicked(boolean isStopClicked) {

		this.isStopClicked = isStopClicked;
	}

	public boolean isStopClicked() {

		return isStopClicked;
	}

	public void stepPanels() throws Exception {

		synchronized (this) {
			for (SortContext sortContext : sortContexts) {
				sortContext.sortPanel.repaint();
			}
			notifyAll();
		}
	}

	public void setSortContexts(SortContext... sortContexts) {

		this.sortContexts = sortContexts;
	}

	public void setSpeed(int speed) {

		this.speed = speed / 10;
	}

	public void setMain(Main main) {

		this.main = main;
	}

	public Main getMain() {

		return main;
	}

	public void setRandomness(Randomness randomness) {

		this.randomness = randomness;

	}
}
