package com.mbn.elkhodary.javaclasses;

import android.util.Log;

import com.mbn.elkhodary.model.CategoryList;
import com.mbn.elkhodary.model.Variation;
import com.mbn.elkhodary.utils.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Bhumi Shah on 11/29/2017.
 */

public class CheckIsVariationAvailable {
    private List<CategoryList.Attribute> categoryList = new ArrayList<>();

    private       int    tempPosition = -1;
    public static String pricehtml;
    public static float  price;
    public static float  regularPrice, salePrice;
    public static float   taxPrice;
    public static int     stockQuantity;
    public static boolean inStock       = false;
    public static String  imageSrc;
    public static boolean isManageStock = false;
    List<CategoryList.Attribute> tempAttrList = new ArrayList<>();
    public boolean isAnyAddedInList = false;
    List<Variation> attributeList = new ArrayList<>();


    public boolean isVariationAvailbale(Map<Integer, String> combination, List<Variation> variationList, List<CategoryList.Attribute> list) {
        categoryList = list;
        String comb = "";


        for (int i = 0; i < combination.size(); i++) {
            if (comb.equals("")) {
                comb = comb + combination.get(i);
            } else {
                if (combination.get(i) != null) {
                    if (!combination.get(i).equals(""))
                        comb = comb + "!" + combination.get(i);
                }
            }
        }


        if (getVariationList(variationList, comb, list).size() > 0) {
            return true;
        }
        return false;
    }


