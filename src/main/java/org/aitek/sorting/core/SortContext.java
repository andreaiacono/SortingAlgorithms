package org.aitek.sorting.core;

import org.aitek.sorting.algorithms.SortStrategy;
import org.aitek.sorting.gui.SortPanel;

import java.lang.reflect.Constructor;

public class SortContext {

    SortStrategy sortStrategy;
    SortPanel sortPanel;

    public SortContext(SortStrategy sortStrategy, SortPanel sortPanel) {

        this.sortStrategy = sortStrategy;
        this.sortPanel = sortPanel;
    }

    public SortStrategy getSortStrategy() {

        return sortStrategy;
    }

    public void setSortStrategy(SortStrategy sortStrategy) {

        this.sortStrategy = sortStrategy;
    }

    public SortPanel getSortPanel() {

        return sortPanel;
    }

    public void renewStrategy(SortsController controller) {

        try {
            String sortClassName = sortStrategy.getClass().getName();
            Class<?> cls = Class.forName(sortClassName);
            Constructor constructor = cls.getConstructor(SortsController.class);
            this.sortStrategy = (SortStrategy) constructor.newInstance(controller);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
