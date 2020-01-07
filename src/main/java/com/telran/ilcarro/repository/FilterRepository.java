package com.telran.ilcarro.repository;

import com.telran.ilcarro.service.model.FilterNode;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Hashtable;

/**
 * mock filter repository
 * @author izum286
 */
@Repository
public class FilterRepository {
    Hashtable<String, FilterNode> hashArray = new Hashtable<>();

    public int size(){
        return hashArray.size();
    }

    public Collection<FilterNode> values(){
        return hashArray.values();
    }

    public boolean containsKey(Object key){
        return hashArray.containsKey(key);
    }

    public FilterNode put (String key, FilterNode value){
        return hashArray.put(key, value);
    }

    public FilterNode get (String key){
        return hashArray.get(key);
    }


}
