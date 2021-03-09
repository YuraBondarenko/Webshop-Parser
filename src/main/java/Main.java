import java.util.List;
import model.Product;
import service.FileWriterService;
import service.MainService;
import service.ParserService;
import service.impl.FileWriterServiceImpl;
import service.impl.MainServiceImpl;
import service.impl.ParserServiceImpl;

public class Main {
    private static final String fileName = "src/main/resources/products.csv";
    private static final String CULTURE_URL
            = "https://allegro.pl/kategoria/kultura-i-rozrywka?string=bargain_zone&bmatch=e2101-d3720-c3682-cul-1-4-0304";
    private static final String ELECTRONICS_URL
            = "https://allegro.pl/kategoria/elektronika?string=bargain_zone&bmatch=cl-e2101-d3720-c3682-ele-1-4-0304";
    private static final String SUPERMARKET_URL
            = "https://allegro.pl/kategoria/supermarket?string=bargain_zone&bmatch=e2101-d3720-c3682-sup-1-4-0304";

    public static void main(String[] args) {
        ParserService parserService = new ParserServiceImpl();
        FileWriterService fileWriterService = new FileWriterServiceImpl();

        MainService mainServiceImpl = new MainServiceImpl(parserService);

        List<Product> cultureProducts = mainServiceImpl.getProducts(CULTURE_URL);
        String culture = mainServiceImpl.getStringFromProducts(cultureProducts);
        fileWriterService.write(culture, fileName);

        List<Product> electronicProducts = mainServiceImpl.getProducts(ELECTRONICS_URL);
        String electronic = mainServiceImpl.getStringFromProducts(electronicProducts);
        fileWriterService.write(electronic, fileName);

        List<Product> supermarketProducts = mainServiceImpl.getProducts(SUPERMARKET_URL);
        String supermarketString = mainServiceImpl.getStringFromProducts(supermarketProducts);
        fileWriterService.write(supermarketString, fileName);
    }
}
