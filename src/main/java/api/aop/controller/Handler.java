package api.aop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.aop.anotation.Log;
import api.aop.anotation.MultipleParameterLog;
import api.aop.anotation.MultipleValueForLog;
import api.aop.anotation.ParameterLog;
import api.aop.anotation.ValueForLog;

@RestController
public class Handler 
{	
	@Value("${name}")
	String name;

	/*
	 * 0 parameter with aop
	 */
	@GetMapping("/1")
	@Log //Joint-Point
	public void call1() throws Exception  
	{	
		method();
	}

	public void method() throws Exception {
		System.out.println("method");
		throw new Exception();	
	}

	@GetMapping("/2")
	@Log
	public void call2() throws Exception 
	{	
		System.out.println("method " + name );
	}

	/*
	 * for single parameter with aop
	 * @ParameterLog - for method
	 * @ValueForLog - for parameter
	 */
	@GetMapping("/parameter")
	@ParameterLog
	public int call2(@ValueForLog @RequestParam("a") int a) throws Exception 
	{	
		System.out.println("parameterized method : a =  " +  a);
		return a;
	}
	/*
	 * for multiple parameter with  
	 * @@MultipleParameterLog - for method
	 * @@MultipleValueForLog - for parameter
	 */
	@GetMapping("/multiple/parameter")
	@MultipleParameterLog
	public int call3(@MultipleValueForLog @RequestParam("a") int a,@MultipleValueForLog @RequestParam("b") int b) throws Exception 
	{	
		System.out.println("parameterized method : a =  " +  a + "  b = " + b);
		return a+b;
	}
}