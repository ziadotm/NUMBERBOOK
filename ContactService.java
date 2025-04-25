package com.example.apisbackend.service;

import com.example.apisbackend.beans.Contact;
import com.example.apisbackend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact updateContact(Long id, Contact newContact) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contact.setName(newContact.getName());
                    contact.setNumber(newContact.getNumber());
                    return contactRepository.save(contact);
                })
                .orElseThrow(() -> new RuntimeException("Contact non trouvé"));
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
}
