package propertyeditors;

import lombok.Data;

import java.util.UUID;

/**
 * program: myStudy
 * description: 动物
 *
 * @author: alien
 * @since: 2021/01/02 21:36
 */
@Data
public abstract class Animal {
    private Long id;
    private String name;
}
