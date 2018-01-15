/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.type;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SelmanKasim
 */
public enum RoleType {
    Student(1),
    Teacher(2);

    static Map<Integer, RoleType> map = new HashMap<>();

    static {
        for (RoleType genderType : RoleType.values()) {
            map.put(genderType.getValue(), genderType);
        }
    }

    int value;

    private RoleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RoleType getByValue(int value) {
        return map.get(value);
    }

}
