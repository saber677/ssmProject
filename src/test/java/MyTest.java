import com.fasterxml.jackson.databind.ext.CoreXMLDeserializers;
import com.qiqi.pojo.Books;
import com.qiqi.service.BookServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MyTest {

    @Test
    public void t1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        BookServiceImpl bookServiceImpl = (BookServiceImpl) context.getBean("bookServiceImpl");

        for (Books bo : bookServiceImpl.queryAllBooks()) {
            System.out.println(bo);
        }
    }
}
