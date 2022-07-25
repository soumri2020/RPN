package com.calculator.rpn.utils;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Stacks {
    private static class StacksHolder {
        public static final HashMap<Integer, ArrayDeque<Double>> stacks = new HashMap();
    }

    public static HashMap<Integer, ArrayDeque<Double>> getInstance() {
        return StacksHolder.stacks;
    }
}
