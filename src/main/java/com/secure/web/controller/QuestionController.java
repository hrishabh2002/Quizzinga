package com.secure.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.secure.web.entity.Questions;
import com.secure.web.quiz.MyQuestionService;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class QuestionController {

    @Autowired
    private MyQuestionService questionService;

    @GetMapping("/quizzes/all-questions")
    public String getAllQuestions(Model model) {
        List<Questions> questions = questionService.getAllQuestions();
       // System.out.println(questions.size()+ " lkk");
        model.addAttribute("questions", questions);
        return "all-questions"; 
    }

    @GetMapping("/quizzes/fetch-questions-for-user")
    public String getQuestionsForUser(Model model, @RequestParam Integer num, @RequestParam String topic) {
        List<Questions> allQuestions = questionService.getQuestionsForUser(num, topic);
        List<Questions> mutableQuestions = new ArrayList<>(allQuestions);
        Collections.shuffle(mutableQuestions);

        int availableQuestions = Math.min(num, mutableQuestions.size());
        List<Questions> randomQuestions = mutableQuestions.subList(0, availableQuestions);
        model.addAttribute("questions", randomQuestions);
        model.addAttribute("category", topic);
        return "questions-for-user"; 
    }

    @GetMapping("/quizzes/question/{id}")
    public String getQuestionById(Model model, @PathVariable Long id) {
        Optional<Questions> theQuestion = questionService.getQuestionById(id);
        theQuestion.ifPresent(question -> model.addAttribute("question", question));
        return "question";
    }
}

