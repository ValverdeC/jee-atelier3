package com.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VueController {

    @RequestMapping("/")
    public String showHomePage() {
        return "cardHome.html";
    }

    @RequestMapping("/signin")
    public String showLoginPage() {
        return "login.html";
    }
    
    @RequestMapping("/signup")
    public String showSignupPage() {
        return "subscribe.html";
    }

    @RequestMapping("/search")
    public String showSearchPage() {
        return "searchCard.html";
    }

    @RequestMapping("/market")
    public String showListPage() {
        return "cardList.html";
    }


    @RequestMapping("/rooms")
    public String showRoomPage() {
        return "roomList.html";
    }

    @RequestMapping("/create")
    public String showGameCreatePage() {
        return "createRoom.html";
    }
    
    @RequestMapping("/addCard")
    public String showCardCreatePage() {
        return "createCard.html";
    }

    @RequestMapping("/game/selectCard")
    public String showGameSelectCardPage() {
        return "selectCardForPlay.html";
    }

    @RequestMapping("/game/wait")
    public String showGameWaitPage() {
        return "waitPlayer.html";
    }

    @RequestMapping("/game/win")
    public String showGameWinPage() {
        return "winGame.html";
    }

    @RequestMapping("/game/play")
    public String showGamePage() {
        return "playRoom.html";
    }

}
