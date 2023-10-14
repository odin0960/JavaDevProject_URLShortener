//package goit.devProjectTeam2.user;
//
//import goit.devProjectTeam2.entity.User;
//import goit.devProjectTeam2.entity.dto.UserDTO;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//@RequiredArgsConstructor
//public class UserController {
//    private UserServiceInterface userServiceInterface;
//
//    private UserDTO userDTO;
//    private User user;
//
//    @GetMapping("/index")
//    public String home(){
//        return "index";
//    }
//
//    @GetMapping("/register")
//    public ModelAndView showRegistrationForm() {
//        ModelAndView result = new ModelAndView("register");
//        result.addObject("user", new User());
//        return result;
//
//    }
//
//    @PostMapping("/register/save")
//    public ModelAndView addUser(@Valid @ModelAttribute("user") User user,
//           BindingResult result) {
////        User existingUser = userServiceInterface.findUserByEmail(userDTO.getEmail());
////        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
////            result.rejectValue("email", null,
////                    "There is already an account registered with the same email");
////        }
////        if (result.hasErrors()) {
////            new ModelAndView().addObject("user", userDTO);
////            return new ModelAndView("redirect:/register");
////
////        }
//        userServiceInterface.saveUser(user);
//        return new ModelAndView("redirect:/login");
//
//    }
//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }
//}
//
//
