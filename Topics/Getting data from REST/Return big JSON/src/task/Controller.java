package task;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class Controller {

    ColorInfo color1 = new ColorInfo("black", "hue", "primary",
            new Code(new int[]{0, 0, 0, 1}, "#000"));

    ColorInfo color2 = new ColorInfo("white", "value", "primary",
            new Code(new int[]{255, 255, 255, 1}, "#FFF"));

    ColorInfo[] colorList = new ColorInfo[]{color1, color2};

    Map<String, ColorInfo[]> colorMap = new HashMap<String, ColorInfo[]>(){{put("colors", colorList);}};

    @GetMapping("/colors")
    public Map<String, ColorInfo[]> getColors() {
        return colorMap;
    }

}

class ColorInfo {

    private String color;
    private String category;
    private String type;
    private Code code;

    public ColorInfo(String color, String category, String type, Code code) {
        this.color = color;
        this.category = category;
        this.type = type;
        this.code = code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }
}

class Code {

    private int[] rgba;
    private String hex;

    public Code(int[] rgba, String hex) {
        this.rgba = rgba;
        this.hex = hex;
    }

    public int[] getRgba() {
        return rgba;
    }

    public void setRgba(int[] rgba) {
        this.rgba = rgba;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }
}