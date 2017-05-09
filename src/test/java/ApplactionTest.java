import com.sbm.Applaction;
import com.sbm.model.Message;
import com.sbm.service.IMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * sbm
 * Created by yadong.zhang on PACKAGE_NAME
 * User：yadong.zhang
 * Date：2016/10/21
 * Time：16:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Applaction.class})
@ActiveProfiles(value = "dev")
public class ApplactionTest {


    @Autowired
    private IMessageService messageService;

    @Test
    public void testInsert() {
        Message message = new Message();
       // message.setId(1111);
        message.setInsertDate(new Date());
        message.setNickName("帅哥一枚2");
        message.setIp("192.168.5.101");
        //要使用mybatis，没有自动实现，这里的insert方法需要自己在mapper里实现
      int result = messageService.insert(message);
      System.out.println(result);
        System.out.println(message.getId());
        List<Message>list=messageService.list();
        for (Message m:list){
            System.out.println(m);
        }
    }
}
