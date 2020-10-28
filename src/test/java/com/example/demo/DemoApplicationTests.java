package com.example.demo;

import com.example.demo.controller.HelloController;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class DemoApplicationTests {

    Logger logger= LoggerFactory.getLogger(DemoApplicationTests.class);
    String  str="{\"flag\":\"1\",\"message\":\"入库成功\"}";

    @Test
    void contextLoads() {
        String  str2="{\"code\":\"S\",\"msg\":\"预占成功\",\"success\":\"true\",\"data\":\"{\\\"flag\\\":\\\"Y\\\",\\\"orderId\\\":\\\"ASOTLN421027001\\\",\\\"detail\\\":[{\\\"cId\\\":\\\"ln102601\\\",\\\"containerNo\\\":\\\"102601\\\",\\\"dataSource\\\":\\\"26\\\",\\\"erpInv\\\":\\\"a\\\",\\\"erpOrg\\\":\\\"26\\\",\\\"ownerId\\\":\\\"1\\\",\\\"qty\\\":\\\"80\\\",\\\"skuId\\\":\\\"20201026\\\",\\\"status\\\":\\\"0\\\",\\\"uId\\\":\\\"ln102601\\\",\\\"whID\\\":\\\"jd\\\"}]}\",\"traceId\":\"outbound-7f9668c526f143db852d9faa4d6039d7\",\"method\":\"setEaOrderPick\",\"businessId\":\"\",\"userName\":\"ARSR_JD\"}";

        String  str="{\\\"code\\\":\\\"S\\\",\\\"msg\\\":\\\"获取资料！\\\",\\\"success\\\":\\\"true\\\",\\\"data\\\":\\\"[{\\\"attribute01\\\":\\\"00EC004116\\\",\\\"attribute02\\\":\\\"C0171114000240\\\",\\\"attribute03\\\":\\\"\\\",\\\"attribute04\\\":\\\"\\\",\\\"attribute05\\\":\\\"\\\",\\\"attribute06\\\":\\\"\\\",\\\"attribute07\\\":\\\"\\\",\\\"attribute08\\\":\\\"\\\",\\\"attribute09\\\":\\\"\\\",\\\"attribute10\\\":\\\"\\\",\\\"binCode\\\":\\\"\\\",\\\"brand\\\":\\\"MICROCHIP\\\",\\\"cId\\\":\\\"2CC0171114000240\\\",\\\"containerNo\\\":\\\"${containerNo}\\\",\\\"containerType\\\":\\\"C\\\",\\\"coo\\\":\\\"CN\\\",\\\"csCapacity\\\":0,\\\"currency\\\":\\\"\\\",\\\"customerId\\\":\\\"\\\",\\\"customerLot\\\":\\\"\\\",\\\"dataSource\\\":\\\"ERP\\\",\\\"dateCode\\\":\\\"1445\\\",\\\"erpInv\\\":\\\"NORMAL-HMA\\\",\\\"erpOrg\\\":\\\"ID1\\\",\\\"expiryDate\\\":\\\"1900-01-01 00:00:00\\\",\\\"firstOwnerID\\\":\\\"\\\",\\\"firstReceiptDate\\\":\\\"2019-01-10 20:24:52\\\",\\\"hsFlag\\\":\\\"\\\",\\\"hue\\\":\\\"\\\",\\\"iipCapacity\\\":0,\\\"ipCapacity\\\":0,\\\"lockNo\\\":\\\"\\\",\\\"ownerId\\\":\\\"5000\\\",\\\"pledgeId\\\":\\\"\\\",\\\"product_line_id\\\":\\\"0076\\\",\\\"qty\\\":15,\\\"receiptDate\\\":\\\"2019-01-10 20:25:00\\\",\\\"receiptId\\\":\\\"EC转仓\\\",\\\"receiptType\\\":\\\"\\\",\\\"revision\\\":\\\"\\\",\\\"skuId\\\":\\\"ATMXT336T-MAU\\\",\\\"status\\\":\\\"OK\\\",\\\"uId\\\":\\\"${uuid}\\\",\\\"unitPrice\\\":\\\"0\\\",\\\"vendorDateCode\\\":\\\"1445\\\",\\\"vendorId\\\":\\\"\\\",\\\"vendorLot\\\":\\\"#\\\",\\\"vendorPack\\\":\\\"\\\",\\\"vendorSerialNo\\\":\\\"\\\",\\\"whId\\\":\\\"WH2\\\"}]\\\",\\\"traceId\\\":\\\"outbound-7f9668c526f143db852d9faa4d6039d7\\\",\\\"method\\\":\\\"getEaReceiveTask\\\",\\\"businessId\\\":\\\"\\\",\\\"userName\\\":\\\"ARSR_JD\\\"}";
        String  result="";
        Pattern pattern = Pattern.compile("\\$\\{(.*?)}");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            result = matcher.group(1);
            System.out.println(result);
         }
    }
}
