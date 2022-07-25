package com.calculator.rpn.services;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import com.calculator.rpn.enums.Operand;
import com.calculator.rpn.utils.OperandAction;
import com.calculator.rpn.utils.Operands;
import com.calculator.rpn.utils.Stacks;


public class CalculatorServiceImpl {
	
    private static final Logger logger = LoggerFactory.getLogger(CalculatorServiceImpl.class);
    
    public HashMap<Integer, ArrayDeque<Double>> getStacks() {
        return Stacks.getInstance();
    }

    public Map.Entry<Integer, ArrayDeque<Double>> createStack() {
        int id = Instant.now().getNano();
        Stacks.getInstance().put(id, new ArrayDeque());
        return Map.entry(id, Stacks.getInstance().get(id));
    }

    public void pushToStackById(final Integer id, final Double value) {
        getStackById(id).add(value);
    }

    public ArrayDeque<Double> getStackById(final Integer id) {
        Assert.notNull(id, "Id must not be null");
        Optional<ArrayDeque<Double>> optionalStack = Optional.ofNullable(Stacks.getInstance().get(id));
        if(optionalStack.isPresent()) {
            return optionalStack.get();
        } else {
            logger.error("Stack not found with id {}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stack not found");
        }
    }

    public void deleteStackById(final Integer id) {
        Stacks.getInstance().remove(id);
    }

    public void applyOperand(final Operand op, final Integer id){
        Assert.notNull(op, "Operand must not be null");
        ArrayDeque<Double> stack = getStackById(id);
        Assert.isTrue(stack.size() >= 2, "Stack must contain at least 2 elements");
        Double latest1 = stack.removeLast();
        Double latest2 = stack.removeLast();
        OperandAction action = Operands.getInstance().get(op);
        Assert.notNull(action, "Could not find the following operand" + op.name());
        stack.add(action.execute(latest1,latest2));
    }

    public Operand[] getOperands() {
        return Operand.values();
    }

}
