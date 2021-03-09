package service;

import java.util.List;
import model.Product;
import org.jsoup.nodes.Document;

public interface ParserService {
    List<Product> parser(Document document);
}
