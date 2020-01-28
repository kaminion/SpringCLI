package cli.controller;

import cli.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class MemberController {
    private MemberService memberService;

    public void insert(String username, String password)
    {
        try {
            memberService.insert(username, password);
        }catch(Exception e)
        {
            log.error(e.getMessage());
        }
    }

    public void print()
    {
        try {
            memberService.print();
        }catch(Exception e)
        {
            log.error(e.getMessage());
        }
    }


}
