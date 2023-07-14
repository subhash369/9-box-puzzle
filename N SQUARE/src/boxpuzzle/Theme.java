package boxpuzzle;

import java.awt.Color;

public class Theme {
    Color colorBack;
    Color colorFront;

    Theme(String theme) {
        if (theme == "Dark") {
            colorBack = new Color(0, 20, 50);
            colorFront = new Color(214, 237, 23);

        } else {
            colorBack = new Color(51, 51, 51);
            colorFront = new Color(250, 200, 200);
        }
    }
}
