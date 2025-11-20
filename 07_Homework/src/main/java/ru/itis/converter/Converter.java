package ru.itis.converter;

import java.util.ArrayList;
import java.util.List;

public interface Converter<S, T> {
    T convertToEntity (S s);
    S convertToModel (T t);

//    default List<T> convertList(List<S> list) {
//        List<T> result = new ArrayList<>();
//        list.forEach(s -> result.add(convert(s)));
//        return result;
//    }
}
