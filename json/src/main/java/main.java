import java.nio.file.Path;
import java.nio.file.Paths;

import json.ConfigurationLoader;
import json.CrawlerConfiguration;

public class main {
    public static void main(String[] args) throws Exception {
        // src/main/config/sample_config.json
        String path = "src/main/config/sample_config.json";
        //Path currentRelativePath = Paths.get("");
        //String s = currentRelativePath.toAbsolutePath().toString();
        //System.out.println(s);
        CrawlerConfiguration config = new ConfigurationLoader(Path.of(path)).load();
        System.out.println(config);
        System.out.println(config.getPopularWordCount());
        System.out.println(config.getProfileOutputPath());
        System.out.println(config.getIgnoredWords());
    }
}