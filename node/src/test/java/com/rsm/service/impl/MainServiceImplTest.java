package com.rsm.service.impl;

import java.util.HashSet;
import java.util.Set;
import com.rsm.dao.RawDataDAO;
import com.rsm.entity.RawData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@SpringBootTest
class MainServiceImplTest {

    @Autowired
    private RawDataDAO rawDataDAO;

    @Test
    public void testSaveRawData_savedObjectIsAvailableByHashCode() {
        Update update = new Update();
        Message msg = new Message();
        msg.setText("Test message text");
        update.setMessage(msg);

        RawData rawData = RawData.builder()
            .event(update)
            .build();
        Set<RawData> testData = new HashSet<>();

        testData.add(rawData);
        rawDataDAO.save(rawData);

        Assert.isTrue(testData.contains(rawData), "Entity not found in the set");
    }
}