package com.aniketvishal.hastalavista.Data;

import com.aniketvishal.hastalavista.model.MainCategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aniketvishal on 27/11/17.
 */

public class Cat4Data {

    //-------------Main Words Category--------------
    public static List<MainCategoryModel> getCategoryData(){
        List<MainCategoryModel> data = new ArrayList<>();

                data.add(new MainCategoryModel("Eliza Bakshi"));
                data.add(new MainCategoryModel("Labony Bhunia"));
                data.add(new MainCategoryModel("Arindam Chattaraj"));
                data.add(new MainCategoryModel("Subhabrata Das"));
                data.add(new MainCategoryModel("Kazi Amirul Islam"));
                data.add(new MainCategoryModel("Sayan Karmakar"));
                data.add(new MainCategoryModel("Sangeeta Maiti"));
                data.add(new MainCategoryModel("Ritubrita Nag"));
                data.add(new MainCategoryModel("Devlina Pal"));
                data.add(new MainCategoryModel("Mahasweta Pal"));
                data.add(new MainCategoryModel("Chanda Pathak"));
                data.add(new MainCategoryModel("Samim Raja"));
                data.add(new MainCategoryModel("Nandini Sahu"));
                data.add(new MainCategoryModel("Nilanjana Sarkar"));
                data.add(new MainCategoryModel("Faizan Ahmed Siddiqui"));
                data.add(new MainCategoryModel("Pinki Singh"));
                data.add(new MainCategoryModel("Sunny Singh"));

        return data;
    }

}
