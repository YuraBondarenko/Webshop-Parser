import java.util.List;
import model.Product;
import service.FileWriterService;
import service.MainService;
import service.ParserService;
import service.impl.FileWriterServiceImpl;
import service.impl.ProductMainServiceImpl;
import service.impl.ProductParserServiceImpl;

public class Main {
    private static final String fileName = "src/main/resources/products.csv";
    private static final String CULTURE_URL
            = "https://allegro.pl/kategoria/kultura-i-rozrywka?string=bargain_zone&bmatch=e2101-d3720-c3682-cul-1-4-0304";
    private static final String ELECTRONICS_URL
            = "https://allegro.pl/kategoria/elektronika?string=bargain_zone&bmatch=cl-e2101-d3720-c3682-ele-1-4-0304";
    private static final String SUPERMARKET_URL
            = "https://allegro.pl/kategoria/supermarket?string=bargain_zone&bmatch=e2101-d3720-c3682-sup-1-4-0304";

    public static void main(String[] args) {
        ParserService<Product> parserService = new ProductParserServiceImpl();
        FileWriterService fileWriterService = new FileWriterServiceImpl();

        MainService<Product> mainService = new ProductMainServiceImpl(parserService);

        List<Product> cultureProducts = mainService.getModelList(CULTURE_URL);
        String culture = mainService.getStringFromModel(cultureProducts);
        fileWriterService.write(culture, fileName);

        List<Product> electronicProducts = mainService.getModelList(ELECTRONICS_URL);
        String electronic = mainService.getStringFromModel(electronicProducts);
        fileWriterService.write(electronic, fileName);

        List<Product> supermarketProducts = mainService.getModelList(SUPERMARKET_URL);
        String supermarketString = mainService.getStringFromModel(supermarketProducts);
        fileWriterService.write(supermarketString, fileName);
    }
}
