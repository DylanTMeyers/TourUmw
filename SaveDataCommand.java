package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class implements the UserInputCommand interface.
 * It creates Objects that handle saving a user's tour data to a .txt file.
 *
 * @author Tessa Lanzafame
 * @version 1.0
 */
public class SaveDataCommand implements UserInputCommand {

    private String fileName;

    /**
     * Non-default constructor for objects of class SaveDataCommand.
     *
     * @param fileName String referring to fileName
     */
    public SaveDataCommand(String fileName) {
        this.fileName = fileName;
    }

    public String carryOut() {

        createSaveFile(fileName);
        saveData(fileName);

        return "The file '" + fileName + "' was saved successfully.\n";
    }

    /**
     * Method that creates a file that will hold the user's tour data.
     *
     * @param fileName the name of the file
     * @return the file created
     */
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

    /**
     * Method that writes the user's tour data to a file.
     *
     * @param fileName the name of the save file
     */
    public void saveData(String fileName) {
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
            myWriter.write("*****" + "\n");

            myWriter.write("Quiz status:" + "\n");
            myWriter.write(tour.getCampus().quizDataForSaveFile());
            myWriter.write("*****");

            myWriter.close();
            System.out.println("Successfully wrote to the file using '" + tour.getCampus().getFilename() + "' as the data file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
