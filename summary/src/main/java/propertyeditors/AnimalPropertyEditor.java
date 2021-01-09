package propertyeditors;

import java.beans.PropertyEditorSupport;

/**
 * program: myStudy
 * description: spring3.0以前类型转换器
 *
 * @author: alien
 * @since: 2021/01/02 21:38
 */
public class AnimalPropertyEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        super.setAsText(text);
    }
}
