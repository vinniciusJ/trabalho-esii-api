package com.project.esii.project_esii.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Search <T extends Comparable<T>>{
    private String key;
    private String operation;
    private T value;
}
