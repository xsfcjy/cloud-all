package com.sfxie.extension.mybatis.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sfxie.exception.framework.implement.exception.DaoException;



/*
 * 用于操作cobar代理层的数据
 * 
 * @author xsf
 *
 * @param <T>
 * 		实体类型
 * @param <PK>
 * 		实体类型主键
 * <p>实体T的用例如下:
	   @TableName(value="MASTER")
		public class Master implements Condition{
			
			@ColumnName(field="NAME_")
			private String name; 
			
			@ColumnName(field="PARTITION_STRING1")
			private String partitionString1;
			
			private String PARTITION_STRING2;
			
			@ColumnName(field="ID_")
			@PartitionField
			@ConditionColumn
			private Long id;
			
			private Date createTime;
			
			
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getPartitionString1() {
				return partitionString1;
			}
			public void setPartitionString1(String partitionString1) {
				this.partitionString1 = partitionString1;
			}
			
			public String getPARTITION_STRING2() {
				return PARTITION_STRING2;
			}
			public void setPARTITION_STRING2(String pARTITION_STRING2) {
				PARTITION_STRING2 = pARTITION_STRING2;
			}
			public Date getCreateTime() {
				return createTime;
			}
			public void setCreateTime(Date createTime) {
				this.createTime = createTime;
			}
			
		}
	</p>
	测试用例如下:
		@Test
		public void insertEnitty() {
			Master master1 = new Master();
			master1.setId(101L);
			master1.setName("TEST");
			master1.setPartitionString1("pARTITION_STRING1");
			master1.setPARTITION_STRING2("pARTITION_STRING2");
			masterMapper.insertEntity(master1);
		}
		@Test
		public void updateEnitty() {
			Master master1 = new Master();
			master1.setId(1L);
			master1.setName("TEST_update");
			master1.setPartitionString1("pARTITION_STRING1_update");
			master1.setPARTITION_STRING2("pARTITION_STRING2_update");
			masterMapper.updateEntity(master1);
		}
		@Test
		public void deleteEnitty() {
			Master master1 = new Master();
			master1.setId(101L);
			master1.setName("TEST_update");
			master1.setPartitionString1("pARTITION_STRING1_update");
			master1.setPARTITION_STRING2("pARTITION_STRING2_update");
			masterMapper.deleteEntity(master1);
			
		}
 */
/**
 * @author xsf
 *
 * @param <T>
 * 		实体类型
 * @param <PK>
 * 		实体类型主键  
 * @todo 用于操作cobar代理层的数据
 *
 */
public interface IMybatisDao<T,PK> {
  
//  @Select(Constants.NAMESPACE_FindEntityById)
//  public T findEntityById(PK id);
  
//  @Select("cniemp.mybatis.autosql.find.entitys")
//  public List<T> findEntity(Object... obj);
  
//  @Select("cniemp.mybatis.autosql.find.ListByLike")
//  public List<T> findLikeEntity(Object... obj);
  /**
   * 基于cobar代理层的添加记录操作
   * @param entity
   */
  @Insert("cniemp.mybatis.autosql.update.insert.entity")
  public void insertEntity(T entity) throws DaoException;
  /**
   * 基于cobar代理层的修改记录操作
   * @param entity
   */
  @Update("cniemp.mybatis.autosql.update.update.entity")
  public void updateEntity(T entity) throws DaoException;
  /**
   * 基于cobar代理层的删除记录操作
   * @param entity
   */
  @Delete("cniemp.mybatis.autosql.update.delete.entity")
  public void deleteEntity(T entity) throws DaoException;
  /**
   * 基于cobar代理层的删除记录操作
   * @param entity
   */
  @Select("cniemp.mybatis.autosql.query.entity")
  public void select(String sql) throws DaoException;
  
  /**
   * 获取自增主键 SELECT LAST_INSERT_ID()
   * @TODO	
   * @return	
   *
   */
  @Select("cniemp.mybatis.autosql.selectKeyId")
  public int selectKeyId();
//  @Delete("cniemp.mybatis.autosql.delete.condition")
//  public void deleteByCondition(Object param);
  
  
//  @Select("cniemp.mybatis.autosql.find.entity.queryByVo")
//  public PageMyBatis<T> queryByVo(int i,int c,Object... obj);
//  
//  @Select("cniemp.mybatis.autosql.find.entity.queryByVoLike")
//  public PageMyBatis<T> LikequeryByVo(int i,int c,Object... obj);
  
  
}