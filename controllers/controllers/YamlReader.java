package controllers;

import java.io.FileReader;
import java.io.Reader;
import java.util.Map;
import java.util.StringTokenizer;

import org.yaml.snakeyaml.Yaml;

public class YamlReader {

	static int initialcheck = 0;
    public String yamlFilePath = "dataFiles//dbConfig.yml";
    
	

	    /*public String setYamlFilePath(String filePath) {
	        yamlFilePath = filePath;
	        return filePath;
	    }*/
	    

	    @SuppressWarnings("rawtypes")
		public String getYamlValue(String yamlToken) {
	        Reader reader = null;
	        int tokenCount = 0, i = 0;
	        Map map = null;

	        StringTokenizer st = new java.util.StringTokenizer(yamlToken, ".");
	        try {
	            reader = new FileReader(yamlFilePath);
	            String val = null;
	            Yaml yaml = new Yaml();
	            map = (Map) yaml.load(reader);
	            tokenCount = st.countTokens();
	            for (i = 1; i < tokenCount; i++) {
	                String token = st.nextToken();
	                map = (Map) map.get(token);
	            }
	            val = map.get(st.nextToken()).toString();
	            return val;
	        } catch (Exception e) {
	            System.out.println("Yaml file not found!!!\n" + e);
	            return "";
	        }
	    }
	    
	
	
}
