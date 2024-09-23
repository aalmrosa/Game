/* 23/09/2024 - Gust
 * Parser for YAML files. Chose to use YAML files for simplicity and readability of data for humans.
 * Last updated: 23/09/2024 - Gust
 */

package com.game.demo.logic;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


public class YamlReader {

    //chose to set mapper as public and static so that it can be referred from anywhere in the application
    public static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    /**
     * Defines properties for the parser.
     */
    public static void build(){
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
