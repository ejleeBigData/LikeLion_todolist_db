package com.leeej.toDoList2.controller;

import com.leeej.toDoList2.dto.SignupDTO;
import com.leeej.toDoList2.model.User;
import com.leeej.toDoList2.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignupController {
    private final UserRepository userRepository;

    @GetMapping("/signup")
    public String showSignup(Model model) {
        model.addAttribute("signupDTO", new SignupDTO());

        return "signup";
    }

    @PostMapping("signup")
    public String doSignup(
       @Valid @ModelAttribute SignupDTO signupDTO,  //유효성 검사
       BindingResult bindingResult,                 //유효성 검사결과 저장
       Model model
    ) {
        if(bindingResult.hasErrors()) {
            return "signup";
        }

        //회원가입시 중복 가입여부 체크

        User user = User.builder()
                .username(signupDTO.getUsername())
                .password(signupDTO.getPassword())
                .build();

        userRepository.save(user);

        return "redirect:/login?registered";
    }
}
