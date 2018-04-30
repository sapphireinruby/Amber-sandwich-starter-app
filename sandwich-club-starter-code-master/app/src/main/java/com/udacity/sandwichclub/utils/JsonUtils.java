package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {

            JSONObject jSandRoot = new JSONObject(json);
            //create a new obj, parse the first outer {} json obj

            JSONObject jSandNames = jSandRoot.getJSONObject("name");
            //create another new Obj, for parsing name{} json obj

            String mainName = jSandNames.optString("mainName");
            // parse mainName{} json obj inside "name" to get mainName string
            

            ArrayList<String> alsoKnownAs = new ArrayList<>();
            // create a new class for ArrayList
            // the data type <> after new can be omitted, it's the same as the one declares
            //在 new 後方的 <> 內資料型態是可以不用給的，因為與前面宣告的一樣所以可省略
            //其他像是 Arrays、Map、HashMap 等都可以使用此方法少打一點字
            
            JSONArray akaJsonArray = jSandNames.optJSONArray("alsoKnownAs");
            //pares the aka JStr from name{}, to JArr

            for (int i = 0; i < akaJsonArray.length(); i++) {
                alsoKnownAs.add(akaJsonArray.getString(i));
                //loop akaJsonArray, use .add() to add elements
                //use getString()for JArray to avoid problems
            }

            String placeOfOrigin = jSandRoot.optString("placeOfOrigin");
            // parse placeOfOrigin JObj to string

            String description = jSandRoot.optString("description");
            // parse description JObj to string

            String image = jSandRoot.optString("image");
            // parse JObj to string


            ArrayList<String> ingredients = new ArrayList<>();
            JSONArray indJsonArray = jSandRoot.optJSONArray("ingredients");
            for (int i = 0; i < indJsonArray.length(); i++) {
                ingredients.add(indJsonArray.getString(i));
            }


            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);


            

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    }


//Xxx getXxx(String name) : use key "name" to get json obj value, if not-> might cause problem.
//Xxx optXxx(String name) : use key "name" to get json obj value,if not-> return "" or default.

//  {
//
//   "name":{
//
//      "mainName":"Gua bao",
//      "alsoKnownAs":
//      [
//         "Steamed bao",
//         "Pork belly bun"
//      ]
//   },
//
//   "placeOfOrigin":"Taiwan",
//   "description":"a bunch of text",
//   "image":"a long url",
//
//   "ingredients":[
//      "Steamed bread",
//      "Stewed meat",
//      "Condiments"
//   ]
//  }