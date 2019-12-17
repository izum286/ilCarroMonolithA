package com.telran.ilcarro.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.telran.ilcarro.service.model.FilterNode;

import java.io.IOException;

/**
 * Class for custom serialization by ObjectMapper
 * why do we need this: See explanation aboveL:
 * By using native Jackson mapping we got this node:
 * "octavia" : {
 *           "type" : "model",
 *           "value" : "octavia",
 *           "childs" : {
 *             "fwd" : {
 *               "type" : "wd",
 *               "value" : "fwd",
 *               "childs" : {
 *                 "150" : {
 *                   "type" : "hp",
 *                   "value" : "150",
 *                   "childs" : {
 *                     "250" : {
 *                       "type" : "torque",
 *                       "value" : "250",
 *                       "childs" : {
 *                         "5" : {
 *                           "type" : "doors",
 *                           "value" : "5",
 *                           "childs" : {
 *                             "4" : {
 *                               "type" : "seats",
 *                               "value" : "4",
 *                               "childs" : { }
 *
 *    But describing our own serializer we can transfer it to this:
 *    it's more simple to iterate thorough this json string
 *      "manuf" : [ {
 *     "key: " : "skoda",
 *     "model" : [ {
 *       "key: " : "octavia",
 *       "wd" : [ {
 *         "key: " : "fwd",
 *         "hp" : [ {
 *           "key: " : "150",
 *           "torque" : [ {
 *             "key: " : "250",
 *             "doors" : [ {
 *               "key: " : "5",
 *               "seats" : [ {
 *                 "key: " : "4"
 */
public class FilterNodeSerializer extends JsonSerializer<FilterNode> {
    @Override
    public void serialize(FilterNode filterNode, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject(); //<---start object
        jgen.writeStringField("key: ", filterNode.getValue());
        if(!filterNode.getChilds().isEmpty()){
            for(FilterNode node : filterNode.getChilds()) {
                jgen.writeArrayFieldStart(node.getType()); //<-- start NewJsonArray
                serialize(node,  jgen,                     //<-- recursive call serialize method
                        provider);
                jgen.writeEndArray();                       //<-- end NewJsonArray
            }
        }
        jgen.writeEndObject();  //<---end object
    }
}