    public List<CategoryList.Attribute> getVariationList(List<Variation> variationList, String name, List<CategoryList.Attribute> catlist) {
        categoryList = catlist;
        attributeList = variationList;
        tempAttrList = new ArrayList<>();
        isAnyAddedInList = false;
        for (int i = 0; i < variationList.size(); i++) {

            if (containOption(variationList.get(i).attributes, name)) {
                for (int j = 0; j < categoryList.size(); j++) {
                    try {
                        if (containsTempList(tempAttrList, variationList.get(i).attributes.get(j).name)) {
                            if (!tempAttrList.get(tempPosition).options.contains(variationList.get(i).attributes.get(j).option))
                                tempAttrList.get(tempPosition).options.add(variationList.get(i).attributes.get(j).option);
                        } else {
                            CategoryList.Attribute object = new CategoryList().getAttributeInstance();
                            object.id = variationList.get(i).attributes.get(j).id;
                            object.name = variationList.get(i).attributes.get(j).name;
                            List<String> optionList = new ArrayList<>();
                            optionList.add(variationList.get(i).attributes.get(j).option);
                            object.options = optionList;
                            List<CategoryList.NewOption> newOptionList = new ArrayList<>();
                            for (int k = 0; k < categoryList.get(j).newOptions.size(); k++) {
                                CategoryList.NewOption newOption = new CategoryList().getNewOptionInstance();
                                if (categoryList.get(j).options.get(k).contains(variationList.get(i).attributes.get(j).option)) {
                                    newOption.variationName = categoryList.get(j).newOptions.get(k).variationName;
                                    newOption.color = categoryList.get(j).newOptions.get(k).color;
                                    newOption.image = categoryList.get(j).newOptions.get(k).image;
                                    newOptionList.add(newOption);
                                }
                            }
                            object.newOptions = newOptionList;
                            tempAttrList.add(object);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        if (!tempAttrList.contains(categoryList.get(j)))
                            tempAttrList.add(categoryList.get(j));
                    }

                }
            }
        }

        return tempAttrList;
    }

//    public boolean containsTempList(List<CategoryList.Attribute> list, long id) {
//
//        tempPosition = -1;
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).id == id) {
//                tempPosition = i;
//                return true;
//            }
//        }
//        tempPosition = -1;
//        return false;
//    }

    public boolean containsTempList(List<CategoryList.Attribute> list, String id) {

        tempPosition = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).name.equals(id)) {
                tempPosition = i;
                return true;
            }
        }
        tempPosition = -1;
        return false;
    }

    public boolean containOption(List<Variation.Attribute> list, String name) {
        String[] nameArray = name.split("!");
        if (nameArray.length > 0) {
            for (int i = 0; i < nameArray.length; i++) {
                if (!isVariationContain(list, nameArray[i])) {
                    for (int j = 0; j < list.size(); j++) {
                        String combinationValue = list.get(j).name + "->" + list.get(j).option;
                        if (!name.contains(combinationValue) &&name.contains(list.get(j).name )) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    public boolean isVariationContain(List<Variation.Attribute> list, String name) {
        for (int j = 0; j < categoryList.size(); j++) {
            try {
                if ((list.get(j).name + "->" + list.get(j).option).equals(name)) {
                    return true;
                }
            } catch (IndexOutOfBoundsException e) {
                for (int i = 0; i < categoryList.get(j).options.size(); i++) {
                    if ((categoryList.get(j).name + "->" + categoryList.get(j).options.get(i)).equals(name)) {
                        return true;
                    } else if (!name.contains(categoryList.get(j).name)) {
                        if (!isAttributeContain(name)) {
                            if (!isAnyAddedInList) {
                                if (name.contains("->")) {
                                    Log.e("Any Varation ", "Called");
                                    String[] array = name
                                            .split("->");
                                    if (array.length > 0) {
                                        CategoryList.Attribute object = new CategoryList().getAttributeInstance();
                                        object.id = 0;
                                        object.name = array[0];
                                        List<String> optionList = new ArrayList<>();
                                        optionList.add(array[1]);
                                        object.options = optionList;
                                        tempAttrList.add(object);
                                    }
                                }
                                isAnyAddedInList = true;
                            }
                            return true;
                        }

                    }
                }
            }
        }


        return false;
    }


    public boolean isAttributeContain(String name) {
        int count = 0;
        for (int i = 0; i < this.attributeList.size(); i++) {
            for (int j = 0; j < attributeList.get(i).attributes.size(); j++) {
                if (name.contains(this.attributeList.get(i).attributes.get(j).name)) {
                    return true;
                }
            }

        }
        return false;
    }

    public int getVariationid(List<Variation> variationList, List<String> selectAttribute) {

        int count = 0;
        for (int i = 0; i < variationList.size(); i++) {
            count = 0;
            for (int j = 0; j < variationList.get(i).attributes.size(); j++) {

                if (selectAttribute.contains(variationList.get(i).attributes.get(j).name + "->" + variationList.get(i).attributes.get(j).option)) {
                    count = count + 1;
                }
            }

            if (count == variationList.get(i).attributes.size() && count != 0) {
                pricehtml = variationList.get(i).priceHtml;
                stockQuantity = variationList.get(i).stockQuantity;
                imageSrc = variationList.get(i).image.src;
                isManageStock = variationList.get(i).manageStock;
                inStock = variationList.get(i).inStock;
                if (!variationList.get(i).price.equals("")) {
                    price = Float.parseFloat(getPrice(variationList.get(i).price));
                    if (!variationList.get(i).regularPrice.equals("") && variationList.get(i).regularPrice != null) {
                        regularPrice = Float.parseFloat(getPrice(variationList.get(i).regularPrice));
                    }

                    if (!variationList.get(i).salePrice.equals("") && variationList.get(i).salePrice != null) {
                        salePrice = Float.parseFloat(getPrice(variationList.get(i).salePrice));
                    } else {
                        salePrice = 0;
                    }

                } else {
                    price = 0;
                    if (!variationList.get(i).regularPrice.equals("") && variationList.get(i).regularPrice != null) {
                        regularPrice = Float.parseFloat(getPrice(variationList.get(i).regularPrice));
                    }

                    if (!variationList.get(i).salePrice.equals("") && variationList.get(i).salePrice != null) {
                        salePrice = Float.parseFloat(getPrice(variationList.get(i).salePrice));
                    } else {
                        salePrice = 0;
                    }

                }
                if (variationList.get(i).taxPrice != null && !variationList.get(i).taxPrice.equals("") && variationList.get(i).taxStatus.equals("taxable")) {
                    try {

                        taxPrice = Float.parseFloat(getPrice(variationList.get(i).taxPrice));

                    } catch (Exception e) {
                        Log.e("Exception is ", e.getMessage());
                        taxPrice = 0;
                    }
                } else {
                    taxPrice = 0;
                }

                return variationList.get(i).id;
            } else if (count == 0 && variationList.get(i).attributes.size() == 0) {
                pricehtml = variationList.get(i).priceHtml;
                inStock = variationList.get(i).inStock;
                stockQuantity = variationList.get(i).stockQuantity;
                imageSrc = variationList.get(i).image.src;
                isManageStock = variationList.get(i).manageStock;
                if (!variationList.get(i).price.equals("")) {
                    price = Float.parseFloat(getPrice(variationList.get(i).price));

                    if (!variationList.get(i).regularPrice.equals("") && variationList.get(i).regularPrice != null) {
                        regularPrice = Float.parseFloat(getPrice(variationList.get(i).regularPrice));
                    }

                    if (!variationList.get(i).salePrice.equals("") && variationList.get(i).salePrice != null) {
                        salePrice = Float.parseFloat(getPrice(variationList.get(i).salePrice));
                    } else {
                        salePrice = 0;
                    }

                } else {
                    price = 0;
                    if (!variationList.get(i).regularPrice.equals("")) {
                        regularPrice = Float.parseFloat(getPrice(variationList.get(i).regularPrice));
                    }

                    if (!variationList.get(i).salePrice.equals("") && variationList.get(i).salePrice != null) {
                        salePrice = Float.parseFloat(getPrice(variationList.get(i).salePrice));
                    } else {
                        salePrice = 0;
                    }

                }
                if (variationList.get(i).taxPrice != null && !variationList.get(i).taxPrice.equals("") && variationList.get(i).taxStatus.equals("taxable")) {
                    try {
                        taxPrice = Float.parseFloat(getPrice(variationList.get(i).taxPrice));
                    } catch (Exception e) {
                        Log.e("Exception is ", e.getMessage());
                        taxPrice = 0;
                    }
                } else {
                    taxPrice = 0;
                }

            }

        }


        return 0;
    }

    public String getPrice(String price) {
        price = price.replace("\\s+", "");
        price = price.replace(Constant.THOUSANDSSEPRETER, "");
        return price;
    }
}
