package nology.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution {
    int noRows;
    int noCols;
    int nolayerCells;
    int rollingCellValue;
    int layerCounter;
    String[] parsedInput;
    String rowString;
    ArrayList<Integer> zeroesPerLayer;
    ArrayList<Integer> onesPerLayer;
    ArrayList<Integer> twosPerLayer;
    ArrayList<Integer> visibleArray;
    ArrayList<Boolean> filledArrayCheck;
    ArrayList<ArrayList<Integer>> counterArrays;

    public Solution(int noRows, int noCols, String inputString) {
        this.noRows = noRows;
        this.noCols = noCols;
        this.parsedInput = inputString.split("");
        nolayerCells = this.noRows * this.noCols;
        zeroesPerLayer = new ArrayList<Integer>();
        onesPerLayer = new ArrayList<Integer>();
        twosPerLayer = new ArrayList<Integer>();
        counterArrays = new ArrayList<ArrayList<Integer>>(Arrays.asList(zeroesPerLayer,onesPerLayer, twosPerLayer));
        layerCounter = 0;;
        rollingCellValue = 0;
        visibleArray = new ArrayList<Integer>();
        filledArrayCheck = new ArrayList<Boolean>();
        rowString = " ";
    }

    public void processData() {
        generateNewLayerIndex();
        handleDataLoop();
    }

    public void handleDataLoop() {
        for (int i = 0; i < parsedInput.length; i++) {
            if (i % nolayerCells == 0 && i != 0) {
                handleNewLayer();
            }
            updateCorrectArrays(i);
            processDigit(Integer.parseInt(parsedInput[i]));
            rollingCellValue++;
        }
    }

    public void handleNewLayer(){
        layerCounter++;
        generateNewLayerIndex();
        rollingCellValue=0;
    }

    public void updateCorrectArrays(int i){
        if (i < nolayerCells) {
            handleFirstLayer(Integer.parseInt(parsedInput[i]));
        } else {
            updateVisibleArray(Integer.parseInt(parsedInput[i]));
        }
    }

    public void updateVisibleArray(Integer val) {
        if (!filledArrayCheck.get(rollingCellValue) && val != 2){
            visibleArray.set(rollingCellValue, val);
            filledArrayCheck.set(rollingCellValue, true);
        }
    }

    public void handleFirstLayer(int val) {
        filledArrayCheck.add( !(val ==2) );
        visibleArray.add(val);
    }

    public void processDigit(Integer val) {
        counterArrays.get(val).set(layerCounter, counterArrays.get(val).get(layerCounter)+1);
        }

    public void generateNewLayerIndex() {
        zeroesPerLayer.add(0);
        onesPerLayer.add(0);
        twosPerLayer.add(0);
    }

    public int getNolayerCells() {
        return nolayerCells;
    }

    public void answerTaskOne() {
        System.out.println("Min value of zeros is: " + Collections.min(zeroesPerLayer));
        System.out.println("Found in layer with index: " + zeroesPerLayer.indexOf(Collections.min(zeroesPerLayer)));
        System.out.println("Number of ones at this index is: "  + onesPerLayer.get(zeroesPerLayer.indexOf(Collections.min(zeroesPerLayer))));
        System.out.println("Number of twos at this index is: "  +twosPerLayer.get(zeroesPerLayer.indexOf(Collections.min(zeroesPerLayer))));
        System.out.println("Their product is: " + onesPerLayer.get(zeroesPerLayer.indexOf(Collections.min(zeroesPerLayer))) * twosPerLayer.get(zeroesPerLayer.indexOf(Collections.min(zeroesPerLayer))));
    }

    public void answerTaskTwo() {
        System.out.println("Answer for Task 2 is as follows: ");
        for (int r = 0; r < noRows; r++) {
            for (int c = 0; c < noCols; c ++) {
                if (visibleArray.get((r*noCols) + c) == 1){
                    rowString += "#";
                } else {
                    rowString += " ";
                }
            }
            System.out.println(rowString);
            rowString = " ";
        }
    }
}
