package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class controller {

    private final Integer RANGE_MIN = 0;
    private final Integer RANGE_MAX = 9;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/response")
    public String response(@RequestParam String number, Model model) {

        List<Integer> range = IntStream.rangeClosed(RANGE_MIN, RANGE_MAX).boxed().toList();
        range =  new ArrayList<>(range);
        List<String> images = new ArrayList<>();
        
        try{
            for(int i = 0; i < Integer.parseInt(number); i++) {
                int randint = ThreadLocalRandom.current().nextInt(0, range.size());
                images.add(range.get(randint) + ".png");
                range.remove(randint);
            }
        } catch (NumberFormatException e) {
            images = null;
        } catch (Exception e) {
            images = null;
        }
        
        model.addAttribute("images", images);

        return "response";
    }
}
