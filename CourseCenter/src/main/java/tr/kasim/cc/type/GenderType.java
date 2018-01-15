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
public enum GenderType {
    MALE(1),
    FEMALE(2);

    static Map<Integer, GenderType> map = new HashMap<>();

    static {
        for (GenderType genderType : GenderType.values()) {
            map.put(genderType.getValue(), genderType);
        }
    }

    int value;

    private GenderType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public GenderType getByValue(int value) {
        return map.get(value);
    }

}
