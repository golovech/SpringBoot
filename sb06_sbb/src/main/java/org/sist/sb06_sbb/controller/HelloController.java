package org.sist.sb06_sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	
	@GetMapping("/hi/{id}/{age}")
    @ResponseBody
    public String hi(@PathVariable(name = "id") String id, @PathVariable("age") int age) {
    //public String hi(@PathVariable String id, @PathVariable int age) {
        
      return String.format("\"%s\"(%d)님 hi~"  , id, age);
   
    }

}
