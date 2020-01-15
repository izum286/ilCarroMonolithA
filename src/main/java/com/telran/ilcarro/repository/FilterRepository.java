package com.telran.ilcarro.repository;

import com.telran.ilcarro.repository.entity.FilterNodeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Hashtable;

/**
 *  filter repository
 * @author izum286
 */
@Repository
public interface FilterRepository extends MongoRepository<FilterNodeEntity, String> {
//    Hashtable<String, FilterNodeEntity> hashArray = new Hashtable<>();
//
//    public int size(){
//        return hashArray.size();
//    }
//
//    public Collection<FilterNodeEntity> values(){
//        return hashArray.values();
//    }
//
//    public boolean containsKey(Object key){
//        return hashArray.containsKey(key);
//    }
//
//    public FilterNodeEntity put (String key, FilterNodeEntity value){
//        return hashArray.put(key, value);
//    }
//
//    public FilterNodeEntity get (String key){
//        return hashArray.get(key);
//    }


}
