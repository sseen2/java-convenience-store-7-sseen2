package store.view;

import camp.nextstep.edu.missionutils.DateTimes;
import store.DateParser;
import store.domain.ConvenienceStore;
import store.domain.Product;
import store.domain.Promotion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResourceFileReadView {
    private static final String LINE_SPLIT_STRING = ",";
    private static final String PROMOTIONS_FILE_NAME = "promotions.md";
    private static final String PRODUCTS_FILE_NAME = "products.md";

    public static List<ConvenienceStore> fileRead(String fileName) {
        try (InputStream inputStream = ResourceFileReadView.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            return fileLineRead(bufferedReader, fileName);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    private static List<ConvenienceStore> fileLineRead(BufferedReader bufferedReader, String fileName) throws IOException {
        String fileLine;
        bufferedReader.readLine();
        List<ConvenienceStore> returnObject = new ArrayList<>();
        while ((fileLine = bufferedReader.readLine()) != null) {
            classificationFile(returnObject, fileName, List.of(fileLine.split(LINE_SPLIT_STRING)));
        }
        return returnObject;
    }

    private static void classificationFile(List<ConvenienceStore> returnObject, String fileName, List<String> inputData) {
        if (fileName.equals(PROMOTIONS_FILE_NAME)) {
            promotionsFileRead(returnObject, inputData);
        }
        if (fileName.equals(PRODUCTS_FILE_NAME)) {
            productsFileRead(returnObject, inputData);
        }
    }

    private static void promotionsFileRead(List<ConvenienceStore> returnObject, List<String> promotion) {
        String name = promotion.get(0);
        int buy = Integer.parseInt(promotion.get(1));
        int get = Integer.parseInt(promotion.get(2));
        LocalDate startDate = DateParser.parseDate(promotion.get(3));
        LocalDate endDate = DateParser.parseDate(promotion.get(4));

        if (isWithinPeriod(startDate, endDate)) {
            returnObject.add(new Promotion(name, buy, get, startDate, endDate));
        }
    }

    private static boolean isWithinPeriod(LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = DateTimes.now().toLocalDate();
        if ((currentDate.equals(startDate) || currentDate.isAfter(startDate))
            && (currentDate.equals(endDate) || currentDate.isBefore(endDate))) {
            return true;
        }
        return false;
    }

    private static void productsFileRead(List<ConvenienceStore> returnObject, List<String> product) {
        String name = product.get(0);
        int price = Integer.parseInt(product.get(1));
        int quantity = Integer.parseInt(product.get(2));
        String promotion = product.get(3);

        if (!hasProducts(returnObject, name, quantity, promotion)) {
            returnObject.add(new Product(name, price, quantity, promotion));
        }
    }

    private static boolean hasProducts(List<ConvenienceStore> returnObject, String name, int quantity, String promotion) {
        for (ConvenienceStore convenienceStore : returnObject) {
            if (convenienceStore instanceof Product product && product.isNameEquals(name)) {
                updateProducts(product, quantity, promotion);
                return true;
            }
        }
        return false;
    }

    private static void updateProducts(Product product, int quantity, String promotion) {
        if (promotion.equals("null") && !product.isPromotionNull()) {
            product.setGeneralQuantity(quantity);
        }
        if (!promotion.equals("null") && product.isPromotionNull()) {
            product.setPromotionQuantity(quantity);
            product.setPromotion(promotion);
        }
    }
}
