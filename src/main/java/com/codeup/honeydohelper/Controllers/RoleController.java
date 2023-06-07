package com.codeup.honeydohelper.Controllers;
import com.codeup.honeydohelper.Repositories.RoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleController {
    private final RoleRepository roleDao;
    public RoleController(RoleRepository roleDao) {
        this.roleDao = roleDao;
    }
}
