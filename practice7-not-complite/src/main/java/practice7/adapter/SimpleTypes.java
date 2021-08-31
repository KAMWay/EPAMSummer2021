package practice7.adapter;

public enum SimpleTypes {

    STRING(String.class),
    INTEGER(Integer.class),
    INT(int.class);

    Class<?> clazz;

    SimpleTypes(Class<?> clazz) {
        this.clazz=clazz;
    }

}
