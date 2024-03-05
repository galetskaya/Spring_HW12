package com.server.controller.templatesControllers;

import com.server.model.users.Client;
import com.server.service.users.clientsService.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ClientDitailsController {

    private final ClientServiceImpl clientService;

    @Autowired
    public ClientDitailsController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/clients/{id}")
    public String getClient(Model model, @PathVariable(name = "id") int id) {

        final Client client = clientService.read(id);

        model.addAttribute("client", client);

        return "clientInfo";
    }


    @PostMapping ("/clients/{id}")
    public String updateClient
            (
            @Valid Client client,
            Model model,
            @PathVariable(name = "id") int id
            )
    {
        model.addAttribute("client", clientService.update(client, id));
        return "redirect:/clients/{id}";
    }

    @DeleteMapping("/clients/{id}")
    public String deleteClient(@Valid Client client, Model model) {

        model.addAttribute(clientService.delete(client.getId()));
        return "redirect:/clients";
    }
}
