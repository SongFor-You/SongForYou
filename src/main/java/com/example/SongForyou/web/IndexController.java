package com.example.SongForyou.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/playlists")
    public String index(Model model){
        return "playlist";
    }
}


