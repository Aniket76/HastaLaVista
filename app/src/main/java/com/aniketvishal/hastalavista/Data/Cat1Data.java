package com.aniketvishal.hastalavista.Data;

import com.aniketvishal.hastalavista.model.MainCategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aniketvishal on 27/11/17.
 */

public class Cat1Data {

    //-------------Main Words Category--------------
    public static List<MainCategoryModel> getCategoryData(){
        List<MainCategoryModel> data = new ArrayList<>();

                data.add(new MainCategoryModel("Sourav Agarwal"));
                data.add(new MainCategoryModel("Ankit Aich"));
                data.add(new MainCategoryModel("Harsh Bharvada"));
                data.add(new MainCategoryModel("Rudranil Bhowmik"));
                data.add(new MainCategoryModel("Sampriti Barman"));
                data.add(new MainCategoryModel("Modhurima Bhowmik"));
                data.add(new MainCategoryModel("Sreejita Brahma"));
                data.add(new MainCategoryModel("Somrita Das"));
                data.add(new MainCategoryModel("Priya Dey"));
                data.add(new MainCategoryModel("Neha Joshi"));
                data.add(new MainCategoryModel("Sudipti Kiran"));
                data.add(new MainCategoryModel("Sayan Mukherjee"));
                data.add(new MainCategoryModel("Fatima Nazar"));
                data.add(new MainCategoryModel("Vivek Prakash"));
                data.add(new MainCategoryModel("Amitesh Kumar Upadhyay"));

        return data;
    }

}
