package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.common.PageMaker;
import com.spring.mvc.chap05.common.Search;
import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    // 1. 목록 조회 요청 (/board/list : GET)
    @GetMapping("/list")
    public String list(@ModelAttribute("s") Search page, Model model){
        System.out.println("GET!!!/board/list");
        System.out.println(page);

        List<BoardListResponseDTO> dtoList = boardService.getList(page);

        //페이징 계싼 알고리즘 적용
        PageMaker pageMaker = new PageMaker(page, boardService.getCount(page));
        System.out.println("pageMaker = " + pageMaker);
        System.out.println("pageMaker = " + pageMaker);
        int a=(int) Math.ceil((double) pageMaker.getTotalCount()/6);
        model.addAttribute("bList",dtoList);
        model.addAttribute("maker",pageMaker);
        model.addAttribute("a",a);
        //model.addAttribute("s",page);
        return "chap05/list";
    }
    // 2. 글쓰기 화면요청 (/board/write : GET)
    @GetMapping("/write")
    public String write(){
        System.out.println("/board/write : GET!");
        return "chap05/write";
    }
    // 3. 글쓰기 등록요청 (/board/write : POST)
    @PostMapping("/write")
    public String write(BoardWriteRequestDTO dto){
        System.out.println("/board/write : POST! -"+dto);
        boardService.register(dto);
        return "redirect:/board/list";
    }
    // 4. 글 삭제 요청 (/board/delete : GET)
    @GetMapping("/delete")
    public String delete(int bno) {
        System.out.println("/board/delete : GET");
        boardService.delete(bno);
        return "redirect:/board/list";
    }
    // 5. 글 상세보기 요청 (/board/detail : GET)
    @GetMapping("/detail")
    public String detail(int bno,@ModelAttribute("s") Search search, Model model) {
        System.out.println("/board/detail : GET");
        model.addAttribute("b", boardService.getDetail(bno));
        return "chap05/detail";
    }
}
