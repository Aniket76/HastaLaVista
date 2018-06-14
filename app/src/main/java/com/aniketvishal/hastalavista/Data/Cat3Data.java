package com.aniketvishal.hastalavista.Data;

import com.aniketvishal.hastalavista.model.MainCategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aniketvishal on 27/11/17.
 */

public class Cat3Data {

    //-------------Main Words Category--------------
    public static List<MainCategoryModel> getCategoryData(){
        List<MainCategoryModel> data = new ArrayList<>();

                data.add(new MainCategoryModel("Abhiroop Bhattacharya"));
                data.add(new MainCategoryModel("Sayan Biswas"));
                data.add(new MainCategoryModel("Shaket Ganguly"));
                data.add(new MainCategoryModel("Sneha Bhattacharjee"));
                data.add(new MainCategoryModel("Siddhartha Guha"));
                data.add(new MainCategoryModel("Divyansh Kumar Jain"));
                data.add(new MainCategoryModel("Nayan Jain"));
                data.add(new MainCategoryModel("Md. Salekin Jamal"));
                data.add(new MainCategoryModel("Satabhisa Roy"));
                data.add(new MainCategoryModel("Setanjan Roy"));
                data.add(new MainCategoryModel("Diprodeb Saha"));
                data.add(new MainCategoryModel("Fiza Tarannum"));
                data.add(new MainCategoryModel("Alok Tiwari"));
                data.add(new MainCategoryModel("Tripti Tiwari"));

        return data;
    }

}
