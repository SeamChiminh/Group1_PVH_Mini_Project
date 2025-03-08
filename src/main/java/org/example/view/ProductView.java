package org.example.view;

import org.example.config.color.Color;
import org.example.controller.ProductController;
import org.example.model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductView {
    public static void displayProduct()
    {

        try{
            String option = null;
            Scanner sc = new Scanner(System.in);
            ProductController productController = new ProductController();
            ArrayList<Product> listUnsaved = new ArrayList<>();
            do {
                productController.getAllProducts().stream().forEach(allProduct -> {
                    System.out.println(allProduct.getId()+" "+allProduct.getName()+" "+allProduct.getQuantity()+" "+allProduct.getUnitPrice()+" "+allProduct.getImportedDate());
                });
                System.out.println("(W).Write \t (R).Read(id) \t (U).Update \t (D).Delete \t (S).Search(name) \t (Se).Set row");
                System.out.println("(Sa).Save \t (Us).Unsaved \t (Ba).Backup \t (Re).Restore \t (E)Exit");
                System.out.println("Choose an option:");
                option = sc.nextLine().trim().toLowerCase();
                switch (option){
                    case "w":{
                        productController.writeProduct(listUnsaved);
                        break;
                    }
                    case "us":{
                        productController.unSaveProduct(listUnsaved);
                    }
                    case "0":{
                        listUnsaved.forEach(data -> {
                            System.out.println(data.getName()+"\t"+data.getUnitPrice()+"\t"+data.getQuantity());
                        });
                        break;
                    }
                    case "sa":{
                        productController.saveProduct(listUnsaved);
                        break;
                    }
                    case "r":{
                        productController.seachProductbyID();
                    }
                    case "d":{
                        productController.deleteProductById();
                    }
                }
            }while (!option.equalsIgnoreCase("e"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    void table() {
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);
        Table t = new Table(5, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);

        t.setColumnWidth(0, 10, 10);
        t.setColumnWidth(1, 30, 30);
        t.setColumnWidth(2, 30, 30);
        t.setColumnWidth(3, 20, 20);
        t.setColumnWidth(4, 30, 30);

        t.addCell(Color.YELLOW + "ID" + Color.RESET, cellStyle);
        t.addCell(Color.YELLOW + "NAME" + Color.RESET, cellStyle);
        t.addCell(Color.YELLOW + "UNIT PRICE" + Color.RESET, cellStyle);
        t.addCell(Color.YELLOW + "QTY" + Color.RESET, cellStyle);
        t.addCell(Color.YELLOW + "IMPORT DATE" + Color.RESET, cellStyle);
    }
}
