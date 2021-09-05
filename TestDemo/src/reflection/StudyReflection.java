package reflection;

import annotation.StudyAnnotation;
import model.Order;
import model.Person;
import util.GenerateSqlUtil;

import java.lang.reflect.Field;

/**
 * @author Cdu
 * @discription:
 * @create 2021-02-06 21:26
 */
public class StudyReflection {

    public static void main(String[] args) throws Exception {
        //通过反射获取class元信息
        Person person = new Person();
        Class<? extends Person> aClass = person.getClass();//类元信息
        Class<?> aClass1 = Class.forName("model.Person");//spring

        //通过反射获取类名，包名
        String name = aClass.getName();
        System.out.println(name);
        System.out.println(aClass1);
        System.out.println(aClass);

        //类属性
        Field[] declaredFields = aClass.getDeclaredFields();
        Object p = aClass.newInstance();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if (declaredField.getName().equals("name")){
                declaredField.set(p,"csd" );
            }else {
                declaredField.set(p, 18);
            }
            System.out.println(declaredField.get(p));
        }

        //获取注解
        StudyAnnotation annotation = aClass.getAnnotation(StudyAnnotation.class);

        GenerateSqlUtil generateSqlUtil = new GenerateSqlUtil();
        Order order = new Order();
        order.setOrderNo("123");
        String query = generateSqlUtil.query(order);
        System.out.println(query);
    }
}