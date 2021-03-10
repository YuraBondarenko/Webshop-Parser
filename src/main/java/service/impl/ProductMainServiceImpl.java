package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Product;
import org.jsoup.nodes.Document;
import service.MainService;
import service.ParserService;
import util.DocumentUtil;

public class ProductMainServiceImpl implements MainService<Product> {
    private static final int MAX_LIST_SIZE = 100;
    private static final int FIRST_PAGE = 1;
    private static final String PAGE = "?p=";
    private final ParserService<Product> parserService;

    public ProductMainServiceImpl(ParserService<Product> parserService) {
        this.parserService = parserService;
    }

    public List<Product> getModelList(String url) {
        List<Product> products = new ArrayList<>();
        int page = FIRST_PAGE;
        while (products.size() < MAX_LIST_SIZE) {
            Document document = DocumentUtil.getDocument(url + PAGE + page++);
            products.addAll(parserService.parser(document));
        }
        return products;
    }

    public String getStringFromModel(List<Product> models) {
        return models.stream()
                .map(Product::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
