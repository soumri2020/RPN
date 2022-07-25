package com.calculator.rpn.utils;

@FunctionalInterface
public interface OperandAction  {
    Double execute(Double v1, Double v2);
}