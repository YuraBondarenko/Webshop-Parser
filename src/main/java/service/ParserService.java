package service;

import java.util.List;
import org.jsoup.nodes.Document;

public interface ParserService<T> {
    List<T> parser(Document document);
}
