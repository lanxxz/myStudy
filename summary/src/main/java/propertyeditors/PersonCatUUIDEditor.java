package propertyeditors;

import org.springframework.beans.propertyeditors.UUIDEditor;

/**
 * program: myStudy
 * description: {@link Person} 类中的 {@link Cat} 类的 UUID 编辑器
 *
 * @author: alien
 * @since: 2021/01/02 21:57
 */
public class PersonCatUUIDEditor extends UUIDEditor {

    private static final String SUFFIX = "_YourBatman";

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        text = text.replace(SUFFIX, "");
        super.setAsText(text);
    }

    @Override
    public String getAsText() {
        return super.getAsText().concat(SUFFIX);
    }
}
