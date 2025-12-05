package org.example.config;

import java.util.HashMap;
import java.util.Map;


public class ConfigurationManager {
    

    private static volatile ConfigurationManager instance;
    

    private final Map<String, String> configurations;
    
    private ConfigurationManager() {
        configurations = new HashMap<>();
        loadDefaultConfigurations();
    }
    
    public static ConfigurationManager getInstance() {

        if (instance == null) {

            synchronized (ConfigurationManager.class) {

                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    private void loadDefaultConfigurations() {
        configurations.put("app.name", "ShopTech E-Commerce");
        configurations.put("app.version", "1.0.0");
        configurations.put("max.cart.items", "50");
        configurations.put("currency", "MXN");
        configurations.put("tax.rate", "0.10");
    }
    

    public String getConfig(String key) {
        return configurations.get(key);
    }
    

    public void setConfig(String key, String value) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Configuration key cannot be null or empty");
        }
        configurations.put(key, value);
    }
    
    public Map<String, String> getAllConfigs() {
        return new HashMap<>(configurations);
    }

    public boolean hasConfig(String key) {
        return configurations.containsKey(key);
    }
}
