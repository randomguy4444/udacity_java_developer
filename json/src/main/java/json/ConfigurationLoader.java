package json;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * A static utility class that loads a JSON configuration file.
 */
public final class ConfigurationLoader {

    private final Path path;

    /**
     * Create a {@link ConfigurationLoader} that loads configuration from the given {@link Path}.
     */
    public ConfigurationLoader(Path path) {
        this.path = Objects.requireNonNull(path);
    }

    /**
     * Loads configuration from this {@link ConfigurationLoader}'s path
     *
     * @return the loaded {@link CrawlerConfiguration}.
     */
    public CrawlerConfiguration load() {
        // TODO: Fill in this method.
        try (Reader reader = Files.newBufferedReader(path)) {
            return read(reader);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        //return new CrawlerConfiguration.Builder().build();
    }

    /**
     * Loads crawler configuration from the given reader.
     *
     * @param reader a Reader pointing to a JSON string that contains crawler configuration.
     * @return a crawler configuration
     */
    public static CrawlerConfiguration read(Reader reader) {
        // TODO: Fill in this method
        // This is here to get rid of the unused variable warning.
        Objects.requireNonNull(reader);
        // TODO: Fill in this method
        // https://javadoc.io/doc/com.fasterxml.jackson.core/jackson-databind/2.3.1/com/fasterxml/jackson/databind/ObjectMapper.html
        // https://www.baeldung.com/jackson-object-mapper-tutorial
        ObjectMapper mapper = new ObjectMapper();
        // https://fasterxml.github.io/jackson-core/javadoc/2.7/com/fasterxml/jackson/core/JsonParser.Feature.html#:~:text=AUTO_CLOSE_SOURCE,-public%20static%20final&text=JsonParser.Feature%20AUTO_CLOSE_SOURCE-,Feature%20that%20determines%20whether%20parser%20will%20automatically%20close%20underlying%20input,used%20to%20create%20the%20parser.
        mapper.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);

        try {
            CrawlerConfiguration config;
            config = mapper.readValue(reader, CrawlerConfiguration.Builder.class).build();
            return config;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        //return new CrawlerConfiguration.Builder().build();
    }
}
