package ssm.config.write.dao;

import org.apache.ibatis.annotations.Param;

public interface WriteDao {
	void addUser(String name);

	void updateUser(@Param("id") String id, @Param("name") String name);
}
