package com.calculator.rpn.utils;

import java.util.HashMap;

import com.calculator.rpn.enums.Operand;

public class Operands {
    private static class OperandsHolder {
        public static final HashMap<Operand, OperandAction> operands = new HashMap();

        static {
            operands.put(Operand.ADD, (v1, v2) -> v1 + v2);
            operands.put(Operand.DIVIDE, (v1, v2) -> v1 / v2);
            operands.put(Operand.MULTIPLY, (v1, v2) -> v1 * v2);
            operands.put(Operand.SUBTRACT, (v1, v2) -> v1 - v2);
        }
    }

    public static HashMap<Operand, OperandAction> getInstance() {
        return OperandsHolder.operands;
    }
}
