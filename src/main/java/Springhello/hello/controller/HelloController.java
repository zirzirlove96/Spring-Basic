package Springhello.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String main(Model model){
        model.addAttribute("data","hello");
        return "hello";
    }

    @GetMapping("hello-template")
    public String main2(@RequestParam("name")String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String main3(@RequestParam("name")String name){
        return "hello "+name;
    }

    @GetMapping("hello-json")
    @ResponseBody
    public Hello main4(@RequestParam("name")String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public void setName(String name){
            this.name=name;
        }

        public String getName() {
            return this.name;
        }



    }
}
