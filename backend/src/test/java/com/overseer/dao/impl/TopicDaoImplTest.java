package com.overseer.dao.impl;

import com.overseer.dao.TopicDao;
import com.overseer.model.Topic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class TopicDaoImplTest {

    @Autowired
    private TopicDao topicDao;

    private Topic testTopic;

    private Long testTopicId;

    @Before
    public void setUp() throws Exception {
        testTopic = new Topic("Some topic");
        testTopic = topicDao.save(testTopic);
        testTopicId = testTopic.getId();
    }

    @Test
    public void findUserTopics() throws Exception {
        final Long userId = 16L;
        final List<Topic> userTopics = topicDao.findUserTopics(userId);
        final int expected = 12;
        final int actual = userTopics.size();
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void save() throws Exception {
        final Topic actual = topicDao.findOne(testTopicId);
        Assert.assertEquals(testTopic, actual);
    }

    @Test
    public void deleteById() throws Exception {
        topicDao.delete(testTopicId);
        final Topic actual = topicDao.findOne(testTopicId);
        Assert.assertNull(actual);
    }

    @Test
    public void deleteByValue() throws Exception {
        topicDao.delete(testTopic);
        final Topic actual = topicDao.findOne(testTopicId);
        Assert.assertNull(actual);
    }

    @Test
    public void exists() throws Exception {
        final boolean actual = topicDao.exists(testTopicId);
        Assert.assertTrue(actual);
    }

    @Test
    public void count() throws Exception {
        final Long expected = 23L;
        final Long actual = topicDao.count();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findByName() throws Exception {
        final Topic actual = topicDao.findByName(testTopic.getTitle());
        Assert.assertEquals(testTopic, actual);
    }
}