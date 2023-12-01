package com.spring.mvc.chap04.controllor;

/*
    # 컨트롤러
    - 클라이언트의 요청을 받아서 처리하고 응답을 제공하는 객체

    # 요청 URL Endpoint
    1. 학생의 성적정보 등록폼 화면을 보여주고
       동시에 지금까지 저장되어 있는 성적 정보 목록을 조회
    - /score/list   :   GET

    2. 학생의 입력된 성적정보를 데이터베이스에 저장하는 요청
    - /score/register   :  POST

    3. 성적정보를 삭제 요청
    - /score/remove    :  GET or POST

    4. 성적정보 상세 조회 요청
    - /score/detail  :   GET
 */

import com.spring.mvc.chap04.DTO.ScoreRequstDTO;
import com.spring.mvc.chap04.DTO.ScoreResponseDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import com.spring.mvc.chap04.service.ScoreService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/score")
@RequiredArgsConstructor //final이 붙은 필드를 초기화하는 생성자 생성
//@AllArgsConstructor //모든 필드를 초기화하는 생성자 생성
public class ScoreControllor {
    //저장소에 의존하여 데이터 처리를 위임한다.
    //의존객체는 불변성을 가지는 것이 좋다.
    private final ScoreService service;

    //@Autowired //스프링에 등록된 빈을 자동주입
    // 생성자 주입을 사용하고 생성자가 단 하나 <- autowired 생략 조건
//    public ScoreControllor(ScoreRepository repository){
//        this.repository=repository;
//    }


    //1. 성적 폼 띄우기 + 목록조회
    // -jsp파일로 입력 폼 화면을 띄워줘야 함(view 포워딩)
    // - 저장된 성적정보 리스트를 jsp에 보내줘야 함 (model 데이터 전송)
    // - 저장된 성적정보 리스트를 어떻게 가져오느냐 from DB
    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "num") String sort) {
        System.out.println("/score/list GET !!");

        List<ScoreResponseDTO> dtolist = service.getList(sort);
        //db에서 조회한 모든데이터
//        System.out.println("scoreList = " + scoreList);
//        //클라이언트가 필요한 정제된 데이터
//        List<ScoreResponseDTO> dtoList = new ArrayList<>();
//        for(Score score: scoreList)
//            dtoList.add(new ScoreResponseDTO(score));
        model.addAttribute("sList", dtolist);
        return "chap04/score-list";
    }

    //2. 성적정보를 데이터베이스에 저장하는 요청
    @PostMapping("/register")
    public String register(ScoreRequstDTO scoreRequstDTO) {
        System.out.println("/score/register POST !!");
        System.out.println("scoreRequstDTO = " + scoreRequstDTO);

        //DTO를 엔터티로 변환 - > 데이터 생성
        service.insertScore(scoreRequstDTO);
        /*
        forward vs redirect
        - 포워드는 요청 리소스를 그대로 전달해줌.
        - 따라서 URL이 변경되지 않고 한번의 요청과 한번의 응답만 이뤄진다
        -리다이렉트는 요청후에 자동응답이 나가고
        2번째 자동요청이 들어오면서 2번째 응답을 내보냄
        -따라서 2번째 요청의 URL로 자동 변경 됨
         */
        //forward는 포워딩 파일의 경로를 적는다.
        //ex)/WEB-INF/views/chap04/score-list.jsp
        //redirect는 리다이렉트 요청 URL을 적는 것
        //ex)localhost.8181/score/list
        return "redirect:/score/list";
    }

    //3. 성적 삭제 요청
    @RequestMapping(value = "/remove/{stuNum}", method = {RequestMethod.GET, RequestMethod.POST})
    public String remove(HttpServletRequest request, @PathVariable int stuNum) {
        System.out.printf("/score/remove %s !!\n", request.getMethod());
        System.out.println("stuNum = " + stuNum);
        service.deleteScore(stuNum);
        return "redirect:/score/list";
    }

    //4. 성적 상세 조회 요청
    @GetMapping("/detail")
    public String detail(int stuNum, Model model) {
        System.out.println("/score/detail GET !!");
        retrieve(stuNum, model);
        return "chap04/score-detail";
    }
    // 5. 수정 입력 폼을 열어주는 요청
    // /score/modify : GET
    @GetMapping("modify")
    public String modify(int stuNum, Model model) {
        System.out.println("/score/modify GET");
        retrieve(stuNum, model);
        return "chap04/score-modify";
    }
    @PostMapping("modify")
    public String modify(int stuNum,ScoreRequstDTO dto) {
        System.out.println("/score/modify POST");
        // 수정의 흐름
        //클라이언트가 수정할 데이터를 보냄
        //-> 서버에 저장되어 있는 기존데이터를 조회해서 수정한다.
        service.updateScore(stuNum,dto);

        return "redirect:/score/detail?stuNum="+stuNum;
    }
    private void retrieve(int stuNum, Model model) {
        Score score = service.retrieve(stuNum);
        model.addAttribute("s", score);
    }
}
