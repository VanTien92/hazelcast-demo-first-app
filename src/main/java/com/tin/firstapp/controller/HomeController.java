package com.tin.firstapp.controller;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VAN_TIEN on 08/04/2017.
 */
@Controller
public class HomeController {
    public static final String MESSAGES = "messages";

    @Autowired
    HazelcastInstance hazelcastInstance;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping( value = "/", method = RequestMethod.GET )
    public ModelAndView index(HttpSession session, @AuthenticationPrincipal User user )
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName( "welcome" );
        mv.addObject( "user", user.getUsername() );
        return mv;
    }

    @RequestMapping( value = "/abc", method = RequestMethod.GET )
    public String postMessage( HttpSession session, @RequestParam( "message" ) String message )
    {
        final List< String > messages = (List< String >)session.getAttribute(MESSAGES);
        messages.add( message );

        return "redirect:/";
    }
}
