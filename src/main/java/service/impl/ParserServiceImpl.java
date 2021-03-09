package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Product;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import service.ParserService;

public class ParserServiceImpl implements ParserService {
    private static Long id = 1L;
    private static final int MAX_ID = 100;
    private static final String TITLE
            = "h2[class=mgn2_14 m9qz_yp mqu1_16 mp4t_0 m3h2_0 mryx_0 munh_0]";
    private static final String DISCOUNT = "span[class=_9c44d_1uHr2]";
    private static final String QUANTITY_OF_PRODUCT_FOR_DISCOUNT
            = "span[class=mpof_uk mqu1_ae _9c44d_18kEF m9qz_yp _9c44d_2BSa0  ]";
    private static final String ARTICLE = "article[data-item=true]";
    private static final String PURCHASES = "span[class=msa3_z4]";
    private static final String URL
            = "div[class=mpof_ki myre_zn m389_6m m09p_k4 mse2_56 _9c44d_2Tos9]";
    private static final String PRICE_WITH_SHIPPING = "div[class=mqu1_g3]";
    private static final String PRICE = "span[class=_1svub _lf05o]";
    private static final String PRICE_WITHOUT_DISCOUNT
            = "span[class=mpof_uk mqu1_ae _9c44d_18kEF m9qz_yp _9c44d_2BSa0  _9c44d_KrRuv]";
    private static final String DELIVERY
            = "span[class=mpof_uk mqu1_ae _9c44d_18kEF _9c44d_3gH36 m9qz_yq ]";
    private static final String CATEGORY = "div[class=_17qy1 _bxr46 _d2756_GNE_S _d2756_2aXO4]";
    private static final String FILTER_1 = "%";
    private static final String FILTER_2 = "wyprzedaż";
    private static final int ZERO = 0;
    private static final int PRODUCT_URL = 0;
    private static final String URL_REGEX = "(https):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?+-=\\\\.&]*";
    private static final String NUMBER_REGEX = "[^\\d,]";
    private static final String PERCENT = "%";
    private static final String COMMA = ",";
    private static final String DOT = ".";

    @Override
    public List<Product> parser(Document document) {
        List<Product> products = new ArrayList<>();
        String category = document.select(CATEGORY).text();
        Elements productElements = document.select(ARTICLE);

        for (Element element : productElements) {
            String discount = element.select(DISCOUNT).text();
            if ((discount.contains(FILTER_1) || discount.contains(FILTER_2))
                    && id <= MAX_ID) {
                Product product = new Product();

                product.setId(id++);

                product.setCategory(category);

                product.setTitle(element.select(TITLE).text());

                product.setPrice(Double.parseDouble(element.select(PRICE).text()
                        .replaceAll(NUMBER_REGEX, "").replaceAll(COMMA, DOT)));

                String priceWithoutDiscount = element.select(PRICE_WITHOUT_DISCOUNT).text()
                        .replaceAll(NUMBER_REGEX, "").replaceAll(COMMA, DOT);
                if (priceWithoutDiscount.isEmpty()) {
                    setDiscount(product, discount);
                    product.setDiscount(product.getDiscount()
                            + element.select(QUANTITY_OF_PRODUCT_FOR_DISCOUNT).text());
                    product.setPriceWithoutDiscount(product.getPrice());
                } else {
                    product.setPriceWithoutDiscount(Double.parseDouble(priceWithoutDiscount));
                    setDiscount(product, discount);
                }

                String priceWithShipping = element.select(PRICE_WITH_SHIPPING).text()
                        .replaceAll(NUMBER_REGEX, "").replaceAll(COMMA, DOT);
                product.setPriceWithShipping(priceWithShipping.isEmpty() ? product.getPrice()
                        : Double.parseDouble(priceWithShipping));

                product.setCurrency(element.select(PRICE).text().replaceAll("[\\d, ]", ""));

                String numberOfPurchases = element.select(PURCHASES).text().replaceAll("\\D+", "");
                product.setNumberOfPurchases(numberOfPurchases.isEmpty() ? ZERO
                        : Integer.parseInt(numberOfPurchases));

                List<String> urls = getUrl(element.select(URL).first());
                product.setUrl(urls.get(PRODUCT_URL));

                product.setDelivery(element.select(DELIVERY).text());

                products.add(product);
            }
        }
        if (id >= MAX_ID) {
            id = 0L;
        }
        return products;
    }

    private List<String> getUrl(Element element) {
        String text = element.toString();
        List<String> containedUrls = new ArrayList<>();
        Pattern pattern = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find()) {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }
        return containedUrls;
    }

    private void setDiscount(Product product, String discount) {
        product.setDiscount(discount.contains(FILTER_1) ? discount
                .replaceAll("[^\\d,%]", "")
                : Math.round((1.0 - product.getPrice() / product.getPriceWithoutDiscount()) * 100)
                + PERCENT);
    }
}

