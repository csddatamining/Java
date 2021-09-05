package util;

import annotation.Column;
import annotation.SqlState;

import java.lang.reflect.Field;

/**
 * @author Cdu
 * @discription:
 * @create 2021-02-06 22:33
 */
public class GenerateSqlUtil {

    public String query(Object tab) throws Exception {
        StringBuffer sb = new StringBuffer();

        //select 字段 from 表名 where 条件
        sb.append("select");

        StringBuffer sb2 = new StringBuffer();
        //反射机制
        Class<?> cls = tab.getClass();
        SqlState annotation = cls.getAnnotation(SqlState.class);//表名
        if (annotation == null)
            throw new RuntimeException("缺少注解");
        String tableName = annotation.value();

        Field[] declaredFields = cls.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Column c = declaredField.getAnnotation(Column.class);
            if (c == null)
                continue;//没写就不查字段
            if (c.value().equals("")) {//表字段名和属性名一致
                String name = declaredField.getName();
                declaredField.setAccessible(true);
                Object o = declaredField.get(tab);
                if (o != null && Integer.parseInt(o.toString()) != 0) {
                    sb2.append(" and " + name + "=" + o);
                    sb.append(" " + name + ",");
                }
            } else {
                sb.append(" " + c.value() + " ,");
                declaredField.setAccessible(true);
                Object o = declaredField.get(tab);
                if (o != null && Integer.parseInt(o.toString()) != 0) {
                    sb2.append(" and " +c.value() + "=" + o);
                }
            }
        }

        sb.deleteCharAt(sb.length()-1);
        sb.append("from " + tableName);
        sb.append(sb2);

        return sb.toString();
    }

}