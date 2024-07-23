package com.surabhi.service;


import com.surabhi.model.MenuItem;
import java.util.List;

public interface MenuItemService {
    
    MenuItem createMenuItem(MenuItem menuItem);
    
    MenuItem getMenuItemById(Long menuItemId);
    
    List<MenuItem> getAllMenuItems();
    
    MenuItem updateMenuItem(Long menuItemId, MenuItem menuItem);
    
    void deleteMenuItem(Long menuItemId);
}

