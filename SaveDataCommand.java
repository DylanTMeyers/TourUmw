package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveDataCommand implements UserInputCommand {

    private String fileName;

    public SaveDataCommand(String fileName) {
        this.fileName = fileName;
    }

    public String carryOut() {

        createSaveFile(fileName);
        saveData(fileName);

        return "The file was saved successfully.\n";
    }

    public File createSaveFile(String fileName) {

        File myObj = new File(fileName);

        try {

            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (
                IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return myObj;
    }

    public void saveData(String fileName) {     //fileName = save file name     does overwrite file if same file is saved to more than once
        TourStatus tour = TourStatus.getInstance();

        try {
            FileWriter myWriter = new FileWriter(fileName);

            myWriter.write("SAVE FILE" + "\n");
            myWriter.write("Data file:" + tour.getCampus().getFilename() + "\n");
            myWriter.write("*****" + "\n");
            myWriter.write("Location states:" + "\n");

            myWriter.write(tour.getCampus().locationDataForSaveFile());

            myWriter.write("*****" + "\n");
            myWriter.write("Visitor:" + "\n");
            myWriter.write("current location:" + tour.getCurrentLocation().getName() + "\n");

            myWriter.write("backpack:" + tour.bpDataForSaveFile() + "\n");
            myWriter.write("*****");

            myWriter.close();
            System.out.println("Successfully wrote to the file using " + tour.getCampus().getFilename() + " as the data file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
