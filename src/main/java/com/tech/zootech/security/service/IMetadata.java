package com.tech.zootech.security.service;

import java.util.List;

public interface IMetadata<T> {
    List<T> extractData();
    void uploadData(T data);
}
