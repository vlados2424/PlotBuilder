package com.company;

import javafx.util.Pair;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PlotFunctions implements PlotBuilder{

    public void functionGenerator(File fileMain) throws IOException {
        File file = new File(String.valueOf(fileMain));
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] x = new int[50];
        int[] y = new int[50];

        for (int i = 0; i < 50; ++i) {
            x[i] = (int) (Math.random() * 50);
            y[i] = (int) (Math.random() * 50);
        }

        FileWriter fileWriter = new FileWriter(file);

        for (int i = 0; i < x.length; ++i) {
            fileWriter.write(Integer.toString(x[i]) + "\t" + Integer.toString(y[i]) + "\n");
        }
        fileWriter.close();

        FileReader fileReader = new FileReader(file);

        int[] xAxis = new int[50];
        int[] yAxis = new int[50];
        int xAxisIndex = 0;
        int yAxisIndex = 0;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String str = null;
        while ((str = reader.readLine()) != null) {
            System.out.println(str);
            String[] arrStr = str.split("\t");
            xAxis[xAxisIndex] = Integer.parseInt(arrStr[0]);
            yAxis[yAxisIndex] = Integer.parseInt(arrStr[1]);
            xAxisIndex++;
            yAxisIndex++;
        }

        str = null;

        System.out.println(Arrays.toString(xAxis));
        System.out.println(Arrays.toString(yAxis));
    }


    @Override
    public ArrayList<Pair<Integer, Integer>> functionLoader(File fileMain) throws FileNotFoundException {
        ArrayList <Pair<Integer, Integer>> listPair = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileMain);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] strs = line.split("\t");
                ArrayList<String> values = new ArrayList<>();
                for (String str : strs) {
                    if (str != null) {
                        values.add(str);
                    }
                }
                Pair<Integer, Integer> pair = new Pair<>(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)));
                listPair.add(pair);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listPair;
    }

    @Override
    public void plotPainter(ArrayList<Pair<Integer, Integer>> function, JPanel panel) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Y and X");
        for (int i = 0; i < 50; ++i) {
            series.add(function.get(i).getKey(), function.get(i).getValue());
        }
        dataset.addSeries(series);
        XYDataset xydataset = dataset;
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Line Chart",
                "X",
                "Y",
                xydataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        JFrame frame = new JFrame("plot");
        frame.getContentPane().add(new ChartPanel(chart));
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}