package com.leeej.toDoList2.controller;

import com.leeej.toDoList2.dto.SignupDto;
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
        model.addAttribute("signupDto", new SignupDto());

        return "signup";
    }

    @PostMapping("signup")
    public String doSignup(
            @Valid @ModelAttribute SignupDto signupDto,  //유효성 검사
            BindingResult bindingResult,                 //유효성 검사결과 저장
            Model model
    ) {
        if(bindingResult.hasErrors()) {
            return "signup";
        }

        //회원가입시 중복 가입여부 체크

        User user = User.builder()
                .username(signupDto.getUsername())
                .password(signupDto.getPassword())
                .build();

        userRepository.save(user);

        return "redirect:/login?registered";
    }
}
