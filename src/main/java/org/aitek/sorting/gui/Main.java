package org.aitek.sorting.gui;

import org.aitek.sorting.algorithms.*;
import org.aitek.sorting.core.Constants;
import org.aitek.sorting.core.Randomness;
import org.aitek.sorting.core.SortContext;
import org.aitek.sorting.core.SortsController;
import org.aitek.sorting.utils.SwingUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener, ChangeListener {

    private static final long serialVersionUID = 0L;
    private final JComboBox randomnessCombo;
    SortsController sortsController;
    private JButton jbStop;
    private JButton jbStart;
    private JButton jbStep;
    private boolean isFinished;

    public Main() throws Exception {

        super("Sorting Algorithms");

        ToolTipManager.sharedInstance().setInitialDelay(200);
        ToolTipManager.sharedInstance().setDismissDelay(30000);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        sortsController = new SortsController();

        setSize((Constants.PANEL_WIDTH) * 4 + 28, Constants.PANEL_HEIGHT * 2 + 80);
        setResizable(false);
        SpringLayout sl = new SpringLayout();
        setLayout(sl);

        jbStart = new JButton("Start");
        add(jbStart);
        jbStart.addActionListener(this);
        sl.putConstraint(SpringLayout.WEST, jbStart, 5, SpringLayout.WEST, this.getContentPane());
        sl.putConstraint(SpringLayout.SOUTH, jbStart, -5, SpringLayout.SOUTH, this.getContentPane());

        jbStep = new JButton("Step");
        add(jbStep);
        jbStep.addActionListener(this);
        sl.putConstraint(SpringLayout.WEST, jbStep, 5, SpringLayout.EAST, jbStart);
        sl.putConstraint(SpringLayout.SOUTH, jbStep, -5, SpringLayout.SOUTH, this.getContentPane());

        jbStop = new JButton("Stop");
        add(jbStop);
        jbStop.addActionListener(this);
        jbStop.setEnabled(false);
        sl.putConstraint(SpringLayout.WEST, jbStop, 5, SpringLayout.EAST, jbStep);
        sl.putConstraint(SpringLayout.SOUTH, jbStop, -5, SpringLayout.SOUTH, this.getContentPane());

        JButton jbShuffle = new JButton("Shuffle");
        add(jbShuffle);
        jbShuffle.addActionListener(this);
        sl.putConstraint(SpringLayout.WEST, jbShuffle, 15, SpringLayout.EAST, jbStop);
        sl.putConstraint(SpringLayout.SOUTH, jbShuffle, -5, SpringLayout.SOUTH, this.getContentPane());

        JButton jbQuit = new JButton("Quit");
        add(jbQuit);
        jbQuit.addActionListener(this);
        sl.putConstraint(SpringLayout.EAST, jbQuit, -5, SpringLayout.EAST, this.getContentPane());
        sl.putConstraint(SpringLayout.SOUTH, jbQuit, -5, SpringLayout.SOUTH, this.getContentPane());

        JLabel speedLabel = new JLabel("Speed: ");
        add(speedLabel);
        sl.putConstraint(SpringLayout.WEST, speedLabel, 20, SpringLayout.EAST, jbShuffle);
        sl.putConstraint(SpringLayout.SOUTH, speedLabel, -10, SpringLayout.SOUTH, this.getContentPane());

        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 1000, 50);
        sortsController.setSpeed(50);
        speedSlider.setName("speed");
        add(speedSlider);
        speedSlider.addChangeListener(this);
        sl.putConstraint(SpringLayout.WEST, speedSlider, 0, SpringLayout.EAST, speedLabel);
        sl.putConstraint(SpringLayout.EAST, speedSlider, 120, SpringLayout.EAST, speedLabel);
        sl.putConstraint(SpringLayout.SOUTH, speedSlider, -10, SpringLayout.SOUTH, this.getContentPane());

        JLabel randomnessLabel = new JLabel("Randomness: ");
        add(randomnessLabel);
        sl.putConstraint(SpringLayout.WEST, randomnessLabel, 20, SpringLayout.EAST, speedSlider);
        sl.putConstraint(SpringLayout.SOUTH, randomnessLabel, -10, SpringLayout.SOUTH, this.getContentPane());

        randomnessCombo = new JComboBox(Randomness.values());
        randomnessCombo.addActionListener(this);
        randomnessCombo.setName("randomness");
        add(randomnessCombo);
        sortsController.setRandomness(Randomness.RANDOM);

        sl.putConstraint(SpringLayout.WEST, randomnessCombo, 0, SpringLayout.EAST, randomnessLabel);
//		sl.putConstraint(SpringLayout.EAST, randomnessCombo, 80, SpringLayout.EAST, randomnessLabel);
        sl.putConstraint(SpringLayout.SOUTH, randomnessCombo, -5, SpringLayout.SOUTH, this.getContentPane());

        BubbleSort bubbleSort = new BubbleSort(sortsController);
        SortPanel bubblePanel = new SortPanel("Bubble Sort", bubbleSort);
        add(bubblePanel);
        sl.putConstraint(SpringLayout.WEST, bubblePanel, 5, SpringLayout.WEST, this.getContentPane());
        sl.putConstraint(SpringLayout.EAST, bubblePanel, Constants.PANEL_WIDTH + 5, SpringLayout.WEST, this.getContentPane());
        sl.putConstraint(SpringLayout.NORTH, bubblePanel, 5, SpringLayout.NORTH, getContentPane());
        sl.putConstraint(SpringLayout.SOUTH, bubblePanel, Constants.PANEL_HEIGHT + 5, SpringLayout.NORTH, getContentPane());

        SelectionSort selectionSort = new SelectionSort(sortsController);
        SortPanel selectionPanel = new SortPanel("Selection Sort", selectionSort);
        add(selectionPanel);
        sl.putConstraint(SpringLayout.WEST, selectionPanel, 5, SpringLayout.EAST, bubblePanel);
        sl.putConstraint(SpringLayout.EAST, selectionPanel, Constants.PANEL_WIDTH + 5, SpringLayout.EAST, bubblePanel);
        sl.putConstraint(SpringLayout.NORTH, selectionPanel, 5, SpringLayout.NORTH, getContentPane());
        sl.putConstraint(SpringLayout.SOUTH, selectionPanel, Constants.PANEL_HEIGHT + 5, SpringLayout.NORTH, getContentPane());

        InsertionSort insertionSort = new InsertionSort(sortsController);
        SortPanel insertionPanel = new SortPanel("Insertion Sort", insertionSort);
        add(insertionPanel);
        sl.putConstraint(SpringLayout.WEST, insertionPanel, 5, SpringLayout.EAST, selectionPanel);
        sl.putConstraint(SpringLayout.EAST, insertionPanel, Constants.PANEL_WIDTH + 5, SpringLayout.EAST, selectionPanel);
        sl.putConstraint(SpringLayout.NORTH, insertionPanel, 5, SpringLayout.NORTH, getContentPane());
        sl.putConstraint(SpringLayout.SOUTH, insertionPanel, Constants.PANEL_HEIGHT + 5, SpringLayout.NORTH, getContentPane());

        ShellSort shellSort = new ShellSort(sortsController);
        SortPanel shellPanel = new SortPanel("Shell Sort", shellSort);
        add(shellPanel);
        sl.putConstraint(SpringLayout.WEST, shellPanel, 5, SpringLayout.EAST, insertionPanel);
        sl.putConstraint(SpringLayout.EAST, shellPanel, Constants.PANEL_WIDTH + 5, SpringLayout.EAST, insertionPanel);
        sl.putConstraint(SpringLayout.NORTH, shellPanel, 5, SpringLayout.NORTH, getContentPane());
        sl.putConstraint(SpringLayout.SOUTH, shellPanel, Constants.PANEL_HEIGHT + 5, SpringLayout.NORTH, getContentPane());

        QuickSort quickSort = new QuickSort(sortsController);
        SortPanel quickPanel = new SortPanel("Quick Sort", quickSort);
        add(quickPanel);
        sl.putConstraint(SpringLayout.WEST, quickPanel, 5, SpringLayout.WEST, this.getContentPane());
        sl.putConstraint(SpringLayout.EAST, quickPanel, Constants.PANEL_WIDTH + 5, SpringLayout.WEST, this.getContentPane());
        sl.putConstraint(SpringLayout.NORTH, quickPanel, 5, SpringLayout.SOUTH, bubblePanel);
        sl.putConstraint(SpringLayout.SOUTH, quickPanel, Constants.PANEL_HEIGHT + 5, SpringLayout.SOUTH, bubblePanel);

        MergeSort mergeSort = new MergeSort(sortsController);
        SortPanel mergePanel = new SortPanel("Merge Sort", mergeSort);
        add(mergePanel);
        sl.putConstraint(SpringLayout.WEST, mergePanel, 5, SpringLayout.EAST, quickPanel);
        sl.putConstraint(SpringLayout.EAST, mergePanel, Constants.PANEL_WIDTH + 5, SpringLayout.EAST, quickPanel);
        sl.putConstraint(SpringLayout.NORTH, mergePanel, 5, SpringLayout.SOUTH, bubblePanel);
        sl.putConstraint(SpringLayout.SOUTH, mergePanel, Constants.PANEL_HEIGHT + 5, SpringLayout.SOUTH, bubblePanel);

        HeapSort heapSort = new HeapSort(sortsController);
        SortPanel heapPanel = new SortPanel("Heap Sort", heapSort);
        add(heapPanel);
        sl.putConstraint(SpringLayout.WEST, heapPanel, 5, SpringLayout.EAST, mergePanel);
        sl.putConstraint(SpringLayout.EAST, heapPanel, Constants.PANEL_WIDTH + 5, SpringLayout.EAST, mergePanel);
        sl.putConstraint(SpringLayout.NORTH, heapPanel, 5, SpringLayout.SOUTH, bubblePanel);
        sl.putConstraint(SpringLayout.SOUTH, heapPanel, Constants.PANEL_HEIGHT + 5, SpringLayout.SOUTH, bubblePanel);

        RadixSort radixSort = new RadixSort(sortsController);
        SortPanel radixPanel = new SortPanel("Radix Sort", radixSort);
        add(radixPanel);
        sl.putConstraint(SpringLayout.WEST, radixPanel, 5, SpringLayout.EAST, heapPanel);
        sl.putConstraint(SpringLayout.EAST, radixPanel, Constants.PANEL_WIDTH + 5, SpringLayout.EAST, heapPanel);
        sl.putConstraint(SpringLayout.NORTH, radixPanel, 5, SpringLayout.SOUTH, bubblePanel);
        sl.putConstraint(SpringLayout.SOUTH, radixPanel, Constants.PANEL_HEIGHT + 5, SpringLayout.SOUTH, bubblePanel);

        // CountingSort countingSort = new CountingSort(sortsController);
        // SortPanel countingPanel = new SortPanel("Counting Sort", countingSort);
        // add(countingPanel);
        // sl.putConstraint(SpringLayout.WEST, countingPanel, 5, SpringLayout.EAST, radixPanel);
        // sl.putConstraint(SpringLayout.EAST, countingPanel, Constants.PANEL_WIDTH + 5,
        // SpringLayout.EAST, radixPanel);
        // sl.putConstraint(SpringLayout.NORTH, countingPanel, 5, SpringLayout.SOUTH, shellPanel);
        // sl.putConstraint(SpringLayout.SOUTH, countingPanel, Constants.PANEL_HEIGHT + 5,
        // SpringLayout.SOUTH, shellPanel);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sortsController.setMain(this);
        sortsController.setSortContexts(new SortContext(bubbleSort, bubblePanel), new SortContext(selectionSort, selectionPanel), new SortContext(insertionSort, insertionPanel), new SortContext(shellSort, shellPanel), new SortContext(quickSort, quickPanel), new SortContext(mergeSort, mergePanel), new SortContext(heapSort, heapPanel), new SortContext(radixSort, radixPanel));
        sortsController.shuffle();
        sortsController.setStopClicked(true);
    }

    public static void main(String[] args) {

        try {

            Main m = new Main();
            m.setVisible(true);
        }
        catch (Exception e) {
            SwingUtils.showFormError(e);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            if (e.getActionCommand().equals("Quit")) {
                System.exit(0);
            }
            else if (e.getActionCommand().equals("Start")) {
                isFinished = false;
                jbStop.setEnabled(true);
                jbStep.setEnabled(false);
                jbStart.setEnabled(false);
                sortsController.setStopClicked(false);
                Thread t = new Thread(sortsController);
                t.start();
            }
            else if (e.getActionCommand().equals("Stop")) {
                jbStart.setEnabled(true);
                jbStep.setEnabled(true);
                jbStop.setEnabled(false);
                sortsController.setStopClicked(true);
            }
            else if (e.getActionCommand().equals("Step")) {
                sortsController.setStopClicked(true);
                sortsController.stepPanels();
            }
            else if (e.getActionCommand().equals("Shuffle")) {

                if (isFinished) {
                    jbStart.setEnabled(true);
                    jbStep.setEnabled(true);
                }

                sortsController.shuffle();
            }
            else if (e.getActionCommand().equals("comboBoxChanged")) {

                JComboBox cb = (JComboBox) e.getSource();
                Randomness randomness = Randomness.get(cb.getSelectedItem().toString());
                sortsController.setRandomness(randomness);
                // do we really want that changing the dropdown cancel the actual sorting?
//                    sortsController.shuffle();
            }
        }
        catch (Exception ex) {
            SwingUtils.showFormError(ex);
        }

    }

    public void finish() {

        isFinished = true;
        jbStart.setEnabled(true);
        jbStep.setEnabled(true);
        jbStop.setEnabled(false);
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        JSlider slider = (JSlider) e.getSource();
        if (slider.getName().equals("speed")) sortsController.setSpeed(slider.getValue());
    }

}
