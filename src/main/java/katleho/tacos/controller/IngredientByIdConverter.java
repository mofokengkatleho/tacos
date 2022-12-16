package katleho.tacos.controller;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter  implements Converter<String,String> {
    @Override
    public String convert(String source) {
        return null;
    }
}
