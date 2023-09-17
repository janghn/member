package com.orderproject.member.controller;

import com.orderproject.member.dto.MemberDto;
import com.orderproject.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
  //생성자 주입
  private final MemberService memberService;

  //회원가입 페이지 출력 요청
  @GetMapping("/member/save") //링크는 @GetMapping
  public String svaeForm(){
    return "save";
  }

  @PostMapping("/member/save")
  public String save(@ModelAttribute MemberDto memberDto){
    System.out.println("MemberController.save");
    System.out.println("memberDto = " + memberDto);
    memberService.save(memberDto);

    return "login";
  }

  @PostMapping("/member/login")
  public String login(@ModelAttribute MemberDto memberDto, HttpSession session){
    MemberDto loginResult = memberService.login(memberDto);
    if(loginResult != null){
      //login 성공
      session.setAttribute("loginEmail",loginResult.getMemberEmail());
      return "main";
    }else{
      //login 실패
      return "login";
    }
  }

  @GetMapping("/member/login")
  public String loginForm(){
    return "login";
  }

  @GetMapping("/member/")
  public String findAll(Model model){
    List<MemberDto> memberDtoList =  memberService.findAll();
    //어떠한 html로 가져갈 데이터가 있다면 model사용
    model.addAttribute("memberList",memberDtoList);
    return "list";
    
  }

  @GetMapping("/member/{id}")
  public String findById(@PathVariable Long id, Model model){
    MemberDto memberDto = memberService.findById(id);
    model.addAttribute("member",memberDto);
    return "detail";

  }

  @GetMapping("/member/update")
  public String updateForm(HttpSession session, Model model){
    String myEmail = (String) session.getAttribute("loginEmail");
    MemberDto memberDto = memberService.updateFrom(myEmail);
    model.addAttribute("updateMember",memberDto);
    return "update";
  }

  @PostMapping("/member/update")
  public String update(@ModelAttribute MemberDto memberDto){
    memberService.update(memberDto);
    return "redirect:/member/" + memberDto.getId() ;
  }

  @GetMapping("/member/delete/{id}")
  public String deleteById(@PathVariable Long id){
    memberService.deleteById(id);
    return "redirect:/member/";
  }

  @GetMapping("/member/logout")
  public String logout(HttpSession session){
    session.invalidate(); //세션을 무효화
    return "index";
  }

  @PostMapping("/member/email-check")
  public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail){
    System.out.println("memberEmail = " + memberEmail);
    String checkResult = memberService.emailCheck(memberEmail);
//    return "체크완료";

    if(checkResult != null){
      return "ok";
    }else {
      return "no";
    }
  }

  @GetMapping("/ajax/")
  public String ajax(){
    return "ajax";
  }


}
