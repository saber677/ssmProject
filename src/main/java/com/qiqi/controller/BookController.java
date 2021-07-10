package com.qiqi.controller;

import com.qiqi.pojo.Books;
import com.qiqi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

    @RequestMapping("/allBook")
    public String list(Model model) {

        List<Books> list = bookService.queryAllBooks();

        model.addAttribute("list", list);

        return "allBook";
    }

    //跳转到添加书籍页面
    @RequestMapping("/toAddPage")
    public String toAddPage() {
        return "addBook";
    }

    //添加书籍页面的请求
    @RequestMapping("/addBook")
    public String addBook(Books books) {

        System.out.println(books);

        bookService.addBook(books);

        return "redirect:/book/allBook";
    }

    //跳转到修改书籍页面
    @RequestMapping("/toUpdatePage")
    public String toUpdatePage(int id, Model model) {

        Books books = bookService.queryBookById(id);

        model.addAttribute("books", books);

        return "toUpdatePage";
    }

    //修改书籍的请求
    @RequestMapping("/updateBook")
    public String updateBook(Books books) {

        System.out.println(books);

        bookService.updateBook(books);

        return "redirect:/book/allBook";
    }

    //删除数据
    @RequestMapping("/deleteBook/{bookID}")
    public String deleteBook(@PathVariable("bookID") int id) {

        bookService.deleteBookById(id);

        return "redirect:/book/allBook";
    }

    //高级查询
    @RequestMapping("/queryBook")
    public String queryBook(String queryBookName, Model model) {

        Books book = bookService.queryBookByName(queryBookName);

        List<Books> list = new ArrayList();

        if (book == null) {

            model.addAttribute("msg", "未查询到");

            list = bookService.queryAllBooks();

            model.addAttribute("list",list);

            return "allBook";
        }

        list.add(book);

        model.addAttribute("list", list);

        return "allBook";
    }
}
