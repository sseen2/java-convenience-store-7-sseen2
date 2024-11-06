package store;

import java.util.List;

public class MainController {
    public void run() {
        fileReadRun();
    }

    private void fileReadRun() {
        ResourceFileRead fileReader = new ResourceFileRead();
        fileReader.fileRead("promotions.md");
        fileReader.fileRead("products.md");
    }
}
