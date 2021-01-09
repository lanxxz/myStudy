package propertyeditors;

import lombok.Data;

import java.util.UUID;

/**
 * program: myStudy
 * description: 猫
 *
 * @author: alien
 * @since: 2021/01/02 21:37
 */
@Data
public class Cat extends Animal {
    private UUID uuid;
}
