package com.zmj.wkt;

import com.zmj.wkt.common.exception.CommonException;
import com.zmj.wkt.entity.Bs_person;
import com.zmj.wkt.mapper.Bs_personMapper;
import com.zmj.wkt.utils.sysenum.ErrorCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WktApplicationTests {

	@Autowired
	Bs_personMapper bs_personMapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test(){
		Bs_person bs_person = bs_personMapper.findByWXOpenID("oc3305bW94d6luN3AMStRjjZQwQE");
		if (bs_person==null){
			throw new CommonException(ErrorCode.NULL_ERROR,"未找到该用户！");
		}
	}
}
