package crud.dao;

import java.util.ArrayList;
import java.util.List;

import crud.dto.Member;

public class MemberDao {
	public List<Member> members; 

	public MemberDao() {
		members = new ArrayList<>();
	}
}