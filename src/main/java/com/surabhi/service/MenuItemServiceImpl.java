package com.surabhi.service;


import com.surabhi.model.MenuItem;
import com.surabhi.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    
    @Autowired
    private MenuItemRepository menuItemRepository;
    
    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }
    
    @Override
    public MenuItem getMenuItemById(Long menuItemId) {
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(menuItemId);
        return optionalMenuItem.orElse(null);
    }
    
    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }
    
    @Override
    public MenuItem updateMenuItem(Long menuItemId, MenuItem updatedMenuItem) {
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(menuItemId);
        if (optionalMenuItem.isPresent()) {
            MenuItem existingMenuItem = optionalMenuItem.get();
            existingMenuItem.setName(updatedMenuItem.getName());
            existingMenuItem.setDescription(updatedMenuItem.getDescription());
            existingMenuItem.setPrice(updatedMenuItem.getPrice());
            return menuItemRepository.save(existingMenuItem);
        }
        return null;
    }
    
    @Override
    public void deleteMenuItem(Long menuItemId) {
        menuItemRepository.deleteById(menuItemId);
    }
}
