import annotations.XmlIgnore;
import annotations.XmlName;
import annotations.XmlTypeName;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

public class XmlReader {

    String serialize(Collection<?> objects) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();

        if (objects instanceof List<?>) {
            sb.append("[").append("\n");
            for (Object obj : objects) {
                Class<?> clazz = obj.getClass();
                String closingFieldName = "";
                for (Annotation annotation: clazz.getAnnotations()) {
                    if(annotation instanceof XmlTypeName xmlTypeNameAnnotation) {
                        final String xmlTypeName = xmlTypeNameAnnotation.value();
                        sb.append("<").append(xmlTypeName).append(">").append("\n");
                        closingFieldName = xmlTypeName;
                    }
                }
                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field declaredField: declaredFields) {
                    if (declaredField.isAnnotationPresent(XmlIgnore.class)) {
                        continue;
                    }
                    String name = declaredField.getName();
                    final XmlName annotation = declaredField.getAnnotation(XmlName.class);

                    if (annotation != null) {
                        name = annotation.v();
                    }

                    sb.append("\t<").append(name).append(">");
                    declaredField.setAccessible(true);
                    sb.append(declaredField.get(obj).toString());
                    sb.append("</").append(name).append(">\n");
                }
                sb.append("<").append(closingFieldName).append("/>").append("\n");
            }
            sb.append("]");
        }
        return sb.toString();
    }

    Object deserialize(String xmlRow, Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        Object object = constructor.newInstance();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField: declaredFields) {
            declaredField.setAccessible(true);
            if ( declaredField.getType().isPrimitive()) {
                declaredField.set(object, Double.parseDouble(getFieldValue(xmlRow, declaredField.getAnnotation(XmlName.class).v())));
            } else {
                if (declaredField.getAnnotation(XmlName.class) != null) {
                    declaredField.set(object,
                        getFieldValue(xmlRow, declaredField.getAnnotation(XmlName.class).v()));
                }
            }
        }
        return object;
    }

    String getFieldValue(String xmlRow, String fieldName) {
        String[] splits = xmlRow.split("\n");
        for (String split: splits) {
            if (split.contains(fieldName)) {
                return split.split(">")[1].split("<")[0];
            }
        }
        return null;
    }

}
