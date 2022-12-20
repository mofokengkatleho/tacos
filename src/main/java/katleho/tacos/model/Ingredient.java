package katleho.tacos.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import javax.persistence.Entity;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient {
    @Id
    private final String id;
    private final String name;
    private final Type type;


    public enum Type{
        WRAP,SAUCE,CHEESE,VEGGIES,PROTEIN
    }
}
