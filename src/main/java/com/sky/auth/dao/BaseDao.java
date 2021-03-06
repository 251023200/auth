package com.sky.auth.dao;

import java.util.List;
import java.util.Map;


/**
 * 数据访问层基础支撑接口.
 * author：yangfan
 */
public interface BaseDao<T> {

    /**
     * 函数功能说明 ：单条插入数据. 
     * 
     * @参数：@param entity
     * @参数：@return
     * @return：int
     * @throws
     */
    int insert(T entity);

    /**
     * 函数功能说明 ：批量插入数据.
     * 
     * @参数：@param list
     * @参数：@return
     * @return：int
     * @throws
     */
    int insert(List<T> list);

    /**
     * 函数功能说明 ：根据id单条更新数据
     * 
     * @参数：@param entity
     * @参数：@return
     * @return：int
     * @throws
     */
    int update(T entity);

    /**
     * 函数功能说明 ：根据id批量更新数据
     * 
     * @参数：@param list
     * @参数：@return
     * @return：int
     * @throws
     */
    int update(List<T> list);

    /**
     * 函数功能说明 ：根据column批量更新数据
     * 
     * @参数：@param paramMap
     * @参数：@return
     * @return：int
     * @throws
     */
    int update(Map<String, Object> paramMap);

    /**
     * 函数功能说明 ：根据id查询数据
     * 
     * @参数：@param id
     * @参数：@return
     * @return：T
     * @throws
     */
    T getById(String id);
    
    /**
     * 函数功能说明 ：根据id查询数据
     * 
     * @参数：@param id
     * @参数：@return
     * @return：T
     * @throws
     */
    T getByIdForUpdate(String id);

    /**
     * 函数功能说明 ：根据column查询数据
     * 
     * @参数：@param paramMap
     * @参数：@return
     * @return：T
     * @throws
     */
    public T getByColumn(Map<String, Object> paramMap);

    /**
     * 根据条件查询 listBy: <br/>
     * 
     * @param paramMap
     * @return 返回实体
     */
    //public T getBy(Map<String, Object> paramMap);
    
    /**
     * 根据条件查询列表数据
     */
    //public List<T> listBy(Map<String, Object> paramMap);

    /**
     * 函数功能说明 ：根据column查询列表数据
     * 
     * @参数：@param paramMap
     * @参数：@return
     * @return：List<T>
     * @throws
     */
    public List<T> listByColumn(Map<String, Object> paramMap);

    /**
     * 函数功能说明 ：根据column查询记录数
     * 
     * @参数：@param paramMap
     * @参数：@return
     * @return：Long
     * @throws
     */
    Long countByColumn(Map<String, Object> paramMap);

    /**
     * 函数功能说明 ：根据id删除数据
     * 
     * @参数：@param id
     * @参数：@return
     * @return：int
     * @throws
     */
    int deleteById(String id);
    
    /**
     * 函数功能说明 ：根据id批量删除数据
     * 
     * @参数：@param ids
     * @参数：@return
     * @return：int
     * @throws
     */
    int deleteByIds(String[] ids);
    
    /**
     * 删除用户角色
     * @param userId
     * @return
     */
    int deleteByUserId(String userId);

    /**
     * 函数功能说明 ：根据id批量删除数据
     * 
     * @参数：@param list
     * @参数：@return
     * @return：int
     * @throws
     */
    int delete(List<T> list);

    /**
     * 函数功能说明 ：根据column批量删除数据.
     * 
     * @参数：@param paramMap
     * @参数：@return
     * @return：int
     * @throws
     */
    int delete(Map<String, Object> paramMap);
    
    /**
     * 函数功能说明 ：查询全部数据
     * @return
     */
    public List<T> listAll();

    /**
     * 函数功能说明 ：分页查询数据
     * 
     * @参数：@param pageParam, 分页参数
     * @参数：@param paramMap， column查询条件
     * @参数：@return
     * @return：PageBean
     * @throws
     */
 //   public List<T> listPage(@Param("page") PageParam pageParam, @Param("param") Map<String, Object> paramMap);
    
    /**
     * 函数功能说明 ：分页查询数据
     * 
     * @参数：@param pageNum, 分页参数
     * @param pageSize 分页大小
     * @参数：@param paramMap， column查询条件
     * @参数：@return
     * @return：PageBean
     * @throws
     */
    public List<T> listPage(int pageNum,int pageSize, Map<String, Object> paramMap);
    
    public long listPageCount(Map<String,Object> paramMap);
    
    public List<T> search(T t);	//搜索
    
    
}
