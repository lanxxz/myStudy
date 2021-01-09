package propertyeditors;

import lombok.Data;

/**
 * program: myStudy
 * description: 人类
 *
 * @author: alien
 * @since: 2021/01/02 21:54
 */
@Data
public class Person extends Animal {
    private Long id;
    private String name;
    private Cat cat;
}
