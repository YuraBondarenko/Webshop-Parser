package service;

import java.util.List;

public interface MainService<T> {
    List<T> getModelList(String url);

    String getStringFromModel(List<T> models);
}
