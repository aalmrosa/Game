package com.game.demo.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlReader {
    public static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
}
