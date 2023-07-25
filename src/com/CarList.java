/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author ADMIN
 */
public class CarList extends ArrayList<Car> {

    private BrandList brandList;

    public CarList(BrandList brandList) {
        this.brandList = brandList;
    }

    public boolean loadFromFile(String fname) {
        // Clear the array if it is not null
        /*        if (this.size() > 0) {
        this.clear();
        }*/

        // Try to read file
        try {
            File file = new File(fname);
            if (!file.exists()) {
                System.out.println("File does not exist");
                return false;
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            // Extract data
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ", ");
                String carID = stk.nextToken().toUpperCase();
                String brandId = stk.nextToken().toUpperCase();
                String color = stk.nextToken().toUpperCase();
                String frameID = stk.nextToken().toUpperCase();
                String engineID = stk.nextToken().toUpperCase();
                

                int index = brandList.searchID(brandId);
                Brand brand = brandList.get(index);

                Car car = new Car(carID, brand, color, frameID, engineID);
                this.add(car);
            }

            // Close File
            bufferedReader.close();
            fileReader.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    public boolean saveToFile(String fname) {
        if (this.size() == 0) {
            System.out.println("There is no car");
            return false;
        }
        try {
            File file = new File(fname);
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Car car : this) {
                printWriter.println(car.getCarId() + ", " + car.getBrand().getBrandId() + ", " + car.getColor() + ", "
                        + car.getFrameID() + ", " + car.getEngineID());
            }

            printWriter.close();
            fileWriter.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("SAVE FILE SUCCESSFULLY");
        return true;
    }

    public int searchID(String carID) {
        for (int index = 0; index < this.size(); index++) {
            if (this.get(index).getCarId().equals(carID)) {
                return index;
            }
        }
        return -1;
    }

    public int searchFrame(String frameID) {
        for (int index = 0; index < this.size(); index++) {
            if (this.get(index).getFrameID().equals(frameID)) {
                return index;
            }
        }
        return -1;
    }

    public int searchEngine(String engineID) {
        for (int index = 0; index < this.size(); index++) {
            if (this.get(index).getEngineID().equals(engineID)) {
                return index;
            }
        }
        return -1;
    }

    public void addCar() {
        System.out.println("------ADD CAR------");
        String carID;

        System.out.println("Please enter a Car ID: ");
        carID = Validator.inputString();

        if (searchID(carID) != -1) {
            System.out.println("Car ID cannot be duplicated!");
            return;
        }

        Menu<Brand> menu = new Menu<Brand>();
        Brand brand = menu.ref_getChoice(brandList);

        System.out.println("Please enter a color: ");
        String color = Validator.inputString();
        String frameID;
        do {
            frameID = Validator.inputFrame();
            if (searchFrame(frameID) == -1) {
                break;
            }
            System.out.println("Duplicate Frame ID! Try again.");
        } while (true);

        String engineID;
        do {
            engineID = Validator.inputEngine();
            if (searchEngine(engineID) == -1) {
                break;
            }
            System.out.println("Duplicate Engine ID! Try again.");
        } while (true);

        Car car = new Car(carID, brand, color, frameID, engineID);
        this.add(car);
    }

    public void printBasedBrandName() {
        System.out.println("------PRINT BASED ON BRAND NAME------");
        System.out.println("Please enter a part of brand name:");
        String brandNamePartial = Validator.inputString();
        int count = 0;

        for (Car car : this) {
            if (car.getBrand().getBrandName().contains(brandNamePartial)) {
                System.out.println(car.screenString());
                count = count + 1;
            }
        }

        if (count == 0) {
            System.out.println("No car is detected!");
        }
    }

    public boolean removeCar() {
        System.out.println("------REMOVE CAR------");
        if (this.isEmpty()) {
            System.out.println("There is no car to remove!");
            return false;
        }

        System.out.println("Please enter a removed ID");
        String removedID = Validator.inputString();

        int index = searchID(removedID);
        if (index == -1) {
            System.out.println("Not found!");
            return false;
        } else {
            this.remove(index);
            System.out.println("Remove ID " + removedID + " successfully!");
        }
        return true;
    }

    public boolean updateCar() {
        System.out.println("------UPDATE CAR------");
        if (this.isEmpty()) {
            System.out.println("There is no car to update!");
            return false;
        }

        System.out.println("Please entr a Car ID");
        String carID = Validator.inputString();

        int index = searchID(carID);
        if (index == -1) {
            System.out.println("Not found!");
            return false;
        } else {
            Menu<Brand> menu = new Menu<Brand>();
            Brand brand = menu.ref_getChoice(brandList);

            System.out.println("Please enter a color: ");
            String color = Validator.inputString();
            String frameID;
            String engineID;

            do {
                frameID = Validator.inputFrame();
                if (searchFrame(frameID) != -1 && !this.get(index).getFrameID().equals(frameID)) {
                    System.out.println("FrameID cannot be duplicated!");
                    continue;
                }
                break;
            } while (true);

            do {
                engineID = Validator.inputEngine();
                if (searchEngine(engineID) != -1 && !this.get(index).getEngineID().equals(engineID)) {
                    System.out.println("EngineID cannot be duplicated!");
                    continue;
                }
                break;
            } while (true);

            Car car = new Car(carID, brand, color, frameID, engineID);
            this.set(index, car);
        }
        return true;
    }

    public void listCars() {
        System.out.println("------LIST CAR------");
        if (this.isEmpty()) {
            System.out.println("There is no car.");
            return;
        }
        Collections.sort(this);
        for (Car car : this) {
            System.out.println(car.screenString());
        }
    }

    // Use to update the car brand based on brand ID when using brandList.updateBrand()
    // As the car brand is parameterized at the beginning of the program.
    public void updateCarBrand() {
        if (this.isEmpty()) {
            return;
        }
        for (Car car : this) {
            String brandId = car.getBrand().getBrandId();
            int index = brandList.searchID(brandId);
            Brand brand = brandList.get(index);
            car.setBrand(brand);
        }
    }
}
