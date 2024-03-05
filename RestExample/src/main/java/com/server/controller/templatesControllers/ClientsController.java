package com.server.controller.templatesControllers;

import com.server.model.users.Client;
import com.server.service.users.clientsService.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;


@Controller
public class ClientsController {

    private final ClientServiceImpl clientService;

    @Autowired
    public ClientsController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/clients")
    public String allClients(Model model) {

        final List<Client> clients = clientService.readAll();

        model.addAttribute("clients", clients);
        model.addAttribute("client", Client.class);

        return "index";
    }

    @PostMapping("/clients")
    public String addClient(@Valid Client client, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("animals", clientService.readAll());
            return "index";
        }
        clientService.create(client);
        return "redirect:/clients";
    }
}


