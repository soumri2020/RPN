package com.calculator.rpn.controllers;

import com.calculator.rpn.enums.Operand;
import com.calculator.rpn.services.CalculatorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rpn")
@Api(tags = "rpn")
public class CaculatorController {

	private CalculatorService calculatorService;

	@ApiOperation(value = "List all the operand")
	@GetMapping("/op")
	public Operand[] getAllOperands() {
		return this.calculatorService.getOperands();
	}

	@ApiOperation(value = "Apply operand to stack")
	@PostMapping("/op/{op}/stack/{stack_id}")
	public void applyOperand(@PathVariable("op") Operand op, @PathVariable("stack_id") Integer stackId) {
		this.calculatorService.applyOperand(op, stackId);
	}

	@ApiOperation(value = "Create new stack")
	@PostMapping("/stack")
	public Map.Entry<Integer, ArrayDeque<Double>> createStack() {
		return this.calculatorService.createStack();
	}
	
	@ApiOperation(value = "List all the stacks")
	@GetMapping("/stack")
	public HashMap<Integer, ArrayDeque<Double>> getAllStacks() {
		return this.calculatorService.getStacks();
	}

	@ApiOperation(value = "Delete stack by Id")
	@DeleteMapping("/stack/{stack_id}")
	public void deleteStackById(@PathVariable("stack_id") Integer stackId) {
		this.calculatorService.deleteStackById(stackId);
	}

	@ApiOperation(value = "Push a new stack")
	@PostMapping("/stack/{stack_id}")
	public void pushToStackById(@PathVariable("stack_id") Integer stackId, @RequestBody Double value) {
		this.calculatorService.pushToStackById(stackId, value);
	}

	@ApiOperation(value = "Get stack by Id")
	@GetMapping("/stack/{stack_id}")
	public ArrayDeque<Double> getStackById(@PathVariable("stack_id") Integer stackId) {
		return this.calculatorService.getStackById(stackId);
	}

}
