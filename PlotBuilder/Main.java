package com.company;

import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        PlotBuilder builder = new PlotFunctions();
        File file = new File("test.txt");
        builder.functionGenerator(file);
        ArrayList<Pair<Integer, Integer>> XY = builder.functionLoader(file);
        builder.plotPainter(XY, null);
    }
}
