package com.telran.ilcarro.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedList;
import java.util.List;


public class FilterService {

    class FilterNode {
        ObjectMapper mapper = new ObjectMapper();
        FilterNode mainNode;
        FilterNode parent; //cars
        String type; //manuf
        String value; // mazda
        List<FilterNode> childs = new LinkedList<>();

        public String provideNode(){
            return "";
        }


        public void addNode(){

        }

    }

//    Map<String,  //make
//        Map<String, //model
//        Map<Integer, //year
//        Map<String, //engine
//        Map<String, //fuel
//        Map<String, //transmission
//        Map<String, //wd
//        Map<Integer, //hp
//        Map<Integer, //torque
//        Map<Integer, //Doors
//        Map<Integer, //Seats
//        Map<Class, //class
//        Set<String> //FuelConsumption
//                >>>>>>>>>>>> filter = new ConcurrentHashMap<>();

}
