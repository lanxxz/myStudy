package propertyeditors;

import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.PropertyEditorRegistrySupport;
import org.springframework.beans.propertyeditors.CharsetEditor;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.UUIDEditor;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceEditor;

import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * program: myStudy
 * description: spring扩展的propertyeditor测试,线程不安全<br/>
 * PropertyEditor	功能	举例<br/>
 * ZoneIdEditor	转为java.time.ZoneId	Asia/Shanghai<br/>
 * URLEditor	转为URL，支持传统方式file:,http:，也支持Spring风格：classpath:,context上下文相对路径等等	http://www.baidu.com<br/>
 * StringTrimmerEditor	trim()字符串，也可删除指定字符char	任意字符串<br/>
 * StringArrayPropertyEditor	转为字符串数组	A,B,C<br/>
 * PropertiesEditor	转为Properties	name = YourBatman<br/>
 * PatternEditor	转为Pattern	(\D*)(\d+)(.*)<br/>
 * PathEditor	转为java.nio.file.Path。支持传统URL和Spring风格的url	classpath:xxx<br/>
 * ClassEditor	转为Class	全类名<br/>
 * CustomBooleanEditor	转为Boolean	见示例<br/>
 * CharsetEditor	转为Charset	见示例<br/>
 * CustomDateEditor	转为java.util.Date	见示例<br/>
 *
 * @author: alien
 * @since: 2021/01/02 15:27
 */
public class PropertyeditorsTest {

    public static void testCustomBooleanEditor() {
        PropertyEditor editor = new CustomBooleanEditor(true);
        // 这些都是ture 不区分大小写
        editor.setAsText("trUe");
        System.out.println(editor.getAsText());
        editor.setAsText("on");
        System.out.println(editor.getAsText());
        editor.setAsText("yes");
        System.out.println(editor.getAsText());
        editor.setAsText("1");
        System.out.println(editor.getAsText());

        // 这些是 false(null不会输出false，而是空字符)
        System.out.println("=================");
        editor.setAsText(null);
        System.out.println(editor.getAsText());
        editor.setAsText("falSe");
        System.out.println(editor.getAsText());
        editor.setAsText("off");
        System.out.println(editor.getAsText());
        editor.setAsText("no");
        System.out.println(editor.getAsText());
        editor.setAsText("0");
        System.out.println(editor.getAsText());

        // 报错
        editor.setAsText("2");
        System.out.println(editor.getAsText());
    }

    public static void testCharsetEditor() {
        PropertyEditor editor = new CharsetEditor();
        editor.setAsText("Utf-8");
        System.out.println(editor.getAsText());
        editor.setAsText("uTf8");
        System.out.println(editor.getAsText());
    }

    public static void testCustomDateEditor() {
        PropertyEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true);
        editor.setAsText("2021-01-02 17:08:00");
        System.out.println(editor.getAsText());

        editor.setAsText(null);
        System.out.println(editor.getAsText());

        editor.setAsText("2020-01-02");
        System.out.println(editor.getAsText());
    }

    public static void testResourceEditor() {
        // 支持标准URL如file:C:/myfile.txt，也支持classpath:myfile.txt
        // 同时还支持占位符形式
        PropertyEditor editor = new ResourceEditor(new DefaultResourceLoader(), new StandardEnvironment(), true);

        // file:形式本处略
        // editor.setAsText("file:...");
        // System.out.println(editor.getAsText());

        // classpath形式（注意：若文件不存在不会报错，而是输出null）
        editor.setAsText("classpath:app.properties");
        // 输出带盘符的全路径
        System.out.println(editor.getAsText());

        System.setProperty("myFile.name", "app.properties");
        editor.setAsText("classpath:${myFile.name}");
        // 输出带盘符的全路径
        System.out.println(editor.getAsText());
    }

    public static void testAnimalPropertyEditor() {
        PropertyEditorRegistry propertyEditorRegistry = new PropertyEditorRegistrySupport();
        propertyEditorRegistry.registerCustomEditor(Animal.class, new AnimalPropertyEditor());

        // 父类型子类型均可匹配上编辑器
        PropertyEditor customEditor = propertyEditorRegistry.findCustomEditor(Cat.class, null);
        PropertyEditor customEditor1 = propertyEditorRegistry.findCustomEditor(Animal.class, null);
        System.out.println(customEditor1 == customEditor);
        System.out.println(customEditor.getClass().getSimpleName());
    }

    /**
     * description: UUID类型统一交给UUIDEditor处理（当然包括Cat里面的UUID类型）<br/>
     *     Person类里面的Cat的UUID类型，需要单独特殊处理，因此格式不一样需要“特殊照顾”
     * @since: 2021/1/2
     */
    public static void testAnimalPropertyEditor2() {
        PropertyEditorRegistry propertyEditorRegistry = new PropertyEditorRegistrySupport();
        // 通用的
        propertyEditorRegistry.registerCustomEditor(UUID.class, new UUIDEditor());
        // 专用的
        propertyEditorRegistry.registerCustomEditor(Person.class, "cat.uuid", new PersonCatUUIDEditor());

        String uuidStr = "1-2-3-4-5";
        String personCatUuidStr = "1-2-3-4-5_YourBatman";

        PropertyEditor customEditor = propertyEditorRegistry.findCustomEditor(UUID.class, null);
        // 抛异常：java.lang.NumberFormatException: For input string: "5_YourBatman"
        // customEditor.setAsText(personCatUuidStr);
        customEditor.setAsText(uuidStr);
        System.out.println(customEditor.getAsText());

        customEditor = propertyEditorRegistry.findCustomEditor(Person.class, "cat.uuid");
        customEditor.setAsText(personCatUuidStr);
        System.out.println(customEditor.getAsText());

    }

    public static void main(String[] args) {
//        testCustomBooleanEditor();
//        testCharsetEditor();
//        testCustomDateEditor();
//        testResourceEditor();
        testAnimalPropertyEditor();
        System.out.println("============");
        testAnimalPropertyEditor2();
    }
}
