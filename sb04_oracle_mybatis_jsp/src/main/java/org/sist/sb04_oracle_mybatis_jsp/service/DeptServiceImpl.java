package org.sist.sb04_oracle_mybatis_jsp.service;

import java.util.List;

import org.sist.sb04_oracle_mybatis_jsp.domain.DeptVO;
import org.sist.sb04_oracle_mybatis_jsp.persistence.DeptMapper;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import lombok.extern.java.Log;

@Service
@Log
public class DeptServiceImpl implements DeptService{

	@Resource
	private DeptMapper deptMapper;
	
	@Override
	public List<DeptVO> getDeptList() throws Exception {
		
		return this.deptMapper.getDeptList(); // 서비스에서 매퍼 호출 -> 디비 가져옴.
	}

}
