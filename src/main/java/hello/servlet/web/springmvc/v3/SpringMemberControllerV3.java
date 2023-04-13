package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * v3
 * Model 도입
 * ViewName 직접 반환
 * @RequestParam 사용
 * @RequestMapping -> @GetMapping, @PostMapping
 */
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    // 회원 등록 폼
    @GetMapping("/new-form")
    public String newForm(){
        return "new-form";
    }

    // 회원 저장
    @PostMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("age") int age,
                       Model model){
        // HTTP 요청 파라미터를 @RequestParam으로 받아서 저장한다
        Member member = new Member(username, age);
        memberRepository.save(member);
        // 스프링이 제공하는 모델을 가져와서 member 객체를 저장한다
        model.addAttribute("member", member);
        return "save-result";
    }

    // 회원 목록 조회
    @GetMapping
    public String members(Model model){
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";
    }
}
