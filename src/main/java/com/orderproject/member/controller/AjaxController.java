package com.orderproject.member.controller;

import com.orderproject.member.dto.AjaxDTO;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AjaxController {
  @GetMapping("/ex01")
  public String ex01(){
    System.out.println("AjaxController.ex01");
    return "index"; // index.html에 작성된 내용이 출력
  }

  @PostMapping("/ex02")
  public @ResponseBody String ex02(){
    System.out.println("AjaxController.ex02");
    return "index"; // 리턴값이 그대로 출력
  }

  @GetMapping("/ex03")
  public @ResponseBody String ex03(@RequestParam("param1") String parma1,
                                   @RequestParam("param2") String param2){
    System.out.println("param1 = " + parma1 + ", param2 = " + param2);
    return "ex03메서드 호출 완료";
  }

  @PostMapping("/ex04")
  public @ResponseBody String ex04(@RequestParam("param1") String parma1,
                                   @RequestParam("param2") String param2){
    System.out.println("param1 = " + parma1 + ", param2 = " + param2);
    return "ex04메서드 호출 완료";
  }

  @GetMapping("/ex05")
  public @ResponseBody AjaxDTO ex05(@ModelAttribute AjaxDTO ajaxDTO){
    System.out.println("ajaxDTO = " + ajaxDTO);
    return ajaxDTO;
  }

  @PostMapping("/ex06")
  public @ResponseBody AjaxDTO ex06(@ModelAttribute AjaxDTO ajaxDTO){
    System.out.println("ajaxDTO = " + ajaxDTO);
    return ajaxDTO;
  }

  @PostMapping("/ex07")
  public @ResponseBody AjaxDTO ex07(@RequestBody AjaxDTO ajaxDTO){
    System.out.println("ajaxDTO = " + ajaxDTO);
    return ajaxDTO;
  }

  private List<AjaxDTO> DtoList(){
    List<AjaxDTO> dtoList = new ArrayList<>();
    dtoList.add(new AjaxDTO("data1","data11"));
    dtoList.add(new AjaxDTO("data2","data22"));
    return dtoList;
  }

  @PostMapping("/ex08")
  public @ResponseBody List<AjaxDTO> ex08(@RequestBody AjaxDTO ajaxDTO){
    System.out.println("ajaxDTO = " + ajaxDTO);
    List<AjaxDTO> dtoList = DtoList();
    dtoList.add(ajaxDTO);
    return dtoList;
  }

  @PostMapping("/ex09")
  public ResponseEntity ex09(@RequestBody AjaxDTO ajaxDTO){
    System.out.println("ajaxDTO = " + ajaxDTO);
    return new ResponseEntity<>(ajaxDTO,HttpStatus.OK);
  }

  @PostMapping("/ex10")
  public ResponseEntity ex10(@RequestBody AjaxDTO ajaxDTO){
    System.out.println("ajaxDTO = " + ajaxDTO);
    List<AjaxDTO> dtoList = DtoList();
    dtoList.add(ajaxDTO);
    return new ResponseEntity<>(dtoList,HttpStatus.OK);
  }




}
