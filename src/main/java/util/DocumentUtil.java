package util;

import exception.ConnectionException;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocumentUtil {
    public static Document getDocument(String url) {
        try {
            return Jsoup.connect(url).timeout(3000).get();
        } catch (IOException e) {
            throw new ConnectionException("Failed to create connection by url " + url, e);
        }
    }
}
