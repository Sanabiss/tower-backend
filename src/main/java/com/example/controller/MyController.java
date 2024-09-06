package com.example.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = {"/tower", "/api/v1"})
public class MyController {
    private Output moves;


    @GetMapping(path = "")
    public String hello() {
        return "hello my friend";
    }

    @GetMapping(path = "/say-hello")
    public String hello2(@RequestParam String name) {
        return STR."hello \{name}";
    }

    public static class Input {
        private Integer n;

        public Input() {
        }

        public Input(Integer n) {
            this.n = n;
        }

        public Integer getN() {
            return n;
        }

        public void setN(Integer n) {
            this.n = n;
        }
    }
    public static class Steps {
        private String from;
        private String to;

        public Steps() {
        }

        public Steps(String from, String to) {
            this.from = from;
            this.to = to;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }

    public static class Output {
        private long time;
        private List<Steps> steps;
        /// [visibilty] type name

        public List<Steps> getSteps() {
            return steps;
        }

        public void setSteps(List<Steps> steps) {
            this.steps = steps;
        }

        public Output() {
            this.steps = new ArrayList<>();
        }

        public Output(long time, List<Steps> steps) {
            this.time = time;
            this.steps = steps;
        }



        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }
    @PostMapping(path = "brunch")
    public  Output brunch(@RequestBody Input input) {
        long startTime = System.currentTimeMillis();
        Output output = new Output();
        System.out.println(startTime);
        shiftDisk(input.n, 'A', 'C', 'B',output);


        long endTime = System.currentTimeMillis();
        System.out.println(endTime);
        long executionTime = endTime - startTime;
        System.out.println(executionTime);

        //Output(executionTime);
        output.setTime(executionTime);
        return output;
    }

     private static void shiftDisk(int n, char TA, char TC, char TB, Output output) {
        if (n == 1) {
            output.steps.add(new Steps("" + TA, "" +TB));

        } else {
            MyController.shiftDisk(n - 1, TA, TB, TC, output);
            output.steps.add(new Steps("" + TA, ""+ TB));

            shiftDisk(n - 1, TC, TA, TB, output);

        }
    }
}




