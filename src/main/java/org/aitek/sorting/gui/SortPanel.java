package org.aitek.sorting.gui;

import org.aitek.sorting.algorithms.SortStrategy;
import org.aitek.sorting.core.Constants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

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
        setToolTipText(sortStrategy.getHtmlSourceCode());
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        int size;
        int[] values = sortStrategy.getData();
        int changed1 = sortStrategy.getLastCoupleChanged()[0];
        int changed2 = sortStrategy.getLastCoupleChanged()[1];
        int compared1 = sortStrategy.getLastCoupleCompared()[0];
        int compared2 = sortStrategy.getLastCoupleCompared()[1];

        if (sortStrategy.isFinished()) {
            setBorder(borderFinished);
        }
        else setBorder(borderWorking);

        for (int j = 0; j < values.length; j++) {
            if (sortStrategy.isFinished()) g.setColor(Constants.DOTS_FINISHED_COLOR);
            else g.setColor(Constants.DOTS_COLOR);
            draw(g, 1, j, 0, values);

            if (j == changed1 || j == changed2) {
                g.setColor(Constants.DOTS_CHANGED_COLOR);
                draw(g, 5, j, -2, values);
            }
            if (j == compared1 || j == compared2) {
                g.setColor(Constants.DOTS_COMPARED_COLOR);
                draw(g, 5, j, -2, values);
            }
        }
    }

    private void draw(Graphics g, int size, int index, int shift, int[] values) {
        g.drawRect(index + Constants.EMPTY_FRAME_SIZE + shift, Constants.SORT_MAX_VALUE - values[index] + 15 + shift, size, size);
    }

    public void setSortStrategy(SortStrategy sortStrategy) {

        this.sortStrategy = sortStrategy;
    }

}
