/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.handytrack.HandyCell;

/**
 *
 * @author FookNaRak
 */
public interface TableActionEvent {
    public void onSetting(int row);
    
    public void onDelete(int row);
    
    public void onPrint(int row);
}
