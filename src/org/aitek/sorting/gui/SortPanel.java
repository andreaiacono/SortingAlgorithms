package org.aitek.sorting.gui;

import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.aitek.sorting.algorithms.SortStrategy;
import org.aitek.sorting.core.Constants;

public class SortPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private SortStrategy sortStrategy;
	private TitledBorder borderWorking, borderFinished;

	public SortPanel(String sortName, SortStrategy sortStrategy) {

		super(true);
		this.sortStrategy = sortStrategy;
		borderWorking = BorderFactory.createTitledBorder(new LineBorder(Constants.DOTS_COLOR, 2), sortName);
		borderWorking.setTitleColor(Constants.DOTS_COLOR);
		borderFinished = BorderFactory.createTitledBorder(new LineBorder(Constants.DOTS_FINISHED_COLOR, 2), sortName);
		borderFinished.setTitleColor(Constants.DOTS_FINISHED_COLOR);
		setBorder(borderWorking);
		setToolTipText(sortStrategy.getHTMLSourceCode());
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		int size;
		int[] values = sortStrategy.getData();
		int changed1 = sortStrategy.getLastCoupleChanged()[0];
		int changed2 = sortStrategy.getLastCoupleChanged()[1];

		if (sortStrategy.isFinished()) {
			setBorder(borderFinished);
		}
		else setBorder(borderWorking);

		for (int j = 0; j < values.length; j++) {
			if (j == changed1 || j == changed2) {
				g.setColor(Constants.DOTS_CHANGED_COLOR);
				size = 5;
			}
			else {
				if (sortStrategy.isFinished()) g.setColor(Constants.DOTS_FINISHED_COLOR);
				else g.setColor(Constants.DOTS_COLOR);
				size = 1;
			}
			g.drawRect(j + 2, Constants.SORT_MAX_VALUE - values[j] + 15, size, size);
		}
	}

	public void setSortStrategy(SortStrategy sortStrategy) {

		this.sortStrategy = sortStrategy;
	}

}
