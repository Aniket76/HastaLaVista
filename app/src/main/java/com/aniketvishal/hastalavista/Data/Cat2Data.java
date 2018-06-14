package com.aniketvishal.hastalavista.Data;

import com.aniketvishal.hastalavista.R;
import com.aniketvishal.hastalavista.model.MainCategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aniketvishal on 27/11/17.
 */

public class Cat2Data {

    //-------------Main Words Category--------------
    public static List<MainCategoryModel> getCategoryData(){
        List<MainCategoryModel> data = new ArrayList<>();

                data.add(new MainCategoryModel("Debarghya Basu"));
                data.add(new MainCategoryModel("Susweta Adhikary"));
                data.add(new MainCategoryModel("Sunanda Biswas"));
                data.add(new MainCategoryModel("Sayak Chattopadhyay"));
                data.add(new MainCategoryModel("Satyaki Das"));
                data.add(new MainCategoryModel("Shatadipta Ghosh"));
                data.add(new MainCategoryModel("Suman Kapat"));
                data.add(new MainCategoryModel("Akask Shaw"));
                data.add(new MainCategoryModel("Awadhesh Kumar"));
                data.add(new MainCategoryModel("Vinesh Kumar"));
                data.add(new MainCategoryModel("Anik Sengupta"));
                data.add(new MainCategoryModel("Kewal Sharma"));
                data.add(new MainCategoryModel("Himanshu Shukla"));

        return data;
    }

}
