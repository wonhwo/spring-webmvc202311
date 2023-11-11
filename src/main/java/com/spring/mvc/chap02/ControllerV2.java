package com.spring.mvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/model")
public class ControllerV2 {
    /**
     * /model/hobbies : GET
     * -> hobbies.jsp파일로 사용자 이름정보와 취미목록을 뿌려주고 싶다.
     * -> Model 객체 사용
     * -> 자바가 가지고 있는 데이터를 JSP로 넘겨줄 때 사용하는 바구니 같은 역할
     * @return
     */
    @GetMapping("/hobbies")
    public String hobbies(Model model){
        System.out.println("취미 안녕");

        String name="짹짹이";
        List<String> list = List.of("전깃줄 앉아있기", "좁살쭈워먹기", "ㅎㅎㅎㅎ");
        model.addAttribute("userName",name);
        model.addAttribute("hobbies",list);
        return "chap02/hobbies";
    }
    @GetMapping("/hobbies2")
    public String hobbies2(){
        System.out.println("취미 안녕2");
        return "";
    }
}
