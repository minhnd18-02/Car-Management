/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import com.BrandList;
import com.CarList;
import com.Menu;
import com.Validator;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class CarManagement {

    public static void main(String[] args) {
        BrandList brandList = new BrandList();
        brandList.loadFromFile("brands.txt");

        CarList carList = new CarList(brandList);
        carList.loadFromFile("cars.txt");

        int choice;
        Menu<String> menu = new Menu<String>();

        ArrayList<String> optionsList = new ArrayList<String>();
        optionsList.add("List all brands");
        optionsList.add("Add a new brand");
        optionsList.add("Search a brand based on its ID");
        optionsList.add("Update a brand");
        optionsList.add("Save brands to the file, named brands.txt");
        optionsList.add("List all cars in ascending order of brand names");
        optionsList.add("List cars based on a part of an input brand name");
        optionsList.add("Add a car");
        optionsList.add("Remove a car based on its ID");
        optionsList.add("Update a car based on its ID");
        optionsList.add("Save cars to file, named cars.txt");
        optionsList.add("Quit");

        do {
            System.out.println("----------------------------------------");
            System.out.println("              CAR MANAGER               ");
            System.out.println("----------------------------------------");
            choice = menu.int_getChoice(optionsList);

            switch (choice) {
                case 1:
                    brandList.listBrands();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    String brandId = Validator.inputString();
                    int index = brandList.searchID(brandId);
                    if (index == -1) {
                        System.out.println("Not Found");
                    } else {
                        System.out.println(brandList.get(index));
                    }
                    break;
                case 4:
                    brandList.updateBrand();

                    // Do a car brand update.
                    carList.updateCarBrand();
                    break;
                case 5:
                    brandList.saveToFile("brands.txt");
                    break;
                case 6:
                    carList.listCars();
                    break;
                case 7:
                    carList.printBasedBrandName();
                    break;
                case 8:
                    carList.addCar();
                    break;
                case 9:
                    carList.removeCar();
                    break;
                case 10:
                    carList.updateCar();
                    break;
                case 11:
                    carList.saveToFile("cars.txt");
                    break;
                case 12:
                    return;
            }
        } while (choice != 12);
    }
}
